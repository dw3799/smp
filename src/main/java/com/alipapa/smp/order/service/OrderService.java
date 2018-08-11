package com.alipapa.smp.order.service;

import com.alipapa.smp.order.mapper.OrderMapper;
import com.alipapa.smp.order.pojo.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
