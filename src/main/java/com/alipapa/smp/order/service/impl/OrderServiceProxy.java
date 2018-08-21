package com.alipapa.smp.order.service.impl;

import com.alipapa.smp.common.enums.*;
import com.alipapa.smp.consumer.pojo.Consumer;
import com.alipapa.smp.consumer.pojo.SysDict;
import com.alipapa.smp.consumer.service.ConsumerService;
import com.alipapa.smp.consumer.service.SysDictService;
import com.alipapa.smp.order.pojo.*;
import com.alipapa.smp.order.service.*;
import com.alipapa.smp.order.vo.ConsumerOrderCount;
import com.alipapa.smp.order.vo.ConsumerOrderVo;
import com.alipapa.smp.order.vo.OrderVo;
import com.alipapa.smp.user.vo.UserVo;
import com.alipapa.smp.utils.DateUtil;
import com.alipapa.smp.utils.PriceUtil;
import com.alipapa.smp.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.*;

@Service
public class OrderServiceProxy {

    @Autowired
    private OrderService orderService;

    @Autowired
    private SubOrderService subOrderService;

    @Autowired
    private SysDictService sysDictService;

    @Autowired
    private ConsumerService consumerService;

    @Autowired
    private OrderWorkFlowService orderWorkFlowService;

    @Autowired
    private ConsumerFrontPayService consumerFrontPayService;

    /**
     * 创建主订单及产品订单及产品明细
     *
     * @param order
     * @param subOrderList
     * @return
     */
    @Transactional
    public Boolean createOrder(Order order, List<SubOrder> subOrderList) throws Exception {
        if (CollectionUtils.isEmpty(subOrderList)) {
            throw new Exception("订单数据异常");
        }
        boolean flag = orderService.saveOrder(order);

        if (flag) {
            OrderTypeEnum orderTypeEnum = OrderTypeEnum.valueOf(order.getOrderType());
            for (SubOrder subOrder : subOrderList) {
                subOrderService.saveSubOrder(subOrder, orderTypeEnum);
            }
            //保存订单流转记录
            OrderWorkFlow orderWorkFlow = new OrderWorkFlow();
            orderWorkFlow.setCreatedTime(new Date());
            orderWorkFlow.setNewOrderStatus(order.getOrderStatus());
            orderWorkFlow.setOldOrderStatus(OrderStatusEnum.CREATE.getCode());
            orderWorkFlow.setOpUserName(order.getSalerUserName());
            orderWorkFlow.setOpUserNo(order.getSalerUserNo());
            orderWorkFlow.setOpUserRole(RoleEnum.saler.getDec());
            orderWorkFlow.setOrderNo(order.getOrderNo());
            orderWorkFlow.setType(OrderWorkFlowTypeEnum.M_ORDER.getCodeName());
            if (order.getOrderStatus() == OrderStatusEnum.UN_SUBMIT.getCode()) {
                orderWorkFlow.setRemark("创建订单");
            } else if (order.getOrderStatus() == OrderStatusEnum.SPR_APV.getCode()) {
                orderWorkFlow.setRemark("创建并提交订单");
            }
            orderWorkFlow.setResult("成功");
            orderWorkFlow.setUpdatedTime(new Date());

            orderWorkFlowService.save(orderWorkFlow);
        }
        return true;
    }


