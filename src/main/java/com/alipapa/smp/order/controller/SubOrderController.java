package com.alipapa.smp.order.controller;

import com.alipapa.smp.common.Constant;
import com.alipapa.smp.common.enums.OrderTypeEnum;
import com.alipapa.smp.common.enums.OrderWorkFlowTypeEnum;
import com.alipapa.smp.common.enums.SubOrderStatusEnum;
import com.alipapa.smp.common.request.UserInfo;
import com.alipapa.smp.common.request.UserStatus;
import com.alipapa.smp.order.pojo.*;
import com.alipapa.smp.order.service.OrderService;
import com.alipapa.smp.order.service.OrderWorkFlowService;
import com.alipapa.smp.order.service.SubOrderService;
import com.alipapa.smp.order.service.impl.SubOrderServiceProxy;
import com.alipapa.smp.order.vo.OrderProductVo;
import com.alipapa.smp.order.vo.OrderWorkFlowVo;
import com.alipapa.smp.order.vo.SubOrderVo;
import com.alipapa.smp.utils.DateUtil;
import com.alipapa.smp.utils.PriceUtil;
import com.alipapa.smp.utils.StringUtil;
import com.alipapa.smp.utils.WebApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static com.alipapa.smp.utils.WebApiResponse.error;

/**
 * 采购订单接口
 *
 * @author liuwei
 * @version 2.0
 * 2018-09-16
 */
@RestController
@CrossOrigin
@RequestMapping("/api/subOrder")
public class SubOrderController {
    private static Logger logger = LoggerFactory.getLogger(SubOrderController.class);

    @Autowired
    private OrderService orderService;


    @Autowired
    private SubOrderServiceProxy subOrderServiceProxy;


    @Autowired
    private SubOrderService subOrderService;

    @Autowired
    private OrderWorkFlowService orderWorkFlowService;


