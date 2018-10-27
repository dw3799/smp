package com.alipapa.smp.order.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alipapa.smp.common.Constant;
import com.alipapa.smp.common.enums.*;
import com.alipapa.smp.common.request.UserInfo;
import com.alipapa.smp.common.request.UserStatus;
import com.alipapa.smp.consumer.pojo.Consumer;
import com.alipapa.smp.order.pojo.*;
import com.alipapa.smp.order.service.*;
import com.alipapa.smp.order.service.impl.SubOrderServiceProxy;
import com.alipapa.smp.order.vo.*;
import com.alipapa.smp.product.pojo.Product;
import com.alipapa.smp.product.pojo.ProductCategory;
import com.alipapa.smp.product.pojo.ProductPicture;
import com.alipapa.smp.product.pojo.Supplier;
import com.alipapa.smp.product.service.ProductCategoryService;
import com.alipapa.smp.product.service.ProductPictureService;
import com.alipapa.smp.product.service.ProductService;
import com.alipapa.smp.product.service.SupplierService;
import com.alipapa.smp.user.pojo.Role;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.alipapa.smp.utils.WebApiResponse.error;
import static com.alipapa.smp.utils.WebApiResponse.success;

/**
 * 采购订单接口
 *
 * @author liuwei
 * @version 2.0
 * 2018-09-16
 */
@RestController
@CrossOrigin
@RequestMapping("/api/subOrder")
public class SubOrderController {
    private static Logger logger = LoggerFactory.getLogger(SubOrderController.class);

    @Autowired
    private OrderService orderService;

    @Autowired
    private SubOrderServiceProxy subOrderServiceProxy;

    @Autowired
    private SubOrderService subOrderService;

    @Autowired
    private OrderWorkFlowService orderWorkFlowService;

    @Autowired
    private MaterielOrderService materielOrderService;

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductCategoryService productCategoryService;

    @Autowired
    private ProductPictureService productPictureService;

    @Autowired
    private SupplierService supplierService;

    @Autowired
    private UserService userService;

    @Autowired
    private PurchaseOrderExtService purchaseOrderExtService;

    @Autowired
    private RoleService roleService;


