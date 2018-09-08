package com.alipapa.smp.order.controller;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alipapa.smp.common.Constant;
import com.alipapa.smp.common.enums.*;
import com.alipapa.smp.common.request.UserInfo;
import com.alipapa.smp.common.request.UserStatus;
import com.alipapa.smp.consumer.pojo.Consumer;
import com.alipapa.smp.consumer.service.ConsumerService;
import com.alipapa.smp.consumer.service.UserConsumerRelationService;
import com.alipapa.smp.order.pojo.*;
import com.alipapa.smp.order.service.ConsumerFrontPayService;
import com.alipapa.smp.order.service.OrderService;
import com.alipapa.smp.order.service.OrderWorkFlowService;
import com.alipapa.smp.order.service.SubOrderService;
import com.alipapa.smp.order.service.impl.OrderServiceProxy;
import com.alipapa.smp.order.vo.BasicOrderInfo;
import com.alipapa.smp.order.vo.ConsumerFrontPayVo;
import com.alipapa.smp.order.vo.OrderProductVo;
import com.alipapa.smp.order.vo.OrderWorkFlowVo;
import com.alipapa.smp.product.pojo.Product;
import com.alipapa.smp.product.pojo.ProductCategory;
import com.alipapa.smp.product.pojo.ProductPicture;
import com.alipapa.smp.product.service.ProductCategoryService;
import com.alipapa.smp.product.service.ProductPictureService;
import com.alipapa.smp.product.service.ProductService;
import com.alipapa.smp.user.pojo.User;
import com.alipapa.smp.user.service.UserService;
import com.alipapa.smp.utils.*;
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

/**
 * 订单管理接口
 *
 * @author liuwei
 * @version 2.0
 * 2018-08-16
 */
@RestController
@CrossOrigin
@RequestMapping("/api/order")
public class OrderDetailController {
    private static Logger logger = LoggerFactory.getLogger(OrderDetailController.class);


    @Autowired
    private OrderService orderService;


    @Autowired
    private SubOrderService subOrderService;

    @Autowired
    private OrderWorkFlowService orderWorkFlowService;

    @Autowired
    private UserService userService;

    @Autowired
    private ConsumerService consumerService;

    @Autowired
    private UserConsumerRelationService userConsumerRelationService;

    @Autowired
    private OrderServiceProxy orderServiceProxy;

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductCategoryService productCategoryService;

    @Autowired
    private ProductPictureService productPictureService;

    @Autowired
    private ConsumerFrontPayService consumerFrontPayService;

