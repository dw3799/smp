package com.alipapa.smp.consumer.service;

import com.alipapa.smp.consumer.mapper.ConsumerFollowRecordMapper;
import com.alipapa.smp.consumer.mapper.ConsumerMapper;
import com.alipapa.smp.consumer.pojo.ConsumerFollowRecord;
import com.alipapa.smp.consumer.pojo.ConsumerFollowRecordExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsumerFollowRecordService {

    @Autowired
    private ConsumerFollowRecordMapper consumerFollowRecordMapper;

    /**
     * @param consumerFollowRecord
     * @return
     */
    public boolean insert(ConsumerFollowRecord consumerFollowRecord) {
        consumerFollowRecordMapper.insert(consumerFollowRecord);
        return true;
    }

    /**
     * 跟进记录查询
     *
     * @param consumerId
     * @param userId
     * @return
     */
    public List<ConsumerFollowRecord> listConsumerFollowRecord(Long consumerId, Long userId) {
        ConsumerFollowRecordExample example = new ConsumerFollowRecordExample();
        ConsumerFollowRecordExample.Criteria criteria = example.createCriteria();

        criteria.andConsumerIdEqualTo(consumerId);
        if (userId != null) {
            criteria.andUserIdEqualTo(userId);
        }
        example.setOrderByClause("created_time DESC");
        return consumerFollowRecordMapper.selectByExample(example);
    }


    /**
     * 本组跟进记录查询
     *
     * @param consumerId
     * @param userId
     * @param groupId
     * @return
     */
    public List<ConsumerFollowRecord> listGroupConsumerFollowRecord(Long consumerId, Long userId, Long groupId) {
        return consumerFollowRecordMapper.listGroupConsumerFollowRecord(consumerId, groupId);
    }


}
