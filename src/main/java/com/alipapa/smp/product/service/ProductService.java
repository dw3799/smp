package com.alipapa.smp.product.service;

import com.alipapa.smp.common.Constant;
import com.alipapa.smp.order.pojo.SubOrder;
import com.alipapa.smp.order.service.SubOrderService;
import com.alipapa.smp.product.mapper.ProductMapper;
import com.alipapa.smp.product.pojo.Product;
import com.alipapa.smp.product.pojo.ProductExt;
import com.alipapa.smp.product.pojo.ProductPicture;
import com.alipapa.smp.product.vo.ProductVo;
import com.alipapa.smp.utils.PriceUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProductService {
    private static Logger logger = LoggerFactory.getLogger(ProductService.class);

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private ProductPictureService productPicturesService;

    @Autowired
    private SubOrderService subOrderService;


    /**
     * @param productId
     * @return
     */
    public Product getProductById(Long productId) {
        return productMapper.selectByPrimaryKey(productId);
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

            List<ProductPicture> productPictureList = productPicturesService.listProductPictureByProductId(product.getId());
            if (!CollectionUtils.isEmpty(productPictureList)) {
                ProductPicture productPicture = productPictureList.get(0);
                productVo.setPic(productPicture.getPicName());
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

            List<ProductPicture> productPictureList = productPicturesService.listProductPictureByProductId(product.getId());
            if (!CollectionUtils.isEmpty(productPictureList)) {
                ProductPicture productPicture = productPictureList.get(0);
                productVo.setPic(productPicture.getPicName());
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
