package com.alipapa.smp.invoice.controller;


import com.alibaba.fastjson.JSONObject;
import com.alipapa.smp.common.enums.*;
import com.alipapa.smp.common.request.UserInfo;
import com.alipapa.smp.common.request.UserStatus;
import com.alipapa.smp.consumer.vo.SysDictVo;
import com.alipapa.smp.invoice.pojo.*;
import com.alipapa.smp.invoice.service.*;
import com.alipapa.smp.invoice.service.impl.InvoiceOrderServiceProxy;
import com.alipapa.smp.invoice.vo.BasicInvoiceOrderInfo;
import com.alipapa.smp.invoice.vo.InvoiceAdressVo;
import com.alipapa.smp.invoice.vo.InvoiceOrderVo;
import com.alipapa.smp.order.pojo.*;
import com.alipapa.smp.order.service.OrderService;
import com.alipapa.smp.order.service.OrderWorkFlowService;
import com.alipapa.smp.order.service.SubOrderService;
import com.alipapa.smp.order.service.impl.SubOrderServiceProxy;
import com.alipapa.smp.order.vo.OrderWorkFlowVo;
import com.alipapa.smp.order.vo.SubOrderVo;
import com.alipapa.smp.user.pojo.User;
import com.alipapa.smp.user.service.UserService;
import com.alipapa.smp.utils.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

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
    private InvoiceOrderServiceProxy invoiceOrderServiceProxy;


    @Autowired
    private SubOrderService subOrderService;


    @Autowired
    private OrderService orderService;


    @Autowired
    private UserService userService;


    @Autowired
    private InvoiceOrderService invoiceOrderService;


    @Autowired
    private InvoiceCostInfoService invoiceCostInfoService;


    @Autowired
    private InvoiceProductService invoiceProductService;


    @Autowired
    private OrderWorkFlowService orderWorkFlowService;

    @Autowired
    private InvoiceOrderExtService invoiceOrderExtService;


    @Autowired
    private InvoiceDeliverInfoService invoiceDeliverInfoService;


    /**
     * 发货单状态列表
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/invoiceStatusSelect", method = RequestMethod.GET)
    public WebApiResponse<List<SysDictVo>> followStatus() {
        List<SysDictVo> sysDictVoList = new ArrayList<>();
        for (InvoiceOrderStatusEnum invoiceOrderStatusEnum : InvoiceOrderStatusEnum.values()) {
            SysDictVo sysDictVo = new SysDictVo();
            sysDictVo.setId(Long.valueOf(invoiceOrderStatusEnum.getCode()));
            sysDictVo.setCategoryCode("发货单状态");
            sysDictVo.setDictText(invoiceOrderStatusEnum.getDec());
            sysDictVo.setDictValue(invoiceOrderStatusEnum.getCodeName());
            sysDictVoList.add(sysDictVo);
        }

        return WebApiResponse.success(sysDictVoList);
    }


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

                if (subOrder.getSubOrderStatus() != SubOrderStatusEnum.INVOICE_APPLY.getCode()) {
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
            invoiceOrder.setInvoiceStatus(InvoiceOrderStatusEnum.FIN_APV.getCode());
            invoiceOrder.setMobile(mobile);
            invoiceOrder.setPostalCode(postalCode);
            invoiceOrder.setRemark(remark);
            invoiceOrder.setSalerUserName(order.getSalerUserName());
            invoiceOrder.setSalerUserNo(order.getSalerUserNo());
            invoiceOrder.setUpdatedTime(new Date());
            invoiceOrderService.saveInvoiceOrder(order, invoiceOrder, subOrderList, userInfo);
            return WebApiResponse.success("success");
        } catch (Exception ex) {
            logger.error("提交发货单异常", ex);
            return error("提交发货单异常");
        }
    }


    /**
     * 我的采购单查询
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/listMyInvoiceOrder", method = RequestMethod.GET)
    public WebApiResponse<List<InvoiceOrderVo>> listMyInvoiceOrder(@RequestParam(name = "pageSize", required = false) Integer pageSize,
                                                                   @RequestParam(name = "pageNum", required = false) Integer pageNum,
                                                                   @RequestParam(name = "invoiceOrderNo", required = false) String invoiceOrderNo,
                                                                   @RequestParam(name = "orderNo", required = false) String orderNo,
                                                                   @RequestParam(name = "consumerName", required = false) String consumerName,
                                                                   @RequestParam(name = "salerUserId", required = false) Long salerUserId,
                                                                   @RequestParam(name = "invoiceOrderStatus", required = false) String invoiceOrderStatus,
                                                                   @RequestParam(name = "createdTimeStart", required = false) String createdTimeStart, //YYYY-MM-DD HH:MM SS
                                                                   @RequestParam(name = "createdTimeEnd", required = false) String createdTimeEnd) {
        UserInfo userInfo = UserStatus.getUserInfo();
        Map<String, Object> params = new HashMap<>();

        if (StringUtil.isNotEmptyString(invoiceOrderNo)) {
            params.put("invoiceOrderNo", invoiceOrderNo);
        }

        if (StringUtil.isNotEmptyString(orderNo)) {
            params.put("orderNo", orderNo);
        }

        if (StringUtil.isNotEmptyString(consumerName)) {
            params.put("consumerName", consumerName);
        }

        if (StringUtil.isNotEmptyString(invoiceOrderStatus)) {
            params.put("invoiceOrderStatus", InvoiceOrderStatusEnum.valueOf(invoiceOrderStatus).getCode());
        }

        //创建时间开始
        if (!StringUtil.isNotEmptyString(createdTimeStart)) {
            logger.info("createdTimeStart:" + createdTimeStart);
            params.put("createdTimeStart", createdTimeStart);
        }

        //创建时间结束
        if (!StringUtil.isNotEmptyString(createdTimeEnd)) {
            logger.info("createdTimeEnd:" + createdTimeEnd);
            params.put("createdTimeEnd", createdTimeEnd);
        }

        User user = userService.getUserByUserNo(userInfo.getUserNo());

        if (userInfo.getRoleName().equals(RoleEnum.saler.getCodeName())) {
            params.put("salerUserNo", user.getUserNo());
        } else if (userInfo.getRoleName().equals(RoleEnum.supervisor.getCodeName())) {
            params.put("groupId", user.getGroupId());
        } else if (userInfo.getRoleName().equals(RoleEnum.agentBuyer.getCodeName()) || userInfo.getRoleName().equals(RoleEnum.selfBuyer.getCodeName())) {
            params.put("buyerUserNo", user.getUserNo());
        } else if (userInfo.getRoleName().equals(RoleEnum.superBuyer.getCodeName())) {
            params.put("buyerGroupId", user.getGroupId());
        } else {
            if (salerUserId != null && salerUserId > 0) {
                User selectedUser = userService.getUserById(salerUserId);
                params.put("salerUserNo", selectedUser.getUserNo());
            }
        }

        if (pageSize == null) {
            pageSize = 30;
        }

        if (pageNum == null) {
            pageNum = 1;
        }

        Integer start = (pageNum - 1) * pageSize;
        Integer size = pageSize;

        List<InvoiceOrderVo> invoiceOrderVos = invoiceOrderServiceProxy.listMyInvoiceOrderByParams(params, start, size);
        if (!CollectionUtils.isEmpty(invoiceOrderVos)) {
            WebApiResponse response = WebApiResponse.success(invoiceOrderVos);
            response.setTotalCount(invoiceOrderVos.get(0).getTotalCount());
            return response;
        }

        return WebApiResponse.success(null);
    }


    /**
     * 发货订单基本信息
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/getInvoiceOrderBasicInfo", method = RequestMethod.GET)
    public WebApiResponse<BasicInvoiceOrderInfo> getBasicOrderInfo(@RequestParam("invoiceOrderNo") String invoiceOrderNo) {
        UserInfo userInfo = UserStatus.getUserInfo();

        if (StringUtil.isEmptyString(invoiceOrderNo)) {
            return WebApiResponse.error("参数不能为空");
        }

        InvoiceOrder invoiceOrder = invoiceOrderService.selectInvoiceOrderByInvoiceOrderNo(invoiceOrderNo);
        if (invoiceOrder == null) {
            return WebApiResponse.error("发货单不存在");
        }

        List<InvoiceProduct> invoiceProductList = invoiceProductService.listInvoiceProductByInvoiceOrderNo(invoiceOrderNo);

        if (CollectionUtils.isEmpty(invoiceProductList)) {
            return WebApiResponse.error("发货单产品不存在");
        }
        try {
            BasicInvoiceOrderInfo basicInvoiceOrderInfo = new BasicInvoiceOrderInfo();
            basicInvoiceOrderInfo.setCreatedTime(DateUtil.formatToStrTimeV1(invoiceOrder.getCreatedTime()));
            basicInvoiceOrderInfo.setId(invoiceOrder.getId());
            basicInvoiceOrderInfo.setInvoiceOrderNo(invoiceOrder.getInvoiceNo());
            basicInvoiceOrderInfo.setInvoiceStatus(InvoiceOrderStatusEnum.valueOf(invoiceOrder.getInvoiceStatus()).getDec());
            basicInvoiceOrderInfo.setOrderNo(invoiceOrder.getOrderNo());

            List<JSONObject> invoiceProducts = new ArrayList<>();
            for (InvoiceProduct invoiceProduct : invoiceProductList) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("id", invoiceProduct.getId());
                jsonObject.put("invoiceNo", invoiceProduct.getInvoiceNo());
                jsonObject.put("productCategoryId", invoiceProduct.getProductCategoryId());
                jsonObject.put("productCategory", invoiceProduct.getProductCategory());
                jsonObject.put("productId", invoiceProduct.getProductId());
                jsonObject.put("productName", invoiceProduct.getProductName());
                jsonObject.put("subOrderNo", invoiceProduct.getSubOrderNo());
                SubOrder subOrder = subOrderService.getSubOrderBySubOrderNo(invoiceProduct.getSubOrderNo());
                jsonObject.put("subOrderStatus", SubOrderStatusEnum.valueOf(subOrder.getSubOrderStatus()).getDec());
                invoiceProducts.add(jsonObject);
            }

            basicInvoiceOrderInfo.setInvoiceProducts(invoiceProducts);
            return WebApiResponse.success(basicInvoiceOrderInfo);
        } catch (Exception ex) {
            logger.error("获取发货订单基本信息异常", ex);
            return WebApiResponse.error("获取发货订单基本信息异常");
        }
    }


    /**
     * 获取发货单发货信息
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/getInvoiceAddressInfo", method = RequestMethod.GET)
    public WebApiResponse<InvoiceAdressVo> getInvoiceAddressInfo(@RequestParam("invoiceOrderNo") String invoiceOrderNo) {
        UserInfo userInfo = UserStatus.getUserInfo();

        if (StringUtil.isEmptyString(invoiceOrderNo)) {
            return WebApiResponse.error("参数不能为空");
        }

        InvoiceOrder invoiceOrder = invoiceOrderService.selectInvoiceOrderByInvoiceOrderNo(invoiceOrderNo);
        if (invoiceOrder == null) {
            return WebApiResponse.error("发货单不存在");
        }

        try {
            InvoiceAdressVo invoiceAdressVo = new InvoiceAdressVo();
            invoiceAdressVo.setAddress(invoiceOrder.getAddress());
            invoiceAdressVo.setConsignee(invoiceOrder.getConsignee());
            invoiceAdressVo.setDeliverTime(DateUtil.formatToStrTimeV1(invoiceOrder.getCreatedTime()));
            invoiceAdressVo.setDeliverType(DeliverTypeEnum.valueOf(invoiceOrder.getDeliverType()).getDec());
            invoiceAdressVo.setId(invoiceOrder.getId());
            invoiceAdressVo.setInvoiceOrderNo(invoiceOrder.getInvoiceNo());
            invoiceAdressVo.setMobile(invoiceOrder.getMobile());
            invoiceAdressVo.setOrderNo(invoiceOrder.getOrderNo());
            invoiceAdressVo.setPostalCode(invoiceOrder.getPostalCode());
            return WebApiResponse.success(invoiceAdressVo);
        } catch (Exception ex) {
            logger.error("获取发货单发货信息异常", ex);
            return WebApiResponse.error("获取获取发货单发货信息异常");
        }
    }


    /**
     * 获取发货单流转记录
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/listInvoiceOrderWorkFlow", method = RequestMethod.GET)
    public WebApiResponse<List<OrderWorkFlowVo>> listInvoiceOrderWorkFlow(@RequestParam("invoiceOrderNo") String invoiceOrderNo) {

        if (StringUtil.isEmptyString(invoiceOrderNo)) {
            return WebApiResponse.error("参数不能为空");
        }

        InvoiceOrder invoiceOrder = invoiceOrderService.selectInvoiceOrderByInvoiceOrderNo(invoiceOrderNo);
        if (invoiceOrder == null) {
            return WebApiResponse.error("发货单不存在");
        }

        List<OrderWorkFlowVo> orderWorkFlowVoList = new ArrayList<>();
        try {
            List<OrderWorkFlow> orderWorkFlowList = orderWorkFlowService.listOrderWorkFlowByParams(invoiceOrderNo, OrderWorkFlowTypeEnum.IV_ORDER.getCodeName());
            if (!CollectionUtils.isEmpty(orderWorkFlowList)) {
                for (OrderWorkFlow orderWorkFlow : orderWorkFlowList) {
                    OrderWorkFlowVo orderWorkFlowVo = new OrderWorkFlowVo();
                    orderWorkFlowVo.setCreatedTime(DateUtil.formatToStrTimeV1(orderWorkFlow.getCreatedTime()));
                    orderWorkFlowVo.setOpUserRole(orderWorkFlow.getOpUserRole());
                    orderWorkFlowVo.setOrderNo(invoiceOrderNo);
                    orderWorkFlowVo.setOpUserName(orderWorkFlow.getOpUserName());
                    orderWorkFlowVo.setRemark(orderWorkFlow.getRemark());
                    orderWorkFlowVo.setResult(orderWorkFlow.getResult());
                    orderWorkFlowVoList.add(orderWorkFlowVo);
                }
            }
        } catch (Exception ex) {
            logger.error("获取发货单流转记录异常", ex);
            return WebApiResponse.error("获取发货单流转记录异常");
        }

        return WebApiResponse.success(orderWorkFlowVoList);
    }


    /**
     * 待财务审核发货单列表
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/listFinApvInvoiceOrder", method = RequestMethod.GET)
    public WebApiResponse<List<InvoiceOrderVo>> listFinApvInvoiceOrder(@RequestParam(name = "pageSize", required = false) Integer pageSize,
                                                                       @RequestParam(name = "pageNum", required = false) Integer pageNum) {
        try {
            if (pageSize == null) {
                pageSize = 30;
            }

            if (pageNum == null) {
                pageNum = 1;
            }

            Integer start = (pageNum - 1) * pageSize;
            Integer size = pageSize;


            Map<String, Object> params = new HashMap<>();

            params.put("invoiceOrderStatus", InvoiceOrderStatusEnum.FIN_APV.getCode());


            List<InvoiceOrderVo> invoiceOrderVos = invoiceOrderServiceProxy.listMyInvoiceOrderByParams(params, start, size);
            if (!CollectionUtils.isEmpty(invoiceOrderVos)) {
                WebApiResponse response = WebApiResponse.success(invoiceOrderVos);
                response.setTotalCount(invoiceOrderVos.get(0).getTotalCount());
                return response;
            }

            return WebApiResponse.success(null);
        } catch (Exception ex) {
            logger.error("待财务审核发货单列表异常", ex);
            return error("待财务审核发货单列表异常");
        }
    }


    /**
     * 财务审核发货单
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/finApproveInvoiceOrder", method = RequestMethod.POST)
    public WebApiResponse<String> finApproveInvoiceOrder(@RequestParam(name = "invoiceOrderNo") String invoiceOrderNo,
                                                         @RequestParam(name = "result") String result,
                                                         @RequestParam(name = "remark", required = false) String remark) {
        UserInfo userInfo = UserStatus.getUserInfo();
        try {
            if (StringUtil.isEmptyString(invoiceOrderNo) || StringUtil.isEmptyString(result)) {
                return error("缺少必传参数");
            }

            InvoiceOrder invoiceOrder = invoiceOrderService.selectInvoiceOrderByInvoiceOrderNo(invoiceOrderNo);
            if (invoiceOrder == null) {
                return WebApiResponse.error("发货单不存在");
            }

            if (!RoleEnum.admin.getCodeName().equals(userInfo.getRoleName())) {
                if (!RoleEnum.financial.getCodeName().equals(userInfo.getRoleName())) {
                    return error("没有权限");
                }
            }

            if (invoiceOrder.getInvoiceStatus() != InvoiceOrderStatusEnum.FIN_APV.getCode()) {
                return error("当前状态财务无法审核发货单！");
            }

            User user = userService.getUserByUserNo(userInfo.getUserNo());

            if ("N".equals(result)) {
                //保存发货单流转记录
                OrderWorkFlow invoiceOrderWorkFlow = new OrderWorkFlow();
                invoiceOrderWorkFlow.setCreatedTime(new Date());
                invoiceOrderWorkFlow.setNewOrderStatus(InvoiceOrderStatusEnum.DISCARD.getCode());
                invoiceOrderWorkFlow.setOldOrderStatus(invoiceOrder.getInvoiceStatus());
                invoiceOrderWorkFlow.setOpUserName(userInfo.getUserName());
                invoiceOrderWorkFlow.setOpUserNo(userInfo.getUserNo());
                invoiceOrderWorkFlow.setOpUserRole(userInfo.getRoleName());
                invoiceOrderWorkFlow.setOrderNo(invoiceOrder.getInvoiceNo());
                invoiceOrderWorkFlow.setType(OrderWorkFlowTypeEnum.IV_ORDER.getCodeName());
                invoiceOrderWorkFlow.setRemark(remark);
                invoiceOrderWorkFlow.setResult("财务审核发货单不通过");
                invoiceOrderWorkFlow.setUpdatedTime(new Date());

                invoiceOrder.setInvoiceStatus(InvoiceOrderStatusEnum.DISCARD.getCode());
                invoiceOrder.setRemark(invoiceOrder.getRemark() + "财务审核发货单不通过");
                invoiceOrderService.updateInvoiceOrder(invoiceOrder);
                orderWorkFlowService.save(invoiceOrderWorkFlow);


                List<InvoiceProduct> invoiceProductList = invoiceProductService.listInvoiceProductByInvoiceOrderNo(invoiceOrderNo);
                for (InvoiceProduct invoiceProduct : invoiceProductList) {
                    SubOrder subOrder = subOrderService.getSubOrderBySubOrderNo(invoiceProduct.getSubOrderNo());
                    //保存采购单流转记录
                    OrderWorkFlow orderWorkFlow = new OrderWorkFlow();
                    orderWorkFlow.setCreatedTime(new Date());
                    orderWorkFlow.setNewOrderStatus(SubOrderStatusEnum.INVOICE_APPLY.getCode());
                    orderWorkFlow.setOldOrderStatus(subOrder.getSubOrderStatus());
                    orderWorkFlow.setOpUserName(user.getName());
                    orderWorkFlow.setOpUserNo(userInfo.getUserNo());
                    orderWorkFlow.setOpUserRole(RoleEnum.financial.getDec());
                    orderWorkFlow.setOrderNo(subOrder.getSubOrderNo());
                    orderWorkFlow.setType(OrderWorkFlowTypeEnum.SUB_ORDER.getCodeName());
                    orderWorkFlow.setRemark(remark);
                    orderWorkFlow.setResult("财务审核发货单不通过");
                    orderWorkFlow.setUpdatedTime(new Date());

                    subOrder.setSubOrderStatus(SubOrderStatusEnum.INVOICE_APPLY.getCode());
                    subOrderService.updateSubOrder(subOrder);
                    orderWorkFlowService.save(orderWorkFlow);
                }
            } else if ("Y".equals(result)) {
                //保存发货单流转记录
                OrderWorkFlow invoiceOrderWorkFlow = new OrderWorkFlow();
                invoiceOrderWorkFlow.setCreatedTime(new Date());
                invoiceOrderWorkFlow.setNewOrderStatus(InvoiceOrderStatusEnum.DOC_DELIVER_INFO.getCode());
                invoiceOrderWorkFlow.setOldOrderStatus(invoiceOrder.getInvoiceStatus());
                invoiceOrderWorkFlow.setOpUserName(userInfo.getUserName());
                invoiceOrderWorkFlow.setOpUserNo(userInfo.getUserNo());
                invoiceOrderWorkFlow.setOpUserRole(userInfo.getRoleName());
                invoiceOrderWorkFlow.setOrderNo(invoiceOrder.getInvoiceNo());
                invoiceOrderWorkFlow.setType(OrderWorkFlowTypeEnum.IV_ORDER.getCodeName());
                invoiceOrderWorkFlow.setRemark(remark);
                invoiceOrderWorkFlow.setResult("财务审核发货单通过");
                invoiceOrderWorkFlow.setUpdatedTime(new Date());

                invoiceOrder.setInvoiceStatus(InvoiceOrderStatusEnum.DOC_DELIVER_INFO.getCode());
                invoiceOrderService.updateInvoiceOrder(invoiceOrder);
                orderWorkFlowService.save(invoiceOrderWorkFlow);


                InvoiceOrderExt invoiceOrderExt = invoiceOrderExtService.getInvoiceOrderExtBySubOrderNo(invoiceOrder.getInvoiceNo());
                if (invoiceOrderExt != null) {
                    invoiceOrderExt.setFinApvTime(new Date());
                    invoiceOrderExtService.updateInvoiceOrderExt(invoiceOrderExt);
                }

                List<InvoiceProduct> invoiceProductList = invoiceProductService.listInvoiceProductByInvoiceOrderNo(invoiceOrderNo);
                for (InvoiceProduct invoiceProduct : invoiceProductList) {
                    SubOrder subOrder = subOrderService.getSubOrderBySubOrderNo(invoiceProduct.getSubOrderNo());
                    //保存采购单流转记录
                    OrderWorkFlow orderWorkFlow = new OrderWorkFlow();
                    orderWorkFlow.setCreatedTime(new Date());
                    orderWorkFlow.setNewOrderStatus(SubOrderStatusEnum.INVOICE_DELIVER.getCode());
                    orderWorkFlow.setOldOrderStatus(subOrder.getSubOrderStatus());
                    orderWorkFlow.setOpUserName(user.getName());
                    orderWorkFlow.setOpUserNo(userInfo.getUserNo());
                    orderWorkFlow.setOpUserRole(userInfo.getRoleName());
                    orderWorkFlow.setOrderNo(subOrder.getSubOrderNo());
                    orderWorkFlow.setType(OrderWorkFlowTypeEnum.SUB_ORDER.getCodeName());
                    orderWorkFlow.setRemark(remark);
                    orderWorkFlow.setResult("财务审核发货单通过");
                    orderWorkFlow.setUpdatedTime(new Date());

                    subOrder.setSubOrderStatus(SubOrderStatusEnum.INVOICE_DELIVER.getCode());
                    subOrderService.updateSubOrder(subOrder);
                    orderWorkFlowService.save(orderWorkFlow);
                }
            } else {
                return error("参数有误");
            }

        } catch (Exception ex) {
            logger.error("财务审核发货单信息异常", ex);
            return error("财务审核发货单信息异常");
        }
        return WebApiResponse.success("success");
    }


    /**
     * 待单证补充发货信息发货单列表
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/listDocApvInvoiceOrder", method = RequestMethod.GET)
    public WebApiResponse<List<InvoiceOrderVo>> listDocApvInvoiceOrder(@RequestParam(name = "pageSize", required = false) Integer pageSize,
                                                                       @RequestParam(name = "pageNum", required = false) Integer pageNum) {
        try {
            if (pageSize == null) {
                pageSize = 30;
            }

            if (pageNum == null) {
                pageNum = 1;
            }

            Integer start = (pageNum - 1) * pageSize;
            Integer size = pageSize;


            Map<String, Object> params = new HashMap<>();

            params.put("invoiceOrderStatus", InvoiceOrderStatusEnum.DOC_DELIVER_INFO.getCode());


            List<InvoiceOrderVo> invoiceOrderVos = invoiceOrderServiceProxy.listMyInvoiceOrderByParams(params, start, size);
            if (!CollectionUtils.isEmpty(invoiceOrderVos)) {
                WebApiResponse response = WebApiResponse.success(invoiceOrderVos);
                response.setTotalCount(invoiceOrderVos.get(0).getTotalCount());
                return response;
            }

            return WebApiResponse.success(null);
        } catch (Exception ex) {
            logger.error("待单证补充发货信息发货单列表异常", ex);
            return error("待单证补充发货信息发货单列表异常");
        }
    }


    /**
     * 单证补充发货信息
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/addDocDeliverInfo", method = RequestMethod.POST)
    public WebApiResponse<String> addDocDeliverInfo(@RequestParam(name = "invoiceOrderNo") String invoiceOrderNo,
                                                    @RequestParam(name = "transportChannel") String transportChannel,
                                                    @RequestParam(name = "transportCost") String transportCost,
                                                    @RequestParam(name = "remark", required = false) String remark) {
        UserInfo userInfo = UserStatus.getUserInfo();
        try {
            if (StringUtil.isEmptyString(invoiceOrderNo) || StringUtil.isEmptyString(transportCost) || StringUtil.isEmptyString(transportChannel)) {
                return error("缺少必传参数");
            }

            InvoiceOrder invoiceOrder = invoiceOrderService.selectInvoiceOrderByInvoiceOrderNo(invoiceOrderNo);
            if (invoiceOrder == null) {
                return WebApiResponse.error("发货单不存在");
            }

            if (!RoleEnum.admin.getCodeName().equals(userInfo.getRoleName())) {
                if (!RoleEnum.documentation.getCodeName().equals(userInfo.getRoleName())) {
                    return error("没有权限");
                }
            }

            if (invoiceOrder.getInvoiceStatus() != InvoiceOrderStatusEnum.DOC_DELIVER_INFO.getCode()) {
                return error("当前状态单证无法补充发货费用！");
            }

            User user = userService.getUserByUserNo(userInfo.getUserNo());

            //保存发货单流转记录
            OrderWorkFlow invoiceOrderWorkFlow = new OrderWorkFlow();
            invoiceOrderWorkFlow.setCreatedTime(new Date());
            invoiceOrderWorkFlow.setNewOrderStatus(InvoiceOrderStatusEnum.OUT_READY.getCode());
            invoiceOrderWorkFlow.setOldOrderStatus(invoiceOrder.getInvoiceStatus());
            invoiceOrderWorkFlow.setOpUserName(userInfo.getUserName());
            invoiceOrderWorkFlow.setOpUserNo(userInfo.getUserNo());
            invoiceOrderWorkFlow.setOpUserRole(userInfo.getRoleName());
            invoiceOrderWorkFlow.setOrderNo(invoiceOrder.getInvoiceNo());
            invoiceOrderWorkFlow.setType(OrderWorkFlowTypeEnum.IV_ORDER.getCodeName());
            invoiceOrderWorkFlow.setRemark(remark);
            invoiceOrderWorkFlow.setResult("单证补充发货费用");
            invoiceOrderWorkFlow.setUpdatedTime(new Date());

            invoiceOrder.setInvoiceStatus(InvoiceOrderStatusEnum.OUT_READY.getCode());
            invoiceOrderService.updateInvoiceOrder(invoiceOrder);
            orderWorkFlowService.save(invoiceOrderWorkFlow);


            InvoiceCostInfo invoiceCostInfo = new InvoiceCostInfo();
            invoiceCostInfo.setCreatedTime(new Date());
            invoiceCostInfo.setInvoiceNo(invoiceOrder.getInvoiceNo());
            invoiceCostInfo.setOpUserName(userInfo.getUserName());
            invoiceCostInfo.setOpUserNo(userInfo.getUserNo());
            invoiceCostInfo.setOrderNo(invoiceOrder.getOrderNo());
            invoiceCostInfo.setRemark(remark);
            invoiceCostInfo.setTransportChannel(transportChannel);
            invoiceCostInfo.setTransportCost(transportCost);
            invoiceCostInfo.setUpdatedTime(new Date());
            invoiceCostInfoService.saveInvoiceCostInfo(invoiceCostInfo);


            InvoiceOrderExt invoiceOrderExt = invoiceOrderExtService.getInvoiceOrderExtBySubOrderNo(invoiceOrder.getInvoiceNo());
            if (invoiceOrderExt != null) {
                invoiceOrderExt.setDocTime(new Date());
                invoiceOrderExtService.updateInvoiceOrderExt(invoiceOrderExt);
            }

            //采购单更新
            List<InvoiceProduct> invoiceProductList = invoiceProductService.listInvoiceProductByInvoiceOrderNo(invoiceOrderNo);
            for (InvoiceProduct invoiceProduct : invoiceProductList) {
                SubOrder subOrder = subOrderService.getSubOrderBySubOrderNo(invoiceProduct.getSubOrderNo());
                //保存采购单流转记录
                OrderWorkFlow orderWorkFlow = new OrderWorkFlow();
                orderWorkFlow.setCreatedTime(new Date());
                orderWorkFlow.setNewOrderStatus(SubOrderStatusEnum.READY_OUT.getCode());
                orderWorkFlow.setOldOrderStatus(subOrder.getSubOrderStatus());
                orderWorkFlow.setOpUserName(user.getName());
                orderWorkFlow.setOpUserNo(userInfo.getUserNo());
                orderWorkFlow.setOpUserRole(userInfo.getRoleName());
                orderWorkFlow.setOrderNo(subOrder.getSubOrderNo());
                orderWorkFlow.setType(OrderWorkFlowTypeEnum.SUB_ORDER.getCodeName());
                orderWorkFlow.setRemark(remark);
                orderWorkFlow.setResult("单证补充发货信息");
                orderWorkFlow.setUpdatedTime(new Date());

                subOrder.setSubOrderStatus(SubOrderStatusEnum.READY_OUT.getCode());
                subOrderService.updateSubOrder(subOrder);
                orderWorkFlowService.save(orderWorkFlow);
            }

        } catch (Exception ex) {
            logger.error("单证补充发货信息异常", ex);
            return error("单证补充发货信息异常");
        }
        return WebApiResponse.success("success");
    }


    /**
     * 获取发货费用信息
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/getDocDeliverInfo", method = RequestMethod.GET)
    public WebApiResponse<JSONObject> getDocDeliverInfo(@RequestParam(name = "invoiceOrderNo") String invoiceOrderNo) {
        UserInfo userInfo = UserStatus.getUserInfo();
        try {
            if (StringUtil.isEmptyString(invoiceOrderNo)) {
                return error("缺少必传参数");
            }

            InvoiceOrder invoiceOrder = invoiceOrderService.selectInvoiceOrderByInvoiceOrderNo(invoiceOrderNo);
            if (invoiceOrder == null) {
                return WebApiResponse.error("发货单不存在");
            }

            InvoiceCostInfo invoiceCostInfo = invoiceCostInfoService.getInvoiceCostInfoBySubOrderNo(invoiceOrderNo);
            if (invoiceCostInfo == null) {
                return WebApiResponse.success(null);
            }

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("transportChannel", invoiceCostInfo.getTransportChannel());
            jsonObject.put("transportCost", invoiceCostInfo.getTransportCost());

            return WebApiResponse.success(jsonObject);
        } catch (Exception ex) {
            logger.error("获取发货费用信息异常", ex);
            return error("获取发货费用信息异常");
        }
    }


    /**
     * 待出库发货单列表
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/listOutReadyInvoiceOrder", method = RequestMethod.GET)
    public WebApiResponse<List<InvoiceOrderVo>> listOutReadyInvoiceOrder(@RequestParam(name = "pageSize", required = false) Integer pageSize,
                                                                         @RequestParam(name = "pageNum", required = false) Integer pageNum) {
        try {
            if (pageSize == null) {
                pageSize = 30;
            }

            if (pageNum == null) {
                pageNum = 1;
            }

            Integer start = (pageNum - 1) * pageSize;
            Integer size = pageSize;


            Map<String, Object> params = new HashMap<>();

            params.put("invoiceOrderStatus", InvoiceOrderStatusEnum.OUT_READY.getCode());


            List<InvoiceOrderVo> invoiceOrderVos = invoiceOrderServiceProxy.listMyInvoiceOrderByParams(params, start, size);
            if (!CollectionUtils.isEmpty(invoiceOrderVos)) {
                WebApiResponse response = WebApiResponse.success(invoiceOrderVos);
                response.setTotalCount(invoiceOrderVos.get(0).getTotalCount());
                return response;
            }

            return WebApiResponse.success(null);
        } catch (Exception ex) {
            logger.error("待出库发货单列表异常", ex);
            return error("待出库发货单列表异常");
        }
    }


    /**
     * 仓储发货单出库
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/checkOut", method = RequestMethod.POST)
    public WebApiResponse<String> checkOut(@RequestParam(name = "invoiceOrderNo") String invoiceOrderNo,
                                           @RequestParam(name = "logisticsNo") String logisticsNo,
                                           @RequestParam(name = "actaulDeliverTime") String actaulDeliverTime,
                                           @RequestParam(name = "remark", required = false) String remark) {
        UserInfo userInfo = UserStatus.getUserInfo();
        try {
            if (StringUtil.isEmptyString(invoiceOrderNo) || StringUtil.isEmptyString(logisticsNo) || StringUtil.isEmptyString(actaulDeliverTime)) {
                return error("缺少必传参数");
            }

            InvoiceOrder invoiceOrder = invoiceOrderService.selectInvoiceOrderByInvoiceOrderNo(invoiceOrderNo);
            if (invoiceOrder == null) {
                return WebApiResponse.error("发货单不存在");
            }

            if (!RoleEnum.admin.getCodeName().equals(userInfo.getRoleName())) {
                if (!RoleEnum.storage.getCodeName().equals(userInfo.getRoleName())) {
                    return error("没有权限");
                }
            }

            if (invoiceOrder.getInvoiceStatus() != InvoiceOrderStatusEnum.OUT_READY.getCode()) {
                return error("当前状态仓储无法出库！");
            }

            User user = userService.getUserByUserNo(userInfo.getUserNo());
            //保存发货单流转记录
            OrderWorkFlow invoiceOrderWorkFlow = new OrderWorkFlow();
            invoiceOrderWorkFlow.setCreatedTime(new Date());
            invoiceOrderWorkFlow.setNewOrderStatus(InvoiceOrderStatusEnum.COMPLETE.getCode());
            invoiceOrderWorkFlow.setOldOrderStatus(invoiceOrder.getInvoiceStatus());
            invoiceOrderWorkFlow.setOpUserName(userInfo.getUserName());
            invoiceOrderWorkFlow.setOpUserNo(userInfo.getUserNo());
            invoiceOrderWorkFlow.setOpUserRole(userInfo.getRoleName());
            invoiceOrderWorkFlow.setOrderNo(invoiceOrder.getInvoiceNo());
            invoiceOrderWorkFlow.setType(OrderWorkFlowTypeEnum.IV_ORDER.getCodeName());
            invoiceOrderWorkFlow.setRemark(remark);
            invoiceOrderWorkFlow.setResult("仓储出库完成");
            invoiceOrderWorkFlow.setUpdatedTime(new Date());

            invoiceOrder.setInvoiceStatus(InvoiceOrderStatusEnum.COMPLETE.getCode());
            invoiceOrderService.updateInvoiceOrder(invoiceOrder);
            orderWorkFlowService.save(invoiceOrderWorkFlow);

            InvoiceDeliverInfo invoiceDeliverInfo = new InvoiceDeliverInfo();
            invoiceDeliverInfo.setCreatedTime(new Date());
            invoiceDeliverInfo.setInvoiceNo(invoiceOrder.getInvoiceNo());
            invoiceDeliverInfo.setOpUserName(userInfo.getUserName());
            invoiceDeliverInfo.setOpUserNo(userInfo.getUserNo());
            invoiceDeliverInfo.setOrderNo(invoiceOrder.getOrderNo());
            invoiceDeliverInfo.setRemark(remark);
            invoiceDeliverInfo.setActaulDeliverTime(DateUtil.parseObjToDate(actaulDeliverTime));
            invoiceDeliverInfo.setLogisticsNo(logisticsNo);
            invoiceDeliverInfo.setUpdatedTime(new Date());
            invoiceDeliverInfoService.saveInvoiceDeliverInfo(invoiceDeliverInfo);

            InvoiceOrderExt invoiceOrderExt = invoiceOrderExtService.getInvoiceOrderExtBySubOrderNo(invoiceOrder.getInvoiceNo());
            if (invoiceOrderExt != null) {
                invoiceOrderExt.setCheckOutTime(new Date());
                invoiceOrderExt.setStorageUserName(userInfo.getUserName());
                invoiceOrderExt.setStorageUserNo(userInfo.getUserNo());
                invoiceOrderExtService.updateInvoiceOrderExt(invoiceOrderExt);
            }

            //采购单更新
            List<InvoiceProduct> invoiceProductList = invoiceProductService.listInvoiceProductByInvoiceOrderNo(invoiceOrderNo);
            for (InvoiceProduct invoiceProduct : invoiceProductList) {
                SubOrder subOrder = subOrderService.getSubOrderBySubOrderNo(invoiceProduct.getSubOrderNo());
                //保存采购单流转记录
                OrderWorkFlow orderWorkFlow = new OrderWorkFlow();
                orderWorkFlow.setCreatedTime(new Date());
                orderWorkFlow.setNewOrderStatus(SubOrderStatusEnum.COMPLETE.getCode());
                orderWorkFlow.setOldOrderStatus(subOrder.getSubOrderStatus());
                orderWorkFlow.setOpUserName(user.getName());
                orderWorkFlow.setOpUserNo(userInfo.getUserNo());
                orderWorkFlow.setOpUserRole(userInfo.getRoleName());
                orderWorkFlow.setOrderNo(subOrder.getSubOrderNo());
                orderWorkFlow.setType(OrderWorkFlowTypeEnum.SUB_ORDER.getCodeName());
                orderWorkFlow.setRemark(remark);
                orderWorkFlow.setResult("仓储出库完成");
                orderWorkFlow.setUpdatedTime(new Date());

                subOrder.setSubOrderStatus(SubOrderStatusEnum.COMPLETE.getCode());
                subOrderService.updateSubOrder(subOrder);
                orderWorkFlowService.save(orderWorkFlow);
            }


            boolean isAllCompelte = true;
            List<SubOrder> subOrderList = subOrderService.listSubOrderByOrderNoWithOutDetail(invoiceOrder.getOrderNo());

            for (SubOrder subOrder : subOrderList) {
                if (subOrder.getSubOrderStatus() != SubOrderStatusEnum.COMPLETE.getCode()) {
                    isAllCompelte = false;
                }
            }

            if (isAllCompelte) {
                Order order = orderService.selectOrderByOrderNo(invoiceOrder.getOrderNo());
                order.setOrderStatus(OrderStatusEnum.COMPLETE.getCode());
                //保存订单流转记录
                OrderWorkFlow orderWorkFlow = new OrderWorkFlow();
                orderWorkFlow.setCreatedTime(new Date());
                orderWorkFlow.setNewOrderStatus(order.getOrderStatus());
                orderWorkFlow.setOldOrderStatus(OrderStatusEnum.DELIVERY.getCode());
                orderWorkFlow.setOpUserName(userInfo.getUserName());
                orderWorkFlow.setOpUserNo(userInfo.getUserNo());
                orderWorkFlow.setOpUserRole(userInfo.getRoleName());
                orderWorkFlow.setOrderNo(order.getOrderNo());
                orderWorkFlow.setType(OrderWorkFlowTypeEnum.M_ORDER.getCodeName());
                orderWorkFlow.setRemark("产品已全部发货");
                orderWorkFlow.setResult("已完成");
                orderWorkFlow.setUpdatedTime(new Date());
                orderWorkFlowService.save(orderWorkFlow);
                orderService.updateOrder(order);
            }
        } catch (Exception ex) {
            logger.error("仓储发货单出库异常", ex);
            return error("仓储发货单出库异常");
        }
        return WebApiResponse.success("success");
    }


    /**
     * 获取产品采购与发货情况
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/listSubOrderDeliverInfo", method = RequestMethod.GET)
    public WebApiResponse<List<JSONObject>> listSubOrderDeliverInfo(@RequestParam(name = "orderNo") String orderNo) {
        try {
            if (StringUtil.isEmptyString(orderNo)) {
                return error("缺少必传参数");
            }

            Order order = orderService.selectOrderByOrderNo(orderNo);
            if (order == null) {
                return WebApiResponse.error("订单不存在");
            }
            List<JSONObject> jsonObjectList = new ArrayList<>();

            List<SubOrder> subOrderList = subOrderService.listSubOrderByOrderNoWithOutDetail(orderNo);
            for (SubOrder subOrder : subOrderList) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("productName", subOrder.getProductName());
                jsonObject.put("productCategory", subOrder.getProductCategory());
                jsonObject.put("subOrderNo", subOrder.getSubOrderNo());
                jsonObject.put("subOrderStatus", SubOrderStatusEnum.valueOf(subOrder.getSubOrderStatus()).getDec());

                if (subOrder.getSubOrderStatus() == SubOrderStatusEnum.INVOICE_APPLY.getCode()) {
                    jsonObject.put("isDeliver", "是");
                } else {
                    jsonObject.put("isDeliver", "否");
                }

                InvoiceProduct invoiceProduct = invoiceProductService.getInvoiceProductByParam(orderNo, subOrder.getSubOrderNo());
                if (invoiceProduct != null) {
                    InvoiceOrder invoiceOrder = invoiceOrderService.selectInvoiceOrderByInvoiceOrderNo(invoiceProduct.getInvoiceNo());
                    if (invoiceOrder != null && invoiceOrder.getInvoiceStatus() != InvoiceOrderStatusEnum.DISCARD.getCode()) {
                        jsonObject.put("invoiceOrderNo", invoiceOrder.getInvoiceNo());
                        jsonObject.put("invoiceStatus", InvoiceOrderStatusEnum.valueOf(invoiceOrder.getInvoiceStatus()).getDec());
                        jsonObject.put("isDeliver", "已发货");
                    }
                }
                jsonObjectList.add(jsonObject);
            }

            return WebApiResponse.success(jsonObjectList);
        } catch (Exception ex) {
            logger.error("获取产品采购与发货情况异常", ex);
            return error("获取产品采购与发货情况异常");
        }
    }

}
