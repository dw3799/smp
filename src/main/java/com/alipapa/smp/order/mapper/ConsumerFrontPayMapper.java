package com.alipapa.smp.order.mapper;

import com.alipapa.smp.order.pojo.ConsumerFrontPay;
import com.alipapa.smp.order.pojo.ConsumerFrontPayExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ConsumerFrontPayMapper {
    long countByExample(ConsumerFrontPayExample example);

    int deleteByExample(ConsumerFrontPayExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ConsumerFrontPay record);

    int insertSelective(ConsumerFrontPay record);

    List<ConsumerFrontPay> selectByExample(ConsumerFrontPayExample example);

    ConsumerFrontPay selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ConsumerFrontPay record, @Param("example") ConsumerFrontPayExample example);

    int updateByExample(@Param("record") ConsumerFrontPay record, @Param("example") ConsumerFrontPayExample example);

    int updateByPrimaryKeySelective(ConsumerFrontPay record);

    int updateByPrimaryKey(ConsumerFrontPay record);
}