    /**
     * 更新主订单及产品订单及产品明细
     *
     * @param order
     * @param subOrderList
     * @return
     */
    @Transactional
    public Boolean updateOrder(Order order, List<SubOrder> subOrderList) throws Exception {
        if (CollectionUtils.isEmpty(subOrderList)) {
            throw new Exception("订单数据异常");
        }


        boolean flag = orderService.updateOrder(order);

        if (flag) {
            OrderTypeEnum orderTypeEnum = OrderTypeEnum.valueOf(order.getOrderType());
            List<SubOrder> oldSubOrderList = subOrderService.listSubOrderByOrderNo(order.getOrderNo(), orderTypeEnum);
            HashMap<String, SubOrder> oldSubOrderMap = new HashMap<>();

            List<String> oldSubOrderNoList = new ArrayList<>();

            if (!CollectionUtils.isEmpty(oldSubOrderList)) {
                for (SubOrder oldSubOrder : oldSubOrderList) {
                    oldSubOrderMap.put(oldSubOrder.getSubOrderNo(), oldSubOrder);
                    oldSubOrderNoList.add(oldSubOrder.getSubOrderNo());
                }
            }


            for (SubOrder newSubOrder : subOrderList) {
                if (newSubOrder.getId() == null) { //新增产品订单
                    subOrderService.saveSubOrder(newSubOrder, orderTypeEnum);
                } else if (oldSubOrderMap.get(newSubOrder.getSubOrderNo()) != null) {
                    subOrderService.updateSubOrder(newSubOrder, orderTypeEnum);
                    oldSubOrderNoList.remove(newSubOrder.getSubOrderNo());
                }
            }


            //删除产品订单
            if (!CollectionUtils.isEmpty(oldSubOrderNoList)) {
                for (String delSubOrderNo : oldSubOrderNoList) {
                    SubOrder subOrder = oldSubOrderMap.get(delSubOrderNo);
                    subOrderService.delSubOrder(subOrder, orderTypeEnum);
                }
            }

            //保存订单流转记录
            OrderWorkFlow orderWorkFlow = new OrderWorkFlow();
            orderWorkFlow.setCreatedTime(new Date());
            orderWorkFlow.setNewOrderStatus(order.getOrderStatus());
            orderWorkFlow.setOldOrderStatus(OrderStatusEnum.UN_SUBMIT.getCode());
            orderWorkFlow.setOpUserName(order.getSalerUserName());
            orderWorkFlow.setOpUserNo(order.getSalerUserNo());
            orderWorkFlow.setOpUserRole(RoleEnum.saler.getDec());
            orderWorkFlow.setOrderNo(order.getOrderNo());
            orderWorkFlow.setType(OrderWorkFlowTypeEnum.M_ORDER.getCodeName());
            if (order.getOrderStatus() == OrderStatusEnum.UN_SUBMIT.getCode()) {
                orderWorkFlow.setRemark("修改订单");
            } else if (order.getOrderStatus() == OrderStatusEnum.SPR_APV.getCode()) {
                orderWorkFlow.setRemark("修改并提交订单");
            }
            orderWorkFlow.setResult("成功");
            orderWorkFlow.setUpdatedTime(new Date());
            orderWorkFlowService.save(orderWorkFlow);
        }
        return true;
    }

    /**
     * 获取客户历史订单列表
     *
     * @param consumerNo
     * @return
     */
    public List<ConsumerOrderVo> listConsumerOrder(String consumerNo, Integer start, Integer size) {
        List<ConsumerOrderVo> consumerOrderVoList = new ArrayList<>();

        Map<String, Object> params = new HashMap<>();
        params.put("consumerNo", consumerNo);

        Long totalCount = orderService.listOrderByParamCount(params);

        if (totalCount <= 0) {
            return null;
        }
        params.put("start", start);
        params.put("size", size);

        List<Order> orderList = orderService.getOrderListByParams(params);
        if (!CollectionUtils.isEmpty(orderList)) {
            for (Order order : orderList) {
                ConsumerOrderVo consumerOrderVo = new ConsumerOrderVo();
                consumerOrderVo.setConsumerName(order.getConsumerName());
                consumerOrderVo.setConsumerNo(order.getConsumerNo());
                consumerOrderVo.setOrderNo(order.getOrderNo());
                consumerOrderVo.setTotalCount(totalCount);

                Date submitTime = order.getSubmitTime();
                if (submitTime != null) {
                    consumerOrderVo.setSubmitDate(DateUtil.formatToStr(submitTime));
                }

                consumerOrderVo.setOrderStatus(OrderStatusEnum.valueOf(order.getOrderStatus()).getCodeName());

                List<SysDict> sysDictList = sysDictService.listSysDict(OrderCategoryCode.Currency.getCodeName(), order.getCurrency());

                if (CollectionUtils.isEmpty(sysDictList)) {
                    SysDict currencySysDict = sysDictList.get(0);
                    consumerOrderVo.setAmount(PriceUtil.convertToYuanStr(order.getOrderAmount()) + currencySysDict.getDictValue());
                }
            }
        }

        return consumerOrderVoList;
    }


    /**
     * 获取客户订单汇总
     *
     * @param consumerNo
     * @return
     */
    public ConsumerOrderCount getConsumerOrderCount(String consumerNo) {
        List<Order> orderList = orderService.getOrderList(consumerNo);
        if (!CollectionUtils.isEmpty(orderList)) {
            ConsumerOrderCount consumerOrderCount = new ConsumerOrderCount();
            Consumer consumer = consumerService.getConsumerByConsumerNo(consumerNo);
            consumerOrderCount.setConsumerNo(consumerNo);
            consumerOrderCount.setConsumerName(consumer.getName());
            Integer dealOrderCount = 0;
            Long amount = 0L;

            String currency = null;
            for (Order order : orderList) {
                if (order.getOrderStatus() == OrderStatusEnum.COMPLETE.getCode()) {
                    dealOrderCount++;
                    amount = amount + order.getOrderAmount();
                    if (StringUtil.isEmptyString(currency)) {
                        currency = order.getCurrency();
                    }
                }
            }
            List<SysDict> sysDictList = sysDictService.listSysDict(OrderCategoryCode.Currency.getCodeName(), currency);
            SysDict currencySysDict = sysDictList.get(0);
            consumerOrderCount.setDealOrderCount(dealOrderCount);
            consumerOrderCount.setDealOrderAmount(PriceUtil.convertToYuanStr(amount) + currencySysDict.getDictValue());
            return consumerOrderCount;
        }
        return null;
    }


