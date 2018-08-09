package com.alipapa.smp.product.service;

import com.alipapa.smp.product.mapper.ProductMapper;
import com.alipapa.smp.product.pojo.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    private static Logger logger = LoggerFactory.getLogger(ProductService.class);

    @Autowired
    private ProductMapper productMapper;


    /**
     * @param productId
     * @return
     */
    public Product getProductById(Long productId) {
        return productMapper.selectByPrimaryKey(productId);
    }

}
