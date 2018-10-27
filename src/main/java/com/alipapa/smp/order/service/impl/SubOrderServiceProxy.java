package com.alipapa.smp.order.service.impl;


import com.alibaba.fastjson.JSONObject;
import com.alipapa.smp.common.Constant;
import com.alipapa.smp.common.enums.*;
import com.alipapa.smp.order.mapper.SubOrderMapper;
import com.alipapa.smp.order.pojo.*;
import com.alipapa.smp.order.service.MaterielOrderService;
import com.alipapa.smp.order.service.OrderService;
import com.alipapa.smp.order.service.OrderWorkFlowService;
import com.alipapa.smp.order.service.PurchaseOrderExtService;
import com.alipapa.smp.order.vo.SubOrderVo;
import com.alipapa.smp.utils.DateUtil;
import com.alipapa.smp.utils.PriceUtil;
import com.alipapa.smp.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.*;

@Service
public class SubOrderServiceProxy {

    @Autowired
    private OrderService orderService;

    @Resource
    private SubOrderMapper subOrderMapper;

    @Autowired
    private OrderWorkFlowService orderWorkFlowService;

    @Autowired
    private MaterielOrderService materielOrderService;

    @Autowired
    private PurchaseOrderExtService purchaseOrderExtService;

    /**
     * 保存物料订单
     *
     * @param subOrder
     * @param materielOrderList
     * @return
     */
    @Transactional
    public Boolean saveMaterielOrder(Order order, SubOrder subOrder, List<MaterielOrder> materielOrderList) throws Exception {
        if (CollectionUtils.isEmpty(materielOrderList)) {
            throw new Exception("物料订单数据异常");
        }

        subOrderMapper.updateByPrimaryKey(subOrder);

        List<MaterielOrder> oldMaterielOrderList = materielOrderService.listMaterielOrderBySubOrderNo(subOrder.getSubOrderNo());
        HashMap<String, MaterielOrder> oldMaterielOrderOrderMap = new HashMap<>();

        List<String> oldMaterielOrderNoList = new ArrayList<>();

        if (!CollectionUtils.isEmpty(oldMaterielOrderList)) {
            for (MaterielOrder oldMaterielOrder : oldMaterielOrderList) {
                oldMaterielOrderOrderMap.put(String.valueOf(oldMaterielOrder.getId()), oldMaterielOrder);
                oldMaterielOrderNoList.add(String.valueOf(oldMaterielOrder.getId()));
            }
        }


        for (MaterielOrder newMaterielOrder : materielOrderList) {
            if (newMaterielOrder.getId() == null) { //新增产品订单
                materielOrderService.saveMaterielOrder(newMaterielOrder);
            } else if (oldMaterielOrderOrderMap.get(String.valueOf(newMaterielOrder.getId())) != null) {
                materielOrderService.updateMaterielOrder(newMaterielOrder);
                oldMaterielOrderNoList.remove(String.valueOf(newMaterielOrder.getId()));
            }
        }

        //删除物料订单
        if (!CollectionUtils.isEmpty(oldMaterielOrderNoList)) {
            for (String delSubOrderNo : oldMaterielOrderNoList) {
                materielOrderService.delMaterielOrder(Long.valueOf(delSubOrderNo));
            }
        }

        //保存订单流转记录
        OrderWorkFlow orderWorkFlow = new OrderWorkFlow();
        orderWorkFlow.setCreatedTime(new Date());
        orderWorkFlow.setNewOrderStatus(subOrder.getSubOrderStatus());
        orderWorkFlow.setOldOrderStatus(SubOrderStatusEnum.BUYER_ORDER.getCode());
        orderWorkFlow.setOpUserName(order.getBuyerUserName());
        orderWorkFlow.setOpUserNo(order.getBuyerUserNo());

        if (order.getOrderType() == OrderTypeEnum.SELF_ORDER.getCode()) {
            orderWorkFlow.setOpUserRole(RoleEnum.selfBuyer.getDec());
        } else {
            orderWorkFlow.setOpUserRole(RoleEnum.agentBuyer.getDec());
        }

        orderWorkFlow.setOrderNo(subOrder.getSubOrderNo());
        orderWorkFlow.setType(OrderWorkFlowTypeEnum.SUB_ORDER.getCodeName());
        if (subOrder.getSubOrderStatus() == SubOrderStatusEnum.BUYER_ORDER.getCode()) {
            orderWorkFlow.setRemark("保存物料订单");
        } else if (subOrder.getSubOrderStatus() == SubOrderStatusEnum.SPR_BUYER_APV.getCode()) {
            orderWorkFlow.setRemark("保存并提交物料订单");
        }
        orderWorkFlow.setResult("成功");
        orderWorkFlow.setUpdatedTime(new Date());
        orderWorkFlowService.save(orderWorkFlow);
        return true;
    }


