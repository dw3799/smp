package com.alipapa.smp.product.controller;

import com.alibaba.fastjson.JSONObject;
import com.alipapa.smp.common.enums.RoleEnum;
import com.alipapa.smp.common.request.UserInfo;
import com.alipapa.smp.common.request.UserStatus;
import com.alipapa.smp.product.pojo.Picture;
import com.alipapa.smp.product.pojo.Product;
import com.alipapa.smp.product.pojo.ProductCategory;
import com.alipapa.smp.product.pojo.ProductPicture;
import com.alipapa.smp.product.service.PictureService;
import com.alipapa.smp.product.service.ProductCategoryService;
import com.alipapa.smp.product.service.ProductPictureService;
import com.alipapa.smp.product.service.ProductService;
import com.alipapa.smp.product.vo.ProductCategoryVo;
import com.alipapa.smp.product.vo.ProductDetailVo;
import com.alipapa.smp.product.vo.ProductVo;
import com.alipapa.smp.user.pojo.User;
import com.alipapa.smp.utils.DateUtil;
import com.alipapa.smp.utils.OrderNumberGenerator;
import com.alipapa.smp.utils.StringUtil;
import com.alipapa.smp.utils.WebApiResponse;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

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

    @Autowired
    private ProductPictureService productPictureService;

    @Value("${upload.fail.pathPrefix}")
    private String pathPrefix;


    /**
     * 产品查询下拉列表
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/listProductSelect", method = RequestMethod.GET)
    public WebApiResponse<List<Product>> listProductSelect(@RequestParam(value = "productName", required = false) String productName,
                                                           @RequestParam(value = "productCategoryId", required = false) Long productCategoryId) {
        if (StringUtil.isEmptyString(productName)) {
            return WebApiResponse.error("参数有误！");
        }
        try {
            List<Product> productList = productService.listProductByProductNameAndCategory(productName, productCategoryId);
            if (!CollectionUtils.isEmpty(productList)) {
                return success(productList);
            }
        } catch (Exception ex) {
            logger.error("产品查询下拉列表异常", ex);
            return error("产品查询下拉列表异常");
        }
        return success(null);
    }


    /**
     * 产品类别查询下拉列表
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/listProductCategorySelect", method = RequestMethod.GET)
    public WebApiResponse<List<ProductCategoryVo>> listProductCategorySelect(@RequestParam(value = "categoryName", required = false) String categoryName) {
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

            if (!CollectionUtils.isEmpty(productVoList)) {
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
        if (!userInfo.getRoleName().equals(RoleEnum.admin.getCodeName()) && !userInfo.getRoleName().equals(RoleEnum.selfBuyer.getCodeName()) && !userInfo.getRoleName().equals(RoleEnum.agentBuyer.getCodeName()) && !userInfo.getRoleName().equals(RoleEnum.superBuyer.getCodeName())) {
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
            if (!userInfo.getRoleName().equals(RoleEnum.admin.getCodeName()) && !userInfo.getRoleName().equals(RoleEnum.selfBuyer.getCodeName()) && !userInfo.getRoleName().equals(RoleEnum.agentBuyer.getCodeName()) && !userInfo.getRoleName().equals(RoleEnum.superBuyer.getCodeName())) {
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


    /**
     * 图片删除
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/delPic", method = RequestMethod.POST)
    public WebApiResponse<String> delPic(@RequestParam(value = "picNo") String picNo) {
        UserInfo userInfo = UserStatus.getUserInfo();
        try {
            if (!userInfo.getRoleName().equals(RoleEnum.admin.getCodeName()) && !userInfo.getRoleName().equals(RoleEnum.selfBuyer.getCodeName()) && !userInfo.getRoleName().equals(RoleEnum.agentBuyer.getCodeName()) && !userInfo.getRoleName().equals(RoleEnum.superBuyer.getCodeName())) {
                return error("没有权限");
            }

            if (StringUtil.isEmptyString(picNo)) {
                return WebApiResponse.error("缺少必填参数！");
            }
            productService.delPicture(picNo);
            return WebApiResponse.success("success");
        } catch (Exception ex) {
            logger.error("图片删除异常", ex);
            return error("图片删除异常");
        }
    }


    /**
     * 产品删除
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/delProduct", method = RequestMethod.POST)
    public WebApiResponse<String> delProduct(@RequestParam(value = "productIds") String productIds) {
        UserInfo userInfo = UserStatus.getUserInfo();
        try {
            if (!userInfo.getRoleName().equals(RoleEnum.admin.getCodeName()) && !userInfo.getRoleName().equals(RoleEnum.selfBuyer.getCodeName()) && !userInfo.getRoleName().equals(RoleEnum.agentBuyer.getCodeName()) && !userInfo.getRoleName().equals(RoleEnum.superBuyer.getCodeName())) {
                return error("没有权限");
            }

            if (StringUtil.isEmptyString(productIds)) {
                return WebApiResponse.error("缺少必填参数！");
            }


            String[] productIdArray = productIds.split(";");

            for (String productId : productIdArray) {
                productService.delProduct(Long.valueOf(productId));
            }
            return WebApiResponse.success("success");
        } catch (Exception ex) {
            logger.error("产品删除异常", ex);
            return error("产品删除异常");
        }
    }

    /**
     * 更新产品
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/updateProduct", method = RequestMethod.POST)
    public WebApiResponse<String> updateProduct(@RequestParam(value = "categoryName") String categoryName,
                                                @RequestParam(value = "productId") Long productId,
                                                @RequestParam(value = "categoryId", required = false) Long categoryId,
                                                @RequestParam(value = "productName") String productName,
                                                @RequestParam(value = "picNos", required = false) String picNos) {
        UserInfo userInfo = UserStatus.getUserInfo();
        try {
            if (!userInfo.getRoleName().equals(RoleEnum.admin.getCodeName()) && !userInfo.getRoleName().equals(RoleEnum.selfBuyer.getCodeName()) && !userInfo.getRoleName().equals(RoleEnum.agentBuyer.getCodeName()) && !userInfo.getRoleName().equals(RoleEnum.superBuyer.getCodeName())) {
                return error("没有权限");
            }

            if (productId == null || StringUtil.isEmptyString(categoryName) || StringUtil.isEmptyString(productName)) {
                return WebApiResponse.error("缺少必填参数！");
            }

            if (productService.getProductById(productId) == null) {
                return error("该产品不存在！");
            }
            productService.updateProduct(productId, categoryName, categoryId, productName, picNos, userInfo);
            return success("success");
        } catch (Exception ex) {
            logger.error("更新产品异常", ex);
            return error("更新产品异常");
        }
    }


    /**
     * 获取产品详情
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/getProduct", method = RequestMethod.GET)
    public WebApiResponse<ProductDetailVo> getProduct(@RequestParam(value = "productId") Long productId) {
        UserInfo userInfo = UserStatus.getUserInfo();
        try {
            if (!userInfo.getRoleName().equals(RoleEnum.admin.getCodeName()) && !userInfo.getRoleName().equals(RoleEnum.selfBuyer.getCodeName()) && !userInfo.getRoleName().equals(RoleEnum.agentBuyer.getCodeName()) && !userInfo.getRoleName().equals(RoleEnum.superBuyer.getCodeName())) {
                return error("没有权限");
            }

            if (productId == null) {
                return WebApiResponse.error("缺少必填参数！");
            }

            Product product = productService.getProductById(productId);

            if (product == null) {
                return error("产品ID有误");
            }

            if (product.getIsDel() == 1) {
                return error("产品已作废");
            }

            ProductDetailVo productDetailVo = new ProductDetailVo();
            productDetailVo.setCategoryId(product.getProductCategoryId());
            productDetailVo.setCategoryName(product.getCategoryName());
            productDetailVo.setProductId(product.getId());
            productDetailVo.setProductName(product.getProductName());
            List<ProductPicture> productPictureList = productPictureService.listProductPictureByProductId(productId);

            if (!CollectionUtils.isEmpty(productPictureList)) {
                String picNos = "";
                for (ProductPicture productPicture : productPictureList) {
                    picNos = picNos + productPicture.getPicNo() + ";";
                }
                productDetailVo.setPicNos(picNos.substring(0, picNos.length() - 1));
            }

            return success(productDetailVo);
        } catch (Exception ex) {
            logger.error("获取产品详情异常", ex);
            return error("获取产品详情异常");
        }
    }

    /**
     * 下载产品图片
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/downloadPic/{picNo}", method = RequestMethod.GET)
    public ResponseEntity downloadPic(@PathVariable(value = "picNo") String picNo) {
        try {
            if (StringUtil.isEmptyString(picNo)) {
                logger.error("缺少必填参数！");
                return null;
            }

            Picture picture = pictureService.getPictureByPicNo(picNo);
            if (picture == null) {
                logger.error("图片不存在");
                return null;
            }


            File file = new File(picture.getPath());
            if (!file.exists()) {
                logger.error("图片文件不存在");
                return null;
            }


            String picSuffix = StringUtil.substringAfterLast(picture.getPath(), ".");

            logger.info("download filePath: {} , picSuffix:{}", picture.getPath(), picSuffix);

            byte[] pictureData = File2byte(file);
            if (pictureData == null) {
                logger.error("获取图片信息为空");
                return null;
            }

            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add("Cache-Control", "no-store");
            httpHeaders.add("Pragma", "no-cache");
            httpHeaders.add("content-type", "image/" + picSuffix);
            httpHeaders.add("expires", "0");
            return new ResponseEntity(pictureData, httpHeaders, HttpStatus.OK);
        } catch (Exception ex) {
            logger.error("下载产品图片异常", ex);
        }
        return null;
    }


    @RequestMapping(value = "/image/{picNo}", method = RequestMethod.GET)
    public byte[] downImage(@PathVariable String picNo) {
        try {
            if (StringUtil.isEmptyString(picNo)) {
                logger.error("缺少必填参数！");
                return null;
            }

            Picture picture = pictureService.getPictureByPicNo(picNo);
            if (picture == null) {
                logger.error("图片不存在");
                return null;
            }


            File file = new File(picture.getPath());
            if (!file.exists()) {
                logger.error("图片文件不存在");
                return null;
            }

            String picSuffix = StringUtil.substringAfterLast(picture.getPath(), ".");

            logger.info("download filePath: {} , picSuffix:{}", picture.getPath(), picSuffix);

            byte[] pictureData = File2byte(file);
            if (pictureData == null) {
                logger.error("获取图片信息为空");
                return null;
            }


            return pictureData;
        } catch (Exception ex) {
            logger.error("文件下载失败", ex);
        }
        return null;
    }


    private static byte[] File2byte(File file) {
        byte[] buffer = null;
        try {
            FileInputStream fis = new FileInputStream(file);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            byte[] b = new byte[1024];
            int n;
            while ((n = fis.read(b)) != -1) {
                bos.write(b, 0, n);
            }
            fis.close();
            bos.close();
            buffer = bos.toByteArray();
        } catch (FileNotFoundException e) {
            logger.error("File2byte:", e);
        } catch (IOException e) {
            logger.error("File2byte:", e);

        }
        return buffer;
    }


}



