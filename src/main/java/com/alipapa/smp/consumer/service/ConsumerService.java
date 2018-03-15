package com.alipapa.smp.consumer.service;

import com.alipapa.smp.consumer.mapper.ConsumerMapper;
import com.alipapa.smp.consumer.pojo.Consumer;
import org.springframework.beans.factory.annotation.Autowired;

public class ConsumerService {

    @Autowired
    private ConsumerMapper consumerMapper;

    /**
     * @param id
     * @return
     */
    public Consumer getConsumerById(Long id) {
        return consumerMapper.selectByPrimaryKey(id);
    }

}
