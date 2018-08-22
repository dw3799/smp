package com.alipapa.smp.product.mapper;

import com.alipapa.smp.product.pojo.Product;
import com.alipapa.smp.product.pojo.ProductExample;

import java.util.List;
import java.util.Map;

import com.alipapa.smp.product.pojo.ProductExt;
import org.apache.ibatis.annotations.Param;

public interface ProductMapper {
    long countByExample(ProductExample example);

    int deleteByExample(ProductExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Product record);

    int insertSelective(Product record);

    List<Product> selectByExample(ProductExample example);

    Product selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Product record, @Param("example") ProductExample example);

    int updateByExample(@Param("record") Product record, @Param("example") ProductExample example);

    int updateByPrimaryKeySelective(Product record);

    int updateByPrimaryKey(Product record);

    List<Product> listProductByParam(Map<String, Object> params);

    long listProductByParamCount(Map<String, Object> params);

    List<ProductExt> listProductBySaleNo(Map<String, Object> params);

    long listProductBySaleNoCount(Map<String, Object> params);


}