    /**
     * 新增物料订单
     *
     * @param subOrder
     * @param materielOrderList
     * @return
     */
    @Transactional
    public Boolean addMaterielOrder(Order order, SubOrder subOrder, List<MaterielOrder> materielOrderList) throws Exception {
        if (CollectionUtils.isEmpty(materielOrderList)) {
            throw new Exception("物料订单数据异常");
        }

        subOrderMapper.updateByPrimaryKey(subOrder);

        List<MaterielOrder> oldMaterielOrderList = materielOrderService.listMaterielOrderBySubOrderNo(subOrder.getSubOrderNo());
        HashMap<String, MaterielOrder> oldMaterielOrderOrderMap = new HashMap<>();

        List<String> oldMaterielOrderNoList = new ArrayList<>();

        if (!CollectionUtils.isEmpty(oldMaterielOrderList)) {
            for (MaterielOrder oldMaterielOrder : oldMaterielOrderList) {
                oldMaterielOrderOrderMap.put(String.valueOf(oldMaterielOrder.getId()), oldMaterielOrder);
                oldMaterielOrderNoList.add(String.valueOf(oldMaterielOrder.getId()));
            }
        }


        for (MaterielOrder newMaterielOrder : materielOrderList) {
            if (newMaterielOrder.getId() == null) { //新增产品订单
                materielOrderService.saveMaterielOrder(newMaterielOrder);
            } else if (oldMaterielOrderOrderMap.get(String.valueOf(newMaterielOrder.getId())) != null) {
                materielOrderService.updateMaterielOrder(newMaterielOrder);
                oldMaterielOrderNoList.remove(String.valueOf(newMaterielOrder.getId()));
            }
        }

        //删除物料订单
        if (!CollectionUtils.isEmpty(oldMaterielOrderNoList)) {
            throw new Exception("不可以删除物料");
        }

        //保存订单流转记录
        OrderWorkFlow orderWorkFlow = new OrderWorkFlow();
        orderWorkFlow.setCreatedTime(new Date());
        orderWorkFlow.setNewOrderStatus(subOrder.getSubOrderStatus());
        orderWorkFlow.setOldOrderStatus(SubOrderStatusEnum.BUYER_FOLLOW_ORDER.getCode());
        orderWorkFlow.setOpUserName(order.getBuyerUserName());
        orderWorkFlow.setOpUserNo(order.getBuyerUserNo());

        if (order.getOrderType() == OrderTypeEnum.SELF_ORDER.getCode()) {
            orderWorkFlow.setOpUserRole(RoleEnum.selfBuyer.getDec());
        } else {
            orderWorkFlow.setOpUserRole(RoleEnum.agentBuyer.getDec());
        }

        orderWorkFlow.setOrderNo(subOrder.getSubOrderNo());
        orderWorkFlow.setType(OrderWorkFlowTypeEnum.SUB_ORDER.getCodeName());
        if (subOrder.getSubOrderStatus() == SubOrderStatusEnum.BUYER_ORDER.getCode()) {
            orderWorkFlow.setRemark("新增物料订单，重新审核");
        } else if (subOrder.getSubOrderStatus() == SubOrderStatusEnum.SPR_BUYER_APV.getCode()) {
            orderWorkFlow.setRemark("新增并提交物料订单，重新审核");
        }
        orderWorkFlow.setResult("成功");
        orderWorkFlow.setUpdatedTime(new Date());
        orderWorkFlowService.save(orderWorkFlow);
        return true;
    }


    /**
     * 查询我的采购单
     *
     * @param subOrderStatusEnum
     * @param buyerUserNo
     */

    public List<SubOrderVo> listMySubOrder(SubOrderStatusEnum subOrderStatusEnum, String buyerUserNo, Integer start, Integer size) {
        Map<String, Object> params = new HashMap<>();
        params.put("subOrderStatus", subOrderStatusEnum.getCode());
        if (!StringUtil.isEmptyString(buyerUserNo)) {
            params.put("buyerUserNo", buyerUserNo);
        }

        Long totalCount = subOrderMapper.listMySubOrderByParamCount(params);

        if (totalCount <= 0) {
            return null;
        }
        params.put("start", start);
        params.put("size", size);

        List<SubOrder> subOrderList = subOrderMapper.listMySubOrderByParam(params);

        List<SubOrderVo> orderVoList = this.convertSubOrderVo(subOrderList, totalCount);

        return orderVoList;
    }


