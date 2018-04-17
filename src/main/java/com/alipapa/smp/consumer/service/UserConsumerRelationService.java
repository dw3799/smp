package com.alipapa.smp.consumer.service;

import com.alipapa.smp.consumer.mapper.ConsumerMapper;
import com.alipapa.smp.consumer.mapper.UserConsumerRelationMapper;
import com.alipapa.smp.consumer.pojo.UserConsumerRelation;
import com.alipapa.smp.consumer.pojo.UserConsumerRelationExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class UserConsumerRelationService {

    @Autowired
    private UserConsumerRelationMapper userConsumerRelationMapper;

    /**
     * 查询客户的所有跟进人
     *
     * @param consumerId
     * @return
     */
    public List<UserConsumerRelation> listAllValidRelationByConsumerId(Long consumerId) {
        if (consumerId == null) {
            return null;
        }

        UserConsumerRelationExample example = new UserConsumerRelationExample();
        UserConsumerRelationExample.Criteria criteria = example.createCriteria();
        criteria.andConsumerIdEqualTo(consumerId);
        criteria.andIsDelEqualTo(0);

        return userConsumerRelationMapper.selectByExample(example);
    }

    /**
     * 创建员工客户关联
     *
     * @param userConsumerRelation
     * @return
     */
    public boolean addUserConsumerRelation(UserConsumerRelation userConsumerRelation) {
        userConsumerRelationMapper.insert(userConsumerRelation);
        return true;
    }


    /**
     * 更新员工客户关联
     *
     * @param userConsumerRelation
     * @return
     */
    public boolean updateUserConsumerRelation(UserConsumerRelation userConsumerRelation) {
        userConsumerRelationMapper.updateByPrimaryKey(userConsumerRelation);
        return true;
    }


    /**
     * @param consumerId
     * @return
     */
    public UserConsumerRelation getRelationByConsumerIsDel(Long consumerId, Long userId, Integer isDel) {
        UserConsumerRelationExample example = new UserConsumerRelationExample();
        UserConsumerRelationExample.Criteria criteria = example.createCriteria();
        criteria.andConsumerIdEqualTo(consumerId);
        criteria.andUserIdEqualTo(userId);
        if (isDel != null) {
            criteria.andIsDelEqualTo(isDel);
        }
        List<UserConsumerRelation> userConsumerRelationList = userConsumerRelationMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(userConsumerRelationList)) {
            return null;
        }
        return userConsumerRelationList.get(0);
    }
}
