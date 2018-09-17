package com.alipapa.smp.order.service.impl;


import com.alipapa.smp.common.Constant;
import com.alipapa.smp.common.enums.*;
import com.alipapa.smp.order.mapper.SubOrderMapper;
import com.alipapa.smp.order.pojo.MaterielOrder;
import com.alipapa.smp.order.pojo.Order;
import com.alipapa.smp.order.pojo.OrderWorkFlow;
import com.alipapa.smp.order.pojo.SubOrder;
import com.alipapa.smp.order.service.MaterielOrderService;
import com.alipapa.smp.order.service.OrderService;
import com.alipapa.smp.order.service.OrderWorkFlowService;
import com.alipapa.smp.order.vo.SubOrderVo;
import com.alipapa.smp.utils.DateUtil;
import com.alipapa.smp.utils.PriceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.*;

@Service
public class SubOrderServiceProxy {

    @Autowired
    private OrderService orderService;

    @Autowired
    private SubOrderMapper subOrderMapper;

    @Autowired
    private OrderWorkFlowService orderWorkFlowService;

    @Autowired
    private MaterielOrderService materielOrderService;


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
     * 查询我的采购单
     *
     * @param subOrderStatusEnum
     * @param buyerUserNo
     */

    public List<SubOrderVo> listMySubOrder(SubOrderStatusEnum subOrderStatusEnum, String buyerUserNo, Integer start, Integer size) {
        Map<String, Object> params = new HashMap<>();
        params.put("subOrderStatus", subOrderStatusEnum.getCode());
        params.put("buyerUserNo", buyerUserNo);

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
            subOrderVo.setProductName(subOrder.getProductName());
            subOrderVo.setSalerName(order.getSalerUserName());
            subOrderVo.setSubOrderId(subOrder.getId());
            subOrderVo.setSubOrderNo(subOrder.getSubOrderNo());
            subOrderVo.setSubOrderStatus(SubOrderStatusEnum.valueOf(subOrder.getSubOrderStatus()).getDec());
            subOrderVo.setTotalCount(totalCount);
            subOrderVoList.add(subOrderVo);
        }

        return subOrderVoList;
    }
}
