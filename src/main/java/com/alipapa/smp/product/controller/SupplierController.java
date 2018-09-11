package com.alipapa.smp.product.controller;

import com.alipapa.smp.common.enums.OrderStatusEnum;
import com.alipapa.smp.common.enums.OrderTypeEnum;
import com.alipapa.smp.common.enums.RoleEnum;
import com.alipapa.smp.common.request.UserInfo;
import com.alipapa.smp.common.request.UserStatus;
import com.alipapa.smp.order.vo.OrderVo;
import com.alipapa.smp.product.pojo.*;
import com.alipapa.smp.product.service.ProductPictureService;
import com.alipapa.smp.product.service.ProductService;
import com.alipapa.smp.product.service.SupplierService;
import com.alipapa.smp.product.vo.*;
import com.alipapa.smp.utils.StringUtil;
import com.alipapa.smp.utils.WebApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import static com.alipapa.smp.utils.WebApiResponse.error;
import static com.alipapa.smp.utils.WebApiResponse.success;

/**
 * 供应商管理接口
 *
 * @author liuwei
 * @version 2.0
 * 2018-08-21
 */
@RestController
@CrossOrigin
@RequestMapping("/api/supply")
public class SupplierController {
    private static Logger logger = LoggerFactory.getLogger(ProductController.class);


    @Autowired
    private SupplierService supplierService;


    @Autowired
    private ProductService productService;

    @Autowired
    private ProductPictureService productPictureService;


