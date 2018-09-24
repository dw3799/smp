package com.alipapa.smp.order.controller;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alipapa.smp.common.enums.*;
import com.alipapa.smp.common.request.UserInfo;
import com.alipapa.smp.common.request.UserStatus;
import com.alipapa.smp.consumer.pojo.SysDict;
import com.alipapa.smp.consumer.service.SysDictService;
import com.alipapa.smp.consumer.vo.SysDictVo;
import com.alipapa.smp.order.pojo.*;
import com.alipapa.smp.order.service.*;
import com.alipapa.smp.order.service.impl.SubOrderServiceProxy;
import com.alipapa.smp.order.vo.OrderFollowRecordVo;
import com.alipapa.smp.order.vo.OrderVo;
import com.alipapa.smp.order.vo.SubOrderVo;
import com.alipapa.smp.product.pojo.Product;
import com.alipapa.smp.product.pojo.ProductCategory;
import com.alipapa.smp.product.pojo.ProductPicture;
import com.alipapa.smp.product.pojo.Supplier;
import com.alipapa.smp.product.service.ProductCategoryService;
import com.alipapa.smp.product.service.ProductPictureService;
import com.alipapa.smp.product.service.ProductService;
import com.alipapa.smp.product.service.SupplierService;
import com.alipapa.smp.user.pojo.Group;
import com.alipapa.smp.user.pojo.User;
import com.alipapa.smp.user.service.GroupService;
import com.alipapa.smp.user.service.RoleService;
import com.alipapa.smp.user.service.UserService;
import com.alipapa.smp.utils.DateUtil;
import com.alipapa.smp.utils.PriceUtil;
import com.alipapa.smp.utils.StringUtil;
import com.alipapa.smp.utils.WebApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

import static com.alipapa.smp.utils.WebApiResponse.error;
import static com.alipapa.smp.utils.WebApiResponse.success;

/**
 * 采购订单跟单接口
 *
 * @author liuwei
 * @version 2.0
 * 2018-09-20
 */
@RestController
@CrossOrigin
@RequestMapping("/api/subOrder")
public class MaterielOrderFollowController {
    private static Logger logger = LoggerFactory.getLogger(SubOrderController.class);

    @Autowired
    private SubOrderServiceProxy subOrderServiceProxy;

    @Autowired
    private UserService userService;

    @Autowired
    private MaterielOrderService materielOrderService;

    @Autowired
    private SubOrderService subOrderService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderWorkFlowService orderWorkFlowService;


    @Autowired
    private OrderFollowRecordService orderFollowRecordService;


    @Autowired
    private ProductService productService;

    @Autowired
    private ProductCategoryService productCategoryService;

    @Autowired
    private ProductPictureService productPictureService;

    @Autowired
    private SupplierService supplierService;

    @Autowired
    private PurchaseOrderExtService purchaseOrderExtService;

    @Autowired
    private RoleService roleService;