    /**
     * 采购获取待提交采购单
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/listUnSubmitSubOrder", method = RequestMethod.GET)
    public WebApiResponse<List<SubOrderVo>> listUnSubmitSubOrder(@RequestParam(name = "pageSize", required = false) Integer pageSize,
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

            List<SubOrderVo> orderVoList = subOrderServiceProxy.listMySubOrder(SubOrderStatusEnum.BUYER_ORDER, userInfo.getUserNo(), start, size);
            if (!CollectionUtils.isEmpty(orderVoList)) {
                WebApiResponse response = WebApiResponse.success(orderVoList);
                response.setTotalCount(orderVoList.get(0).getTotalCount());
                return response;
            }
            return WebApiResponse.success(null);
        } catch (Exception ex) {
            logger.error("获取待提交采购单异常", ex);
            return WebApiResponse.error("获取待提交采购单异常");
        }
    }


    /**
     * 获取采购单基本信息
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/listSubOrderDetailInfo", method = RequestMethod.GET)
    public WebApiResponse<OrderProductVo> listSubOrderDetailInfo(@RequestParam("subOrderNo") String subOrderNo) {
        if (StringUtil.isEmptyString(subOrderNo)) {
            return WebApiResponse.error("参数不能为空");
        }

        SubOrder subOrder = subOrderService.getSubOrderBySubOrderNo(subOrderNo);
        if (subOrder == null) {
            return WebApiResponse.error("采购订单不存在");
        }

        Order order = orderService.selectOrderByOrderNo(subOrder.getOrderNo());
        String currencyDec = orderService.getCurrencyDec(order);

        OrderTypeEnum orderTypeEnum = OrderTypeEnum.valueOf(order.getOrderType());
        if (orderTypeEnum == null) {
            return error("订单类型有误");
        }
        try {

            OrderProductVo orderProductVo = new OrderProductVo();
            orderProductVo.setSubOrderNo(subOrder.getSubOrderNo());
            orderProductVo.setOrderNo(subOrder.getOrderNo());
            orderProductVo.setProductCategoryId(subOrder.getProductCategoryId());
            orderProductVo.setProductCategory(subOrder.getProductCategory());
            orderProductVo.setProductId(subOrder.getProductId());
            orderProductVo.setProductName(subOrder.getProductName());
            orderProductVo.setProductAmount(PriceUtil.convertToYuanStr(subOrder.getProductAmount()) + currencyDec);
            orderProductVo.setExpectPurchaseAmount(PriceUtil.convertToYuanStr(subOrder.getExpectPurchaseAmount()) + Constant.YMB);
            orderProductVo.setProductRemark(subOrder.getProductRemark());
            orderProductVo.setPicNo(subOrder.getPic());
            orderProductVo.setOrderType(OrderTypeEnum.valueOf(order.getOrderType()).getDec());
            if (OrderTypeEnum.SELF_ORDER == orderTypeEnum) {
                SelfOrderDetail selfOrderDetail = subOrder.getSelfOrderDetail();
                orderProductVo.setSaleAmount(PriceUtil.convertToYuanStr(subOrder.getSaleAmount()) + currencyDec);
                orderProductVo.setFactoryAmount(PriceUtil.convertToYuanStr(subOrder.getFactoryAmount()) + Constant.YMB);

                orderProductVo.setWeight(selfOrderDetail.getWeight());
                orderProductVo.setMaterial(selfOrderDetail.getMaterial());
                orderProductVo.setSize(selfOrderDetail.getSize());
                orderProductVo.setColor(selfOrderDetail.getColor());
                orderProductVo.setSuturing(selfOrderDetail.getSuturing());

                orderProductVo.setPrinting(selfOrderDetail.getPrinting());
                orderProductVo.setQuantity(selfOrderDetail.getQuantity());
            } else {
                AgentOrderDetail agentOrderDetail = subOrder.getAgentOrderDetail();

                orderProductVo.setSaleAmount(PriceUtil.convertToYuanStr(subOrder.getSaleAmount()) + currencyDec);
                orderProductVo.setFactoryAmount(PriceUtil.convertToYuanStr(subOrder.getFactoryAmount()) + Constant.YMB);
                orderProductVo.setUnit(agentOrderDetail.getUnit());
                orderProductVo.setSinglePackageCount(agentOrderDetail.getSinglePackageCount());
                orderProductVo.setPackageNumber(agentOrderDetail.getPackageNumber());
                orderProductVo.setSingleVolume(agentOrderDetail.getSingleVolume());
                orderProductVo.setSingleWeight(agentOrderDetail.getSingleWeight());
                orderProductVo.setTotalVolume(agentOrderDetail.getTotalVolume());
                orderProductVo.setTotalWeight(agentOrderDetail.getTotalWeight());
            }
            orderProductVo.setCreatedTime(DateUtil.formatToStrTimeV1(subOrder.getCreatedTime()));
            orderProductVo.setSubOrderStatus(SubOrderStatusEnum.valueOf(subOrder.getSubOrderStatus()).getDec());
            return WebApiResponse.success(orderProductVo);

        } catch (Exception ex) {
            logger.error("获取采购单基本信息异常", ex);
            return WebApiResponse.error("获取采购单基本信息异常");
        }
    }


    /**
     * 获取采购单流转记录
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/listSubOrderWorkFlow", method = RequestMethod.GET)
    public WebApiResponse<List<OrderWorkFlowVo>> listSubOrderWorkFlow(@RequestParam("subOrderNo") String subOrderNo) {

        if (StringUtil.isEmptyString(subOrderNo)) {
            return WebApiResponse.error("参数不能为空");
        }

        SubOrder subOrder = subOrderService.getSubOrderBySubOrderNo(subOrderNo);
        if (subOrder == null) {
            return WebApiResponse.error("采购订单不存在");
        }

        List<OrderWorkFlowVo> orderWorkFlowVoList = new ArrayList<>();
        try {
            List<OrderWorkFlow> orderWorkFlowList = orderWorkFlowService.listOrderWorkFlowByParams(subOrderNo, OrderWorkFlowTypeEnum.SUB_ORDER.getCodeName());
            if (!CollectionUtils.isEmpty(orderWorkFlowList)) {
                for (OrderWorkFlow orderWorkFlow : orderWorkFlowList) {
                    OrderWorkFlowVo orderWorkFlowVo = new OrderWorkFlowVo();
                    orderWorkFlowVo.setCreatedTime(DateUtil.formatToStrTimeV1(orderWorkFlow.getCreatedTime()));
                    orderWorkFlowVo.setOpUserRole(orderWorkFlow.getOpUserRole());
                    orderWorkFlowVo.setOrderNo(subOrderNo);
                    orderWorkFlowVo.setOpUserName(orderWorkFlow.getOpUserName());
                    orderWorkFlowVo.setRemark(orderWorkFlow.getRemark());
                    orderWorkFlowVo.setResult(orderWorkFlow.getResult());
                    orderWorkFlowVoList.add(orderWorkFlowVo);
                }

            }
        } catch (Exception ex) {
            logger.error("获取采购单流转记录异常", ex);
            return WebApiResponse.error("获取采购单流转记录异常");
        }

        return WebApiResponse.success(orderWorkFlowVoList);
    }

    /**
     * 保存物料订单明细（待提交采购订单页面）
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/saveMaterielOrder", method = RequestMethod.POST)
    public WebApiResponse<String> saveMaterielOrder(HttpServletRequest request) {
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

            if (subOrder.getSubOrderStatus() != SubOrderStatusEnum.BUYER_ORDER.getCode()) {
                return error("已提交不能修改！");
            }

            String materiels = request.getParameter("materiels");
            String opType = request.getParameter("opType");

            logger.info("opType:" + opType);
            logger.info("materiels:" + materiels);

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

            Long totalPurchaseAmount = 0L;
            Long totalPurchaseFrontAmount = 0L;

            List<MaterielOrder> materielOrderList = new ArrayList<>();

            JSONArray materielsArray = JSONArray.parseArray(materiels);
            for (int i = 0; i < materielsArray.size(); i++) {
                JSONObject jsonObject = materielsArray.getJSONObject(i);

                      /*materielOrderId:5,//物料订单ID.不传即为新增物料
                        productId:"122",//产品ID
                        supplierId:12,//供应商ID
                        purchaseAmount:"18800.22",//产品总金额（外币），销售单价*数量
                        purchaseFrontAmount:"1109.12",//预估产品采购总价(人民币),创建订单时写入
                        remark:"毛皮要厚的"//生产要求备注*/

