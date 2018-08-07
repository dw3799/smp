package com.alipapa.smp.order.mapper;

import com.alipapa.smp.order.pojo.ConsumerTailPay;
import com.alipapa.smp.order.pojo.ConsumerTailPayExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ConsumerTailPayMapper {
    long countByExample(ConsumerTailPayExample example);

    int deleteByExample(ConsumerTailPayExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ConsumerTailPay record);

    int insertSelective(ConsumerTailPay record);

    List<ConsumerTailPay> selectByExample(ConsumerTailPayExample example);

    ConsumerTailPay selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ConsumerTailPay record, @Param("example") ConsumerTailPayExample example);

    int updateByExample(@Param("record") ConsumerTailPay record, @Param("example") ConsumerTailPayExample example);

    int updateByPrimaryKeySelective(ConsumerTailPay record);

    int updateByPrimaryKey(ConsumerTailPay record);
}