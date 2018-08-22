package com.alipapa.smp.product.service;

import com.alipapa.smp.product.mapper.ProductCategoryMapper;
import com.alipapa.smp.product.pojo.ProductCategory;
import com.alipapa.smp.product.pojo.ProductCategoryExample;
import com.alipapa.smp.utils.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

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


    /**
     * @param productCategoryName
     * @return
     */
    public List<ProductCategory> listProductCategoryByName(String productCategoryName) {
        ProductCategoryExample example = new ProductCategoryExample();
        ProductCategoryExample.Criteria criteria = example.createCriteria();
        if (StringUtil.isEmptyString(productCategoryName)) {
            criteria.andCategoryCodeLike("%" + productCategoryName + "%");
        }
        List<ProductCategory> productCategoryList = productCategoryMapper.selectByExample(example);
        if (!CollectionUtils.isEmpty(productCategoryList)) {
            return productCategoryList;
        }
        return null;
    }


}
