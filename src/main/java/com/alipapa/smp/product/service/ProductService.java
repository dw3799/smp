package com.alipapa.smp.product.service;

import com.alipapa.smp.common.Constant;
import com.alipapa.smp.common.request.UserInfo;
import com.alipapa.smp.order.pojo.SubOrder;
import com.alipapa.smp.order.service.SubOrderService;
import com.alipapa.smp.product.mapper.ProductMapper;
import com.alipapa.smp.product.pojo.*;
import com.alipapa.smp.product.vo.ProductVo;
import com.alipapa.smp.utils.PriceUtil;
import com.alipapa.smp.utils.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.io.File;
import java.util.*;

@Service
public class ProductService {
    private static Logger logger = LoggerFactory.getLogger(ProductService.class);

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private ProductPictureService productPictureService;

    @Autowired
    private SubOrderService subOrderService;

    @Autowired
    private ProductCategoryService productCategoryService;


    @Autowired
    private PictureService pictureService;

    /**
     * @param productId
     * @return
     */
    public Product getProductById(Long productId) {
        return productMapper.selectByPrimaryKey(productId);
    }


    /**
     * @param categoryName
     * @param categoryId
     * @param productName
     * @param picNos
     * @return
     */
    @Transactional
    public boolean saveProduct(String categoryName, Long categoryId, String productName, String picNos, UserInfo userInfo) throws Exception {
        ProductCategory productCategory = null;
        if (categoryId != null) {
            productCategory = productCategoryService.getProductCategoryById(categoryId);
            categoryName = productCategory.getCategoryName();
        } else {
            productCategory = productCategoryService.getProductCategoryByName(categoryName);
            if (productCategory == null) { //添加分类
                productCategory = new ProductCategory();
                productCategory.setCategoryName(categoryName);
                productCategory.setCategoryCode(categoryName);
                productCategory.setCreatedTime(new Date());
                productCategory.setUpdatedTime(new Date());
                productCategoryService.saveProductCategory(productCategory);
                Thread.sleep(1000);
                productCategory = productCategoryService.getProductCategoryByName(categoryName);
            }
        }

        Product product = new Product();
        product.setCategoryName(categoryName);
        product.setCreatedTime(new Date());
        product.setOpUserName(userInfo.getUserName());
        product.setOpUserNo(userInfo.getUserNo());
        product.setOpUserRole(userInfo.getRoleName());
        product.setProductCategoryId(productCategory.getId());
        product.setProductName(productName);
        product.setUpdatedTime(new Date());
        productMapper.insert(product);

        if (StringUtil.isNotEmptyString(picNos)) {
            Thread.sleep(1000);
            product = this.getProductByName(productName);

            String[] picArray = picNos.split(";");
            for (String picNo : picArray) {
                Picture picture = pictureService.getPictureByPicNo(picNo);
                if (picture == null) {
                    logger.info("getPictureByPicNo is null,picNo=" + picNo);
                    continue;
                }

                ProductPicture productPicture = new ProductPicture();
                productPicture.setCreatedTime(new Date());
                productPicture.setFileType(0);
                productPicture.setPicId(String.valueOf(picture.getId()));
                productPicture.setPicNo(picNo);
                productPicture.setProductId(product.getId());
                productPicture.setUpdatedTime(new Date());
                productPictureService.save(productPicture);
            }
        }

        return true;
    }


    /**
     * 图片删除
     *
     * @param picNo
     * @return
     */
    @Transactional
    public boolean delPicture(String picNo) throws Exception {
        Picture picture = pictureService.getPictureByPicNo(picNo);
        if (picture == null) {
            logger.info("getPictureByPicNo is null,picNo=" + picNo);
            return false;
        }

        File file = new File(picture.getPath());
        if (file.exists()) {
            file.delete();
        }

        List<ProductPicture> productPictureList = productPictureService.listProductPictureByPicNo(picNo);
        if (!CollectionUtils.isEmpty(productPictureList)) {
            for (ProductPicture productPicture : productPictureList) {
                productPictureService.delete(productPicture);
            }
        }
        pictureService.delete(picture);
        return true;
    }

    /**
     * @param productCategoryId
     * @param productName
     * @param start
     * @param size
     * @return
     */
    public List<ProductVo> listProductByParam(Long productCategoryId, String productName, Integer start, Integer size) {
        Map<String, Object> params = new HashMap<>();
        params.put("productCategoryId", productCategoryId);
        params.put("productName", productName);

        Long totalCount = productMapper.listProductByParamCount(params);

        if (totalCount <= 0) {
            return null;
        }
        params.put("start", start);
        params.put("size", size);

        List<Product> products = productMapper.listProductByParam(params);

        List<ProductVo> productVoList = this.convertProductVo(products, totalCount);

        return productVoList;
    }


