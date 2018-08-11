package com.alipapa.smp.order.service;

import com.alipapa.smp.order.mapper.OrderMapper;
import com.alipapa.smp.order.pojo.Order;
import com.alipapa.smp.order.pojo.OrderExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class OrderService {

    @Autowired
    private OrderMapper orderMapper;

    /**
     * 保存订单
     *
     * @param record
     * @return
     */
    public boolean saveOrder(Order record) {
        orderMapper.insert(record);
        return true;
    }


    /**
     * @return
     */
    public List<Order> getOrderList(String consumerNo) {
        OrderExample example = new OrderExample();
        OrderExample.Criteria criteria = example.createCriteria();
        criteria.andConsumerNoEqualTo(consumerNo);
        example.setOrderByClause("submitTime desc");
        return orderMapper.selectByExample(example);
    }


    /**
     * @return
     */
    public List<Order> getOrderListByParams(Map<String, Object> params) {
       /*
        OrderExample example = new OrderExample();
        OrderExample.Criteria criteria = example.createCriteria();
        criteria.andConsumerNoEqualTo(consumerNo);
        example.setOrderByClause("submitTime desc");
        return orderMapper.selectByExample(example);
        */
        return null;
    }


}
