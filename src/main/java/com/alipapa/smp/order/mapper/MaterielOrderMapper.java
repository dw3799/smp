package com.alipapa.smp.order.mapper;

import com.alipapa.smp.order.pojo.MaterielOrder;
import com.alipapa.smp.order.pojo.MaterielOrderExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MaterielOrderMapper {
    long countByExample(MaterielOrderExample example);

    int deleteByExample(MaterielOrderExample example);

    int deleteByPrimaryKey(Long id);

    int insert(MaterielOrder record);

    int insertSelective(MaterielOrder record);

    List<MaterielOrder> selectByExample(MaterielOrderExample example);

    MaterielOrder selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") MaterielOrder record, @Param("example") MaterielOrderExample example);

    int updateByExample(@Param("record") MaterielOrder record, @Param("example") MaterielOrderExample example);

    int updateByPrimaryKeySelective(MaterielOrder record);

    int updateByPrimaryKey(MaterielOrder record);
}