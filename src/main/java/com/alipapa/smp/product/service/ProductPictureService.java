package com.alipapa.smp.product.service;

import com.alipapa.smp.product.mapper.ProductPictureMapper;
import com.alipapa.smp.product.pojo.ProductPicture;
import com.alipapa.smp.product.pojo.ProductPictureExample;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class ProductPictureService {
    private static Logger logger = LoggerFactory.getLogger(ProductPictureService.class);


    @Autowired
    private ProductPictureMapper productPictureMapper;


    /**
     * @param productPictureId
     * @return
     */
    public ProductPicture getProductPictureById(Long productPictureId) {
        return productPictureMapper.selectByPrimaryKey(productPictureId);
    }


    /**
     * 根据产品ID获取图片
     *
     * @param productId
     * @return
     */
    public List<ProductPicture> listProductPictureByProductId(Long productId) {
        if (productId == null) {
            return null;
        }

        logger.info("listProductPictureByProductId,productId=" + productId);
        ProductPictureExample example = new ProductPictureExample();
        ProductPictureExample.Criteria criteria = example.createCriteria();
        criteria.andProductIdEqualTo(productId);
        List<ProductPicture> userList = productPictureMapper.selectByExample(example);
        if (!CollectionUtils.isEmpty(userList)) {
            return userList;
        }
        return null;
    }

}
