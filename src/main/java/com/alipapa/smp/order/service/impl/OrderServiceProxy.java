package com.alipapa.smp.order.service.impl;

import com.alipapa.smp.common.enums.OrderTypeEnum;
import com.alipapa.smp.order.pojo.Order;
import com.alipapa.smp.order.pojo.SubOrder;
import com.alipapa.smp.order.service.OrderService;
import com.alipapa.smp.order.service.SubOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class OrderServiceProxy {

    @Autowired
    private OrderService orderService;

    @Autowired
    private SubOrderService subOrderService;

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
        }
        return true;
    }


}
