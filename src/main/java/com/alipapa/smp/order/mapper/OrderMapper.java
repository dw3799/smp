package com.alipapa.smp.order.mapper;

import com.alipapa.smp.consumer.pojo.Consumer;
import com.alipapa.smp.order.pojo.Order;
import com.alipapa.smp.order.pojo.OrderExample;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface OrderMapper {
    long countByExample(OrderExample example);

    int deleteByExample(OrderExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Order record);

    int insertSelective(Order record);

    List<Order> selectByExample(OrderExample example);

    Order selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Order record, @Param("example") OrderExample example);

    int updateByExample(@Param("record") Order record, @Param("example") OrderExample example);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);

    List<Order> listOrderByParam(Map<String, Object> params);

    long listOrderByParamCount(Map<String, Object> params);

    List<Order> listApproveOrderByParam(Map<String, Object> params);

    long listApproveOrderByParamCount(Map<String, Object> params);
}