    /**
     * 查询组内采购单
     *
     * @param subOrderStatusEnum
     * @param groupId
     */

    public List<SubOrderVo> listGroupSubOrder(SubOrderStatusEnum subOrderStatusEnum, Long groupId, Integer start, Integer size) {
        Map<String, Object> params = new HashMap<>();
        params.put("subOrderStatus", subOrderStatusEnum.getCode());
        params.put("groupId", groupId);

        Long totalCount = subOrderMapper.listMySubOrderByParamCount(params);

        if (totalCount <= 0) {
            return null;
        }
        params.put("start", start);
        params.put("size", size);

        List<SubOrder> subOrderList = subOrderMapper.listGroupSubOrderByParam(params);

        List<SubOrderVo> orderVoList = this.convertSubOrderVo(subOrderList, totalCount);

        return orderVoList;
    }


    /**
     * 查询组内采购单
     *
     * @param params
     */

    public List<SubOrderVo> listGroupSubOrder(Map<String, Object> params, Integer start, Integer size) {

        Long totalCount = subOrderMapper.listMySubOrderByParamCount(params);

        if (totalCount <= 0) {
            return null;
        }
        params.put("start", start);
        params.put("size", size);

        List<SubOrder> subOrderList = subOrderMapper.listGroupSubOrderByParam(params);

        List<SubOrderVo> orderVoList = this.convertSubOrderVo(subOrderList, totalCount);

        return orderVoList;
    }


    private List<SubOrderVo> convertSubOrderVo(List<SubOrder> subOrderList, Long totalCount) {
        if (CollectionUtils.isEmpty(subOrderList)) {
            return null;
        }

        List<SubOrderVo> subOrderVoList = new ArrayList<>();
        for (SubOrder subOrder : subOrderList) {
            Order order = orderService.selectOrderByOrderNo(subOrder.getOrderNo());
            String currencyDec = orderService.getCurrencyDec(order);

            SubOrderVo subOrderVo = new SubOrderVo();
            subOrderVo.setCreatedTime(DateUtil.formatToStrTimeV1(subOrder.getCreatedTime()));
            subOrderVo.setExpectPurchaseAmount(PriceUtil.convertToYuanStr(subOrder.getExpectPurchaseAmount()) + Constant.YMB);
            subOrderVo.setFactoryAmount(PriceUtil.convertToYuanStr(subOrder.getFactoryAmount()) + Constant.YMB);
            subOrderVo.setOrderNo(subOrder.getOrderNo());
            subOrderVo.setOrderType(OrderTypeEnum.valueOf(order.getOrderType()).getDec());
            subOrderVo.setProductAmount(PriceUtil.convertToYuanStr(subOrder.getProductAmount()) + currencyDec);

            subOrderVo.setExpectFrontAmount(PriceUtil.convertToYuanStr(subOrder.getProductFrontAmount()) + Constant.YMB);
            subOrderVo.setPayedFrontAmount(PriceUtil.convertToYuanStr(subOrder.getActualPurchaseAmount()) + Constant.YMB);
            subOrderVo.setProductName(subOrder.getProductName());
            subOrderVo.setSalerName(order.getSalerUserName());
            subOrderVo.setBuyerName(order.getBuyerUserName());
            subOrderVo.setSubOrderId(subOrder.getId());
            subOrderVo.setSubOrderNo(subOrder.getSubOrderNo());
            subOrderVo.setSubOrderStatus(SubOrderStatusEnum.valueOf(subOrder.getSubOrderStatus()).getDec());
            subOrderVo.setConsumerName(order.getConsumerName());

            PurchaseOrderExt purchaseOrderExt = purchaseOrderExtService.getPurchaseOrderExtBySubOrderNo(subOrder.getSubOrderNo());
            if (purchaseOrderExt != null) {
                subOrderVo.setSubmitTime(DateUtil.formatToStrTimeV1(purchaseOrderExt.getSubmitTime()));
                subOrderVo.setSuperApvTime(DateUtil.formatToStrTimeV1(purchaseOrderExt.getSuperApvTime()));
                subOrderVo.setFinFrontTime(DateUtil.formatToStrTimeV1(purchaseOrderExt.getFinFrontTime()));
                subOrderVo.setCashFrontTime(DateUtil.formatToStrTimeV1(purchaseOrderExt.getCashFrontTime()));
            }

            subOrderVo.setTotalCount(totalCount);
            subOrderVoList.add(subOrderVo);
        }

        return subOrderVoList;
    }


