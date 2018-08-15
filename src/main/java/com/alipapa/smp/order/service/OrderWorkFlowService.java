package com.alipapa.smp.order.service;

import com.alipapa.smp.order.mapper.OrderWorkFlowMapper;
import com.alipapa.smp.order.pojo.OrderWorkFlow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderWorkFlowService {

    @Autowired
    private OrderWorkFlowMapper orderWorkFlowMapper;

    /**
     * 保存流转记录
     *
     * @param record
     * @return
     */
    public boolean save(OrderWorkFlow record) {
        orderWorkFlowMapper.insert(record);
        return true;
    }
}