    /**
     * 订单基本信息
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/getBasicOrderInfo", method = RequestMethod.GET)
    public WebApiResponse<BasicOrderInfo> getBasicOrderInfo(@RequestParam("orderNo") String orderNo) {
        UserInfo userInfo = UserStatus.getUserInfo();

        if (StringUtil.isEmptyString(orderNo)) {
            return WebApiResponse.error("参数不能为空");
        }

        Order order = orderService.selectOrderByOrderNo(orderNo);
        if (order == null) {
            return WebApiResponse.error("订单不存在");
        }
        try {
            BasicOrderInfo basicOrderInfo = new BasicOrderInfo();
            RoleEnum roleEnum = RoleEnum.valueOf(userInfo.getRoleName());
            if (roleEnum == RoleEnum.selfBuyer || roleEnum == RoleEnum.storage || roleEnum == RoleEnum.agentBuyer || roleEnum == RoleEnum.superBuyer || roleEnum == RoleEnum.documentation) {
                basicOrderInfo.setConsumerCountry(null);
                basicOrderInfo.setConsumerName(null);
                basicOrderInfo.setConsumerNo(null);
            } else {
                basicOrderInfo.setConsumerCountry(order.getConsumerCountry());
                basicOrderInfo.setConsumerName(order.getConsumerName());
                basicOrderInfo.setConsumerNo(order.getOrderNo());
            }

            String currencyDec = orderService.getCurrencyDec(order);

            basicOrderInfo.setCreateDateTime(DateUtil.formatToStrTimeV1(order.getCreatedTime()));
            basicOrderInfo.setOrderStatus(OrderStatusEnum.valueOf(order.getOrderStatus()).getDec());
            basicOrderInfo.setOrderType(OrderTypeEnum.valueOf(order.getOrderType()).getDec());
            basicOrderInfo.setOrderNo(orderNo);
            basicOrderInfo.setProductionCycle(order.getProductionCycle());
            basicOrderInfo.setSubmitDateTime(DateUtil.formatToStrTimeV1(order.getSubmitTime()));
            basicOrderInfo.setBuyerUserNo(order.getBuyerUserNo());
            basicOrderInfo.setBuyerUserName(order.getBuyerUserName());
            basicOrderInfo.setCurrency(currencyDec);

            basicOrderInfo.setProductAmount(PriceUtil.convertToYuanStr(order.getProductAmount()) + currencyDec);
            basicOrderInfo.setOrderAmount(PriceUtil.convertToYuanStr(order.getOrderAmount()) + currencyDec);

            return WebApiResponse.success(basicOrderInfo);
        } catch (Exception ex) {
            logger.error("获取订单基本信息异常", ex);
            return WebApiResponse.error("获取订单基本信息异常");
        }
    }


    /**
     * 订单产品信息
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/listOrderProductInfo", method = RequestMethod.GET)
    public WebApiResponse<List<OrderProductVo>> listOrderProductInfo(@RequestParam("orderNo") String orderNo) {
        if (StringUtil.isEmptyString(orderNo)) {
            return WebApiResponse.error("参数不能为空");
        }

        Order order = orderService.selectOrderByOrderNo(orderNo);
        if (order == null) {
            return WebApiResponse.error("订单不存在");
        }

        String currencyDec = orderService.getCurrencyDec(order);

        OrderTypeEnum orderTypeEnum = OrderTypeEnum.valueOf(order.getOrderType());
        if (orderTypeEnum == null) {
            return error("订单类型有误");
        }
        try {
            List<OrderProductVo> jsonObjectList = new ArrayList<>();
            List<SubOrder> subOrderList = subOrderService.listSubOrderByOrderNo(orderNo, OrderTypeEnum.valueOf(order.getOrderType()));
            if (!CollectionUtils.isEmpty(subOrderList)) {
                for (SubOrder subOrder : subOrderList) {
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

                    jsonObjectList.add(orderProductVo);
                }

                return WebApiResponse.success(jsonObjectList);
            }
        } catch (Exception ex) {
            logger.error("获取订单产品信息异常", ex);
            return WebApiResponse.error("获取订单产品信息异常");
        }
        return WebApiResponse.success(null);
    }


    /**
     * 获取订单流转记录
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/listOrderWorkFlow", method = RequestMethod.GET)
    public WebApiResponse<List<OrderWorkFlowVo>> listOrderWorkFlow(@RequestParam("orderNo") String orderNo) {

        if (StringUtil.isEmptyString(orderNo)) {
            return WebApiResponse.error("参数不能为空");
        }

        Order order = orderService.selectOrderByOrderNo(orderNo);
        if (order == null) {
            return WebApiResponse.error("订单不存在");
        }

        List<OrderWorkFlowVo> orderWorkFlowVoList = new ArrayList<>();
        try {
            List<OrderWorkFlow> orderWorkFlowList = orderWorkFlowService.listOrderWorkFlowByParams(orderNo, OrderWorkFlowTypeEnum.M_ORDER.getCodeName());
            if (!CollectionUtils.isEmpty(orderWorkFlowList)) {
                for (OrderWorkFlow orderWorkFlow : orderWorkFlowList) {
                    OrderWorkFlowVo orderWorkFlowVo = new OrderWorkFlowVo();
                    orderWorkFlowVo.setCreatedTime(DateUtil.formatToStrTimeV1(orderWorkFlow.getCreatedTime()));
                    orderWorkFlowVo.setOpUserRole(orderWorkFlow.getOpUserRole());
                    orderWorkFlowVo.setOrderNo(orderNo);
                    orderWorkFlowVo.setOpUserName(orderWorkFlow.getOpUserName());
                    orderWorkFlowVo.setRemark(orderWorkFlow.getRemark());
                    orderWorkFlowVo.setResult(orderWorkFlow.getResult());
                    orderWorkFlowVoList.add(orderWorkFlowVo);
                }

            }
        } catch (Exception ex) {
            logger.error("获取订单产品信息异常", ex);
            return WebApiResponse.error("获取订单产品信息异常");
        }

        return WebApiResponse.success(orderWorkFlowVoList);
    }


    /**
     * 更新订单（待提交订单页面）
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/updateOrder", method = RequestMethod.POST)
    public WebApiResponse<String> updateOrder(HttpServletRequest request) {
        UserInfo userInfo = UserStatus.getUserInfo();

        try {
            //不能为空
            String orderNo = request.getParameter("orderNo");

            Order order = orderService.selectOrderByOrderNo(orderNo);
            if (order == null) {
                return WebApiResponse.error("订单不存在");
            }

            if (!order.getSalerUserNo().equals(userInfo.getUserNo())) {
                return error("没有权限");
            }

            if (order.getOrderStatus() != OrderStatusEnum.UN_SUBMIT.getCode() && order.getOrderStatus() != OrderStatusEnum.CREATE.getCode()) {
                return error("已提交不能修改！");
            }

            String orderType = request.getParameter("orderType");
            String consumerNo = request.getParameter("consumerNo");
            String buyerUserNo = request.getParameter("buyerUserNo");
            String productionCycle = request.getParameter("productionCycle");
            String currency = request.getParameter("currency");
            String productAmount = request.getParameter("productAmount");
            String expectPurchaseAmount = request.getParameter("expectPurchaseAmount");
            String products = request.getParameter("products");
            String opType = request.getParameter("opType");

            //可为空
            String orderVolume = request.getParameter("orderVolume");
            String orderWeight = request.getParameter("orderWeight");
            String remark = request.getParameter("remark");

            if (StringUtil.isEmptyString(orderNo) || StringUtil.isEmptyString(opType) || StringUtil.isEmptyString(orderType) || StringUtil.isEmptyString(consumerNo) || StringUtil.isEmptyString(currency) || StringUtil.isEmptyString(productAmount)
                    || StringUtil.isEmptyString(expectPurchaseAmount) || StringUtil.isEmptyString(products)) {
                return error("缺少必填参数");
            }

            OrderTypeEnum orderTypeEnum = OrderTypeEnum.valueOf(orderType);
            if (orderTypeEnum == null) {
                return error("订单类型有误");
            }


            OrderOPerateTypeEnum orderOPerateTypeEnum = OrderOPerateTypeEnum.valueOf(opType);
            if (orderOPerateTypeEnum == null) {
                return error("操作类型有误");
            }

            order.setActualPurchaseAmount(null);
            if (OrderTypeEnum.AGENT_ORDER == orderTypeEnum) {
                buyerUserNo = userInfo.getUserNo();
            }

            User buyer = userService.getUserByUserNo(buyerUserNo);
            if (buyer == null) {
                return error("采购员不存在");
            }
            order.setBuyerUserNo(buyer.getUserNo());
            order.setBuyerUserName(buyer.getName());
            order.setCnReceiptAmount(0L);

            Consumer consumer = consumerService.getConsumerByConsumerNo(consumerNo);
            if (consumer == null) {
                return error("客户不存在");
            }
            order.setConsumerCountry(consumer.getCountry());
            order.setConsumerName(consumer.getName());
            order.setConsumerNo(consumerNo);

            order.setCreatedTime(new Date());
            order.setCurrency(currency);
            order.setExpectPurchaseAmount(PriceUtil.convertToFen(expectPurchaseAmount));
            order.setOrderAmount(PriceUtil.convertToFen(productAmount)); //创建订单时订单金额暂等于产品总金额

            if (OrderOPerateTypeEnum.SAVE == orderOPerateTypeEnum) {
                order.setOrderStatus(OrderStatusEnum.UN_SUBMIT.getCode());
            } else {
                order.setOrderStatus(OrderStatusEnum.SPR_APV.getCode());
                order.setSubmitTime(new Date());
            }

            order.setOrderNo(orderNo);
            order.setOrderType(orderTypeEnum.getCode());
            order.setOrderVolume(orderVolume);
            order.setOrderWeight(orderWeight);
            order.setPayStatus(OrderPayStatusEnum.UN_PAY.getCode());
            order.setProductAmount(PriceUtil.convertToFen(productAmount));
            order.setProductionCycle(productionCycle);
            order.setReceiptAmount(0L);
            order.setRemark(remark);

            User saler = userService.getUserByUserNo(userInfo.getUserNo());
            if (saler == null) {
                return error("业务员不存在");
            }
            order.setSalerUserNo(userInfo.getUserNo());
            order.setSalerUserName(saler.getName());
            order.setUpdatedTime(new Date());

            List<SubOrder> subOrderList = new ArrayList<>();

            JSONArray productsArray = JSONArray.parseArray(products);
            for (int i = 0; i < productsArray.size(); i++) {
                JSONObject jsonObject = productsArray.getJSONObject(i);
                Long productCategoryId = jsonObject.getLong("productCategoryId");
                Long productId = jsonObject.getLong("productId");
                String subProductAmount = jsonObject.getString("productAmount");
                String subExpectPurchaseAmount = jsonObject.getString("expectPurchaseAmount");
                String productRemark = jsonObject.getString("productRemark");
                String subOrderNo = jsonObject.getString("subOrderNo");

                SubOrder subOrder = subOrderService.getSubOrderBySubOrderNo(orderNo, subOrderNo, orderTypeEnum);
                if (subOrder == null) {
                    subOrder = new SubOrder(); //新增产品
                }

                if (productCategoryId == null || productId == null) {
                    return error("产品缺少必填参数");
                }

                Product product = productService.getProductById(productId);
                ProductCategory productCategory = productCategoryService.getProductCategoryById(productCategoryId);

                List<ProductPicture> productPictureList = productPictureService.listProductPictureByProductId(productId);

                if (product == null || productCategory == null) {
                    return error("产品参数异常");
                }

                subOrder.setActualPurchaseAmount(null);
                subOrder.setCreatedTime(new Date());
                subOrder.setExpectPurchaseAmount(PriceUtil.convertToFen(subExpectPurchaseAmount));
                subOrder.setPayedAmount(0L);
                subOrder.setOrderNo(orderNo);


                if (!CollectionUtils.isEmpty(productPictureList)) {
                    ProductPicture productPicture = productPictureList.get(0);
                    subOrder.setMiniPic(productPicture.getPicNo());
                    subOrder.setPic(productPicture.getPicNo());
                }

                subOrder.setProductAmount(PriceUtil.convertToFen(subProductAmount));
                subOrder.setProductCategoryId(productCategoryId);
                subOrder.setProductCategory(productCategory.getCategoryName());
                subOrder.setPayedAmount(0L);
                subOrder.setProductFrontAmount(0L);
                subOrder.setProductId(productId);
                subOrder.setProductName(product.getProductName());
                subOrder.setProductRemark(productRemark);
                subOrder.setRemark(null);

                subOrder.setSubOrderNo(subOrderNo);
                subOrder.setSubOrderStatus(SubOrderStatusEnum.CREATE.getCode());
                subOrder.setSubPayStatus(SubOrderPayStatusEnum.UN_PAY.getCode());
                subOrder.setUpdatedTime(new Date());

                String saleAmount = jsonObject.getString("saleAmount");
                String factoryAmount = jsonObject.getString("factoryAmount");

                subOrder.setFactoryAmount(PriceUtil.convertToFen(factoryAmount));
                subOrder.setSaleAmount(PriceUtil.convertToFen(saleAmount));
                if (subOrder.getId() == null) {
                    subOrder.setSaleNo(Constant.SALE_NO_PREFIX + product.getId() + consumer.getConsumerNo());
                }

                if (OrderTypeEnum.SELF_ORDER == orderTypeEnum) {
                    SelfOrderDetail selfOrderDetail = new SelfOrderDetail();
                    if (subOrder != null && subOrder.getSelfOrderDetail() != null) {
                        selfOrderDetail = subOrder.getSelfOrderDetail();
                    }

                    String weight = jsonObject.getString("weight");
                    String material = jsonObject.getString("material");
                    String size = jsonObject.getString("size");
                    String color = jsonObject.getString("color");
                    String suturing = jsonObject.getString("suturing");
                    String printing = jsonObject.getString("printing");
                    Integer quantity = jsonObject.getInteger("quantity");

                    selfOrderDetail.setColor(color);
                    selfOrderDetail.setCreatedTime(new Date());
                    selfOrderDetail.setMaterial(material);
                    selfOrderDetail.setPrinting(printing);
                    selfOrderDetail.setQuantity(quantity);
                    selfOrderDetail.setRemark(null);
                    selfOrderDetail.setSubOrderNo(subOrderNo);
                    selfOrderDetail.setSize(size);
                    selfOrderDetail.setSuturing(suturing);
                    selfOrderDetail.setUpdatedTime(new Date());
                    selfOrderDetail.setWeight(weight);
                    subOrder.setSelfOrderDetail(selfOrderDetail);
                } else {
                    AgentOrderDetail agentOrderDetail = new AgentOrderDetail();

                    if (subOrder != null && subOrder.getAgentOrderDetail() != null) {
                        agentOrderDetail = subOrder.getAgentOrderDetail();
                    }

                    String unit = jsonObject.getString("unit");
                    Integer singlePackageCount = jsonObject.getInteger("singlePackageCount");
                    Integer packageNumber = jsonObject.getInteger("packageNumber");
                    String singleVolume = jsonObject.getString("singleVolume");
                    String totalVolume = jsonObject.getString("totalVolume");
                    String singleWeight = jsonObject.getString("singleWeight");
                    String totalWeight = jsonObject.getString("totalWeight");

                    agentOrderDetail.setCreatedTime(new Date());
                    agentOrderDetail.setPackageNumber(packageNumber);

                    agentOrderDetail.setSinglePackageCount(singlePackageCount);
                    agentOrderDetail.setSingleVolume(singleVolume);
                    agentOrderDetail.setSingleWeight(singleWeight);
                    agentOrderDetail.setSubOrderNo(subOrderNo);
                    agentOrderDetail.setTotalVolume(totalVolume);
                    agentOrderDetail.setTotalWeight(totalWeight);
                    agentOrderDetail.setUnit(unit);
                    agentOrderDetail.setUpdatedTime(new Date());
                    subOrder.setAgentOrderDetail(agentOrderDetail);
                }

                subOrderList.add(subOrder);
            }

            boolean flag = orderServiceProxy.updateOrder(order, subOrderList);
            if (flag) {
                userConsumerRelationService.updateHasOrder(consumer.getId(), saler.getId());
                return WebApiResponse.success("success");
            }
        } catch (Exception ex) {
            logger.error("", ex);
            return error("更新订单异常");
        }
        return WebApiResponse.error("更新订单异常");
    }


    /**
     * 主管审核业务订单
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/approve-order", method = RequestMethod.POST)
    public WebApiResponse<String> approveOrder(@RequestParam(name = "orderNo") String orderNo,
                                               @RequestParam(name = "consumerLevel", required = false) String consumerLevel,
                                               @RequestParam(name = "result") String result,
                                               @RequestParam(name = "remark", required = false) String remark) {
        UserInfo userInfo = UserStatus.getUserInfo();
        try {
            if (StringUtil.isEmptyString(orderNo) || StringUtil.isEmptyString(result)) {
                return error("缺少必传参数");
            }

            Order order = orderService.selectOrderByOrderNo(orderNo);
            if (order == null) {
                return WebApiResponse.error("订单不存在");
            }

            if (!RoleEnum.admin.getCodeName().equals(userInfo.getRoleName())) {
                if (RoleEnum.supervisor.getCodeName() != userInfo.getRoleName()) {
                    return error("没有权限");
                }
            }

            if (order.getOrderStatus() != OrderStatusEnum.SPR_APV.getCode()) {
                return error("当前状态不能审核！");
            }


            User user = userService.getUserByUserNo(userInfo.getUserNo());

            if ("N".equals(result)) {
                order.setOrderStatus(OrderStatusEnum.UN_SUBMIT.getCode());
                orderService.updateOrder(order);

                //保存订单流转记录
                OrderWorkFlow orderWorkFlow = new OrderWorkFlow();
                orderWorkFlow.setCreatedTime(new Date());
                orderWorkFlow.setNewOrderStatus(order.getOrderStatus());
                orderWorkFlow.setOldOrderStatus(OrderStatusEnum.SPR_APV.getCode());
                orderWorkFlow.setOpUserName(user.getName());
                orderWorkFlow.setOpUserNo(userInfo.getUserNo());
                orderWorkFlow.setOpUserRole(RoleEnum.supervisor.getDec());
                orderWorkFlow.setOrderNo(order.getOrderNo());
                orderWorkFlow.setType(OrderWorkFlowTypeEnum.M_ORDER.getCodeName());
                orderWorkFlow.setRemark(remark);
                orderWorkFlow.setResult("审核不通过");
                orderWorkFlow.setUpdatedTime(new Date());
                orderWorkFlowService.save(orderWorkFlow);
            } else if ("Y".equals(result)) {
                order.setOrderStatus(OrderStatusEnum.UN_FRONT_PAY.getCode());
                orderService.updateOrder(order);

                String thisRemark = "";
                if (StringUtil.isNotEmptyString(consumerLevel)) {
                    Consumer consumer = consumerService.getConsumerByConsumerNo(order.getConsumerNo());
                    consumer.setLevel(consumerLevel);
                    consumerService.updateConsumer(consumer);
                    thisRemark = "客户" + order.getConsumerNo() + "的客户等级更新为" + consumerLevel + ".";
                }

                //保存订单流转记录
                OrderWorkFlow orderWorkFlow = new OrderWorkFlow();
                orderWorkFlow.setCreatedTime(new Date());
                orderWorkFlow.setNewOrderStatus(order.getOrderStatus());
                orderWorkFlow.setOldOrderStatus(OrderStatusEnum.SPR_APV.getCode());
                orderWorkFlow.setOpUserName(user.getName());
                orderWorkFlow.setOpUserNo(userInfo.getUserNo());
                orderWorkFlow.setOpUserRole(RoleEnum.supervisor.getDec());
                orderWorkFlow.setOrderNo(order.getOrderNo());
                orderWorkFlow.setType(OrderWorkFlowTypeEnum.M_ORDER.getCodeName());
                orderWorkFlow.setRemark(thisRemark + remark);
                orderWorkFlow.setResult("审核通过");
                orderWorkFlow.setUpdatedTime(new Date());
                orderWorkFlowService.save(orderWorkFlow);
            } else {
                return error("参数有误");
            }
        } catch (Exception ex) {
            logger.error("主管审核业务订单异常", ex);
            return error("主管审核业务订单异常");
        }
        return WebApiResponse.success("success");
    }


    /**
     * 业务员提交定金
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/commitOrderFrontPay", method = RequestMethod.POST)
    public WebApiResponse<String> commitOrderFrontPay(HttpServletRequest request) {
        UserInfo userInfo = UserStatus.getUserInfo();

        try {
            //不能为空
            String orderNo = request.getParameter("orderNo");

            Order order = orderService.selectOrderByOrderNo(orderNo);
            if (order == null) {
                return WebApiResponse.error("订单不存在");
            }

            if (!order.getSalerUserNo().equals(userInfo.getUserNo())) {
                return error("没有权限");
            }

            if (order.getOrderStatus() != OrderStatusEnum.UN_FRONT_PAY.getCode()) {
                return error("当前状态不能提交定金！");
            }

            String frontAmount = request.getParameter("frontAmount"); //应收定金
            String receiptChannel = request.getParameter("receiptChannel");
            String receiptNo = request.getParameter("receiptNo");
            String payChannel = request.getParameter("payChannel");
            String payNo = request.getParameter("payNo");
            String opType = request.getParameter("opType");

            if (StringUtil.isEmptyString(orderNo) || StringUtil.isEmptyString(opType) || StringUtil.isEmptyString(frontAmount) || StringUtil.isEmptyString(receiptChannel) || StringUtil.isEmptyString(receiptNo) || StringUtil.isEmptyString(payChannel)
                    || StringUtil.isEmptyString(payNo)) {
                return error("缺少必填参数");
            }


            OrderOPerateTypeEnum orderOPerateTypeEnum = OrderOPerateTypeEnum.valueOf(opType);
            if (orderOPerateTypeEnum == null) {
                return error("操作类型有误");
            }

            String royaltyAmount = request.getParameter("royaltyAmount"); //打版费
            String freightAmount = request.getParameter("freightAmount"); //运输费
            String bankFee = request.getParameter("bankFee"); //银行手续费
            String othersFee = request.getParameter("othersFee"); //其他费用


            ConsumerFrontPay consumerFrontPay = consumerFrontPayService.selectConsumerFrontPayByOrderNo(orderNo);
            if (consumerFrontPay == null) {
                consumerFrontPay = new ConsumerFrontPay();
            }

            consumerFrontPay.setActualAmount(null);//出纳补充
            if (StringUtil.isNotEmptyString(bankFee)) {
                consumerFrontPay.setBankFee(PriceUtil.convertToFen(bankFee));
            } else {
                consumerFrontPay.setBankFee(0L);
            }


            if (StringUtil.isNotEmptyString(othersFee)) {
                consumerFrontPay.setOthersFee(PriceUtil.convertToFen(othersFee));
            } else {
                consumerFrontPay.setOthersFee(0L);
            }

            if (StringUtil.isNotEmptyString(freightAmount)) {
                consumerFrontPay.setFreightAmount(PriceUtil.convertToFen(freightAmount));
            } else {
                consumerFrontPay.setFreightAmount(0L);
            }

            if (StringUtil.isNotEmptyString(freightAmount)) {
                consumerFrontPay.setFreightAmount(PriceUtil.convertToFen(freightAmount));
            } else {
                consumerFrontPay.setFreightAmount(0L);
            }

            if (StringUtil.isNotEmptyString(royaltyAmount)) {
                consumerFrontPay.setRoyaltyAmount(PriceUtil.convertToFen(royaltyAmount));
            } else {
                consumerFrontPay.setRoyaltyAmount(0L);
            }
            consumerFrontPay.setFrontAmount(PriceUtil.convertToFen(frontAmount));
            consumerFrontPay.setActualAmount(0L);
            consumerFrontPay.setCnActualAmount(0L); //财务审核时补充
            consumerFrontPay.setConsumerCountry(order.getConsumerCountry());
            consumerFrontPay.setConsumerName(order.getConsumerName());
            consumerFrontPay.setConsumerNo(order.getConsumerNo());

            if (consumerFrontPay.getCreatedTime() == null) {
                consumerFrontPay.setCreatedTime(new Date());
            }

            consumerFrontPay.setExchangeRate(null);//汇率，财务审核时补充
            consumerFrontPay.setOrderNo(orderNo);
            consumerFrontPay.setPayChannel(payChannel);
            consumerFrontPay.setPayNo(payNo);
            consumerFrontPay.setPayTime(new Date());
            consumerFrontPay.setReceiptChannel(receiptChannel);
            consumerFrontPay.setReceiptNo(receiptNo);
            consumerFrontPay.setRemark(null);
            consumerFrontPay.setUpdatedTime(new Date());

            if (OrderOPerateTypeEnum.SUBMIT == orderOPerateTypeEnum) {
                order.setOrderStatus(OrderStatusEnum.CASH_FRONT_APV.getCode());
                order.setPayStatus(OrderPayStatusEnum.FRONT_PAY_APPROVE.getCode());
            }

            //更新订单总金额
            order.setOrderAmount(order.getOrderAmount() + consumerFrontPay.getBankFee() + consumerFrontPay.getFreightAmount() + consumerFrontPay.getOthersFee() + consumerFrontPay.getRoyaltyAmount());

            consumerFrontPayService.saveOrCreateConsumerFrontPay(order, consumerFrontPay);
            orderService.updateOrder(order);
        } catch (Exception ex) {
            logger.error("业务员提交定金异常", ex);
            return error("业务员提交定金异常");
        }
        return WebApiResponse.success("success");
    }


    /**
     * 获取定金信息
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/getOrderFrontPay", method = RequestMethod.GET)
    public WebApiResponse<ConsumerFrontPayVo> getOrderFrontPay(@RequestParam(name = "orderNo") String orderNo) {
        if (StringUtil.isEmptyString(orderNo)) {
            return error("缺少必要参数");
        }

        Order order = orderService.selectOrderByOrderNo(orderNo);
        if (order == null) {
            return WebApiResponse.error("订单不存在");
        }

        try {
            ConsumerFrontPay consumerFrontPay = consumerFrontPayService.selectConsumerFrontPayByOrderNo(orderNo);
            if (consumerFrontPay == null) {
                return WebApiResponse.success(null);
            }

            ConsumerFrontPayVo consumerFrontPayVo = new ConsumerFrontPayVo();

            String currencyDec = orderService.getCurrencyDec(order);
            if (consumerFrontPay.getActualAmount() != null) {
                consumerFrontPayVo.setActualAmount(PriceUtil.convertToYuanStr(consumerFrontPay.getActualAmount()) + currencyDec);
            }
            if (consumerFrontPay.getBankFee() > 0) {
                consumerFrontPayVo.setBankFee(PriceUtil.convertToYuanStr(consumerFrontPay.getBankFee()) + currencyDec);
            }

            if (consumerFrontPay.getFreightAmount() > 0) {
                consumerFrontPayVo.setFreightAmount(PriceUtil.convertToYuanStr(consumerFrontPay.getFreightAmount()) + currencyDec);
            }

            if (consumerFrontPay.getRoyaltyAmount() > 0) {
                consumerFrontPayVo.setRoyaltyAmount(PriceUtil.convertToYuanStr(consumerFrontPay.getRoyaltyAmount()) + currencyDec);
            }

            if (consumerFrontPay.getOthersFee() > 0) {
                consumerFrontPayVo.setOthersFee(PriceUtil.convertToYuanStr(consumerFrontPay.getOthersFee()) + currencyDec);
            }


            if (consumerFrontPay.getFreightAmount() > 0) {
                consumerFrontPayVo.setFreightAmount(PriceUtil.convertToYuanStr(consumerFrontPay.getFreightAmount()) + currencyDec);
            }


            if (consumerFrontPay.getCnActualAmount() != null) {
                consumerFrontPayVo.setCnActualAmount(PriceUtil.convertToYuanStr(consumerFrontPay.getCnActualAmount()) + Constant.YMB);
            }

            consumerFrontPayVo.setExchangeRate(consumerFrontPay.getExchangeRate());

            consumerFrontPayVo.setFrontAmount(PriceUtil.convertToYuanStr(consumerFrontPay.getFrontAmount()) + currencyDec);
            consumerFrontPayVo.setOrderNo(orderNo);
            consumerFrontPayVo.setOrderAmount(PriceUtil.convertToYuanStr(order.getOrderAmount()) + currencyDec);
            consumerFrontPayVo.setPayChannel(consumerFrontPay.getPayChannel());
            consumerFrontPayVo.setPayNo(consumerFrontPay.getPayNo());
            consumerFrontPayVo.setPayTime(DateUtil.formatToStrTimeV1(consumerFrontPay.getPayTime()));
            consumerFrontPayVo.setProductAmount(PriceUtil.convertToYuanStr(order.getProductAmount()) + currencyDec);
            consumerFrontPayVo.setReceiptChannel(consumerFrontPay.getReceiptChannel());
            consumerFrontPayVo.setReceiptNo(consumerFrontPay.getReceiptNo());

            return WebApiResponse.success(consumerFrontPayVo);
        } catch (Exception ex) {
            logger.error("获取定金信息异常", ex);
            return error("获取定金信息异常");
        }
    }


    /**
     * 出纳确认订单定金信息
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/cashApproveFrontPay", method = RequestMethod.POST)
    public WebApiResponse<String> cashApproveFrontPay(@RequestParam(name = "orderNo") String orderNo,
                                                      @RequestParam(name = "actualAmount") String actualAmount,
                                                      @RequestParam(name = "exchangeRate") String exchangeRate,
                                                      @RequestParam(name = "result") String result,
                                                      @RequestParam(name = "remark", required = false) String remark) {
        UserInfo userInfo = UserStatus.getUserInfo();
        try {
            if (StringUtil.isEmptyString(orderNo) || StringUtil.isEmptyString(result) || StringUtil.isEmptyString(actualAmount) || StringUtil.isEmptyString(exchangeRate)) {
                return error("缺少必传参数");
            }

            Order order = orderService.selectOrderByOrderNo(orderNo);
            if (order == null) {
                return WebApiResponse.error("订单不存在");
            }

            if (!RoleEnum.admin.getCodeName().equals(userInfo.getRoleName())) {
                if (RoleEnum.cashier.getCodeName() != userInfo.getRoleName()) {
                    return error("没有权限");
                }
            }
            ConsumerFrontPay consumerFrontPay = consumerFrontPayService.selectConsumerFrontPayByOrderNo(orderNo);
            if (consumerFrontPay == null) {
                return error("定金信息不存在");
            }

            if (order.getOrderStatus() != OrderStatusEnum.CASH_FRONT_APV.getCode()) {
                return error("当前状态无法确认定金！");
            }

            User user = userService.getUserByUserNo(userInfo.getUserNo());

            if ("N".equals(result)) {
                order.setOrderStatus(OrderStatusEnum.UN_FRONT_PAY.getCode());
                orderService.updateOrder(order);

                //保存订单流转记录
                OrderWorkFlow orderWorkFlow = new OrderWorkFlow();
                orderWorkFlow.setCreatedTime(new Date());
                orderWorkFlow.setNewOrderStatus(order.getOrderStatus());
                orderWorkFlow.setOldOrderStatus(OrderStatusEnum.CASH_FRONT_APV.getCode());
                orderWorkFlow.setOpUserName(user.getName());
                orderWorkFlow.setOpUserNo(userInfo.getUserNo());
                orderWorkFlow.setOpUserRole(RoleEnum.cashier.getDec());
                orderWorkFlow.setOrderNo(order.getOrderNo());
                orderWorkFlow.setType(OrderWorkFlowTypeEnum.M_ORDER.getCodeName());
                orderWorkFlow.setRemark(remark);
                orderWorkFlow.setResult("审核不通过");
                orderWorkFlow.setUpdatedTime(new Date());
                orderWorkFlowService.save(orderWorkFlow);
            } else if ("Y".equals(result)) {
                order.setOrderStatus(OrderStatusEnum.FIN_FRONT_APV.getCode());
                orderService.updateOrder(order);

                consumerFrontPay.setExchangeRate(exchangeRate);
                consumerFrontPay.setActualAmount(PriceUtil.convertToFen(actualAmount));
                Double cnActualAccount = Double.parseDouble(actualAmount) * Double.valueOf(consumerFrontPay.getExchangeRate());
                consumerFrontPay.setCnActualAmount(PriceUtil.convertToFen(cnActualAccount));
                consumerFrontPayService.updateConsumerFrontPay(consumerFrontPay);

                //保存订单流转记录
                OrderWorkFlow orderWorkFlow = new OrderWorkFlow();
                orderWorkFlow.setCreatedTime(new Date());
                orderWorkFlow.setNewOrderStatus(order.getOrderStatus());
                orderWorkFlow.setOldOrderStatus(OrderStatusEnum.CASH_FRONT_APV.getCode());
                orderWorkFlow.setOpUserName(user.getName());
                orderWorkFlow.setOpUserNo(userInfo.getUserNo());
                orderWorkFlow.setOpUserRole(RoleEnum.cashier.getDec());
                orderWorkFlow.setOrderNo(order.getOrderNo());
                orderWorkFlow.setType(OrderWorkFlowTypeEnum.M_ORDER.getCodeName());
                orderWorkFlow.setRemark(remark);
                orderWorkFlow.setResult("审核通过");
                orderWorkFlow.setUpdatedTime(new Date());
                orderWorkFlowService.save(orderWorkFlow);
            } else {
                return error("参数有误");
            }

        } catch (Exception ex) {
            logger.error("出纳确认订单定金信息异常", ex);
            return error("出纳确认订单定金信息异常");
        }
        return WebApiResponse.success("success");
    }


    /**
     * 财务审核订单定金信息
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/finApproveFrontPay", method = RequestMethod.POST)
    public WebApiResponse<String> finApproveFrontPay(@RequestParam(name = "orderNo") String orderNo,
                                                     @RequestParam(name = "result") String result,
                                                     @RequestParam(name = "remark", required = false) String remark) {
        UserInfo userInfo = UserStatus.getUserInfo();
        try {
            if (StringUtil.isEmptyString(orderNo) || StringUtil.isEmptyString(result)) {
                return error("缺少必传参数");
            }

            Order order = orderService.selectOrderByOrderNo(orderNo);
            if (order == null) {
                return WebApiResponse.error("订单不存在");
            }
            if (!RoleEnum.admin.getCodeName().equals(userInfo.getRoleName())) {
                if (RoleEnum.financial.getCodeName() != userInfo.getRoleName()) {
                    return error("没有权限");
                }
            }
            
            ConsumerFrontPay consumerFrontPay = consumerFrontPayService.selectConsumerFrontPayByOrderNo(orderNo);
            if (consumerFrontPay == null) {
                return error("定金信息不存在");
            }

            if (order.getOrderStatus() != OrderStatusEnum.FIN_FRONT_APV.getCode()) {
                return error("当前状态财务无法审核定金！");
            }

            User user = userService.getUserByUserNo(userInfo.getUserNo());

            if ("N".equals(result)) {
                order.setOrderStatus(OrderStatusEnum.UN_FRONT_PAY.getCode());
                orderService.updateOrder(order);

                //保存订单流转记录
                OrderWorkFlow orderWorkFlow = new OrderWorkFlow();
                orderWorkFlow.setCreatedTime(new Date());
                orderWorkFlow.setNewOrderStatus(order.getOrderStatus());
                orderWorkFlow.setOldOrderStatus(OrderStatusEnum.FIN_FRONT_APV.getCode());
                orderWorkFlow.setOpUserName(user.getName());
                orderWorkFlow.setOpUserNo(userInfo.getUserNo());
                orderWorkFlow.setOpUserRole(RoleEnum.financial.getDec());
                orderWorkFlow.setOrderNo(order.getOrderNo());
                orderWorkFlow.setType(OrderWorkFlowTypeEnum.M_ORDER.getCodeName());
                orderWorkFlow.setRemark(remark);
                orderWorkFlow.setResult("审核不通过");
                orderWorkFlow.setUpdatedTime(new Date());
                orderWorkFlowService.save(orderWorkFlow);
            } else if ("Y".equals(result)) {
                order.setOrderStatus(OrderStatusEnum.DELIVERY.getCode());
                order.setPayStatus(OrderPayStatusEnum.FRONT_PAY.getCode());
                order.setReceiptAmount(consumerFrontPay.getActualAmount());
                orderService.updateOrder(order);

                //保存订单流转记录
                OrderWorkFlow orderWorkFlow = new OrderWorkFlow();
                orderWorkFlow.setCreatedTime(new Date());
                orderWorkFlow.setNewOrderStatus(order.getOrderStatus());
                orderWorkFlow.setOldOrderStatus(OrderStatusEnum.FIN_FRONT_APV.getCode());
                orderWorkFlow.setOpUserName(user.getName());
                orderWorkFlow.setOpUserNo(userInfo.getUserNo());
                orderWorkFlow.setOpUserRole(RoleEnum.financial.getDec());
                orderWorkFlow.setOrderNo(order.getOrderNo());
                orderWorkFlow.setType(OrderWorkFlowTypeEnum.M_ORDER.getCodeName());
                orderWorkFlow.setRemark(remark);
                orderWorkFlow.setResult("审核通过");
                orderWorkFlow.setUpdatedTime(new Date());
                orderWorkFlowService.save(orderWorkFlow);
            } else {
                return error("参数有误");
            }

        } catch (Exception ex) {
            logger.error("财务审核订单定金信息异常", ex);
            return error("财务审核订单定金信息异常");
        }
        return WebApiResponse.success("success");
    }
}