    /**
     * 查询待质检采购单
     *
     * @param subOrderStatusEnum
     */

    public List<JSONObject> listQualityCheckSubOrderByStatus(SubOrderStatusEnum subOrderStatusEnum, Integer start, Integer size) {
        Map<String, Object> params = new HashMap<>();
        params.put("subOrderStatus", subOrderStatusEnum.getCode());

        Long totalCount = subOrderMapper.listMySubOrderByParamCount(params);

        if (totalCount <= 0) {
            return null;
        }
        params.put("start", start);
        params.put("size", size);

        List<SubOrder> subOrderList = subOrderMapper.listMySubOrderByParam(params);

        return convertQualityCheckJSONObjectVo(subOrderList, totalCount);
    }


    /**
     * @param subOrderList
     * @param totalCount
     * @return
     */
    private List<JSONObject> convertQualityCheckJSONObjectVo(List<SubOrder> subOrderList, Long totalCount) {
        if (CollectionUtils.isEmpty(subOrderList)) {
            return null;
        }

        List<JSONObject> subOrderVoList = new ArrayList<>();
        for (SubOrder subOrder : subOrderList) {
            Order order = orderService.selectOrderByOrderNo(subOrder.getOrderNo());

            JSONObject subOrderVo = new JSONObject();
            subOrderVo.put("totalCount", totalCount);
            subOrderVo.put("subOrderId", subOrder.getId());
            subOrderVo.put("orderNo", subOrder.getOrderNo());
            subOrderVo.put("subOrderNo", subOrder.getSubOrderNo());
            subOrderVo.put("productName", subOrder.getProductName());
            subOrderVo.put("salerUserName", order.getSalerUserName());
            subOrderVo.put("buyerUserName", order.getBuyerUserName());


            PurchaseOrderExt purchaseOrderExt = purchaseOrderExtService.getPurchaseOrderExtBySubOrderNo(subOrder.getSubOrderNo());
            if (purchaseOrderExt != null) {
                subOrderVo.put("qualityCheckTime", DateUtil.formatToStrTimeV1(purchaseOrderExt.getSubmitQualityCheckTime()));
            }
            subOrderVoList.add(subOrderVo);
        }
        return subOrderVoList;
    }


    /**
     * 查询尾款支付采购单
     *
     * @param subOrderPayStatusEnum
     */

    public List<JSONObject> listTailPaySubOrderByPayStatus(SubOrderPayStatusEnum subOrderPayStatusEnum, Integer start, Integer size) {
        Map<String, Object> params = new HashMap<>();
        params.put("subPayStatus", subOrderPayStatusEnum.getCode());

        Long totalCount = subOrderMapper.listMySubOrderByParamCount(params);

        if (totalCount <= 0) {
            return null;
        }
        params.put("start", start);
        params.put("size", size);

        List<SubOrder> subOrderList = subOrderMapper.listMySubOrderByParam(params);

        return convertTailPayJSONObjectVo(subOrderList, totalCount);
    }


