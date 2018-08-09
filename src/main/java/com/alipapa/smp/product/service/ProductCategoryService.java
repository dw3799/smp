package com.alipapa.smp.product.service;

import com.alipapa.smp.product.mapper.ProductCategoryMapper;
import com.alipapa.smp.product.pojo.ProductCategory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductCategoryService {
    private static Logger logger = LoggerFactory.getLogger(ProductCategoryService.class);


    @Autowired
    private ProductCategoryMapper productCategoryMapper;


    /**
     * @param productCategoryId
     * @return
     */
    public ProductCategory getProductCategoryById(Long productCategoryId) {
        return productCategoryMapper.selectByPrimaryKey(productCategoryId);
    }


}
