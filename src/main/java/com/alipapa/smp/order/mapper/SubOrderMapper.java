package com.alipapa.smp.order.mapper;

import com.alipapa.smp.order.pojo.SubOrder;
import com.alipapa.smp.order.pojo.SubOrderExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

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
}