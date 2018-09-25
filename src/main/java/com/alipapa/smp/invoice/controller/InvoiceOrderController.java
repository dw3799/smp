package com.alipapa.smp.invoice.controller;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alipapa.smp.common.Constant;
import com.alipapa.smp.common.enums.*;
import com.alipapa.smp.common.request.UserInfo;
import com.alipapa.smp.common.request.UserStatus;
import com.alipapa.smp.consumer.pojo.Consumer;
import com.alipapa.smp.consumer.pojo.ConsumerFollowRecord;
import com.alipapa.smp.invoice.pojo.InvoiceOrder;
import com.alipapa.smp.invoice.service.InvoiceOrderService;
import com.alipapa.smp.order.controller.SubOrderController;
import com.alipapa.smp.order.pojo.AgentOrderDetail;
import com.alipapa.smp.order.pojo.Order;
import com.alipapa.smp.order.pojo.SelfOrderDetail;
import com.alipapa.smp.order.pojo.SubOrder;
import com.alipapa.smp.order.service.OrderService;
import com.alipapa.smp.order.service.SubOrderService;
import com.alipapa.smp.order.service.impl.SubOrderServiceProxy;
import com.alipapa.smp.order.vo.SubOrderVo;
import com.alipapa.smp.product.pojo.Product;
import com.alipapa.smp.product.pojo.ProductCategory;
import com.alipapa.smp.product.pojo.ProductPicture;
import com.alipapa.smp.user.pojo.User;
import com.alipapa.smp.utils.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.alipapa.smp.utils.WebApiResponse.error;

/**
 * 发货单接口
 *
 * @author liuwei
 * @version 2.0
 * 2018-09-25
 */
@RestController
@CrossOrigin
@RequestMapping("/api/invoice")
public class InvoiceOrderController {
    private static Logger logger = LoggerFactory.getLogger(InvoiceOrderController.class);


    @Autowired
    private SubOrderServiceProxy subOrderServiceProxy;


    @Autowired
    private SubOrderService subOrderService;


    @Autowired
    private OrderService orderService;


    @Autowired
    private InvoiceOrderService invoiceOrderService;


    /**
     * 待提交发货单的采购单列表
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/listUnSubmitInvoiceSubOrder", method = RequestMethod.GET)
    public WebApiResponse<List<SubOrderVo>> listUnSubmitInvoiceSubOrder(@RequestParam(name = "pageSize", required = false) Integer pageSize,
                                                                        @RequestParam(name = "pageNum", required = false) Integer pageNum) {
        UserInfo userInfo = UserStatus.getUserInfo();

        try {
            if (pageSize == null) {
                pageSize = 30;
            }

            if (pageNum == null) {
                pageNum = 1;
            }

            Integer start = (pageNum - 1) * pageSize;
            Integer size = pageSize;

            List<JSONObject> orderVoList = subOrderServiceProxy.listSubOrderBySaler(SubOrderStatusEnum.INVOICE_APPLY, userInfo.getUserNo(), start, size);
            if (!CollectionUtils.isEmpty(orderVoList)) {
                WebApiResponse response = WebApiResponse.success(orderVoList);
                response.setTotalCount(orderVoList.get(0).getLong("totalCount"));
                return response;
            }
            return WebApiResponse.success(null);
        } catch (Exception ex) {
            logger.error("待提交发货单的采购单列表异常", ex);
            return error("待提交发货单的采购单列表异常");
        }
    }


    /**
     * 保存或提交发货单
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/saveInvoiceOrder", method = RequestMethod.POST)
    public WebApiResponse<String> saveInvoiceOrder(HttpServletRequest request) {
        UserInfo userInfo = UserStatus.getUserInfo();

        try {
            //不能为空
            String subOrderNos = request.getParameter("subOrderNos");
            String opType = request.getParameter("opType");

            String consignee = request.getParameter("consignee");
            String mobile = request.getParameter("mobile");
            String postalCode = request.getParameter("postalCode");
            String address = request.getParameter("address");
            String deliverTime = request.getParameter("deliverTime");
            String deliverType = request.getParameter("deliverType");
            String remark = request.getParameter("remark");

             /*
             subOrderNos："M210808132222;M210808132223;M210808132224", //必传，采购订单编号，多个采购单用分号隔开，须属于同一个订单
                    opType: "SAVE" // SAVE 暂存， SUBMIT 提交
            consignee："迈巴赫11", //收货人
                    mobile："13817959271", //收货人联系方式
                    postalCode："233122" //邮政编码
            address："文一路80号新华墅园" //收货地址
            deliverType："海运" //运输方式
            deliverTime："2018-09-10 12:00:00" //发货时间
            */