    /**
     * @param subOrderList
     * @param totalCount
     * @return
     */
    private List<JSONObject> convertTailPayJSONObjectVo(List<SubOrder> subOrderList, Long totalCount) {
        if (CollectionUtils.isEmpty(subOrderList)) {
            return null;
        }

        List<JSONObject> subOrderVoList = new ArrayList<>();
        for (SubOrder subOrder : subOrderList) {
            Order order = orderService.selectOrderByOrderNo(subOrder.getOrderNo());

            JSONObject subOrderVo = new JSONObject();
            subOrderVo.put("totalCount", totalCount);
            subOrderVo.put("subOrderId", subOrder.getId());
            subOrderVo.put("orderNo", subOrder.getOrderNo());
            subOrderVo.put("subOrderNo", subOrder.getSubOrderNo());
            subOrderVo.put("subOrderStatus", SubOrderStatusEnum.valueOf(subOrder.getSubOrderStatus()).getDec());
            subOrderVo.put("productName", subOrder.getProductName());
            subOrderVo.put("orderType", OrderTypeEnum.valueOf(order.getOrderType()).getDec());
            subOrderVo.put("purchaseAmount", PriceUtil.convertToYuanStr(subOrder.getActualPurchaseAmount()) + Constant.YMB);
            subOrderVo.put("payedAmount", PriceUtil.convertToYuanStr(subOrder.getPayedAmount()) + Constant.YMB);
            subOrderVo.put("restAmount", PriceUtil.convertToYuanStr(subOrder.getActualPurchaseAmount() - subOrder.getPayedAmount()) + Constant.YMB);
            subOrderVo.put("buyerUserName", order.getBuyerUserName());

            PurchaseOrderExt purchaseOrderExt = purchaseOrderExtService.getPurchaseOrderExtBySubOrderNo(subOrder.getSubOrderNo());
            if (purchaseOrderExt != null) {
                subOrderVo.put("completeQualityCheckTime", DateUtil.formatToStrTimeV1(purchaseOrderExt.getCompleteQualityCheckTime()));
                subOrderVo.put("finTailTime", DateUtil.formatToStrTimeV1(purchaseOrderExt.getFinTailTime()));

            }
            subOrderVoList.add(subOrderVo);
        }
        return subOrderVoList;
    }


    /**
     * 查询业务员的采购单
     *
     * @param subOrderStatusEnum
     * @param salerUserNo
     */

    public List<JSONObject> listSubOrderBySaler(SubOrderStatusEnum subOrderStatusEnum, String salerUserNo, Integer start, Integer size) {
        Map<String, Object> params = new HashMap<>();
        params.put("subOrderStatus", subOrderStatusEnum.getCode());
        if (!StringUtil.isEmptyString(salerUserNo)) {
            params.put("salerUserNo", salerUserNo);
        }

        Long totalCount = subOrderMapper.listMySubOrderByParamCount(params);

        if (totalCount <= 0) {
            return null;
        }
        params.put("start", start);
        params.put("size", size);

        List<SubOrder> subOrderList = subOrderMapper.listMySubOrderByParam(params);

        List<JSONObject> jsonObjectList = this.convertBySalerJSONObjectVo(subOrderList, totalCount);

        return jsonObjectList;
    }


    /**
     * @param subOrderList
     * @param totalCount
     * @return
     */
    private List<JSONObject> convertBySalerJSONObjectVo(List<SubOrder> subOrderList, Long totalCount) {
        if (CollectionUtils.isEmpty(subOrderList)) {
            return null;
        }

        List<JSONObject> subOrderVoList = new ArrayList<>();
        for (SubOrder subOrder : subOrderList) {
            Order order = orderService.selectOrderByOrderNo(subOrder.getOrderNo());
            String currencyDec = orderService.getCurrencyDec(order);

            JSONObject subOrderVo = new JSONObject();
            subOrderVo.put("totalCount", totalCount);
            subOrderVo.put("subOrderId", subOrder.getId());
            subOrderVo.put("orderNo", subOrder.getOrderNo());
            subOrderVo.put("subOrderNo", subOrder.getSubOrderNo());
            subOrderVo.put("subOrderStatus", SubOrderStatusEnum.valueOf(subOrder.getSubOrderStatus()).getDec());
            subOrderVo.put("productName", subOrder.getProductName());
            subOrderVo.put("orderType", OrderTypeEnum.valueOf(order.getOrderType()).getDec());
            subOrderVo.put("orderAmount", PriceUtil.convertToYuanStr(order.getOrderAmount()) + currencyDec);
            subOrderVo.put("receiptAmount", PriceUtil.convertToYuanStr(order.getReceiptAmount()) + currencyDec);
            subOrderVo.put("productAmount", PriceUtil.convertToYuanStr(subOrder.getProductAmount()) + currencyDec);


            subOrderVo.put("consumerNo", order.getConsumerNo());
            subOrderVo.put("consumerName", order.getConsumerName());
            subOrderVo.put("consumerCountry", order.getConsumerCountry());

            PurchaseOrderExt purchaseOrderExt = purchaseOrderExtService.getPurchaseOrderExtBySubOrderNo(subOrder.getSubOrderNo());
            if (purchaseOrderExt != null) {
                subOrderVo.put("checkInTime", DateUtil.formatToStrTimeV1(purchaseOrderExt.getCompleteQualityCheckTime()));
            }
            subOrderVoList.add(subOrderVo);
        }
        return subOrderVoList;
    }


}



