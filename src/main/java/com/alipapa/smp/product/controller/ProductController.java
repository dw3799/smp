package com.alipapa.smp.product.controller;

import com.alibaba.fastjson.JSONObject;
import com.alipapa.smp.common.enums.RoleEnum;
import com.alipapa.smp.common.request.UserInfo;
import com.alipapa.smp.common.request.UserStatus;
import com.alipapa.smp.product.pojo.Picture;
import com.alipapa.smp.product.pojo.Product;
import com.alipapa.smp.product.pojo.ProductCategory;
import com.alipapa.smp.product.service.PictureService;
import com.alipapa.smp.product.service.ProductCategoryService;
import com.alipapa.smp.product.service.ProductService;
import com.alipapa.smp.product.vo.ProductCategoryVo;
import com.alipapa.smp.product.vo.ProductVo;
import com.alipapa.smp.user.pojo.User;
import com.alipapa.smp.utils.DateUtil;
import com.alipapa.smp.utils.OrderNumberGenerator;
import com.alipapa.smp.utils.StringUtil;
import com.alipapa.smp.utils.WebApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.CollectionUtils;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.alipapa.smp.utils.WebApiResponse.error;
import static com.alipapa.smp.utils.WebApiResponse.success;

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


    @Autowired
    private ProductService productService;


    @Autowired
    private PictureService pictureService;

    @Value("${upload.fail.pathPrefix}")
    private String pathPrefix;

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
                return success(productCategoryVoList);
            }
        } catch (Exception ex) {
            logger.error("产品类别查询异常", ex);
            return error("产品类别查询异常");
        }

        return success(null);
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
        try {
            if (pageSize == null) {
                pageSize = 30;
            }

            if (pageNum == null) {
                pageNum = 1;
            }

            Integer start = (pageNum - 1) * pageSize;
            Integer size = pageSize;
            List<ProductVo> productVoList = null;

            if (StringUtil.isNotEmptyString(saleNo)) {
                productVoList = productService.listProductByParam(productCategoryId, productName, start, size);
            } else {
                productVoList = productService.listProductBySaleNo(productCategoryId, productName, saleNo, start, size);
            }

            if (CollectionUtils.isEmpty(productVoList)) {
                WebApiResponse response = success(productVoList);
                response.setTotalCount(productVoList.get(0).getTotalCount());
                return response;
            }
        } catch (Exception ex) {
            logger.error("产品管理列表查询异常", ex);
            return error("产品管理列表查询异常");
        }
        return success(null);
    }

    /**
     * 图片上传接口
     *
     * @return
     */
    @RequestMapping(value = "/picUpload", method = RequestMethod.POST)
    public WebApiResponse<JSONObject> sendWelfare(@RequestParam(value = "file") MultipartFile multipartFile, HttpServletRequest request) {
        UserInfo userInfo = UserStatus.getUserInfo();
        if (!userInfo.getRoleName().equals(RoleEnum.selfBuyer.getCodeName()) && !userInfo.getRoleName().equals(RoleEnum.agentBuyer.getCodeName()) && !userInfo.getRoleName().equals(RoleEnum.superBuyer.getCodeName())) {
            return error("没有权限");
        }

        try {
            String nowDay = DateUtil.formatToStr(new Date());

            if (multipartFile == null || multipartFile.isEmpty()) {
                return error("上传的文件必须为多媒体类型");
            }
            String path = pathPrefix + File.separator + "Product" + File.separator + nowDay;
            String picNo = this.uploadFile(multipartFile, path);

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("picNo", picNo);
            return WebApiResponse.success(jsonObject);
        } catch (Exception ex) {
            logger.error("图片上传失败", ex);
            return error("图片上传失败");
        }
    }


    /**
     * @param file MultipartFile对象
     * @param path 保存路径，如“D:\\File\\”
     * @return 保存的全路径 如“D:\\File\\2345678.txt”
     * @throws Exception 文件保存失败
     * @descrption 保存文件
     */
    private String uploadFile(MultipartFile file, String path) throws Exception {
        try {
            String filename = file.getOriginalFilename();
            String extName = filename.substring(filename.lastIndexOf(".")).toLowerCase();
            String lastFileName = System.currentTimeMillis() + extName;
            if (!path.endsWith(File.separator)) {
                path = path + File.separator;
            }
            File temp = new File(path);
            if (!temp.isDirectory()) {
                temp.mkdir();
            }

            File dir = new File(path);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            //写入到新的文件
            File targetFile = new File(path, lastFileName);

            targetFile.createNewFile();
            //复制到新文件
            FileCopyUtils.copy(file.getBytes(), targetFile);

            //保存文件表
            String picNo = "PIC" + OrderNumberGenerator.get();
            Picture picture = new Picture();
            picture.setIsDel(0);
            picture.setCreatedTime(new Date());
            picture.setFileType(0);
            picture.setPath(targetFile.getAbsolutePath());
            picture.setPicName(lastFileName);
            picture.setPicNo(picNo);
            picture.setUpdatedTime(new Date());
            pictureService.save(picture);
            return picNo;
        } catch (Exception e) {
            logger.error("复制文件异常", e);
            throw new Exception("复制文件异常");
        }
    }


    /**
     * 添加产品
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/createProduct", method = RequestMethod.POST)
    public WebApiResponse<String> createProduct(@RequestParam(value = "categoryName") String categoryName,
                                                @RequestParam(value = "categoryId", required = false) Long categoryId,
                                                @RequestParam(value = "productName") String productName,
                                                @RequestParam(value = "picNos", required = false) String picNos) {
        UserInfo userInfo = UserStatus.getUserInfo();

        try {
            if (!userInfo.getRoleName().equals(RoleEnum.selfBuyer.getCodeName()) && !userInfo.getRoleName().equals(RoleEnum.agentBuyer.getCodeName()) && !userInfo.getRoleName().equals(RoleEnum.superBuyer.getCodeName())) {
                return error("没有权限");
            }

            if (StringUtil.isEmptyString(categoryName) || StringUtil.isEmptyString(productName)) {
                return WebApiResponse.error("缺少必填参数！");
            }

            if (productService.getProductByName(productName) != null) {
                return error("该产品已存在！");
            }
            productService.saveProduct(categoryName, categoryId, productName, picNos, userInfo);
            return success("success");
        } catch (Exception ex) {
            logger.error("添加产品异常", ex);
            return error("添加产品异常");
        }
    }


}