    /**
     * @param productName
     * @return
     */
    public Product getProductByName(String productName) {
        if (StringUtil.isEmptyString(productName)) {
            return null;
        }
        ProductExample example = new ProductExample();
        ProductExample.Criteria criteria = example.createCriteria();
        criteria.andProductNameEqualTo(productName);

        List<Product> productList = productMapper.selectByExample(example);
        if (!CollectionUtils.isEmpty(productList)) {
            return productList.get(0);
        }
        return null;
    }


    /**
     * @param productCategoryId
     * @param productName
     * @param saleNo
     * @param start
     * @param size
     * @return
     */
    public List<ProductVo> listProductBySaleNo(Long productCategoryId, String productName, String saleNo, Integer start, Integer size) {
        Map<String, Object> params = new HashMap<>();
        params.put("productCategoryId", productCategoryId);
        params.put("productName", productName);
        params.put("saleNo", saleNo);

        Long totalCount = productMapper.listProductBySaleNoCount(params);

        if (totalCount <= 0) {
            return null;
        }
        params.put("start", start);
        params.put("size", size);

        List<ProductExt> products = productMapper.listProductBySaleNo(params);

        List<ProductVo> productVoList = this.convertProductExtVo(products, totalCount);
        return productVoList;
    }


    /**
     * 数据封装
     *
     * @param products
     * @param totalCount
     * @return
     */
    private List<ProductVo> convertProductVo(List<Product> products, Long totalCount) {
        if (CollectionUtils.isEmpty(products)) {
            return null;
        }

        List<ProductVo> productVoList = new ArrayList<>();
        for (Product product : products) {
            ProductVo productVo = new ProductVo();
            productVo.setProductId(product.getId());
            productVo.setCategoryName(product.getCategoryName());
            productVo.setProductName(product.getProductName());
            productVo.setTotalCount(totalCount);
            productVo.setSaleNo(null);

            List<ProductPicture> productPictureList = productPictureService.listProductPictureByProductId(product.getId());
            if (!CollectionUtils.isEmpty(productPictureList)) {
                ProductPicture productPicture = productPictureList.get(0);
                productVo.setPic(productPicture.getPicNo());
            }
            SubOrder subOrder = subOrderService.getLatestSubOrderByProductId(product.getId());
            if (subOrder != null) {
                productVo.setNewFactoryAmount(PriceUtil.convertToYuanStr(subOrder.getFactoryAmount()) + Constant.YMB);
            }

            SubOrder mostSubOrder = subOrderService.getMostSubOrderByProductId(product.getId());
            if (mostSubOrder != null) {
                productVo.setMostFactoryAmount(PriceUtil.convertToYuanStr(mostSubOrder.getFactoryAmount()) + Constant.YMB);
            }
        }
        return productVoList;
    }


    /**
     * 数据封装
     *
     * @param products
     * @param totalCount
     * @return
     */
    private List<ProductVo> convertProductExtVo(List<ProductExt> products, Long totalCount) {
        if (CollectionUtils.isEmpty(products)) {
            return null;
        }

        List<ProductVo> productVoList = new ArrayList<>();
        for (ProductExt product : products) {
            ProductVo productVo = new ProductVo();
            productVo.setProductId(product.getId());
            productVo.setCategoryName(product.getCategoryName());
            productVo.setProductName(product.getProductName());
            productVo.setTotalCount(totalCount);
            productVo.setSaleNo(product.getSaleNo());

            List<ProductPicture> productPictureList = productPictureService.listProductPictureByProductId(product.getId());
            if (!CollectionUtils.isEmpty(productPictureList)) {
                ProductPicture productPicture = productPictureList.get(0);
                productVo.setPic(productPicture.getPicNo());
            }
            SubOrder subOrder = subOrderService.getLatestSubOrderByProductId(product.getId());
            if (subOrder != null) {
                productVo.setNewFactoryAmount(PriceUtil.convertToYuanStr(subOrder.getFactoryAmount()) + Constant.YMB);
            }

            SubOrder mostSubOrder = subOrderService.getMostSubOrderByProductId(product.getId());
            if (mostSubOrder != null) {
                productVo.setMostFactoryAmount(PriceUtil.convertToYuanStr(mostSubOrder.getFactoryAmount()) + Constant.YMB);
            }
        }
        return productVoList;
    }


}