    /**
     * 物料单跟单状态列表
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/followStatus", method = RequestMethod.GET)
    public WebApiResponse<List<SysDictVo>> followStatus() {
        List<SysDictVo> sysDictVoList = new ArrayList<>();
        for (MaterielFactoryStatusEnum materielFactoryStatusEnum : MaterielFactoryStatusEnum.values()) {
            SysDictVo sysDictVo = new SysDictVo();
            sysDictVo.setId(Long.valueOf(materielFactoryStatusEnum.getCode()));
            sysDictVo.setCategoryCode("物料单跟单状态");
            sysDictVo.setDictText(materielFactoryStatusEnum.getDec());
            sysDictVo.setDictValue(materielFactoryStatusEnum.getCodeName());
            sysDictVoList.add(sysDictVo);
        }

        return WebApiResponse.success(sysDictVoList);
    }


    /**
     * 我的采购订单查询
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/listMySubOrder", method = RequestMethod.GET)
    public WebApiResponse<List<OrderVo>> listMySubOrder(@RequestParam(name = "pageSize", required = false) Integer pageSize,
                                                        @RequestParam(name = "pageNum", required = false) Integer pageNum,
                                                        @RequestParam(name = "orderNo", required = false) String orderNo,
                                                        @RequestParam(name = "subOrderNo", required = false) String subOrderNo,
                                                        @RequestParam(name = "productName", required = false) String productName,
                                                        @RequestParam(name = "subOrderStatus", required = false) String subOrderStatus,
                                                        @RequestParam(name = "consumerName", required = false) String consumerName,
                                                        @RequestParam(name = "supplierName", required = false) String supplierName,
                                                        @RequestParam(name = "createTimeStart", required = false) String createTimeStart, //YYYY-MM-DD HH:MM SS
                                                        @RequestParam(name = "createTimeEnd", required = false) String createTimeEnd) {
        UserInfo userInfo = UserStatus.getUserInfo();
        Map<String, Object> params = new HashMap<>();

        if (StringUtil.isNotEmptyString(orderNo)) {
            params.put("orderNo", orderNo);
        }

        if (StringUtil.isNotEmptyString(subOrderNo)) {
            params.put("subOrderNo", subOrderNo);
        }


        if (StringUtil.isNotEmptyString(productName)) {
            params.put("productName", productName);
        }


        if (StringUtil.isNotEmptyString(consumerName)) {
            params.put("consumerName", consumerName);
        }

        if (StringUtil.isNotEmptyString(subOrderStatus)) {
            params.put("subOrderStatus", SubOrderStatusEnum.valueOf(subOrderStatus).getCode());
        }

        if (StringUtil.isNotEmptyString(supplierName)) {
            params.put("supplierName", OrderTypeEnum.valueOf(supplierName).getCode());
        }

        //创建时间开始
        if (!StringUtil.isNotEmptyString(createTimeStart)) {
            logger.info("createTimeStart:" + createTimeStart);
            params.put("createTimeStart", createTimeStart);
        }

        //创建时间结束
        if (!StringUtil.isNotEmptyString(createTimeEnd)) {
            logger.info("createTimeEnd:" + createTimeEnd);
            params.put("createTimeEnd", createTimeEnd);
        }

        User user = userService.getUserByUserNo(userInfo.getUserNo());

        if (userInfo.getRoleName().equals(RoleEnum.superBuyer.getCodeName())) {
            params.put("groupId", user.getGroupId());
        } else {
            params.put("buyerUserNo", user.getUserNo());
            params.put("groupId", user.getGroupId());
        }


        if (pageSize == null) {
            pageSize = 30;
        }

        if (pageNum == null) {
            pageNum = 1;
        }

        Integer start = (pageNum - 1) * pageSize;
        Integer size = pageSize;


        List<SubOrderVo> subOrderVoList = subOrderServiceProxy.listGroupSubOrder(params, start, size);
        if (!CollectionUtils.isEmpty(subOrderVoList)) {
            WebApiResponse response = WebApiResponse.success(subOrderVoList);
            response.setTotalCount(subOrderVoList.get(0).getTotalCount());
            return response;
        }

        return WebApiResponse.success(null);
    }


    /**
     * 获取待补充跟单信息采购单
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/listBuyerFollowSubOrder", method = RequestMethod.GET)
    public WebApiResponse<List<SubOrderVo>> listBuyerFollowSubOrder(@RequestParam(name = "pageSize", required = false) Integer pageSize,
                                                                    @RequestParam(name = "pageNum", required = false) Integer pageNum) {
        UserInfo userInfo = UserStatus.getUserInfo();

        try {
            if (pageSize == null) {
                pageSize = 30;
            }

            if (pageNum == null) {
                pageNum = 1;
            }

            Integer start = (pageNum - 1) * pageSize;
            Integer size = pageSize;

            List<SubOrderVo> orderVoList = subOrderServiceProxy.listMySubOrder(SubOrderStatusEnum.BUYER_FOLLOW_ORDER, userInfo.getUserNo(), start, size);
            if (!CollectionUtils.isEmpty(orderVoList)) {
                WebApiResponse response = WebApiResponse.success(orderVoList);
                response.setTotalCount(orderVoList.get(0).getTotalCount());
                return response;
            }
            return WebApiResponse.success(null);
        } catch (Exception ex) {
            logger.error("获取待补充跟单信息采购单异常", ex);
            return WebApiResponse.error("获取待补充跟单信息采购单异常");
        }
    }


    /**
     * 物料单作废
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/disCardMaterielOrder", method = RequestMethod.POST)
    public WebApiResponse<String> disCardMaterielOrder(@RequestParam(name = "materielOrderId", required = false) Long materielOrderId) {
        UserInfo userInfo = UserStatus.getUserInfo();

        try {
            if (materielOrderId == null) {
                return WebApiResponse.error("缺少必填参数");
            }

            MaterielOrder materielOrder = materielOrderService.selectMaterielOrderById(materielOrderId);

            if (materielOrder == null) {
                return WebApiResponse.error("物料单不存在");
            }

            Order order = orderService.selectOrderByOrderNo(materielOrder.getOrderNo());

            if (!userInfo.getUserNo().equals(order.getBuyerUserNo())) {
                return WebApiResponse.error("没有权限");
            }


            //保存订单流转记录
            OrderFollowRecord orderFollowRecord = new OrderFollowRecord();
            orderFollowRecord.setUpdatedTime(new Date());
            orderFollowRecord.setTitle("物料单已作废");
            orderFollowRecord.setSubOrderNo(materielOrder.getSubOrderNo());
            orderFollowRecord.setRemark("物料单已作废");
            orderFollowRecord.setSort(MaterielOrderStatusEnum.DISCARDED.getCode());
            orderFollowRecord.setOrderNo(materielOrder.getSubOrderNo());
            orderFollowRecord.setMaterielOrderNo(String.valueOf(materielOrder.getId()));

            orderFollowRecord.setOpUserName(userInfo.getUserName());
            orderFollowRecord.setOpUserNo(userInfo.getUserNo());
            orderFollowRecord.setCreatedTime(new Date());

            materielOrder.setMaterielOrderStatus(MaterielOrderStatusEnum.DISCARDED.getCode());


            SubOrder subOrder = subOrderService.getSubOrderBySubOrderNo(materielOrder.getSubOrderNo());
            //保存订单流转记录
            OrderWorkFlow orderWorkFlow = new OrderWorkFlow();
            orderWorkFlow.setCreatedTime(new Date());
            orderWorkFlow.setNewOrderStatus(SubOrderStatusEnum.BUYER_ORDER.getCode());
            orderWorkFlow.setOldOrderStatus(subOrder.getSubOrderStatus());
            orderWorkFlow.setOpUserName(userInfo.getUserName());
            orderWorkFlow.setOpUserNo(userInfo.getUserNo());
            orderWorkFlow.setOpUserRole(userInfo.getRoleName());
            orderWorkFlow.setOrderNo(subOrder.getSubOrderNo());
            orderWorkFlow.setType(OrderWorkFlowTypeEnum.SUB_ORDER.getCodeName());
            orderWorkFlow.setRemark("物料单作废成功，采购单重新审核");
            orderWorkFlow.setResult("物料单作废成功");
            orderWorkFlow.setUpdatedTime(new Date());

            subOrder.setSubOrderStatus(SubOrderStatusEnum.BUYER_ORDER.getCode());

            materielOrderService.updateMaterielOrder(materielOrder);
            orderFollowRecordService.save(orderFollowRecord);

            subOrderService.updateSubOrder(subOrder);
            orderWorkFlowService.save(orderWorkFlow);
            return WebApiResponse.success("success");
        } catch (Exception ex) {
            logger.error("物料单作废异常", ex);
            return WebApiResponse.error("物料单作废异常");
        }
    }


    /**
     * 新增物料单
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/addMaterielOrder", method = RequestMethod.POST)
    public WebApiResponse<String> addMaterielOrder(HttpServletRequest request) {
        UserInfo userInfo = UserStatus.getUserInfo();

        try {
            //不能为空
            String subOrderNo = request.getParameter("subOrderNo");

            SubOrder subOrder = subOrderService.getSubOrderBySubOrderNo(subOrderNo);
            if (subOrder == null) {
                return WebApiResponse.error("采购订单不存在");
            }

            Order order = orderService.selectOrderByOrderNo(subOrder.getOrderNo());

            if (!order.getBuyerUserNo().equals(userInfo.getUserNo())) {
                return error("没有权限");
            }

            if (subOrder.getSubOrderStatus() != SubOrderStatusEnum.BUYER_FOLLOW_ORDER.getCode()) {
                return error("当前状态不能增加物料！");
            }

            String materiels = request.getParameter("materiels");
            String opType = request.getParameter("opType");

            //可为空
            String remark = request.getParameter("remark");

            if (StringUtil.isEmptyString(opType) || StringUtil.isEmptyString(materiels)) {
                return error("缺少必填参数");
            }

            OrderOPerateTypeEnum orderOPerateTypeEnum = OrderOPerateTypeEnum.valueOf(opType);
            if (orderOPerateTypeEnum == null) {
                return error("操作类型有误");
            }

            subOrder.setRemark(remark);

            if (orderOPerateTypeEnum == OrderOPerateTypeEnum.SUBMIT) {
                subOrder.setSubOrderStatus(SubOrderStatusEnum.SPR_BUYER_APV.getCode());
            } else {
                subOrder.setSubOrderStatus(SubOrderStatusEnum.BUYER_ORDER.getCode());
            }
            boolean flag = false;

            Long totalPurchaseAmount = 0L;
            Long totalPurchaseFrontAmount = 0L;

            List<MaterielOrder> materielOrderList = new ArrayList<>();

            JSONArray materielsArray = JSONArray.parseArray(materiels);
            for (int i = 0; i < materielsArray.size(); i++) {
                JSONObject jsonObject = materielsArray.getJSONObject(i);

                Long materielOrderId = jsonObject.getLong("materielOrderId");
                Long productId = jsonObject.getLong("productId");
                Long supplierId = jsonObject.getLong("supplierId");

                String purchaseAmount = jsonObject.getString("purchaseAmount");
                String purchaseFrontAmount = jsonObject.getString("purchaseFrontAmount");
                String mRemark = jsonObject.getString("remark");


                MaterielOrder materielOrder = null;
                if (materielOrderId != null) {
                    materielOrder = materielOrderService.selectMaterielOrderById(materielOrderId);
                }
                if (materielOrder == null) {
                    materielOrder = new MaterielOrder();
                }

                if (materielOrderService.isCanEdit(materielOrder)) {
                    flag = true;
                    if (productId == null || supplierId == null || StringUtil.isEmptyString(purchaseAmount) || StringUtil.isEmptyString(purchaseFrontAmount)) {
                        return error("产品缺少必填参数");
                    }

                    Product product = productService.getProductById(productId);
                    ProductCategory productCategory = productCategoryService.getProductCategoryById(product.getProductCategoryId());

                    List<ProductPicture> productPictureList = productPictureService.listProductPictureByProductId(productId);

                    if (product == null || productCategory == null) {
                        return error("产品参数异常");
                    }

                    Supplier supplier = supplierService.getSupplierById(supplierId);

                    if (!CollectionUtils.isEmpty(productPictureList)) {
                        ProductPicture productPicture = productPictureList.get(0);
                        materielOrder.setMiniPic(productPicture.getPicNo());
                        materielOrder.setPic(productPicture.getPicNo());
                    }
                    materielOrder.setOrderNo(subOrder.getOrderNo());
                    materielOrder.setPayStatus(MaterielOrderPayStatusEnum.UN_PAY.getCode());
                    materielOrder.setProductCategory(productCategory.getCategoryName());

                    materielOrder.setProductCategoryId(productCategory.getId());
                    materielOrder.setProductId(product.getId());
                    materielOrder.setProductName(product.getProductName());
                    materielOrder.setPurchaseAmount(PriceUtil.convertToFen(purchaseAmount));
                    materielOrder.setPurchaseFrontAmount(PriceUtil.convertToFen(purchaseFrontAmount));
                    materielOrder.setSubOrderNo(subOrderNo);
                    materielOrder.setUpdatedTime(new Date());

                    materielOrder.setCreatedTime(new Date());
                    if (orderOPerateTypeEnum == OrderOPerateTypeEnum.SUBMIT) {
                        materielOrder.setMaterielOrderStatus(MaterielOrderStatusEnum.SPR_BUYER_APV.getCode());
                    } else {
                        materielOrder.setMaterielOrderStatus(MaterielOrderStatusEnum.BUYER_ORDER.getCode());
                    }

                    materielOrder.setSupplierBankAccount(supplier.getBankAccount());
                    materielOrder.setSupplierBankName(supplier.getBankName());
                    materielOrder.setSupplierBankNo(supplier.getBankNo());
                    materielOrder.setSupplierCharge(supplier.getCharge());
                    materielOrder.setSupplierId(supplierId);
                    materielOrder.setSupplierMobile(supplier.getMobile1());
                    materielOrder.setSupplierName(supplier.getName());
                    materielOrder.setRemark(mRemark);

                }

                totalPurchaseAmount = totalPurchaseAmount + materielOrder.getPurchaseAmount();
                totalPurchaseFrontAmount = totalPurchaseFrontAmount + materielOrder.getPurchaseFrontAmount();
                materielOrderList.add(materielOrder);
            }

            if (!flag) {
                return error("没有可新增物料");
            }

            PurchaseOrderExt purchaseOrderExt = purchaseOrderExtService.getPurchaseOrderExtBySubOrderNo(subOrder.getSubOrderNo());
            if (purchaseOrderExt != null && orderOPerateTypeEnum == OrderOPerateTypeEnum.SUBMIT) {
                purchaseOrderExt.setPurchaseFrontAmount(totalPurchaseFrontAmount);
                purchaseOrderExt.setSubmitTime(new Date());
                purchaseOrderExt.setUpdatedTime(new Date());
                purchaseOrderExtService.updatePurchaseOrderExt(purchaseOrderExt);
            }

            subOrder.setActualPurchaseAmount(totalPurchaseAmount);
            subOrderServiceProxy.addMaterielOrder(order, subOrder, materielOrderList);
            return success("success");
        } catch (Exception ex) {
            logger.error("新增物料订单异常", ex);
            return error("新增物料订单异常");
        }
    }


    /**
     * 物料单跟单状态更新
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/updateMaterielOrderStatus", method = RequestMethod.POST)
    public WebApiResponse<String> updateMaterielOrderStatus(HttpServletRequest request) {
        UserInfo userInfo = UserStatus.getUserInfo();

        try {

            String subOrderNo = request.getParameter("subOrderNo");
            if (StringUtil.isEmptyString(subOrderNo)) {
                return WebApiResponse.error("缺少必填参数");
            }

            SubOrder subOrder = subOrderService.getSubOrderBySubOrderNo(subOrderNo);
            if (subOrder == null) {
                return error("采购单不存在");
            }

            boolean flag = false;

            String materiels = request.getParameter("materiels");
            JSONArray materielsArray = JSONArray.parseArray(materiels);
            for (int i = 0; i < materielsArray.size(); i++) {
                JSONObject jsonObject = materielsArray.getJSONObject(i);

                Long materielOrderId = jsonObject.getLong("materielOrderId");
                String materielOrderStatus = jsonObject.getString("materielOrderStatus");

                if (materielOrderId == null || StringUtil.isEmptyString(materielOrderStatus)) {
                    return error("缺少必填参数！");
                }

                MaterielOrder materielOrder = materielOrderService.selectMaterielOrderById(materielOrderId);
                MaterielOrderStatusEnum materielOrderStatusEnum = MaterielOrderStatusEnum.valueOf(materielOrderStatus);

                if (materielOrderStatusEnum == null) {
                    return error("状态参数不合法");
                }

                if (materielOrderStatusEnum.getCode() < materielOrder.getMaterielOrderStatus()) {
                    return error("状态参数有误");
                }

                if (materielOrderStatusEnum.getCode() == materielOrder.getMaterielOrderStatus()) {
                    continue;
                }


                //物料单跟单记录
                OrderFollowRecord orderFollowRecord = new OrderFollowRecord();
                orderFollowRecord.setUpdatedTime(new Date());
                orderFollowRecord.setTitle("跟单记录");
                orderFollowRecord.setSubOrderNo(materielOrder.getSubOrderNo());
                orderFollowRecord.setRemark("物料单状态由" + MaterielOrderStatusEnum.valueOf(materielOrder.getMaterielOrderStatus()).getDec() + "变为" + materielOrderStatusEnum.getDec());
                orderFollowRecord.setSort(materielOrderStatusEnum.getCode());
                orderFollowRecord.setOrderNo(materielOrder.getSubOrderNo());
                orderFollowRecord.setMaterielOrderNo(String.valueOf(materielOrder.getId()));
                orderFollowRecord.setOpUserName(userInfo.getUserName());
                orderFollowRecord.setOpUserNo(userInfo.getUserNo());
                orderFollowRecord.setCreatedTime(new Date());

                materielOrder.setMaterielOrderStatus(materielOrderStatusEnum.getCode());
                materielOrderService.updateMaterielOrder(materielOrder);
                orderFollowRecordService.save(orderFollowRecord);
                flag = true;
            }

            if (flag) {
                //保存订单流转记录
                OrderWorkFlow orderWorkFlow = new OrderWorkFlow();
                orderWorkFlow.setCreatedTime(new Date());
                orderWorkFlow.setNewOrderStatus(subOrder.getSubOrderStatus());
                orderWorkFlow.setOldOrderStatus(SubOrderStatusEnum.BUYER_FOLLOW_ORDER.getCode());
                orderWorkFlow.setOpUserName(userInfo.getUserName());
                orderWorkFlow.setOpUserNo(userInfo.getUserNo());
                orderWorkFlow.setOpUserRole(userInfo.getRoleName());
                orderWorkFlow.setOrderNo(subOrder.getSubOrderNo());
                orderWorkFlow.setType(OrderWorkFlowTypeEnum.SUB_ORDER.getCodeName());
                orderWorkFlow.setRemark("物料单跟单状态已更新");
                orderWorkFlow.setResult("成功");
                orderWorkFlow.setUpdatedTime(new Date());
                orderWorkFlowService.save(orderWorkFlow);
            }

            return WebApiResponse.success("success");
        } catch (Exception ex) {
            logger.error("物料单跟单状态更新异常", ex);
            return WebApiResponse.error("物料单跟单状态更新异常");
        }
    }


    /**
     * 物料单跟单状态更新
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/submitQualityCheck", method = RequestMethod.POST)
    public WebApiResponse<String> submitQualityCheck(HttpServletRequest request) {
        UserInfo userInfo = UserStatus.getUserInfo();

        try {
            String subOrderNo = request.getParameter("subOrderNo");
            if (StringUtil.isEmptyString(subOrderNo)) {
                return WebApiResponse.error("缺少必填参数");
            }

            SubOrder subOrder = subOrderService.getSubOrderBySubOrderNo(subOrderNo);
            if (subOrder == null) {
                return error("采购单不存在");
            }

            boolean flag = false;

            String materiels = request.getParameter("materiels");
            JSONArray materielsArray = JSONArray.parseArray(materiels);

            for (int i = 0; i < materielsArray.size(); i++) {
                JSONObject jsonObject = materielsArray.getJSONObject(i);

                Long materielOrderId = jsonObject.getLong("materielOrderId");
                String materielOrderStatus = jsonObject.getString("materielOrderStatus");

                if (materielOrderId == null || StringUtil.isEmptyString(materielOrderStatus)) {
                    return error("缺少必填参数！");
                }

                MaterielOrder materielOrder = materielOrderService.selectMaterielOrderById(materielOrderId);
                MaterielOrderStatusEnum materielOrderStatusEnum = MaterielOrderStatusEnum.valueOf(materielOrderStatus);

                if (materielOrderStatusEnum == null) {
                    return error("状态参数不合法");
                }

                if (materielOrderStatusEnum.getCode() < materielOrder.getMaterielOrderStatus()) {
                    return error("状态参数有误");
                }

                if (materielOrderStatusEnum != MaterielOrderStatusEnum.FACTORY_INVOICE) {
                    return error("当前状态不能提交质检");
                }

                if (materielOrderStatusEnum.getCode() == materielOrder.getMaterielOrderStatus()) {
                    continue;
                }

                //物料单跟单记录
                OrderFollowRecord orderFollowRecord = new OrderFollowRecord();
                orderFollowRecord.setUpdatedTime(new Date());
                orderFollowRecord.setTitle("跟单记录");
                orderFollowRecord.setSubOrderNo(materielOrder.getSubOrderNo());
                orderFollowRecord.setRemark("物料单状态由" + MaterielOrderStatusEnum.valueOf(materielOrder.getMaterielOrderStatus()).getDec() + "变为" + materielOrderStatusEnum.getDec());
                orderFollowRecord.setSort(materielOrderStatusEnum.getCode());
                orderFollowRecord.setOrderNo(materielOrder.getSubOrderNo());
                orderFollowRecord.setMaterielOrderNo(String.valueOf(materielOrder.getId()));
                orderFollowRecord.setOpUserName(userInfo.getUserName());
                orderFollowRecord.setOpUserNo(userInfo.getUserNo());
                orderFollowRecord.setCreatedTime(new Date());

                materielOrder.setMaterielOrderStatus(materielOrderStatusEnum.getCode());
                orderFollowRecordService.save(orderFollowRecord);
            }


            List<MaterielOrder> materielOrderList = materielOrderService.listMaterielOrderBySubOrderNo(subOrderNo);

            for (MaterielOrder materielOrder : materielOrderList) {
                if (materielOrder.getMaterielOrderStatus() != MaterielOrderStatusEnum.FACTORY_INVOICE.getCode()) {
                    return error("当前状态不能提交质检");
                }
            }

            subOrder.setSubOrderStatus(SubOrderStatusEnum.QUALITY_CHECK.getCode());
            subOrderService.updateSubOrder(subOrder);

            //保存订单流转记录
            OrderWorkFlow orderWorkFlow = new OrderWorkFlow();
            orderWorkFlow.setCreatedTime(new Date());
            orderWorkFlow.setNewOrderStatus(subOrder.getSubOrderStatus());
            orderWorkFlow.setOldOrderStatus(SubOrderStatusEnum.BUYER_FOLLOW_ORDER.getCode());
            orderWorkFlow.setOpUserName(userInfo.getUserName());
            orderWorkFlow.setOpUserNo(userInfo.getUserNo());
            orderWorkFlow.setOpUserRole(userInfo.getRoleName());
            orderWorkFlow.setOrderNo(subOrder.getSubOrderNo());
            orderWorkFlow.setType(OrderWorkFlowTypeEnum.SUB_ORDER.getCodeName());
            orderWorkFlow.setRemark("物料单提交质检");
            orderWorkFlow.setResult("成功");
            orderWorkFlow.setUpdatedTime(new Date());
            orderWorkFlowService.save(orderWorkFlow);


            return WebApiResponse.success("success");
        } catch (Exception ex) {
            logger.error("物料单跟单状态更新异常", ex);
            return WebApiResponse.error("物料单跟单状态更新异常");
        }
    }


    /**
     * 获取待质检采购单
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/listQualityCheckSubOrder", method = RequestMethod.GET)
    public WebApiResponse<List<JSONObject>> listQualityCheckSubOrder(@RequestParam(name = "pageSize", required = false) Integer pageSize,
                                                                     @RequestParam(name = "pageNum", required = false) Integer pageNum) {
        UserInfo userInfo = UserStatus.getUserInfo();

        try {
            if (pageSize == null) {
                pageSize = 30;
            }

            if (pageNum == null) {
                pageNum = 1;
            }

            Integer start = (pageNum - 1) * pageSize;
            Integer size = pageSize;

            List<JSONObject> jsonObjectList = subOrderServiceProxy.listQualityCheckSubOrderByStatus(SubOrderStatusEnum.QUALITY_CHECK, start, size);

            if (!CollectionUtils.isEmpty(jsonObjectList)) {
                WebApiResponse response = WebApiResponse.success(jsonObjectList);
                response.setTotalCount(jsonObjectList.get(0).getLong("totalCount"));
                return response;
            }
            return WebApiResponse.success(null);
        } catch (Exception ex) {
            logger.error("获取待质检采购单异常", ex);
            return WebApiResponse.error("获取待质检采购单异常");
        }
    }


    /**
     * 获取采购单生产跟单信息
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/listSubOrderFollowDetails", method = RequestMethod.GET)
    public WebApiResponse<List<OrderFollowRecordVo>> listSubOrderFollowDetails(@RequestParam(name = "subOrderNo") String subOrderNo) {
        UserInfo userInfo = UserStatus.getUserInfo();

        try {
            List<MaterielOrder> materielOrderList = materielOrderService.listAllMaterielOrderBySubOrderNo(subOrderNo);

            List<OrderFollowRecordVo> orderFollowRecordVoArrayList = new ArrayList<>();


            for (MaterielOrder materielOrder : materielOrderList) {
                OrderFollowRecordVo orderFollowRecordVo = new OrderFollowRecordVo();
                orderFollowRecordVo.setMaterielOrderId(materielOrder.getId());
                orderFollowRecordVo.setMaterielOrderStatus(MaterielOrderStatusEnum.valueOf(materielOrder.getMaterielOrderStatus()).getDec());
                orderFollowRecordVo.setMaterielName(materielOrder.getProductName());

                OrderFollowRecord FACTORY_ORDERED_orderFollowRecord = orderFollowRecordService.getOrderFollowRecordBySort(materielOrder.getId(), MaterielOrderStatusEnum.FACTORY_ORDERED.getCode());
                if (FACTORY_ORDERED_orderFollowRecord != null) {
                    orderFollowRecordVo.setFactoryOrderedTime(DateUtil.formatToStrTimeV1(FACTORY_ORDERED_orderFollowRecord.getCreatedTime()));
                }

                OrderFollowRecord FACTORY_MATERIEL_orderFollowRecord = orderFollowRecordService.getOrderFollowRecordBySort(materielOrder.getId(), MaterielOrderStatusEnum.FACTORY_MATERIEL.getCode());
                if (FACTORY_MATERIEL_orderFollowRecord != null) {
                    orderFollowRecordVo.setFactoryMaterielTime(DateUtil.formatToStrTimeV1(FACTORY_MATERIEL_orderFollowRecord.getCreatedTime()));
                }

                OrderFollowRecord FACTORY_PRINTING_orderFollowRecord = orderFollowRecordService.getOrderFollowRecordBySort(materielOrder.getId(), MaterielOrderStatusEnum.FACTORY_PRINTING.getCode());
                if (FACTORY_PRINTING_orderFollowRecord != null) {
                    orderFollowRecordVo.setFactoryPrintingTime(DateUtil.formatToStrTimeV1(FACTORY_PRINTING_orderFollowRecord.getCreatedTime()));
                }


                OrderFollowRecord FACTORY_PACKAGE_orderFollowRecord = orderFollowRecordService.getOrderFollowRecordBySort(materielOrder.getId(), MaterielOrderStatusEnum.FACTORY_PACKAGE.getCode());
                if (FACTORY_PACKAGE_orderFollowRecord != null) {
                    orderFollowRecordVo.setFactoryPackageTime(DateUtil.formatToStrTimeV1(FACTORY_PACKAGE_orderFollowRecord.getCreatedTime()));
                }
                orderFollowRecordVoArrayList.add(orderFollowRecordVo);
            }


            return WebApiResponse.success(orderFollowRecordVoArrayList);
        } catch (Exception ex) {
            logger.error("获取采购单生产跟单信息异常", ex);
            return WebApiResponse.error("获取采购单生产跟单信息异常");
        }
    }
}
