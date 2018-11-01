package com.alipapa.smp.consumer.service;

import com.alipapa.smp.consumer.mapper.ConsumerMapper;
import com.alipapa.smp.consumer.mapper.UserConsumerRelationMapper;
import com.alipapa.smp.consumer.pojo.UserConsumerRelation;
import com.alipapa.smp.consumer.pojo.UserConsumerRelationExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Date;
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
     * 查询客户的所有跟进人
     *
     * @param consumerId
     * @return
     */
    public long countAllValidRelationByConsumerId(Long consumerId) {
        if (consumerId == null) {
            return 0;
        }

        UserConsumerRelationExample example = new UserConsumerRelationExample();
        UserConsumerRelationExample.Criteria criteria = example.createCriteria();
        criteria.andConsumerIdEqualTo(consumerId);
        criteria.andIsDelEqualTo(0);

        return userConsumerRelationMapper.countByExample(example);
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
     * 更新跟进时间，及下单状态
     *
     * @return
     */
    public boolean updateHasOrder(Long consumerId, Long userId) {
        UserConsumerRelation userConsumerRelation = this.getRelationByConsumerIsDel(consumerId, userId, null);
        userConsumerRelation.setFollowTime(new Date());
        userConsumerRelation.setHasOrder(1);
        this.updateUserConsumerRelation(userConsumerRelation);
        return true;
    }


    /**
     * 更新跟进时间，及下单状态
     *
     * @return
     */
    public boolean updateDealOrder(Long consumerId, Long userId) {
        UserConsumerRelation userConsumerRelation = this.getRelationByConsumerIsDel(consumerId, userId, null);
        userConsumerRelation.setFollowTime(new Date());
        userConsumerRelation.setDealOrder(1);
        this.updateUserConsumerRelation(userConsumerRelation);
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


    /**
     * 按上次跟进时间查询未下单跟进关系
     *
     * @param followTime
     * @return
     */
    public List<UserConsumerRelation> listNewValidRelationByFollowTime(Date followTime) {
        if (followTime == null) {
            return null;
        }
        UserConsumerRelationExample example = new UserConsumerRelationExample();
        UserConsumerRelationExample.Criteria criteria = example.createCriteria();
        criteria.andFollowTimeIsNotNull();
        criteria.andFollowTimeLessThan(followTime);
        criteria.andIsDelEqualTo(0);
        criteria.andHasOrderEqualTo(0);
        return userConsumerRelationMapper.selectByExample(example);
    }


    /**
     * 按上次跟进时间查询未下单跟进关系
     *
     * @param followTime
     * @return
     */
    public List<UserConsumerRelation> listDealValidRelationByFollowTime(Date followTime) {
        if (followTime == null) {
            return null;
        }
        UserConsumerRelationExample example = new UserConsumerRelationExample();
        UserConsumerRelationExample.Criteria criteria = example.createCriteria();
        criteria.andFollowTimeIsNotNull();
        criteria.andFollowTimeLessThan(followTime);
        criteria.andIsDelEqualTo(0);
        criteria.andHasOrderEqualTo(1);
        return userConsumerRelationMapper.selectByExample(example);
    }
}
