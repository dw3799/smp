package com.alipapa.smp.product.mapper;

import com.alipapa.smp.order.pojo.Order;
import com.alipapa.smp.product.pojo.Supplier;
import com.alipapa.smp.product.pojo.SupplierExample;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface SupplierMapper {
    long countByExample(SupplierExample example);

    int deleteByExample(SupplierExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Supplier record);

    int insertSelective(Supplier record);

    List<Supplier> selectByExample(SupplierExample example);

    Supplier selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Supplier record, @Param("example") SupplierExample example);

    int updateByExample(@Param("record") Supplier record, @Param("example") SupplierExample example);

    int updateByPrimaryKeySelective(Supplier record);

    int updateByPrimaryKey(Supplier record);

    List<Supplier> listSupplierByParams(Map<String, Object> params);

    long listSupplierByParamsCount(Map<String, Object> params);
}