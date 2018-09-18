package com.alipapa.smp.order.mapper;

import com.alipapa.smp.order.pojo.Order;
import com.alipapa.smp.order.pojo.SubOrder;
import com.alipapa.smp.order.pojo.SubOrderExample;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface SubOrderMapper {
    long countByExample(SubOrderExample example);

    int deleteByExample(SubOrderExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SubOrder record);

    int insertSelective(SubOrder record);

    List<SubOrder> selectByExample(SubOrderExample example);

    SubOrder selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SubOrder record, @Param("example") SubOrderExample example);

    int updateByExample(@Param("record") SubOrder record, @Param("example") SubOrderExample example);

    int updateByPrimaryKeySelective(SubOrder record);

    int updateByPrimaryKey(SubOrder record);

    List<SubOrder> listMySubOrderByParam(Map<String, Object> params);

    long listMySubOrderByParamCount(Map<String, Object> params);


    List<SubOrder> listGroupSubOrderByParam(Map<String, Object> params);

    long listGroupSubOrderByParamCount(Map<String, Object> params);
    
}