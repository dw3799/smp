package com.alipapa.smp.order.controller;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alipapa.smp.common.Constant;
import com.alipapa.smp.common.enums.*;
import com.alipapa.smp.common.request.UserInfo;
import com.alipapa.smp.common.request.UserStatus;
import com.alipapa.smp.consumer.controller.ConsumerController;
import com.alipapa.smp.consumer.pojo.Consumer;
import com.alipapa.smp.consumer.pojo.SysDict;
import com.alipapa.smp.consumer.pojo.UserConsumerRelation;
import com.alipapa.smp.consumer.service.ConsumerService;
import com.alipapa.smp.consumer.service.SysDictService;
import com.alipapa.smp.consumer.service.UserConsumerRelationService;
import com.alipapa.smp.consumer.vo.SysDictVo;
import com.alipapa.smp.order.pojo.AgentOrderDetail;
import com.alipapa.smp.order.pojo.Order;
import com.alipapa.smp.order.pojo.SelfOrderDetail;
import com.alipapa.smp.order.pojo.SubOrder;
import com.alipapa.smp.order.service.impl.OrderServiceProxy;
import com.alipapa.smp.order.vo.ConsumerOrderCount;
import com.alipapa.smp.order.vo.ConsumerOrderVo;
import com.alipapa.smp.order.vo.OrderVo;
import com.alipapa.smp.product.pojo.Product;
import com.alipapa.smp.product.pojo.ProductCategory;
import com.alipapa.smp.product.pojo.ProductPicture;
import com.alipapa.smp.product.service.ProductCategoryService;
import com.alipapa.smp.product.service.ProductPictureService;
import com.alipapa.smp.product.service.ProductService;
import com.alipapa.smp.user.pojo.Group;
import com.alipapa.smp.user.pojo.Role;
import com.alipapa.smp.user.pojo.User;
import com.alipapa.smp.user.service.GroupService;
import com.alipapa.smp.user.service.RoleService;
import com.alipapa.smp.user.service.UserService;
import com.alipapa.smp.user.vo.FuzzyUserVo;
import com.alipapa.smp.user.vo.GroupSelectVo;
import com.alipapa.smp.utils.OrderNumberGenerator;
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

/**
 * 订单管理接口
 *
 * @author liuwei
 * @version 2.0
 * 2018-08-08
 */
@RestController
@CrossOrigin
@RequestMapping("/api/order")
public class OrderController {
    private static Logger logger = LoggerFactory.getLogger(OrderController.class);


    @Autowired
    private SysDictService sysDictService;


    @Autowired
    private UserService userService;

    @Autowired
    private GroupService groupService;

    @Autowired
    private RoleService roleService;


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