    /**
     * 供应商列表查询
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/listSupplier", method = RequestMethod.GET)
    public WebApiResponse<List<SupplierVo>> listSupplier(@RequestParam(name = "pageSize", required = false) Integer pageSize,
                                                         @RequestParam(name = "pageNum", required = false) Integer pageNum,
                                                         @RequestParam(name = "supplierName", required = false) String supplierName,
                                                         @RequestParam(name = "chargeUser", required = false) String chargeUser,
                                                         @RequestParam(name = "productCategoryName", required = false) String productCategoryName,
                                                         @RequestParam(name = "productName", required = false) String productName,
                                                         @RequestParam(name = "mobile", required = false) String mobile,
                                                         @RequestParam(name = "city", required = false) String city) {
        try {
            if (pageSize == null) {
                pageSize = 30;
            }

            if (pageNum == null) {
                pageNum = 1;
            }

            Integer start = (pageNum - 1) * pageSize;
            Integer size = pageSize;

            HashMap<String, Object> params = new HashMap<>();
            if (StringUtil.isNotEmptyString(supplierName)) {
                params.put("supplierName", supplierName);
            }

            if (StringUtil.isNotEmptyString(chargeUser)) {
                params.put("chargeUser", chargeUser);
            }

            if (StringUtil.isNotEmptyString(productCategoryName)) {
                params.put("productCategoryName", productCategoryName);
            }

            if (StringUtil.isNotEmptyString(productName)) {
                params.put("productName", productName);
            }

            if (StringUtil.isNotEmptyString(mobile)) {
                params.put("mobile", mobile);
            }

            if (StringUtil.isNotEmptyString(city)) {
                params.put("city", city);
            }

            List<SupplierVo> supplierVoList = supplierService.listSupplierByParams(params, start, size);
            if (!CollectionUtils.isEmpty(supplierVoList)) {
                WebApiResponse response = WebApiResponse.success(supplierVoList);
                response.setTotalCount(supplierVoList.get(0).getTotalCount());
                return response;
            }
        } catch (Exception ex) {
            logger.error("供应商列表查询异常", ex);
            return error("供应商列表查询异常");
        }
        return WebApiResponse.success(null);
    }


    /**
     * 添加供应商
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/createSupplier", method = RequestMethod.POST)
    public WebApiResponse<String> createSupplier(@RequestParam(value = "supplierName") String supplierName,
                                                 @RequestParam(value = "chargeUser", required = false) String chargeUser,
                                                 @RequestParam(value = "city", required = false) String city,
                                                 @RequestParam(value = "bankName", required = false) String bankName,
                                                 @RequestParam(value = "bankBranch", required = false) String bankBranch,
                                                 @RequestParam(value = "bankNo", required = false) String bankNo,
                                                 @RequestParam(value = "bankAccount", required = false) String bankAccount,
                                                 @RequestParam(value = "address", required = false) String address,
                                                 @RequestParam(value = "mobile1", required = false) String mobile1,
                                                 @RequestParam(value = "mobile2", required = false) String mobile2,
                                                 @RequestParam(value = "mobile3", required = false) String mobile3,
                                                 @RequestParam(value = "productIds", required = false) String productIds) {
        UserInfo userInfo = UserStatus.getUserInfo();
        try {
            if (!userInfo.getRoleName().equals(RoleEnum.admin.getCodeName()) && !userInfo.getRoleName().equals(RoleEnum.selfBuyer.getCodeName()) && !userInfo.getRoleName().equals(RoleEnum.agentBuyer.getCodeName()) && !userInfo.getRoleName().equals(RoleEnum.superBuyer.getCodeName())) {
                return error("没有权限");
            }

            if (StringUtil.isEmptyString(supplierName)) {
                return WebApiResponse.error("缺少必填参数！");
            }
            Supplier supplier = supplierService.getSupplierByName(supplierName);

            if (supplier != null) {
                return error("供应商已存在");
            }
            supplier = new Supplier();
            supplier.setAddress(address);
            supplier.setBankAccount(bankAccount);
            supplier.setBankName(bankName);
            supplier.setBankBranch(bankBranch);
            supplier.setBankNo(bankNo);
            supplier.setCharge(chargeUser);
            supplier.setCity(city);
            supplier.setCreatedTime(new Date());
            supplier.setMobile1(mobile1);
            supplier.setMobile2(mobile2);
            supplier.setMobile3(mobile3);
            supplier.setName(supplierName);
            supplier.setOpUserName(userInfo.getUserName());
            supplier.setOpUserNo(userInfo.getUserNo());
            supplier.setOpUserRole(userInfo.getRoleName());
            supplier.setUpdatedTime(new Date());
            supplierService.saveSupplier(supplier);

            if (StringUtil.isNotEmptyString(productIds)) {
                Thread.sleep(1000);
                Supplier dbSupplier = supplierService.getSupplierByName(supplierName);

                String[] productIdArray = productIds.split(";");
                for (String productId : productIdArray) {
                    Product product = productService.getProductById(Long.valueOf(productId));
                    if (product == null) {
                        continue;
                    }
                    SupplierProduct supplierProduct = new SupplierProduct();
                    supplierProduct.setCreatedTime(new Date());
                    supplierProduct.setProductId(product.getId());
                    supplierProduct.setProductName(product.getProductName());
                    supplierProduct.setSupplierId(dbSupplier.getId());
                    supplierProduct.setSupplierName(dbSupplier.getName());
                    supplierProduct.setUpdatedTime(new Date());
                    supplierService.saveSupplierProduct(supplierProduct);
                }
            }
            return success("success");
        } catch (Exception ex) {
            logger.error("添加供应商异常", ex);
            return error("添加供应商异常");
        }
    }


    /**
     * 更新供应商
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/updateSupplier", method = RequestMethod.POST)
    public WebApiResponse<String> updateSupplier(@RequestParam(value = "supplierId") Long supplierId,
                                                 @RequestParam(value = "supplierName") String supplierName,
                                                 @RequestParam(value = "chargeUser", required = false) String chargeUser,
                                                 @RequestParam(value = "city", required = false) String city,
                                                 @RequestParam(value = "bankName", required = false) String bankName,
                                                 @RequestParam(value = "bankBranch", required = false) String bankBranch,
                                                 @RequestParam(value = "bankNo", required = false) String bankNo,
                                                 @RequestParam(value = "bankAccount", required = false) String bankAccount,
                                                 @RequestParam(value = "address", required = false) String address,
                                                 @RequestParam(value = "mobile1", required = false) String mobile1,
                                                 @RequestParam(value = "mobile2", required = false) String mobile2,
                                                 @RequestParam(value = "mobile3", required = false) String mobile3,
                                                 @RequestParam(value = "productIds", required = false) String productIds) {
        UserInfo userInfo = UserStatus.getUserInfo();
        try {
            if (!userInfo.getRoleName().equals(RoleEnum.admin.getCodeName()) && !userInfo.getRoleName().equals(RoleEnum.selfBuyer.getCodeName()) && !userInfo.getRoleName().equals(RoleEnum.agentBuyer.getCodeName()) && !userInfo.getRoleName().equals(RoleEnum.superBuyer.getCodeName())) {
                return error("没有权限");
            }

            if (StringUtil.isEmptyString(supplierName) || supplierId == null) {
                return WebApiResponse.error("缺少必填参数！");
            }
            Supplier supplier = supplierService.getSupplierById(supplierId);
            if (supplier == null) {
                return error("供应商不存在");
            }

            supplier.setAddress(address);
            supplier.setBankAccount(bankAccount);
            supplier.setBankName(bankName);
            supplier.setBankBranch(bankBranch);
            supplier.setBankNo(bankNo);
            supplier.setCharge(chargeUser);
            supplier.setCity(city);
            supplier.setMobile1(mobile1);
            supplier.setMobile2(mobile2);
            supplier.setMobile3(mobile3);
            supplier.setName(supplierName);
            supplier.setOpUserName(userInfo.getUserName());
            supplier.setOpUserNo(userInfo.getUserNo());
            supplier.setOpUserRole(userInfo.getRoleName());
            supplier.setUpdatedTime(new Date());
            supplierService.updateSupplier(supplier);


            List<SupplierProduct> supplierProductList = supplierService.listSupplierProductBySupplierId(supplier.getId());
            HashMap<String, SupplierProduct> supplierProductHashMap = new HashMap<>();
            List<Long> deletedSupplierProductList = new ArrayList<>();

            if (!CollectionUtils.isEmpty(supplierProductList)) {
                for (SupplierProduct supplierProduct : supplierProductList) {
                    deletedSupplierProductList.add(supplierProduct.getId());
                    supplierProductHashMap.put(String.valueOf(supplierProduct.getProductId()), supplierProduct);
                }
            }

            if (StringUtil.isNotEmptyString(productIds)) {
                String[] productIdArray = productIds.split(";");
                for (String productId : productIdArray) {
                    if (supplierProductHashMap.get(productId) != null) { //产品已存在
                        deletedSupplierProductList.remove(Long.valueOf(productId));
                        continue;
                    }
                    Product product = productService.getProductById(Long.valueOf(productId));
                    if (product == null) {
                        continue;
                    }
                    SupplierProduct supplierProduct = new SupplierProduct();
                    supplierProduct.setCreatedTime(new Date());
                    supplierProduct.setProductId(product.getId());
                    supplierProduct.setProductName(product.getProductName());
                    supplierProduct.setSupplierId(supplier.getId());
                    supplierProduct.setSupplierName(supplier.getName());
                    supplierProduct.setUpdatedTime(new Date());
                    supplierService.saveSupplierProduct(supplierProduct);
                }
            }

            if (!CollectionUtils.isEmpty(deletedSupplierProductList)) {
                for (Long id : deletedSupplierProductList) {
                    supplierService.delSupplierProduct(id);
                }
            }
            return success("success");
        } catch (Exception ex) {
            logger.error("更新供应商异常", ex);
            return error("更新供应商异常");
        }
    }


    /**
     * 获取供应商详情
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/getSupplierBasicInfo", method = RequestMethod.GET)
    public WebApiResponse<Supplier> getSupplierBasicInfo(@RequestParam(value = "supplierId") Long supplierId) {
        UserInfo userInfo = UserStatus.getUserInfo();
        try {
            if (!userInfo.getRoleName().equals(RoleEnum.admin.getCodeName()) && !userInfo.getRoleName().equals(RoleEnum.selfBuyer.getCodeName()) && !userInfo.getRoleName().equals(RoleEnum.agentBuyer.getCodeName()) && !userInfo.getRoleName().equals(RoleEnum.superBuyer.getCodeName())) {
                return error("没有权限");
            }

            if (supplierId == null) {
                return WebApiResponse.error("缺少必填参数！");
            }

            Supplier supplier = supplierService.getSupplierById(supplierId);

            if (supplier == null) {
                return error("供应商ID有误");
            }

            return success(supplier);
        } catch (Exception ex) {
            logger.error("获取供应商详情异常", ex);
            return error("获取供应商详情异常");
        }
    }


    /**
     * 获取供应商产品列表
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/listSupplierProduct", method = RequestMethod.GET)
    public WebApiResponse<List<SupplierProductVo>> listSupplierProduct(@RequestParam(value = "supplierId") Long supplierId) {
        UserInfo userInfo = UserStatus.getUserInfo();
        try {
            if (!userInfo.getRoleName().equals(RoleEnum.admin.getCodeName()) && !userInfo.getRoleName().equals(RoleEnum.selfBuyer.getCodeName()) && !userInfo.getRoleName().equals(RoleEnum.agentBuyer.getCodeName()) && !userInfo.getRoleName().equals(RoleEnum.superBuyer.getCodeName())) {
                return error("没有权限");
            }

            if (supplierId == null) {
                return WebApiResponse.error("缺少必填参数！");
            }

            List<SupplierProduct> supplierProductList = supplierService.listSupplierProductBySupplierId(supplierId);


            List<SupplierProductVo> supplierProductVoList = new ArrayList<>();
            if (!CollectionUtils.isEmpty(supplierProductList)) {
                for (SupplierProduct supplierProduct : supplierProductList) {
                    SupplierProductVo supplierProductVo = new SupplierProductVo();
                    supplierProductVo.setId(supplierProduct.getId());
                    supplierProductVo.setProductId(supplierProduct.getProductId());
                    supplierProductVo.setSupplierId(supplierProduct.getSupplierId());
                    supplierProductVo.setProductName(supplierProduct.getProductName());
                    supplierProductVo.setSupplierName(supplierProduct.getSupplierName());

                    Product product = productService.getProductById(supplierProduct.getProductId());
                    supplierProductVo.setProductCategoryId(product.getProductCategoryId());
                    supplierProductVo.setProductCategoryName(product.getCategoryName());

                    List<ProductPicture> productPictureList = productPictureService.listProductPictureByProductId(supplierProduct.getProductId());

                    if (!CollectionUtils.isEmpty(productPictureList)) {
                        Integer size = productPictureList.size();
                        supplierProductVo.setPicNo1(productPictureList.get(0).getPicNo());

                        if (size > 1) {
                            supplierProductVo.setPicNo2(productPictureList.get(1).getPicNo());
                        }

                        if (size > 2) {
                            supplierProductVo.setPicNo3(productPictureList.get(2).getPicNo());
                        }
                    }
                    supplierProductVoList.add(supplierProductVo);
                }
            }
            return success(supplierProductVoList);
        } catch (Exception ex) {
            logger.error("获取供应商产品列表异常", ex);
            return error("获取供应商产品列表异常");
        }
    }


    /**
     * 供应商批量删除
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/delSupplier", method = RequestMethod.POST)
    public WebApiResponse<String> delProduct(@RequestParam(value = "supplierIds") String supplierIds) {
        UserInfo userInfo = UserStatus.getUserInfo();
        try {
            if (!userInfo.getRoleName().equals(RoleEnum.admin.getCodeName()) && !userInfo.getRoleName().equals(RoleEnum.selfBuyer.getCodeName()) && !userInfo.getRoleName().equals(RoleEnum.agentBuyer.getCodeName()) && !userInfo.getRoleName().equals(RoleEnum.superBuyer.getCodeName())) {
                return error("没有权限");
            }

            if (StringUtil.isEmptyString(supplierIds)) {
                return WebApiResponse.error("缺少必填参数！");
            }


            String[] supplierIdsArray = supplierIds.split(";");

            for (String supplierId : supplierIdsArray) {
                supplierService.delSupplierById(Long.valueOf(supplierId));
            }
            return WebApiResponse.success("success");
        } catch (Exception ex) {
            logger.error("产品删除异常", ex);
            return error("产品删除异常");
        }
    }


}