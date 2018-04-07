package com.alipapa.smp.consumer.mapper;

import com.alipapa.smp.consumer.pojo.UserConsumerRelation;
import com.alipapa.smp.consumer.pojo.UserConsumerRelationExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserConsumerRelationMapper {
    long countByExample(UserConsumerRelationExample example);

    int deleteByExample(UserConsumerRelationExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UserConsumerRelation record);

    int insertSelective(UserConsumerRelation record);

    List<UserConsumerRelation> selectByExample(UserConsumerRelationExample example);

    UserConsumerRelation selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") UserConsumerRelation record, @Param("example") UserConsumerRelationExample example);

    int updateByExample(@Param("record") UserConsumerRelation record, @Param("example") UserConsumerRelationExample example);

    int updateByPrimaryKeySelective(UserConsumerRelation record);

    int updateByPrimaryKey(UserConsumerRelation record);
}