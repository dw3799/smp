package com.alipapa.smp.order.service;

import com.alipapa.smp.consumer.mapper.ConsumerMapper;
import com.alipapa.smp.order.mapper.AgentOrderDetailMapper;
import com.alipapa.smp.order.mapper.OrderMapper;
import com.alipapa.smp.order.mapper.SelfOrderDetailMapper;
import com.alipapa.smp.order.mapper.SubOrderMapper;
import com.alipapa.smp.order.pojo.Order;
import com.alipapa.smp.order.pojo.SubOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderServiceProxy {

    @Autowired
    private OrderMapper orderMapper;


    @Autowired
    private SubOrderMapper subOrderMapper;


    @Autowired
    private SelfOrderDetailMapper selfOrderDetailMapper;


    @Autowired
    private AgentOrderDetailMapper agentOrderDetailMapper;

    /**
     * 创建主订单及产品订单及产品明细
     *
     * @param order
     * @param subOrderList
     * @return
     */
    @Transactional
    public Boolean CreateOrder(Order order, List<SubOrder> subOrderList) {


        return true;
    }


}
