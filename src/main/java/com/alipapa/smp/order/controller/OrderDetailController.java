package com.alipapa.smp.order.controller;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alipapa.smp.common.Constant;
import com.alipapa.smp.common.enums.*;
import com.alipapa.smp.common.request.UserInfo;
import com.alipapa.smp.common.request.UserStatus;
import com.alipapa.smp.consumer.pojo.Consumer;
import com.alipapa.smp.consumer.pojo.SysDict;
import com.alipapa.smp.consumer.service.ConsumerService;
import com.alipapa.smp.consumer.service.SysDictService;
import com.alipapa.smp.consumer.service.UserConsumerRelationService;
import com.alipapa.smp.order.pojo.*;
import com.alipapa.smp.order.service.OrderFollowRecordService;
import com.alipapa.smp.order.service.OrderService;
import com.alipapa.smp.order.service.OrderWorkFlowService;
import com.alipapa.smp.order.service.SubOrderService;
import com.alipapa.smp.order.service.impl.OrderServiceProxy;
import com.alipapa.smp.order.vo.BasicOrderInfo;
import com.alipapa.smp.order.vo.OrderProductVo;
import com.alipapa.smp.order.vo.OrderWorkFlowVo;
import com.alipapa.smp.product.pojo.Product;
import com.alipapa.smp.product.pojo.ProductCategory;
import com.alipapa.smp.product.pojo.ProductPicture;
import com.alipapa.smp.product.service.ProductCategoryService;
import com.alipapa.smp.product.service.ProductPictureService;
import com.alipapa.smp.product.service.ProductService;
import com.alipapa.smp.user.pojo.Role;
import com.alipapa.smp.user.pojo.User;
import com.alipapa.smp.user.service.RoleService;
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
    private SysDictService sysDictService;

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
    private RoleService roleService;


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

            basicOrderInfo.setCreateDateTime(DateUtil.formatToStrTimeV1(order.getCreatedTime()));
            basicOrderInfo.setOrderStatus(OrderStatusEnum.valueOf(order.getOrderStatus()).getDec());
            basicOrderInfo.setOrderType(OrderTypeEnum.valueOf(order.getOrderType()).getDec());
            basicOrderInfo.setOrderNo(orderNo);
            basicOrderInfo.setProductionCycle(order.getProductionCycle());
            basicOrderInfo.setSubmitDateTime(DateUtil.formatToStrTimeV1(order.getSubmitTime()));
            basicOrderInfo.setBuyerUserNo(order.getBuyerUserNo());
            basicOrderInfo.setBuyerUserName(order.getBuyerUserName());

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

        List<SysDict> sysDictList = sysDictService.listSysDict(OrderCategoryCode.Currency.getCodeName(), order.getCurrency());
        String currencyDec = "UNKNOWN";
        if (CollectionUtils.isEmpty(sysDictList)) {
            SysDict currencySysDict = sysDictList.get(0);
            currencyDec = currencySysDict.getDictValue();
        }


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
                    orderProductVo.setProductCategoryId(subOrder.getProductCategoryId());
                    orderProductVo.setProductCategory(subOrder.getProductCategory());
                    orderProductVo.setProductId(subOrder.getProductId());
                    orderProductVo.setProductName(subOrder.getProductName());
                    orderProductVo.setProductAmount(PriceUtil.convertToYuanStr(subOrder.getProductAmount()) + currencyDec);
                    orderProductVo.setExpectPurchaseAmount(PriceUtil.convertToYuanStr(subOrder.getExpectPurchaseAmount()) + Constant.YMB);
                    orderProductVo.setProductRemark(subOrder.getProductRemark());

                    if (OrderTypeEnum.SELF_ORDER == orderTypeEnum) {
                        SelfOrderDetail selfOrderDetail = subOrder.getSelfOrderDetail();
                        orderProductVo.setSaleAmount(PriceUtil.convertToYuanStr(selfOrderDetail.getSaleAmount()) + currencyDec);
                        orderProductVo.setFactoryAmount(PriceUtil.convertToYuanStr(selfOrderDetail.getFactoryAmount()) + Constant.YMB);

                        orderProductVo.setWeight(selfOrderDetail.getWeight());
                        orderProductVo.setMaterial(selfOrderDetail.getMaterial());
                        orderProductVo.setSize(selfOrderDetail.getSize());
                        orderProductVo.setColor(selfOrderDetail.getColor());
                        orderProductVo.setSuturing(selfOrderDetail.getSuturing());

                        orderProductVo.setPrinting(selfOrderDetail.getPrinting());
                        orderProductVo.setQuantity(selfOrderDetail.getQuantity());
                    } else {
                        AgentOrderDetail agentOrderDetail = subOrder.getAgentOrderDetail();

                        orderProductVo.setSaleAmount(PriceUtil.convertToYuanStr(agentOrderDetail.getSaleAmount()) + currencyDec);
                        orderProductVo.setFactoryAmount(PriceUtil.convertToYuanStr(agentOrderDetail.getFactoryAmount()) + Constant.YMB);
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
                    subOrder.setMiniPic(productPicture.getPicName());
                    subOrder.setPic(productPicture.getPicName());
                }

                subOrder.setProductAmount(PriceUtil.convertToFen(subProductAmount));
                subOrder.setProductCategoryId(productCategoryId);
                subOrder.setProductCategory(productCategory.getCategoryName());
                subOrder.setPayedAmount(0L);
                subOrder.setProductFrontAmount(null);
                subOrder.setProductId(productId);
                subOrder.setProductName(product.getProductName());
                subOrder.setProductRemark(productRemark);
                subOrder.setRemark(null);

                subOrder.setSubOrderNo(subOrderNo);
                subOrder.setSubOrderStatus(SubOrderStatusEnum.CREATE.getCode());
                subOrder.setSubPayStatus(SubOrderPayStatusEnum.UN_PAY.getCode());
                subOrder.setUpdatedTime(new Date());

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
                    String saleAmount = jsonObject.getString("saleAmount");
                    String factoryAmount = jsonObject.getString("factoryAmount");

                    selfOrderDetail.setColor(color);
                    selfOrderDetail.setCreatedTime(new Date());
                    selfOrderDetail.setFactoryAmount(PriceUtil.convertToFen(factoryAmount));
                    selfOrderDetail.setMaterial(material);
                    selfOrderDetail.setPrinting(printing);
                    selfOrderDetail.setQuantity(quantity);
                    selfOrderDetail.setSaleAmount(PriceUtil.convertToFen(saleAmount));
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

                    String saleAmount = jsonObject.getString("saleAmount");
                    String factoryAmount = jsonObject.getString("factoryAmount");
                    String unit = jsonObject.getString("unit");
                    Integer singlePackageCount = jsonObject.getInteger("singlePackageCount");
                    Integer packageNumber = jsonObject.getInteger("packageNumber");
                    String singleVolume = jsonObject.getString("singleVolume");
                    String totalVolume = jsonObject.getString("totalVolume");
                    String singleWeight = jsonObject.getString("singleWeight");
                    String totalWeight = jsonObject.getString("totalWeight");

                    agentOrderDetail.setCreatedTime(new Date());
                    agentOrderDetail.setFactoryAmount(PriceUtil.convertToFen(factoryAmount));
                    agentOrderDetail.setPackageNumber(packageNumber);
                    agentOrderDetail.setSaleAmount(PriceUtil.convertToFen(saleAmount));
                    if (agentOrderDetail.getId() == null) {
                        agentOrderDetail.setSaleNo(Constant.SALE_NO_PREFIX + product.getId() + consumer.getConsumerNo());
                    }
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

            if (RoleEnum.supervisor.getCodeName() != userInfo.getRoleName()) {
                return error("没有权限");
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


}