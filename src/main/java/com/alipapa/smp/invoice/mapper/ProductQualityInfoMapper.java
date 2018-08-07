package com.alipapa.smp.invoice.mapper;

import com.alipapa.smp.invoice.pojo.ProductQualityInfo;
import com.alipapa.smp.invoice.pojo.ProductQualityInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ProductQualityInfoMapper {
    long countByExample(ProductQualityInfoExample example);

    int deleteByExample(ProductQualityInfoExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ProductQualityInfo record);

    int insertSelective(ProductQualityInfo record);

    List<ProductQualityInfo> selectByExample(ProductQualityInfoExample example);

    ProductQualityInfo selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ProductQualityInfo record, @Param("example") ProductQualityInfoExample example);

    int updateByExample(@Param("record") ProductQualityInfo record, @Param("example") ProductQualityInfoExample example);

    int updateByPrimaryKeySelective(ProductQualityInfo record);

    int updateByPrimaryKey(ProductQualityInfo record);
}