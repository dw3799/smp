package com.alipapa.smp.order.mapper;

import com.alipapa.smp.order.pojo.OrderWorkFlow;
import com.alipapa.smp.order.pojo.OrderWorkFlowExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OrderWorkFlowMapper {
    long countByExample(OrderWorkFlowExample example);

    int deleteByExample(OrderWorkFlowExample example);

    int deleteByPrimaryKey(Long id);

    int insert(OrderWorkFlow record);

    int insertSelective(OrderWorkFlow record);

    List<OrderWorkFlow> selectByExample(OrderWorkFlowExample example);

    OrderWorkFlow selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") OrderWorkFlow record, @Param("example") OrderWorkFlowExample example);

    int updateByExample(@Param("record") OrderWorkFlow record, @Param("example") OrderWorkFlowExample example);

    int updateByPrimaryKeySelective(OrderWorkFlow record);

    int updateByPrimaryKey(OrderWorkFlow record);
}