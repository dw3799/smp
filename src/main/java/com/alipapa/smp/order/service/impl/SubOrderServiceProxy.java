package com.alipapa.smp.order.service.impl;


import com.alipapa.smp.common.Constant;
import com.alipapa.smp.common.enums.OrderTypeEnum;
import com.alipapa.smp.common.enums.SubOrderStatusEnum;
import com.alipapa.smp.order.mapper.SubOrderMapper;
import com.alipapa.smp.order.pojo.Order;
import com.alipapa.smp.order.pojo.SubOrder;
import com.alipapa.smp.order.service.OrderService;
import com.alipapa.smp.order.vo.SubOrderVo;
import com.alipapa.smp.utils.DateUtil;
import com.alipapa.smp.utils.PriceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SubOrderServiceProxy {

    @Autowired
    private OrderService orderService;

    @Autowired
    private SubOrderMapper subOrderMapper;

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
