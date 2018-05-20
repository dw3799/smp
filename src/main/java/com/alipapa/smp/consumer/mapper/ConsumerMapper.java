package com.alipapa.smp.consumer.mapper;

import com.alipapa.smp.consumer.pojo.Consumer;
import com.alipapa.smp.consumer.pojo.ConsumerExample;
import com.alipapa.smp.consumer.pojo.ConsumerExt;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ConsumerMapper {
    long countByExample(ConsumerExample example);

    int deleteByExample(ConsumerExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Consumer record);

    int insertSelective(Consumer record);

    List<Consumer> selectByExample(ConsumerExample example);

    Consumer selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Consumer record, @Param("example") ConsumerExample example);

    int updateByExample(@Param("record") Consumer record, @Param("example") ConsumerExample example);

    int updateByPrimaryKeySelective(Consumer record);

    int updateByPrimaryKey(Consumer record);

    Long selectMaxId();

    List<ConsumerExt> findSalerConsumerByParam(Map<String, Object> params);

    long findSalerConsumerByParamCount(Map<String, Object> params);

    List<Consumer> findConsumerByParam(Map<String, Object> params);

    long findConsumerByParamCount(Map<String, Object> params);

    List<Consumer> findSalerManagerConsumerByParam(Map<String, Object> params);

    long findSalerManagerConsumerByParamCount(Map<String, Object> params);


}