                Long materielOrderId = jsonObject.getLong("materielOrderId");
                Long productId = jsonObject.getLong("productId");
                Long supplierId = jsonObject.getLong("supplierId");

                String purchaseAmount = jsonObject.getString("purchaseAmount");
                String purchaseFrontAmount = jsonObject.getString("purchaseFrontAmount");
                String mRemark = jsonObject.getString("remark");


                MaterielOrder materielOrder = null;
                if (materielOrderId != null && materielOrderId > 0) {
                    materielOrder = materielOrderService.selectMaterielOrderById(materielOrderId);
                }
                if (materielOrder == null) {
                    materielOrder = new MaterielOrder();
                }

                if (materielOrderService.isCanEdit(materielOrder)) {
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
                    materielOrder.setActualPurchaseAmount(0L);
                    materielOrder.setActualTailAmount(0L);
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

            PurchaseOrderExt purchaseOrderExt = purchaseOrderExtService.getPurchaseOrderExtBySubOrderNo(subOrder.getSubOrderNo());
            if (purchaseOrderExt != null && orderOPerateTypeEnum == OrderOPerateTypeEnum.SUBMIT) {
                purchaseOrderExt.setPurchaseFrontAmount(totalPurchaseFrontAmount);
                purchaseOrderExt.setSubmitTime(new Date());
                purchaseOrderExt.setUpdatedTime(new Date());
                purchaseOrderExtService.updatePurchaseOrderExt(purchaseOrderExt);
            }

            subOrder.setActualPurchaseAmount(totalPurchaseAmount);
            subOrder.setProductFrontAmount(totalPurchaseFrontAmount);
            subOrderServiceProxy.saveMaterielOrder(order, subOrder, materielOrderList);
            return success("success");
        } catch (Exception ex) {
            logger.error("保存物料订单异常", ex);
            return error("保存物料订单异常");
        }
    }

    /**
     * 获取物料单
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/listMaterielOrders", method = RequestMethod.GET)
    public WebApiResponse<MaterielListVo> listMaterielOrders(@RequestParam("subOrderNo") String subOrderNo) {
        if (StringUtil.isEmptyString(subOrderNo)) {
            return WebApiResponse.error("参数不能为空");
        }

        SubOrder subOrder = subOrderService.getSubOrderBySubOrderNo(subOrderNo);
        if (subOrder == null) {
            return WebApiResponse.error("采购订单不存在");
        }

        Order order = orderService.selectOrderByOrderNo(subOrder.getOrderNo());
        String currencyDec = orderService.getCurrencyDec(order);

        OrderTypeEnum orderTypeEnum = OrderTypeEnum.valueOf(order.getOrderType());
        if (orderTypeEnum == null) {
            return error("订单类型有误");
        }
        try {
            MaterielListVo materielListVo = new MaterielListVo();
            List<MaterielOrder> materielOrderList = materielOrderService.listMaterielOrderBySubOrderNo(subOrderNo);


            materielListVo.setSubOrderNo(subOrderNo);

            if (!CollectionUtils.isEmpty(materielOrderList)) {
                List<MaterielOrderVo> materielOrderVoList = new ArrayList<>();

                Long purchaseFrontAmount = 0L;
                Long totalPurchaseAmount = 0L;
                Long payedAmount = 0L;
                for (MaterielOrder materielOrder : materielOrderList) {
                    MaterielOrderVo materielOrderVo = new MaterielOrderVo();
                    materielOrderVo.setMaterielOrderId(materielOrder.getId());
                    materielOrderVo.setMaterielOrderStatusCode(MaterielOrderStatusEnum.valueOf(materielOrder.getMaterielOrderStatus()).getCode());
                    materielOrderVo.setMaterielOrderStatus(MaterielOrderStatusEnum.valueOf(materielOrder.getMaterielOrderStatus()).getDec());
                    materielOrderVo.setOrderNo(materielOrder.getOrderNo());
                    materielOrderVo.setProductCategory(materielOrder.getProductCategory());
                    materielOrderVo.setProductCategoryId(materielOrder.getProductCategoryId());
                    materielOrderVo.setProductId(materielOrder.getProductId());
                    materielOrderVo.setProductName(materielOrder.getProductName());
                    materielOrderVo.setPurchaseAmount(PriceUtil.convertToYuanStr(materielOrder.getPurchaseAmount()));
                    materielOrderVo.setPurchaseFrontAmount(PriceUtil.convertToYuanStr(materielOrder.getPurchaseFrontAmount()));
                    materielOrderVo.setRemark(materielOrder.getRemark());
                    materielOrderVo.setSubOrderNo(materielOrder.getSubOrderNo());
                    materielOrderVo.setSupplierBankAccount(materielOrder.getSupplierBankAccount());
                    materielOrderVo.setSupplierBankName(materielOrder.getSupplierBankName());
                    materielOrderVo.setSupplierBankNo(materielOrder.getSupplierBankNo());
                    materielOrderVo.setSupplierCharge(materielOrder.getSupplierCharge());
                    materielOrderVo.setSupplierId(materielOrder.getSupplierId());
                    materielOrderVo.setSupplierMobile(materielOrder.getSupplierMobile());
                    materielOrderVo.setSupplierName(materielOrder.getSupplierName());

                    materielOrderVo.setRestAmount(PriceUtil.convertToYuanStr(materielOrder.getPurchaseAmount() - materielOrder.getActualPurchaseAmount()));

                    payedAmount = payedAmount + materielOrder.getActualPurchaseAmount();

                    totalPurchaseAmount = totalPurchaseAmount + materielOrder.getPurchaseAmount();
                    purchaseFrontAmount = purchaseFrontAmount + materielOrder.getPurchaseFrontAmount();
                    materielOrderVoList.add(materielOrderVo);
                }
                materielListVo.setExpectPurchaseAmount(PriceUtil.convertToYuanStr(order.getExpectPurchaseAmount()) + Constant.YMB);
                materielListVo.setTotalPurchaseAmount(PriceUtil.convertToYuanStr(totalPurchaseAmount) + Constant.YMB);
                materielListVo.setPurchaseFrontAmount(PriceUtil.convertToYuanStr(purchaseFrontAmount) + Constant.YMB);
                materielListVo.setTotalRestAmount(PriceUtil.convertToYuanStr(totalPurchaseAmount - purchaseFrontAmount) + Constant.YMB);
                materielListVo.setNeedRestAmount(PriceUtil.convertToYuanStr(totalPurchaseAmount - purchaseFrontAmount) + Constant.YMB);
                materielListVo.setMaterielOrders(materielOrderVoList);
            }

            return WebApiResponse.success(materielListVo);
        } catch (Exception ex) {
            logger.error("获取物料单异常", ex);
            return error("获取物料单异常");
        }
    }


    /**
     * 主管获取待审核采购单
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/listSuperApvSubOrder", method = RequestMethod.GET)
    public WebApiResponse<List<SubOrderVo>> listSuperApvSubOrder(@RequestParam(name = "pageSize", required = false) Integer pageSize,
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

            User user = userService.getUserById(userInfo.getUserId());
            Role role = roleService.getRoleById(userInfo.getRoleId());
            List<SubOrderVo> orderVoList = null;

            if (userInfo.getRoleName().equals(RoleEnum.admin.getCodeName())) {
                orderVoList = subOrderServiceProxy.listGroupSubOrder(SubOrderStatusEnum.SPR_BUYER_APV, null, start, size);
            } else {
                if (1 != user.getIsLeader() && !"1".equals(role.getRoleLevel())) { //组长且为主管有权限
                    return error("没有权限");
                }
                orderVoList = subOrderServiceProxy.listGroupSubOrder(SubOrderStatusEnum.SPR_BUYER_APV, user.getGroupId(), start, size);
            }

            if (!CollectionUtils.isEmpty(orderVoList)) {
                WebApiResponse response = WebApiResponse.success(orderVoList);
                response.setTotalCount(orderVoList.get(0).getTotalCount());
                return response;
            }
            return WebApiResponse.success(null);
        } catch (Exception ex) {
            logger.error("主管获取待审核采购单", ex);
            return WebApiResponse.error("主管获取待审核采购单");
        }
    }


    /**
     * 采购主管审核采购订单
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/approve-order", method = RequestMethod.POST)
    public WebApiResponse<String> approveOrder(@RequestParam(name = "subOrderNo") String subOrderNo,
                                               @RequestParam(name = "result") String result,
                                               @RequestParam(name = "remark", required = false) String remark) {
        UserInfo userInfo = UserStatus.getUserInfo();
        try {
            if (StringUtil.isEmptyString(subOrderNo) || StringUtil.isEmptyString(result)) {
                return error("缺少必传参数");
            }

            SubOrder subOrder = subOrderService.getSubOrderBySubOrderNo(subOrderNo);
            if (subOrder == null) {
                return WebApiResponse.error("采购单不存在");
            }

            if (!RoleEnum.admin.getCodeName().equals(userInfo.getRoleName())) {
                if (!RoleEnum.superBuyer.getCodeName().equals(userInfo.getRoleName())) {
                    return error("没有权限");
                }
            }

            if (subOrder.getSubOrderStatus() != SubOrderStatusEnum.SPR_BUYER_APV.getCode()) {
                return error("当前状态不能审核！");
            }


            User user = userService.getUserByUserNo(userInfo.getUserNo());

            if ("N".equals(result)) {
                subOrder.setSubOrderStatus(SubOrderStatusEnum.BUYER_ORDER.getCode());

                List<MaterielOrder> materielOrderList = materielOrderService.listMaterielOrderBySubOrderNo(subOrderNo);
                for (MaterielOrder materielOrder : materielOrderList) {
                    materielOrder.setMaterielOrderStatus(MaterielOrderStatusEnum.BUYER_ORDER.getCode());
                    materielOrder.setRemark(materielOrder.getRemark());
                    materielOrderService.updateMaterielOrder(materielOrder);
                }
                subOrderService.updateSubOrder(subOrder);

                //保存订单流转记录
                OrderWorkFlow orderWorkFlow = new OrderWorkFlow();
                orderWorkFlow.setCreatedTime(new Date());
                orderWorkFlow.setNewOrderStatus(subOrder.getSubOrderStatus());
                orderWorkFlow.setOldOrderStatus(SubOrderStatusEnum.SPR_BUYER_APV.getCode());
                orderWorkFlow.setOpUserName(user.getName());
                orderWorkFlow.setOpUserNo(userInfo.getUserNo());
                orderWorkFlow.setOpUserRole(RoleEnum.superBuyer.getDec());
                orderWorkFlow.setOrderNo(subOrder.getSubOrderNo());
                orderWorkFlow.setType(OrderWorkFlowTypeEnum.SUB_ORDER.getCodeName());
                orderWorkFlow.setRemark(remark);
                orderWorkFlow.setResult("主管审核不通过");
                orderWorkFlow.setUpdatedTime(new Date());
                orderWorkFlowService.save(orderWorkFlow);
            } else if ("Y".equals(result)) {
                subOrder.setSubOrderStatus(SubOrderStatusEnum.SUB_FIN_FRONT_APV.getCode());

                PurchaseOrderExt purchaseOrderExt = purchaseOrderExtService.getPurchaseOrderExtBySubOrderNo(subOrder.getSubOrderNo());
                if (purchaseOrderExt != null) {
                    purchaseOrderExt.setSuperApvTime(new Date());
                    purchaseOrderExt.setUpdatedTime(new Date());
                    purchaseOrderExtService.updatePurchaseOrderExt(purchaseOrderExt);
                }

                List<MaterielOrder> materielOrderList = materielOrderService.listMaterielOrderBySubOrderNo(subOrderNo);
                for (MaterielOrder materielOrder : materielOrderList) {
                    //未审核的物料订单，改状态
                    if (materielOrder.getMaterielOrderStatus() < MaterielOrderStatusEnum.SUB_FIN_FRONT_APV.getCode())
                        materielOrder.setMaterielOrderStatus(MaterielOrderStatusEnum.SUB_FIN_FRONT_APV.getCode());
                    materielOrderService.updateMaterielOrder(materielOrder);
                }
                subOrderService.updateSubOrder(subOrder);

                //保存订单流转记录
                OrderWorkFlow orderWorkFlow = new OrderWorkFlow();
                orderWorkFlow.setCreatedTime(new Date());
                orderWorkFlow.setNewOrderStatus(subOrder.getSubOrderStatus());
                orderWorkFlow.setOldOrderStatus(SubOrderStatusEnum.SPR_BUYER_APV.getCode());
                orderWorkFlow.setOpUserName(user.getName());
                orderWorkFlow.setOpUserNo(userInfo.getUserNo());
                orderWorkFlow.setOpUserRole(RoleEnum.supervisor.getDec());
                orderWorkFlow.setOrderNo(subOrder.getSubOrderNo());
                orderWorkFlow.setType(OrderWorkFlowTypeEnum.SUB_ORDER.getCodeName());
                orderWorkFlow.setRemark(remark);
                orderWorkFlow.setResult("主管审核通过");
                orderWorkFlow.setUpdatedTime(new Date());
                orderWorkFlowService.save(orderWorkFlow);
            } else {
                return error("参数有误");
            }
        } catch (Exception ex) {
            logger.error("采购主管审核采购订单异常", ex);
            return error("采购主管审核采购订单异常");
        }
        return WebApiResponse.success("success");
    }


    /**
     * 待财务审核采购单定金列表
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/listFinFrontApvSubOrder", method = RequestMethod.GET)
    public WebApiResponse<List<SubOrderVo>> listFinFrontApvSubOrder(@RequestParam(name = "pageSize", required = false) Integer pageSize,
                                                                    @RequestParam(name = "pageNum", required = false) Integer pageNum) {
        UserInfo userInfo = UserStatus.getUserInfo();
/*        if (!RoleEnum.admin.getCodeName().equals(userInfo.getRoleName())) {
            if (userInfo.getRoleName().equals(RoleEnum.financial.getCodeName())) {
                return error("没有权限");
            }
        }*/

        try {
            if (pageSize == null) {
                pageSize = 30;
            }

            if (pageNum == null) {
                pageNum = 1;
            }

            Integer start = (pageNum - 1) * pageSize;
            Integer size = pageSize;

            List<SubOrderVo> orderVoList = subOrderServiceProxy.listMySubOrder(SubOrderStatusEnum.SUB_FIN_FRONT_APV, null, start, size);
            if (!CollectionUtils.isEmpty(orderVoList)) {
                WebApiResponse response = WebApiResponse.success(orderVoList);
                response.setTotalCount(orderVoList.get(0).getTotalCount());
                return response;
            }
            return WebApiResponse.success(null);
        } catch (Exception ex) {
            logger.error("待财务审核采购单定金列表异常", ex);
            return error("待财务审核采购单定金列表异常");
        }
    }


    /**
     * 财务审核采购单定金信息异常
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/finApproveSubOrderFrontPay", method = RequestMethod.POST)
    public WebApiResponse<String> finApproveSubOrderFrontPay(@RequestParam(name = "subOrderNo") String subOrderNo,
                                                             @RequestParam(name = "result") String result,
                                                             @RequestParam(name = "remark", required = false) String remark) {
        UserInfo userInfo = UserStatus.getUserInfo();
        try {
            if (StringUtil.isEmptyString(subOrderNo) || StringUtil.isEmptyString(result)) {
                return error("缺少必传参数");
            }

            SubOrder subOrder = subOrderService.getSubOrderBySubOrderNo(subOrderNo);
            if (subOrder == null) {
                return WebApiResponse.error("采购单不存在");
            }

            if (!RoleEnum.admin.getCodeName().equals(userInfo.getRoleName())) {
                if (!RoleEnum.financial.getCodeName().equals(userInfo.getRoleName())) {
                    return error("没有权限");
                }
            }

            if (subOrder.getSubOrderStatus() != SubOrderStatusEnum.SUB_FIN_FRONT_APV.getCode()) {
                return error("当前状态财务无法审核定金！");
            }

            User user = userService.getUserByUserNo(userInfo.getUserNo());

            if ("N".equals(result)) {
                subOrder.setSubOrderStatus(SubOrderStatusEnum.BUYER_ORDER.getCode());

                List<MaterielOrder> materielOrderList = materielOrderService.listMaterielOrderBySubOrderNo(subOrderNo);
                for (MaterielOrder materielOrder : materielOrderList) {
                    materielOrder.setMaterielOrderStatus(MaterielOrderStatusEnum.BUYER_ORDER.getCode());
                    materielOrder.setRemark(materielOrder.getRemark());
                    materielOrderService.updateMaterielOrder(materielOrder);
                }
                subOrderService.updateSubOrder(subOrder);

                //保存订单流转记录
                OrderWorkFlow orderWorkFlow = new OrderWorkFlow();
                orderWorkFlow.setCreatedTime(new Date());
                orderWorkFlow.setNewOrderStatus(subOrder.getSubOrderStatus());
                orderWorkFlow.setOldOrderStatus(SubOrderStatusEnum.SUB_FIN_FRONT_APV.getCode());
                orderWorkFlow.setOpUserName(user.getName());
                orderWorkFlow.setOpUserNo(userInfo.getUserNo());
                orderWorkFlow.setOpUserRole(RoleEnum.financial.getDec());
                orderWorkFlow.setOrderNo(subOrder.getSubOrderNo());
                orderWorkFlow.setType(OrderWorkFlowTypeEnum.SUB_ORDER.getCodeName());
                orderWorkFlow.setRemark(remark);
                orderWorkFlow.setResult("财务审核不通过");
                orderWorkFlow.setUpdatedTime(new Date());
                orderWorkFlowService.save(orderWorkFlow);
            } else if ("Y".equals(result)) {
                subOrder.setSubOrderStatus(SubOrderStatusEnum.SUB_CASH_FRONT_APV.getCode());

                PurchaseOrderExt purchaseOrderExt = purchaseOrderExtService.getPurchaseOrderExtBySubOrderNo(subOrder.getSubOrderNo());
                if (purchaseOrderExt != null) {
                    purchaseOrderExt.setFinFrontTime(new Date());
                    purchaseOrderExt.setUpdatedTime(new Date());
                    purchaseOrderExtService.updatePurchaseOrderExt(purchaseOrderExt);
                }


                List<MaterielOrder> materielOrderList = materielOrderService.listMaterielOrderBySubOrderNo(subOrderNo);
                for (MaterielOrder materielOrder : materielOrderList) {
                    //未审核的物料订单，改状态
                    if (materielOrder.getMaterielOrderStatus() < MaterielOrderStatusEnum.SUB_CASH_FRONT_APV.getCode())
                        materielOrder.setMaterielOrderStatus(MaterielOrderStatusEnum.SUB_CASH_FRONT_APV.getCode());
                    materielOrderService.updateMaterielOrder(materielOrder);
                }
                subOrderService.updateSubOrder(subOrder);

                //保存订单流转记录
                OrderWorkFlow orderWorkFlow = new OrderWorkFlow();
                orderWorkFlow.setCreatedTime(new Date());
                orderWorkFlow.setNewOrderStatus(subOrder.getSubOrderStatus());
                orderWorkFlow.setOldOrderStatus(SubOrderStatusEnum.SPR_BUYER_APV.getCode());
                orderWorkFlow.setOpUserName(user.getName());
                orderWorkFlow.setOpUserNo(userInfo.getUserNo());
                orderWorkFlow.setOpUserRole(RoleEnum.financial.getDec());
                orderWorkFlow.setOrderNo(subOrder.getSubOrderNo());
                orderWorkFlow.setType(OrderWorkFlowTypeEnum.SUB_ORDER.getCodeName());
                orderWorkFlow.setRemark(remark);
                orderWorkFlow.setResult("财务审核通过");
                orderWorkFlow.setUpdatedTime(new Date());
                orderWorkFlowService.save(orderWorkFlow);
            } else {
                return error("参数有误");
            }

        } catch (Exception ex) {
            logger.error("财务审核采购单定金信息异常", ex);
            return error("财务审核采购单定金信息异常");
        }
        return WebApiResponse.success("success");
    }

    /**
     * 待出纳支付采购单定金列表
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/listCashFrontSubOrder", method = RequestMethod.GET)
    public WebApiResponse<List<SubOrderVo>> listCashFrontSubOrder(@RequestParam(name = "pageSize", required = false) Integer pageSize,
                                                                  @RequestParam(name = "pageNum", required = false) Integer pageNum) {
        UserInfo userInfo = UserStatus.getUserInfo();
/*        if (!RoleEnum.admin.getCodeName().equals(userInfo.getRoleName())) {
            if (userInfo.getRoleName().equals(RoleEnum.financial.getCodeName())) {
                return error("没有权限");
            }
        }*/

        try {
            if (pageSize == null) {
                pageSize = 30;
            }

            if (pageNum == null) {
                pageNum = 1;
            }

            Integer start = (pageNum - 1) * pageSize;
            Integer size = pageSize;

            List<SubOrderVo> orderVoList = subOrderServiceProxy.listMySubOrder(SubOrderStatusEnum.SUB_CASH_FRONT_APV, null, start, size);
            if (!CollectionUtils.isEmpty(orderVoList)) {
                WebApiResponse response = WebApiResponse.success(orderVoList);
                response.setTotalCount(orderVoList.get(0).getTotalCount());
                return response;
            }
            return WebApiResponse.success(null);
        } catch (Exception ex) {
            logger.error("待出纳支付采购单定金列表异常", ex);
            return error("待出纳支付采购单定金列表异常");
        }
    }


    /**
     * 出纳支付采购订单定金信息
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/cashApproveSubOrderFrontPay", method = RequestMethod.POST)
    public WebApiResponse<String> cashApproveSubOrderFrontPay(@RequestParam(name = "subOrderNo") String subOrderNo,
                                                              @RequestParam(name = "payNo") String payNo,
                                                              @RequestParam(name = "payChannel") String payChannel,
                                                              @RequestParam(name = "result") String result,
                                                              @RequestParam(name = "remark", required = false) String remark) {
        UserInfo userInfo = UserStatus.getUserInfo();
        try {
            if (StringUtil.isEmptyString(subOrderNo) || StringUtil.isEmptyString(result)) {
                return error("缺少必传参数");
            }

            SubOrder subOrder = subOrderService.getSubOrderBySubOrderNo(subOrderNo);
            if (subOrder == null) {
                return WebApiResponse.error("采购单不存在");
            }

            if (!RoleEnum.admin.getCodeName().equals(userInfo.getRoleName())) {
                if (!RoleEnum.cashier.getCodeName().equals(userInfo.getRoleName())) {
                    return error("没有权限");
                }
            }

            if (subOrder.getSubOrderStatus() != SubOrderStatusEnum.SUB_CASH_FRONT_APV.getCode()) {
                return error("当前状态出纳无法支付定金！");
            }

            User user = userService.getUserByUserNo(userInfo.getUserNo());

            if ("N".equals(result)) {
                return error("未支付");
            } else if ("Y".equals(result)) {
                subOrder.setSubOrderStatus(SubOrderStatusEnum.BUYER_FOLLOW_ORDER.getCode());
                subOrder.setSubPayStatus(SubOrderPayStatusEnum.SUB_FRONT_PAY.getCode());

                Long payedAmount = 0L;

                List<MaterielOrder> materielOrderList = materielOrderService.listMaterielOrderBySubOrderNo(subOrderNo);
                for (MaterielOrder materielOrder : materielOrderList) {
                    if (materielOrder.getMaterielOrderStatus() < MaterielOrderStatusEnum.BUYER_FOLLOW_ORDER.getCode()) {
                        materielOrder.setMaterielOrderStatus(MaterielOrderStatusEnum.BUYER_FOLLOW_ORDER.getCode());
                        materielOrder.setPayStatus(MaterielOrderPayStatusEnum.SUB_FRONT_PAY.getCode());
                        materielOrder.setActualPurchaseAmount(materielOrder.getPurchaseFrontAmount());
                        materielOrderService.updateMaterielOrder(materielOrder);
                    }
                    payedAmount = payedAmount + materielOrder.getActualPurchaseAmount();
                }

                subOrder.setPayedAmount(payedAmount);
                subOrderService.updateSubOrder(subOrder);

                PurchaseOrderExt purchaseOrderExt = purchaseOrderExtService.getPurchaseOrderExtBySubOrderNo(subOrder.getSubOrderNo());
                if (purchaseOrderExt != null) {
                    purchaseOrderExt.setCashFrontTime(new Date());
                    purchaseOrderExt.setPayNo(payNo);
                    purchaseOrderExt.setPayChannel(payChannel);
                    purchaseOrderExt.setUpdatedTime(new Date());
                    purchaseOrderExtService.updatePurchaseOrderExt(purchaseOrderExt);
                }

                //保存订单流转记录
                OrderWorkFlow orderWorkFlow = new OrderWorkFlow();
                orderWorkFlow.setCreatedTime(new Date());
                orderWorkFlow.setNewOrderStatus(subOrder.getSubOrderStatus());
                orderWorkFlow.setOldOrderStatus(SubOrderStatusEnum.SUB_CASH_FRONT_APV.getCode());
                orderWorkFlow.setOpUserName(user.getName());
                orderWorkFlow.setOpUserNo(userInfo.getUserNo());
                orderWorkFlow.setOpUserRole(RoleEnum.cashier.getDec());
                orderWorkFlow.setOrderNo(subOrder.getSubOrderNo());
                orderWorkFlow.setType(OrderWorkFlowTypeEnum.SUB_ORDER.getCodeName());
                orderWorkFlow.setRemark(remark);
                orderWorkFlow.setResult("出纳已支付定金");
                orderWorkFlow.setUpdatedTime(new Date());
                orderWorkFlowService.save(orderWorkFlow);
            } else {
                return error("参数有误");
            }

        } catch (Exception ex) {
            logger.error("出纳支付采购订单定金信息异常", ex);
            return error("出纳支付采购订单定金信息异常");
        }
        return WebApiResponse.success("success");
    }
}
