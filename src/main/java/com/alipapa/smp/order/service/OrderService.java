package com.alipapa.smp.order.service;

import com.alipapa.smp.order.mapper.OrderMapper;
import com.alipapa.smp.order.pojo.Order;
import com.alipapa.smp.order.pojo.OrderExample;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Service
public class OrderService {
    private static Logger logger = LoggerFactory.getLogger(OrderService.class);

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
        if (params == null) {
            params = new HashMap<>();
        }

        Iterator<Map.Entry<String, Object>> it = params.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, Object> entry = it.next();
            logger.info("key= " + entry.getKey() + " and value= " + entry.getValue());
        }

        List<Order> orderList = orderMapper.findConsumerOrderByParam(params);

        return orderList;
    }


    /**
     * @param params
     * @return
     */
    public Long findConsumerOrderByParamCount(Map<String, Object> params) {
        return orderMapper.findConsumerOrderByParamCount(params);
    }


}
