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

import java.util.Date;
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
     * @param productCategory
     * @return
     */
    public boolean saveProductCategory(ProductCategory productCategory) {
        productCategoryMapper.insert(productCategory);
        return true;
    }


    /**
     * @param productCategoryName
     * @return
     */
    public List<ProductCategory> listProductCategoryByName(String productCategoryName) {
        ProductCategoryExample example = new ProductCategoryExample();
        ProductCategoryExample.Criteria criteria = example.createCriteria();
        if (StringUtil.isNotEmptyString(productCategoryName)) {
            criteria.andCategoryCodeLike("%" + productCategoryName + "%");
        }
        List<ProductCategory> productCategoryList = productCategoryMapper.selectByExample(example);
        if (!CollectionUtils.isEmpty(productCategoryList)) {
            return productCategoryList;
        }
        return null;
    }


    /**
     * @param productCategoryName
     * @return
     */
    public ProductCategory getProductCategoryByName(String productCategoryName) {
        if (StringUtil.isEmptyString(productCategoryName)) {
            return null;
        }
        ProductCategoryExample example = new ProductCategoryExample();
        ProductCategoryExample.Criteria criteria = example.createCriteria();
        criteria.andCategoryNameEqualTo(productCategoryName);

        List<ProductCategory> productCategoryList = productCategoryMapper.selectByExample(example);
        if (!CollectionUtils.isEmpty(productCategoryList)) {
            return productCategoryList.get(0);
        }
        return null;
    }


    public ProductCategory getCategory(Long categoryId, String categoryName) throws Exception {
        ProductCategory productCategory = null;
        if (categoryId != null) {
            productCategory = this.getProductCategoryById(categoryId);
            categoryName = productCategory.getCategoryName();
        } else {
            productCategory = this.getProductCategoryByName(categoryName);
            if (productCategory == null) { //添加分类
                productCategory = new ProductCategory();
                productCategory.setCategoryName(categoryName);
                productCategory.setCategoryCode(categoryName);
                productCategory.setCreatedTime(new Date());
                productCategory.setUpdatedTime(new Date());
                this.saveProductCategory(productCategory);
                Thread.sleep(1000);
                productCategory = this.getProductCategoryByName(categoryName);
            }
        }
        return productCategory;
    }

}