    /**
     * 获取待提交订单列表
     *
     * @param salerUserNo
     * @return
     */
    public List<OrderVo> listOrderBySalerUserNo(String salerUserNo, Integer orderStatus, Integer start, Integer size) {
        Map<String, Object> params = new HashMap<>();
        params.put("salerUserNo", salerUserNo);
        params.put("orderStatus", orderStatus);

        Long totalCount = orderService.listOrderByParamCount(params);

        if (totalCount <= 0) {
            return null;
        }
        params.put("start", start);
        params.put("size", size);

        List<Order> orderList = orderService.getOrderListByParams(params);

        List<OrderVo> orderVoList = this.convertOrderVo(orderList, totalCount);

        return orderVoList;
    }


    /**
     * @param orderNo
     * @return
     */
    public boolean closeOrder(String orderNo, String salerUserNo) throws Exception {
        Order order = orderService.selectOrderByOrderNo(orderNo);
        if (order == null) {
            return false;
        }
        if (salerUserNo.equals(order.getSalerUserNo())) {
            throw new Exception("没有权限！");
        }
        order.setOrderStatus(OrderStatusEnum.CLOSE.getCode());
        return orderService.updateOrder(order);
    }


    /**
     * 获取组内待审核订单列表
     *
     * @param groupId
     * @return
     */
    public List<OrderVo> listApproveOrder(Long groupId, Integer start, Integer size) {

        Map<String, Object> params = new HashMap<>();
        params.put("groupId", groupId);
        params.put("orderStatus", OrderStatusEnum.SPR_APV.getCode());

        Long totalCount = orderService.listApproveOrderByParamCount(params);

        if (totalCount <= 0) {
            return null;
        }
        params.put("start", start);
        params.put("size", size);

        List<Order> orderList = orderService.listApproveOrderByParam(params);

        List<OrderVo> orderVoList = this.convertOrderVo(orderList, totalCount);

        return orderVoList;
    }


    /**
     * 按订单状态查询
     *
     * @param orderStatus
     * @return
     */
    public List<OrderVo> listOrderByStatus(Integer orderStatus, Integer start, Integer size) {
        Map<String, Object> params = new HashMap<>();
        params.put("orderStatus", orderStatus);

        Long totalCount = orderService.listOrderByParamCount(params);

        if (totalCount <= 0) {
            return null;
        }
        params.put("start", start);
        params.put("size", size);

        List<Order> orderList = orderService.getOrderListByParams(params);

        List<OrderVo> orderVoList = this.convertOrderVo(orderList, totalCount);

        return orderVoList;
    }


    /**
     * VO转换
     *
     * @return
     */
    private List<OrderVo> convertOrderVo(List<Order> orderList, Long totalCount) {
        List<OrderVo> orderVoList = new ArrayList<>();

        if (!CollectionUtils.isEmpty(orderList)) {
            for (Order order : orderList) {
                OrderVo orderVo = new OrderVo();
                orderVo.setConsumerName(order.getConsumerName());
                orderVo.setConsumerNo(order.getConsumerNo());
                orderVo.setConsumerCountry(order.getConsumerCountry());
                orderVo.setOrderNo(order.getOrderNo());
                orderVo.setTotalCount(totalCount);
                orderVo.setOrderType(OrderTypeEnum.valueOf(order.getOrderType()).getDec());

                Date submitTime = order.getSubmitTime();
                if (submitTime != null) {
                    orderVo.setSubmitDateTime(DateUtil.formatToStrTimeV1(submitTime));
                }
                orderVo.setCreateDateTime(DateUtil.formatToStrTimeV1(order.getCreatedTime()));

                orderVo.setOrderStatus(OrderStatusEnum.valueOf(order.getOrderStatus()).getDec());

                List<SysDict> sysDictList = sysDictService.listSysDict(OrderCategoryCode.Currency.getCodeName(), order.getCurrency());

                if (CollectionUtils.isEmpty(sysDictList)) {
                    SysDict currencySysDict = sysDictList.get(0);
                }

                String currencyDec = orderService.getCurrencyDec(order);
                orderVo.setAmount(PriceUtil.convertToYuanStr(order.getOrderAmount()) + currencyDec);

                ConsumerFrontPay consumerFrontPay = consumerFrontPayService.selectConsumerFrontPayByOrderNo(order.getOrderNo());
                if (consumerFrontPay != null) {
                    orderVo.setReceiptAmount(PriceUtil.convertToYuanStr(consumerFrontPay.getActualAmount()) + currencyDec);
                }


                orderVo.setBuyerUserName(order.getBuyerUserName());
                orderVo.setBuyerUserNo(order.getBuyerUserNo());
                orderVo.setSalerUserNo(order.getSalerUserNo());
                orderVo.setSalerUserName(order.getSalerUserName());
            }
        }
        return orderVoList;
    }
}
