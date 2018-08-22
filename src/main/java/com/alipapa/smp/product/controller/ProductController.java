package com.alipapa.smp.product.controller;


import com.alipapa.smp.common.enums.OrderStatusEnum;
import com.alipapa.smp.common.request.UserInfo;
import com.alipapa.smp.common.request.UserStatus;
import com.alipapa.smp.order.vo.OrderVo;
import com.alipapa.smp.product.pojo.ProductCategory;
import com.alipapa.smp.product.service.ProductCategoryService;
import com.alipapa.smp.product.vo.ProductCategoryVo;
import com.alipapa.smp.product.vo.ProductVo;
import com.alipapa.smp.utils.StringUtil;
import com.alipapa.smp.utils.WebApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static com.alipapa.smp.utils.WebApiResponse.error;

/**
 * 产品管理接口
 *
 * @author liuwei
 * @version 2.0
 * 2018-08-21
 */
@RestController
@CrossOrigin
@RequestMapping("/api/product")
public class ProductController {
    private static Logger logger = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductCategoryService productCategoryService;

    /**
     * 产品类别查询下拉列表
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/listProductCategory", method = RequestMethod.GET)
    public WebApiResponse<List<ProductCategoryVo>> listProductCategory(@RequestParam(value = "categoryName", required = false) String categoryName) {
        if (StringUtil.isEmptyString(categoryName)) {
            return WebApiResponse.error("参数有误！");
        }
        try {
            List<ProductCategory> productCategoryList = productCategoryService.listProductCategoryByName(categoryName);

            if (!CollectionUtils.isEmpty(productCategoryList)) {

                List<ProductCategoryVo> productCategoryVoList = new ArrayList<>();
                for (ProductCategory productCategory : productCategoryList) {
                    ProductCategoryVo productCategoryVo = new ProductCategoryVo();
                    productCategoryVo.setProductCategoryId(productCategory.getId());
                    productCategoryVo.setCategoryName(productCategory.getCategoryName());
                    productCategoryVoList.add(productCategoryVo);
                }
                return WebApiResponse.success(productCategoryVoList);
            }
        } catch (Exception ex) {
            logger.error("产品类别查询异常", ex);
            return error("产品类别查询异常");
        }

        return WebApiResponse.success(null);
    }


    /**
     * 添加产品
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/createProduct", method = RequestMethod.POST)
    public WebApiResponse<String> createProduct(@RequestParam(value = "categoryName", required = false) String categoryName) {
        if (StringUtil.isEmptyString(categoryName)) {
            return WebApiResponse.error("参数有误！");
        }
        return WebApiResponse.success("success");
    }


    /**
     * 产品管理列表查询
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/listProduct", method = RequestMethod.GET)
    public WebApiResponse<List<ProductVo>> listProduct(@RequestParam(name = "pageSize", required = false) Integer pageSize,
                                                       @RequestParam(name = "pageNum", required = false) Integer pageNum,
                                                       @RequestParam(name = "productCategoryId", required = false) Long productCategoryId,
                                                       @RequestParam(name = "productName", required = false) String productName,
                                                       @RequestParam(name = "saleNo", required = false) String saleNo) {
        UserInfo userInfo = UserStatus.getUserInfo();

        if (pageSize == null) {
            pageSize = 30;
        }

        if (pageNum == null) {
            pageNum = 1;
        }

        Integer start = (pageNum - 1) * pageSize;
        Integer size = pageSize;


        return WebApiResponse.success(null);
    }


}