            if (StringUtil.isEmptyString(opType) || StringUtil.isEmptyString(subOrderNos) || StringUtil.isEmptyString(consignee) || StringUtil.isEmptyString(mobile) || StringUtil.isEmptyString(address)
                    || StringUtil.isEmptyString(deliverTime) || StringUtil.isEmptyString(deliverType)) {
                return error("缺少必填参数");
            }

            OrderOPerateTypeEnum orderOPerateTypeEnum = OrderOPerateTypeEnum.valueOf(opType);
            if (orderOPerateTypeEnum == null) {
                return error("操作类型有误");
            }


            String[] subOrderArray = subOrderNos.split(";");

            List<SubOrder> subOrderList = new ArrayList<>();

            String orderNo = null;
            for (String subOrderNo : subOrderArray) {
                SubOrder subOrder = subOrderService.getSubOrderBySubOrderNo(subOrderNo);
                if (subOrder == null) {
                    return error("参数有误");
                }

                if (StringUtil.isEmptyString(orderNo)) {
                    orderNo = subOrder.getOrderNo();
                }

                if (subOrder.getSubOrderStatus() == SubOrderStatusEnum.INVOICE_APPLY.getCode()) {
                    return error("当前采购单状态无法提交发货单");
                }

                if (!subOrder.getOrderNo().equals(orderNo)) {
                    return error("采购单不属于同一订单");
                }
                subOrderList.add(subOrder);
            }

            Order order = orderService.selectOrderByOrderNo(orderNo);

            if (order == null) {
                return error("订单不存在");
            }

            InvoiceOrder invoiceOrder = new InvoiceOrder();

            String invoiceOrderNo = "IV" + OrderNumberGenerator.get();


            invoiceOrder.setAddress(address);
            invoiceOrder.setActaulDeliverTime(null);
            invoiceOrder.setOrderNo(orderNo);
            invoiceOrder.setBuyerUserName(order.getBuyerUserName());
            invoiceOrder.setBuyerUserNo(order.getBuyerUserNo());
            invoiceOrder.setConsignee(consignee);
            invoiceOrder.setConsumerCountry(order.getConsumerCountry());
            invoiceOrder.setConsumerNo(order.getConsumerNo());
            invoiceOrder.setConsumerName(order.getConsumerName());
            invoiceOrder.setCreatedTime(new Date());
            invoiceOrder.setDeliverTime(DateUtil.parseObjToDate(deliverTime));
            invoiceOrder.setDeliverType(DeliverTypeEnum.valueOf(deliverType).getCode());
            invoiceOrder.setInvoiceNo(invoiceOrderNo);
            invoiceOrder.setInvoiceStatus(InvoiceOrderStatusEnum.UN_SUBMIT.getCode());
            invoiceOrder.setMobile(mobile);
            invoiceOrder.setPostalCode(postalCode);
            invoiceOrder.setRemark(remark);
            invoiceOrder.setSalerUserName(order.getSalerUserName());
            invoiceOrder.setSalerUserNo(order.getSalerUserNo());
            invoiceOrder.setUpdatedTime(new Date());
            invoiceOrderService.saveInvoiceOrder(invoiceOrder, subOrderList, userInfo);
        } catch (Exception ex) {
            logger.error("", ex);
            return error("保存或提交发货单");
        }
        return WebApiResponse.error("保存或提交发货单");
    }


}
