package com.alipapa.smp.consumer.mapper;

import com.alipapa.smp.consumer.pojo.ConsumerFollowRecord;
import com.alipapa.smp.consumer.pojo.ConsumerFollowRecordExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ConsumerFollowRecordMapper {
    long countByExample(ConsumerFollowRecordExample example);

    int deleteByExample(ConsumerFollowRecordExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ConsumerFollowRecord record);

    int insertSelective(ConsumerFollowRecord record);

    List<ConsumerFollowRecord> selectByExample(ConsumerFollowRecordExample example);

    ConsumerFollowRecord selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ConsumerFollowRecord record, @Param("example") ConsumerFollowRecordExample example);

    int updateByExample(@Param("record") ConsumerFollowRecord record, @Param("example") ConsumerFollowRecordExample example);

    int updateByPrimaryKeySelective(ConsumerFollowRecord record);

    int updateByPrimaryKey(ConsumerFollowRecord record);

    List<ConsumerFollowRecord> listGroupConsumerFollowRecord(@Param("consumerId") Long consumerId, @Param("groupId") Long groupId);


}