    /**
     * 采购获取待提交采购单
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/listUnSubmitSubOrder", method = RequestMethod.GET)
    public WebApiResponse<List<SubOrderVo>> listFinFrontApvOrder(@RequestParam(name = "pageSize", required = false) Integer pageSize,
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

            List<SubOrderVo> orderVoList = subOrderServiceProxy.listMySubOrder(SubOrderStatusEnum.BUYER_ORDER, userInfo.getUserNo(), start, size);
            if (!CollectionUtils.isEmpty(orderVoList)) {
                WebApiResponse response = WebApiResponse.success(orderVoList);
                response.setTotalCount(orderVoList.get(0).getTotalCount());
                return response;
            }
            return WebApiResponse.success(null);
        } catch (Exception ex) {
            logger.error("获取待提交采购单异常", ex);
            return WebApiResponse.error("获取待提交采购单异常");
        }
    }


    /**
     * 获取采购单基本信息
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/listSubOrderDetailInfo", method = RequestMethod.GET)
    public WebApiResponse<OrderProductVo> listSubOrderDetailInfo(@RequestParam("subOrderNo") String subOrderNo) {
        if (StringUtil.isEmptyString(subOrderNo)) {
            return WebApiResponse.error("参数不能为空");
        }

        SubOrder subOrder = subOrderService.getSubOrderBySubOrderNo(subOrderNo);
        if (subOrder == null) {
            return WebApiResponse.error("采购订单不存在");
        }

        Order order = orderService.selectOrderByOrderNo(subOrder.getOrderNo());
        String currencyDec = orderService.getCurrencyDec(order);

        OrderTypeEnum orderTypeEnum = OrderTypeEnum.valueOf(order.getOrderType());
        if (orderTypeEnum == null) {
            return error("订单类型有误");
        }
        try {

            OrderProductVo orderProductVo = new OrderProductVo();
            orderProductVo.setSubOrderNo(subOrder.getSubOrderNo());
            orderProductVo.setOrderNo(subOrder.getOrderNo());
            orderProductVo.setProductCategoryId(subOrder.getProductCategoryId());
            orderProductVo.setProductCategory(subOrder.getProductCategory());
            orderProductVo.setProductId(subOrder.getProductId());
            orderProductVo.setProductName(subOrder.getProductName());
            orderProductVo.setProductAmount(PriceUtil.convertToYuanStr(subOrder.getProductAmount()) + currencyDec);
            orderProductVo.setExpectPurchaseAmount(PriceUtil.convertToYuanStr(subOrder.getExpectPurchaseAmount()) + Constant.YMB);
            orderProductVo.setProductRemark(subOrder.getProductRemark());
            orderProductVo.setPicNo(subOrder.getPic());
            if (OrderTypeEnum.SELF_ORDER == orderTypeEnum) {
                SelfOrderDetail selfOrderDetail = subOrder.getSelfOrderDetail();
                orderProductVo.setSaleAmount(PriceUtil.convertToYuanStr(subOrder.getSaleAmount()) + currencyDec);
                orderProductVo.setFactoryAmount(PriceUtil.convertToYuanStr(subOrder.getFactoryAmount()) + Constant.YMB);

                orderProductVo.setWeight(selfOrderDetail.getWeight());
                orderProductVo.setMaterial(selfOrderDetail.getMaterial());
                orderProductVo.setSize(selfOrderDetail.getSize());
                orderProductVo.setColor(selfOrderDetail.getColor());
                orderProductVo.setSuturing(selfOrderDetail.getSuturing());

                orderProductVo.setPrinting(selfOrderDetail.getPrinting());
                orderProductVo.setQuantity(selfOrderDetail.getQuantity());
            } else {
                AgentOrderDetail agentOrderDetail = subOrder.getAgentOrderDetail();

                orderProductVo.setSaleAmount(PriceUtil.convertToYuanStr(subOrder.getSaleAmount()) + currencyDec);
                orderProductVo.setFactoryAmount(PriceUtil.convertToYuanStr(subOrder.getFactoryAmount()) + Constant.YMB);
                orderProductVo.setUnit(agentOrderDetail.getUnit());
                orderProductVo.setSinglePackageCount(agentOrderDetail.getSinglePackageCount());
                orderProductVo.setPackageNumber(agentOrderDetail.getPackageNumber());
                orderProductVo.setSingleVolume(agentOrderDetail.getSingleVolume());
                orderProductVo.setSingleWeight(agentOrderDetail.getSingleWeight());
                orderProductVo.setTotalVolume(agentOrderDetail.getTotalVolume());
                orderProductVo.setTotalWeight(agentOrderDetail.getTotalWeight());
            }
            orderProductVo.setCreatedTime(DateUtil.formatToStrTimeV1(subOrder.getCreatedTime()));
            orderProductVo.setSubOrderStatus(SubOrderStatusEnum.valueOf(subOrder.getSubOrderStatus()).getDec());
            return WebApiResponse.success(orderProductVo);

        } catch (Exception ex) {
            logger.error("获取采购单基本信息异常", ex);
            return WebApiResponse.error("获取采购单基本信息异常");
        }
    }


    /**
     * 获取采购单流转记录
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/listSubOrderWorkFlow", method = RequestMethod.GET)
    public WebApiResponse<List<OrderWorkFlowVo>> listOrderWorkFlow(@RequestParam("subOrderNo") String subOrderNo) {

        if (StringUtil.isEmptyString(subOrderNo)) {
            return WebApiResponse.error("参数不能为空");
        }

        SubOrder subOrder = subOrderService.getSubOrderBySubOrderNo(subOrderNo);
        if (subOrder == null) {
            return WebApiResponse.error("采购订单不存在");
        }

        List<OrderWorkFlowVo> orderWorkFlowVoList = new ArrayList<>();
        try {
            List<OrderWorkFlow> orderWorkFlowList = orderWorkFlowService.listOrderWorkFlowByParams(subOrderNo, OrderWorkFlowTypeEnum.SUB_ORDER.getCodeName());
            if (!CollectionUtils.isEmpty(orderWorkFlowList)) {
                for (OrderWorkFlow orderWorkFlow : orderWorkFlowList) {
                    OrderWorkFlowVo orderWorkFlowVo = new OrderWorkFlowVo();
                    orderWorkFlowVo.setCreatedTime(DateUtil.formatToStrTimeV1(orderWorkFlow.getCreatedTime()));
                    orderWorkFlowVo.setOpUserRole(orderWorkFlow.getOpUserRole());
                    orderWorkFlowVo.setOrderNo(subOrderNo);
                    orderWorkFlowVo.setOpUserName(orderWorkFlow.getOpUserName());
                    orderWorkFlowVo.setRemark(orderWorkFlow.getRemark());
                    orderWorkFlowVo.setResult(orderWorkFlow.getResult());
                    orderWorkFlowVoList.add(orderWorkFlowVo);
                }

            }
        } catch (Exception ex) {
            logger.error("获取采购单流转记录异常", ex);
            return WebApiResponse.error("获取采购单流转记录异常");
        }

        return WebApiResponse.success(orderWorkFlowVoList);
    }


}
