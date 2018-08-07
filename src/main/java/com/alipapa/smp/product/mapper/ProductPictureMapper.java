package com.alipapa.smp.product.mapper;

import com.alipapa.smp.product.pojo.ProductPicture;
import com.alipapa.smp.product.pojo.ProductPictureExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ProductPictureMapper {
    long countByExample(ProductPictureExample example);

    int deleteByExample(ProductPictureExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ProductPicture record);

    int insertSelective(ProductPicture record);

    List<ProductPicture> selectByExample(ProductPictureExample example);

    ProductPicture selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ProductPicture record, @Param("example") ProductPictureExample example);

    int updateByExample(@Param("record") ProductPicture record, @Param("example") ProductPictureExample example);

    int updateByPrimaryKeySelective(ProductPicture record);

    int updateByPrimaryKey(ProductPicture record);
}