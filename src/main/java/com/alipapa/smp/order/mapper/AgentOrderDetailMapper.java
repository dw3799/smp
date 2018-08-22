package com.alipapa.smp.order.mapper;

import com.alipapa.smp.order.pojo.AgentOrderDetail;
import com.alipapa.smp.order.pojo.AgentOrderDetailExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AgentOrderDetailMapper {
    long countByExample(AgentOrderDetailExample example);

    int deleteByExample(AgentOrderDetailExample example);

    int deleteByPrimaryKey(Long id);

    int insert(AgentOrderDetail record);

    int insertSelective(AgentOrderDetail record);

    List<AgentOrderDetail> selectByExample(AgentOrderDetailExample example);

    AgentOrderDetail selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") AgentOrderDetail record, @Param("example") AgentOrderDetailExample example);

    int updateByExample(@Param("record") AgentOrderDetail record, @Param("example") AgentOrderDetailExample example);

    int updateByPrimaryKeySelective(AgentOrderDetail record);

    int updateByPrimaryKey(AgentOrderDetail record);
}