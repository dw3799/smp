package com.alipapa.smp.order.mapper;

import com.alipapa.smp.order.pojo.SelfOrderDetail;
import com.alipapa.smp.order.pojo.SelfOrderDetailExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SelfOrderDetailMapper {
    long countByExample(SelfOrderDetailExample example);

    int deleteByExample(SelfOrderDetailExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SelfOrderDetail record);

    int insertSelective(SelfOrderDetail record);

    List<SelfOrderDetail> selectByExample(SelfOrderDetailExample example);

    SelfOrderDetail selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SelfOrderDetail record, @Param("example") SelfOrderDetailExample example);

    int updateByExample(@Param("record") SelfOrderDetail record, @Param("example") SelfOrderDetailExample example);

    int updateByPrimaryKeySelective(SelfOrderDetail record);

    int updateByPrimaryKey(SelfOrderDetail record);
}