    /**
     * 订单相关下拉列表
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/orderSelect", method = RequestMethod.GET)
    public WebApiResponse<List<SysDictVo>> orderSelect(@RequestParam("categoryCode") Integer categoryCode) {
        if (categoryCode == null || OrderCategoryCode.valueOf(categoryCode) == null) {
            return WebApiResponse.error("参数有误！");
        }
        List<SysDictVo> sysDictVoList = new ArrayList<>();

        if (OrderCategoryCode.OrderStatus == OrderCategoryCode.valueOf(categoryCode)) {
            for (OrderStatusEnum orderStatus : OrderStatusEnum.values()) {
                SysDictVo sysDictVo = new SysDictVo();
                sysDictVo.setId(Long.valueOf(orderStatus.getCode()));
                sysDictVo.setCategoryCode(OrderCategoryCode.OrderStatus.getCodeName());
                sysDictVo.setDictText(orderStatus.getDec());
                sysDictVo.setDictValue(orderStatus.getCodeName());
                sysDictVoList.add(sysDictVo);
            }
        } else if (OrderCategoryCode.OrderType == OrderCategoryCode.valueOf(categoryCode)) {
            for (OrderTypeEnum orderType : OrderTypeEnum.values()) {
                SysDictVo sysDictVo = new SysDictVo();
                sysDictVo.setId(Long.valueOf(orderType.getCode()));
                sysDictVo.setCategoryCode(OrderCategoryCode.OrderType.getCodeName());
                sysDictVo.setDictText(orderType.getDec());
                sysDictVo.setDictValue(orderType.getCodeName());
                sysDictVoList.add(sysDictVo);
            }
        } else {
            List<SysDict> sysDictList = sysDictService.listSysDictLikeText(OrderCategoryCode.valueOf(categoryCode).getCodeName(), null);
            if (!CollectionUtils.isEmpty(sysDictList)) {
                for (SysDict sysDict : sysDictList) {
                    SysDictVo sysDictVo = new SysDictVo();
                    sysDictVo.setId(sysDict.getId());
                    sysDictVo.setCategoryCode(sysDict.getCategoryCode());
                    sysDictVo.setDictText(sysDict.getDictText());
                    sysDictVo.setDictValue(sysDict.getDictValue());
                    sysDictVoList.add(sysDictVo);
                }
            }
        }
        return WebApiResponse.success(sysDictVoList);
    }


    /**
     * 创建订单
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/createOrder", method = RequestMethod.POST)
    public WebApiResponse<String> createOrder(HttpServletRequest request) {
        UserInfo userInfo = UserStatus.getUserInfo();

        try {
            //不能为空
            String orderType = request.getParameter("orderType");
            String consumerNo = request.getParameter("consumerNo");
            String buyerUserNo = request.getParameter("buyerUserNo");
            String productionCycle = request.getParameter("productionCycle");
            String currency = request.getParameter("currency");
            String productAmount = request.getParameter("productAmount");
            String expectPurchaseAmount = request.getParameter("expectPurchaseAmount");
            String products = request.getParameter("products");
            String opType = request.getParameter("opType");


            logger.info("orderType=" + orderType);
            logger.info("consumerNo=" + consumerNo);
            logger.info("buyerUserNo=" + buyerUserNo);
            logger.info("productionCycle=" + productionCycle);
            logger.info("currency=" + currency);
            logger.info("productAmount=" + productAmount);
            logger.info("expectPurchaseAmount=" + expectPurchaseAmount);
            logger.info("opType=" + opType);
            logger.info("products=" + products);

            //可为空
            String orderVolume = request.getParameter("orderVolume");
            String orderWeight = request.getParameter("orderWeight");
            String remark = request.getParameter("remark");

            if (StringUtil.isEmptyString(opType) || StringUtil.isEmptyString(orderType) || StringUtil.isEmptyString(consumerNo) || StringUtil.isEmptyString(currency) || StringUtil.isEmptyString(productAmount)
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

            Order order = new Order();
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

            String orderNo = Constant.MAIN_ORDER_PREFIX + OrderNumberGenerator.get();
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

                if (productCategoryId == null || productId == null) {
                    return error("产品缺少必填参数");
                }

                Product product = productService.getProductById(productId);
                ProductCategory productCategory = productCategoryService.getProductCategoryById(productCategoryId);

                List<ProductPicture> productPictureList = productPictureService.listProductPictureByProductId(productId);

                if (product == null || productCategory == null) {
                    return error("产品参数异常");
                }

                SubOrder subOrder = new SubOrder();
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

                String subOrderNo = Constant.SUB_ORDER_PREFIX + OrderNumberGenerator.get();
                subOrder.setSubOrderNo(subOrderNo);
                subOrder.setSubOrderStatus(SubOrderStatusEnum.CREATE.getCode());
                subOrder.setSubPayStatus(SubOrderPayStatusEnum.UN_PAY.getCode());
                subOrder.setUpdatedTime(new Date());

                String saleAmount = jsonObject.getString("saleAmount");
                String factoryAmount = jsonObject.getString("factoryAmount");

                subOrder.setFactoryAmount(PriceUtil.convertToFen(factoryAmount));
                subOrder.setSaleAmount(PriceUtil.convertToFen(saleAmount));
                subOrder.setSaleNo(Constant.SALE_NO_PREFIX + product.getId() + consumer.getConsumerNo());

                if (OrderTypeEnum.SELF_ORDER == orderTypeEnum) {
                    String weight = jsonObject.getString("weight");
                    String material = jsonObject.getString("material");
                    String size = jsonObject.getString("size");
                    String color = jsonObject.getString("color");
                    String suturing = jsonObject.getString("suturing");
                    String printing = jsonObject.getString("printing");
                    Integer quantity = jsonObject.getInteger("quantity");


                    SelfOrderDetail selfOrderDetail = new SelfOrderDetail();
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
                    String unit = jsonObject.getString("unit");
                    Integer singlePackageCount = jsonObject.getInteger("singlePackageCount");
                    Integer packageNumber = jsonObject.getInteger("packageNumber");
                    String singleVolume = jsonObject.getString("singleVolume");
                    String totalVolume = jsonObject.getString("totalVolume");
                    String singleWeight = jsonObject.getString("singleWeight");
                    String totalWeight = jsonObject.getString("totalWeight");

                    AgentOrderDetail agentOrderDetail = new AgentOrderDetail();
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

            boolean flag = orderServiceProxy.createOrder(order, subOrderList);
            if (flag) {
                userConsumerRelationService.updateHasOrder(consumer.getId(), saler.getId());
                return WebApiResponse.success("success");
            }
        } catch (Exception ex) {
            logger.error("", ex);
            return error("创建订单异常");
        }
        return WebApiResponse.error("创建订单异常");
    }

    /**
     * 客户订单列表
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/listConsumerOrder", method = RequestMethod.GET)
    public WebApiResponse<List<ConsumerOrderVo>> listConsumerOrder(@RequestParam("consumerNo") String consumerNo,
                                                                   @RequestParam(name = "pageSize", required = false) Integer pageSize,
                                                                   @RequestParam(name = "pageNum", required = false) Integer pageNum) {

        if (pageSize == null) {
            pageSize = 30;
        }

        if (pageNum == null) {
            pageNum = 1;
        }

        Integer start = (pageNum - 1) * pageSize;
        Integer size = pageSize;

        List<ConsumerOrderVo> consumerOrderVoList = orderServiceProxy.listConsumerOrder(consumerNo, start, size);
        if (!CollectionUtils.isEmpty(consumerOrderVoList)) {
            WebApiResponse response = WebApiResponse.success(consumerOrderVoList);
            response.setTotalCount(consumerOrderVoList.get(0).getTotalCount());
            return response;
        }
        return WebApiResponse.success(null);
    }


    /**
     * 获取客户订单汇总
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/getConsumerOrderCount", method = RequestMethod.GET)
    public WebApiResponse<ConsumerOrderCount> getConsumerOrderCount(@RequestParam("consumerNo") String consumerNo) {
        ConsumerOrderCount consumerOrderCount = orderServiceProxy.getConsumerOrderCount(consumerNo);
        return WebApiResponse.success(consumerOrderCount);
    }


    /**
     * 待提交订单列表
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/listUnSubmitOrder", method = RequestMethod.GET)
    public WebApiResponse<List<OrderVo>> listUnSubmitOrder(@RequestParam(name = "pageSize", required = false) Integer pageSize,
                                                           @RequestParam(name = "pageNum", required = false) Integer pageNum) {
        UserInfo userInfo = UserStatus.getUserInfo();

        if (pageSize == null) {
            pageSize = 30;
        }

        if (pageNum == null) {
            pageNum = 1;
        }

        Integer start = (pageNum - 1) * pageSize;
        Integer size = pageSize;

        List<OrderVo> orderVoList = orderServiceProxy.listOrderBySalerUserNo(userInfo.getUserNo(), OrderStatusEnum.UN_SUBMIT.getCode(), start, size);
        if (!CollectionUtils.isEmpty(orderVoList)) {
            WebApiResponse response = WebApiResponse.success(orderVoList);
            response.setTotalCount(orderVoList.get(0).getTotalCount());
            return response;
        }
        return WebApiResponse.success(null);
    }


    /**
     * 关闭订单
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/close-order", method = RequestMethod.POST)
    public WebApiResponse<String> closeOrder(@RequestParam(name = "orderNos") String orderNos) {
        UserInfo userInfo = UserStatus.getUserInfo();
        try {
            if (StringUtil.isEmptyString(orderNos)) {
                return error("缺少必传参数");
            }

            String[] orderNoArray = orderNos.split(";");

            for (String orderNo : orderNoArray) {
                orderServiceProxy.closeOrder(orderNo, userInfo.getUserNo());
            }
        } catch (Exception ex) {
            logger.error("", ex);
            return error("关闭订单异常");
        }
        return WebApiResponse.success("success");
    }


    /**
     * 订单相关下拉列表
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/listApproveOrder", method = RequestMethod.GET)
    public WebApiResponse<List<OrderVo>> listApproveOrder(@RequestParam(name = "pageSize", required = false) Integer pageSize,
                                                          @RequestParam(name = "pageNum", required = false) Integer pageNum) {
        UserInfo userInfo = UserStatus.getUserInfo();

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
        List<OrderVo> orderVoList = null;

        if (userInfo.getRoleName().equals(RoleEnum.admin.getCodeName())) {
            orderVoList = orderServiceProxy.listApproveOrder(null, start, size);
        } else {
            if (1 != user.getIsLeader() && !"1".equals(role.getRoleLevel())) { //组长且为主管有权限
                return error("没有权限");
            }
            orderVoList = orderServiceProxy.listApproveOrder(user.getGroupId(), start, size);
        }
        if (!CollectionUtils.isEmpty(orderVoList)) {
            WebApiResponse response = WebApiResponse.success(orderVoList);
            response.setTotalCount(orderVoList.get(0).getTotalCount());
            return response;
        }
        return WebApiResponse.success(null);
    }


    /**
     * 待缴纳定金订单列表
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/listUnFrontPayOrder", method = RequestMethod.GET)
    public WebApiResponse<List<OrderVo>> listUnFrontPayOrder(@RequestParam(name = "pageSize", required = false) Integer pageSize,
                                                             @RequestParam(name = "pageNum", required = false) Integer pageNum) {
        UserInfo userInfo = UserStatus.getUserInfo();

        if (pageSize == null) {
            pageSize = 30;
        }

        if (pageNum == null) {
            pageNum = 1;
        }

        Integer start = (pageNum - 1) * pageSize;
        Integer size = pageSize;

        List<OrderVo> orderVoList = orderServiceProxy.listOrderBySalerUserNo(userInfo.getUserNo(), OrderStatusEnum.UN_FRONT_PAY.getCode(), start, size);
        if (!CollectionUtils.isEmpty(orderVoList)) {
            WebApiResponse response = WebApiResponse.success(orderVoList);
            response.setTotalCount(orderVoList.get(0).getTotalCount());
            return response;
        }
        return WebApiResponse.success(null);
    }


    /**
     * 待出纳确认定金订单列表
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/listCasherFrontApvOrder", method = RequestMethod.GET)
    public WebApiResponse<List<OrderVo>> listCasherFrontApvOrder(@RequestParam(name = "pageSize", required = false) Integer pageSize,
                                                                 @RequestParam(name = "pageNum", required = false) Integer pageNum) {
        UserInfo userInfo = UserStatus.getUserInfo();
        if (userInfo.getRoleName().equals(RoleEnum.cashier.getCodeName())) {
            return error("没有权限");
        }

        if (pageSize == null) {
            pageSize = 30;
        }

        if (pageNum == null) {
            pageNum = 1;
        }

        Integer start = (pageNum - 1) * pageSize;
        Integer size = pageSize;

        List<OrderVo> orderVoList = orderServiceProxy.listOrderByStatus(OrderStatusEnum.CASH_FRONT_APV.getCode(), start, size);
        if (!CollectionUtils.isEmpty(orderVoList)) {
            WebApiResponse response = WebApiResponse.success(orderVoList);
            response.setTotalCount(orderVoList.get(0).getTotalCount());
            return response;
        }
        return WebApiResponse.success(null);
    }


    /**
     * 待财务审核定金订单列表
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/listFinFrontApvOrder", method = RequestMethod.GET)
    public WebApiResponse<List<OrderVo>> listFinFrontApvOrder(@RequestParam(name = "pageSize", required = false) Integer pageSize,
                                                              @RequestParam(name = "pageNum", required = false) Integer pageNum) {
        UserInfo userInfo = UserStatus.getUserInfo();
        if (userInfo.getRoleName().equals(RoleEnum.financial.getCodeName())) {
            return error("没有权限");
        }

        if (pageSize == null) {
            pageSize = 30;
        }

        if (pageNum == null) {
            pageNum = 1;
        }

        Integer start = (pageNum - 1) * pageSize;
        Integer size = pageSize;

        List<OrderVo> orderVoList = orderServiceProxy.listOrderByStatus(OrderStatusEnum.FIN_FRONT_APV.getCode(), start, size);
        if (!CollectionUtils.isEmpty(orderVoList)) {
            WebApiResponse response = WebApiResponse.success(orderVoList);
            response.setTotalCount(orderVoList.get(0).getTotalCount());
            return response;
        }
        return WebApiResponse.success(null);
    }


    /**
     * 我的订单查询
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/listMyOrder", method = RequestMethod.GET)
    public WebApiResponse<List<OrderVo>> listMyOrder(@RequestParam(name = "pageSize", required = false) Integer pageSize,
                                                     @RequestParam(name = "pageNum", required = false) Integer pageNum,
                                                     @RequestParam(name = "orderNo", required = false) String orderNo,
                                                     @RequestParam(name = "consumerName", required = false) String consumerName,
                                                     @RequestParam(name = "orderStatus", required = false) String orderStatus,
                                                     @RequestParam(name = "orderType", required = false) String orderType,
                                                     @RequestParam(name = "userId", required = false) Long userId,
                                                     @RequestParam(name = "groupId", required = false) Long groupId,
                                                     @RequestParam(name = "submitTimeStart", required = false) String submitTimeStart, //YYYY-MM-DD HH:MM SS
                                                     @RequestParam(name = "submitTimeEnd", required = false) String submitTimeEnd) {
        UserInfo userInfo = UserStatus.getUserInfo();
        Map<String, Object> params = new HashMap<>();

        if (StringUtil.isNotEmptyString(orderNo)) {
            params.put("orderNo", orderNo);
        }

        if (StringUtil.isNotEmptyString(consumerName)) {
            params.put("consumerName", consumerName);
        }

        if (StringUtil.isNotEmptyString(orderStatus)) {
            params.put("orderStatus", OrderStatusEnum.valueOf(orderStatus).getCode());
        }

        if (StringUtil.isNotEmptyString(orderType)) {
            params.put("orderType", OrderTypeEnum.valueOf(orderType).getCode());
        }

        //提交时间开始
        if (!StringUtil.isNotEmptyString(submitTimeStart)) {
            logger.info("submitTimeStart:" + submitTimeStart);
            params.put("submitTimeStart", submitTimeStart);
        }

        //提交时间结束
        if (!StringUtil.isNotEmptyString(submitTimeEnd)) {
            logger.info("submitTimeEnd:" + submitTimeEnd);
            params.put("submitTimeEnd", submitTimeEnd);
        }

        User user = userService.getUserByUserNo(userInfo.getUserNo());

        if (userInfo.getRoleName().equals(RoleEnum.saler.getCodeName())) {
            params.put("salerUserNo", user.getUserNo());
        } else if (userInfo.getRoleName().equals(RoleEnum.supervisor.getCodeName())) {
            params.put("groupId", user.getGroupId());
        } else if (userInfo.getRoleName().equals(RoleEnum.agentBuyer.getCodeName()) || userInfo.getRoleName().equals(RoleEnum.selfBuyer.getCodeName())) {
            params.put("buyerUserNo", user.getUserNo());
        } else if (userInfo.getRoleName().equals(RoleEnum.superBuyer.getCodeName())) {
            params.put("buyerGroupId", user.getGroupId());
        } else {
            if (userId != null && userId > 0) {
                User selectedUser = userService.getUserById(userId);
                params.put("salerUserNo", selectedUser.getUserNo());
                params.put("buyerUserNo", selectedUser.getUserNo());
            }

            if (groupId != null && groupId > 0) {
                Group selectedGroup = groupService.getGroupById(groupId);
                params.put("groupId", selectedGroup.getId());
                params.put("buyerGroupId", selectedGroup.getId());
            }
        }

        if (pageSize == null) {
            pageSize = 30;
        }

        if (pageNum == null) {
            pageNum = 1;
        }

        Integer start = (pageNum - 1) * pageSize;
        Integer size = pageSize;


        List<OrderVo> orderVoList = orderServiceProxy.listOrderByParams(params, start, size);
        if (!CollectionUtils.isEmpty(orderVoList)) {
            WebApiResponse response = WebApiResponse.success(orderVoList);
            response.setTotalCount(orderVoList.get(0).getTotalCount());
            return response;
        }

        return WebApiResponse.success(null);
    }


    /**
     * 我的订单查询-组列表
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/groupCondition", method = RequestMethod.GET)
    public WebApiResponse<List<GroupSelectVo>> groupSelect() {
        UserInfo userInfo = UserStatus.getUserInfo();

        if ("admin".equals(userInfo.getRoleName())) {//管理员返回所有组
            return WebApiResponse.success(groupService.listAllGroupSelect());
        }

        User user = userService.getUserById(userInfo.getUserId());
        if (1 == user.getIsLeader()) {
            Group group = groupService.getGroupById(user.getGroupId());
            GroupSelectVo groupSelectVo = new GroupSelectVo();
            groupSelectVo.setGroupId(group.getId());
            groupSelectVo.setGroupNo(group.getGroupNo());
            groupSelectVo.setGroupName(group.getName());
            List<GroupSelectVo> groupSelectVoList = new ArrayList<>();
            groupSelectVoList.add(groupSelectVo);
            return WebApiResponse.success(groupSelectVoList);
        } else {
            Group group = groupService.getGroupById(user.getGroupId());
            if (group != null) {
                GroupSelectVo groupSelectVo = new GroupSelectVo();
                groupSelectVo.setGroupId(group.getId());
                groupSelectVo.setGroupNo(group.getGroupNo());
                groupSelectVo.setGroupName(group.getName());
                List<GroupSelectVo> groupSelectVoList = new ArrayList<>();
                groupSelectVoList.add(groupSelectVo);
                return WebApiResponse.success(groupSelectVoList);
            }
        }

        return WebApiResponse.success(null);
    }


    /**
     * 我的订单查询-组员列表
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/memberCondition", method = RequestMethod.GET)
    public WebApiResponse<List<FuzzyUserVo>> memberCondition(@RequestParam(value = "groupId", required = false) Long groupId) {
        UserInfo userInfo = UserStatus.getUserInfo();
        if ("admin".equals(userInfo.getRoleName())) {//管理员返回所有人员
            if (groupId != null) {
                List<User> userList = userService.listUserByGroupId(groupId);
                List<FuzzyUserVo> fuzzyUserVoList = new ArrayList<>();
                if (!CollectionUtils.isEmpty(userList)) {
                    for (User member : userList) {
                        FuzzyUserVo fuzzyUserVo = new FuzzyUserVo();
                        fuzzyUserVo.setUserId(member.getId());
                        fuzzyUserVo.setUserNo(member.getUserNo());
                        fuzzyUserVo.setName(member.getName());
                        fuzzyUserVoList.add(fuzzyUserVo);
                    }
                }
                return WebApiResponse.success(fuzzyUserVoList);
            } else {
                List<FuzzyUserVo> fuzzyUserVoList = userService.userSearch(null);
                return WebApiResponse.success(fuzzyUserVoList);
            }
        }

        User user = userService.getUserById(userInfo.getUserId());
        if (1 == user.getIsLeader()) {
            Group group = groupService.getGroupById(user.getGroupId());
            List<User> userList = userService.listUserByGroupId(group.getId());

            List<FuzzyUserVo> fuzzyUserVoList = new ArrayList<>();
            if (!CollectionUtils.isEmpty(userList)) {
                for (User member : userList) {
                    FuzzyUserVo fuzzyUserVo = new FuzzyUserVo();
                    fuzzyUserVo.setUserId(member.getId());
                    fuzzyUserVo.setUserNo(member.getUserNo());
                    fuzzyUserVo.setName(member.getName());
                    fuzzyUserVoList.add(fuzzyUserVo);
                }
            }
            return WebApiResponse.success(fuzzyUserVoList);
        } else {
            FuzzyUserVo fuzzyUserVo = new FuzzyUserVo();
            fuzzyUserVo.setUserId(user.getId());
            fuzzyUserVo.setUserNo(user.getUserNo());
            fuzzyUserVo.setName(user.getName());
            List<FuzzyUserVo> fuzzyUserVoList = new ArrayList<>();
            fuzzyUserVoList.add(fuzzyUserVo);
            return WebApiResponse.success(fuzzyUserVoList);
        }
    }

}