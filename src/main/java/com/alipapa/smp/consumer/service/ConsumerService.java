package com.alipapa.smp.consumer.service;

import com.alipapa.smp.consumer.mapper.ConsumerMapper;
import com.alipapa.smp.consumer.pojo.Consumer;
import com.alipapa.smp.consumer.pojo.ConsumerExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
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

    /**
     * @param consumer
     * @return
     */
    public boolean addConsumer(Consumer consumer) {
        consumerMapper.insertSelective(consumer);
        return true;
    }


    /**
     * @param name,email
     * @return
     */
    public List<Consumer> listConsumerByNameAndEmail(String name, String email) {
        ConsumerExample example = new ConsumerExample();
        ConsumerExample.Criteria criteria = example.createCriteria();
        criteria.andNameEqualTo(name);
        criteria.andEmailEqualTo(email);
        return consumerMapper.selectByExample(example);
    }


    /**
     * 获取最新的用户ID
     *
     * @return
     */
    public Long getLatestConsumerId() {
        return consumerMapper.selectMaxId();
    }

}
