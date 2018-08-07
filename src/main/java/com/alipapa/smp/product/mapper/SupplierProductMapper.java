package com.alipapa.smp.product.mapper;

import com.alipapa.smp.product.pojo.SupplierProduct;
import com.alipapa.smp.product.pojo.SupplierProductExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SupplierProductMapper {
    long countByExample(SupplierProductExample example);

    int deleteByExample(SupplierProductExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SupplierProduct record);

    int insertSelective(SupplierProduct record);

    List<SupplierProduct> selectByExample(SupplierProductExample example);

    SupplierProduct selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SupplierProduct record, @Param("example") SupplierProductExample example);

    int updateByExample(@Param("record") SupplierProduct record, @Param("example") SupplierProductExample example);

    int updateByPrimaryKeySelective(SupplierProduct record);

    int updateByPrimaryKey(SupplierProduct record);
}