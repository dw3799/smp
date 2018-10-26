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
import com.alipapa.smp.consumer.vo.SysDictVo;
import com.alipapa.smp.order.pojo.*;
import com.alipapa.smp.order.service.ConsumerTailPayService;
import com.alipapa.smp.order.service.OrderService;
import com.alipapa.smp.order.service.OrderWorkFlowService;
import com.alipapa.smp.order.service.impl.OrderServiceProxy;
import com.alipapa.smp.order.vo.*;
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
import com.alipapa.smp.utils.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

import static com.alipapa.smp.utils.WebApiResponse.error;

/**
 * 订单尾款支付接口
 *
 * @author liuwei
 * @version 2.0
 * 2018-09-11
 */
@RestController
@CrossOrigin
@RequestMapping("/api/order")
public class OrderTailPayController {
    private static Logger logger = LoggerFactory.getLogger(OrderTailPayController.class);

    @Autowired
    private OrderWorkFlowService orderWorkFlowService;

    @Autowired
    private UserService userService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderServiceProxy orderServiceProxy;

    @Autowired
    private ConsumerTailPayService consumerTailPayService;

    /**
     * 待支付尾款列表
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/listTailPayOrder", method = RequestMethod.GET)
    public WebApiResponse<List<TailPayOrderVo>> listTailPayOrder(@RequestParam(name = "pageSize", required = false) Integer pageSize,
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

        List<TailPayOrderVo> orderVoList = orderServiceProxy.listTailPayOrderBySalerUserNo(userInfo.getUserNo(), start, size);
        if (!CollectionUtils.isEmpty(orderVoList)) {
            WebApiResponse response = WebApiResponse.success(orderVoList);
            response.setTotalCount(orderVoList.get(0).getTotalCount());
            return response;
        }
        return WebApiResponse.success(null);
    }


    /**
     * 获取待出纳审核尾款订单列表
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/listCasherTailPayApvOrder", method = RequestMethod.GET)
    public WebApiResponse<List<TailPayOrderVo>> listCasherTailPayApvOrder(@RequestParam(name = "pageSize", required = false) Integer pageSize,
                                                                          @RequestParam(name = "pageNum", required = false) Integer pageNum) {
        UserInfo userInfo = UserStatus.getUserInfo();

        logger.info(StringUtil.printParam(userInfo));
/*        if (!RoleEnum.admin.getCodeName().equals(userInfo.getRoleName())) {
            if (userInfo.getRoleName().equals(RoleEnum.cashier.getCodeName())) {
                return error("没有权限");
            }
        }*/
        if (pageSize == null) {
            pageSize = 30;
        }

        if (pageNum == null) {
            pageNum = 1;
        }

        Integer start = (pageNum - 1) * pageSize;
        Integer size = pageSize;

        List<TailPayOrderVo> orderVoList = orderServiceProxy.listCasherTailPayApvOrder(start, size);
        if (!CollectionUtils.isEmpty(orderVoList)) {
            WebApiResponse response = WebApiResponse.success(orderVoList);
            response.setTotalCount(orderVoList.get(0).getTotalCount());
            return response;
        }
        return WebApiResponse.success(null);
    }


    /**
     * 业务员提交尾款
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/commitOrderTailPay", method = RequestMethod.POST)
    public WebApiResponse<String> commitOrderTailPay(HttpServletRequest request) {
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

            List<ConsumerTailPay> consumerTailPayList = consumerTailPayService.selectInRiewConsumerTailPayByOrderNo(orderNo);
            if (!CollectionUtils.isEmpty(consumerTailPayList)) {
                return error("存在审核中的尾款，不能提交!");
            }

            if (order.getOrderStatus() != OrderStatusEnum.DELIVERY.getCode() && order.getOrderStatus() != OrderStatusEnum.COMPLETE.getCode()) {
                return error("当前状态不能提交订单尾款！");
            }





/*            orderNo：M210808132222, //必传，订单编号
             tailAmount："3600.50" //必传，本次支付尾款金额
            receiptChannel："支付宝" //必传，收款渠道，下拉列表接口请求获取
            receiptNo："13817959271" //必传，收款账号
            payChannel："Paypal" //必传，支付渠道
            payNo："dw#uuuu" //必传，支付账号
            remark："备注" //备注*/


            String tailAmount = request.getParameter("tailAmount"); //应收定金

/*            if (PriceUtil.convertToFen(tailAmount) > order.getOrderAmount() - order.getReceiptAmount()) {
                return error("");
            }*/

            String receiptChannel = request.getParameter("receiptChannel");
            String receiptNo = request.getParameter("receiptNo");
            String payChannel = request.getParameter("payChannel");
            String payNo = request.getParameter("payNo");
            String remark = request.getParameter("remark");

            if (StringUtil.isEmptyString(orderNo) || StringUtil.isEmptyString(tailAmount) || StringUtil.isEmptyString(receiptChannel) || StringUtil.isEmptyString(receiptNo) || StringUtil.isEmptyString(payChannel)
                    || StringUtil.isEmptyString(payNo)) {
                return error("缺少必填参数");
            }


            ConsumerTailPay consumerTailPay = new ConsumerTailPay();

            consumerTailPay.setTailAmount(PriceUtil.convertToFen(tailAmount));
            consumerTailPay.setActualTailAmount(0L);
            consumerTailPay.setCnActualTailAmount(0L); //出纳确认时补充
            consumerTailPay.setConsumerCountry(order.getConsumerCountry());
            consumerTailPay.setConsumerName(order.getConsumerName());
            consumerTailPay.setConsumerNo(order.getConsumerNo());

            consumerTailPay.setCreatedTime(new Date());

            consumerTailPay.setPayStatus(OrderPayStatusEnum.TAIL_CASH_APV.getCode());
            consumerTailPay.setExchangeRate(null);//汇率，财务审核时补充
            consumerTailPay.setOrderNo(orderNo);
            consumerTailPay.setPayChannel(payChannel);
            consumerTailPay.setPayNo(payNo);
            consumerTailPay.setReceiptChannel(receiptChannel);
            consumerTailPay.setReceiptNo(receiptNo);
            consumerTailPay.setRemark(remark);
            consumerTailPay.setUpdatedTime(new Date());

            order.setPayStatus(OrderPayStatusEnum.TAIL_CASH_APV.getCode());

            consumerTailPayService.saveConsumerTailPay(consumerTailPay, order);
            orderService.updateOrder(order);
        } catch (Exception ex) {
            logger.error("业务员提交尾款异常", ex);
            return error("业务员提交尾款异常");
        }
        return WebApiResponse.success("success");
    }


    /**
     * 获取定金信息
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/getOrderTailPay", method = RequestMethod.GET)
    public WebApiResponse<ConsumerTailPayVo> getOrderTailPay(@RequestParam(name = "orderNo") String orderNo) {
        if (StringUtil.isEmptyString(orderNo)) {
            return error("缺少必要参数");
        }

        Order order = orderService.selectOrderByOrderNo(orderNo);
        if (order == null) {
            return WebApiResponse.error("订单不存在");
        }

        try {
            ConsumerTailPayVo consumerTailPayVo = new ConsumerTailPayVo();

            String currencyDec = orderService.getCurrencyDec(order);

            consumerTailPayVo.setOrderNo(order.getOrderNo());
            consumerTailPayVo.setOrderAmount(PriceUtil.convertToYuanStr(order.getOrderAmount()) + currencyDec);
            consumerTailPayVo.setActualAmount(PriceUtil.convertToYuanStr(order.getReceiptAmount()) + currencyDec);

            List<ConsumerTailPay> consumerTailPayList = consumerTailPayService.selectInRiewConsumerTailPayByOrderNo(orderNo);

            if (!CollectionUtils.isEmpty(consumerTailPayList)) {
                ConsumerTailPay consumerTailPay = consumerTailPayList.get(0);
                consumerTailPayVo.setId(consumerTailPay.getId());
                consumerTailPayVo.setPayChannel(consumerTailPay.getPayChannel());
                consumerTailPayVo.setPayNo(consumerTailPay.getPayNo());
                consumerTailPayVo.setReceiptChannel(consumerTailPay.getReceiptChannel());
                consumerTailPayVo.setReceiptNo(consumerTailPay.getReceiptNo());
                consumerTailPayVo.setTailAmount(PriceUtil.convertToYuanStr(consumerTailPay.getTailAmount()) + currencyDec);
            }
            return WebApiResponse.success(consumerTailPayVo);
        } catch (Exception ex) {
            logger.error("获取尾款信息异常", ex);
            return error("获取尾款信息异常");
        }
    }


    /**
     * 出纳确认订单尾款信息
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/cashApproveTailPay", method = RequestMethod.POST)
    public WebApiResponse<String> cashApproveTailPay(@RequestParam(name = "orderNo") String orderNo,
                                                     @RequestParam(name = "actualTailAmount") String actualTailAmount,
                                                     @RequestParam(name = "exchangeRate") String exchangeRate,
                                                     @RequestParam(name = "result") String result,
                                                     @RequestParam(name = "remark", required = false) String remark) {
        UserInfo userInfo = UserStatus.getUserInfo();
        try {
            if (StringUtil.isEmptyString(orderNo) || StringUtil.isEmptyString(result) || StringUtil.isEmptyString(actualTailAmount) || StringUtil.isEmptyString(exchangeRate)) {
                return error("缺少必传参数");
            }

            Order order = orderService.selectOrderByOrderNo(orderNo);
            if (order == null) {
                return WebApiResponse.error("订单不存在");
            }


/*            orderNo：M210808132222, //必传，订单编号
                    actualTailAmount："3600.25", //实收尾款
                    exchangeRate："3.683", //汇率
                    result：Y, //必传，通过Y，不通过N
                    remark："已收款" //非必传，备注*/


            List<ConsumerTailPay> consumerTailPayList = consumerTailPayService.selectInRiewConsumerTailPayByOrderNo(orderNo);
            if (CollectionUtils.isEmpty(consumerTailPayList)) {
                return error("不存在待确认尾款");
            }

            if (order.getPayStatus() != OrderPayStatusEnum.TAIL_CASH_APV.getCode()) {
                return error("当前状态无法确认尾款！");
            }

            User user = userService.getUserByUserNo(userInfo.getUserNo());

            //待审核尾款
            ConsumerTailPay consumerTailPay = consumerTailPayList.get(0);

            if ("N".equals(result)) {
                consumerTailPay.setPayStatus(OrderPayStatusEnum.TAIL_PAY_FAILED.getCode());
                consumerTailPay.setRemark(remark);
                consumerTailPayService.updateConsumerTailPay(consumerTailPay);


                //保存订单流转记录
                OrderWorkFlow orderWorkFlow = new OrderWorkFlow();
                orderWorkFlow.setCreatedTime(new Date());
                orderWorkFlow.setNewOrderStatus(order.getOrderStatus());
                orderWorkFlow.setOldOrderStatus(order.getOrderStatus());
                orderWorkFlow.setOpUserName(user.getName());
                orderWorkFlow.setOpUserNo(userInfo.getUserNo());
                orderWorkFlow.setOpUserRole(RoleEnum.cashier.getDec());
                orderWorkFlow.setOrderNo(order.getOrderNo());
                orderWorkFlow.setType(OrderWorkFlowTypeEnum.M_ORDER.getCodeName());
                orderWorkFlow.setRemark(remark);
                orderWorkFlow.setResult("尾款审核不通过，支付金额:" + PriceUtil.convertToYuanStr(consumerTailPay.getTailAmount()));
                orderWorkFlow.setUpdatedTime(new Date());
                orderWorkFlowService.save(orderWorkFlow);
            } else if ("Y".equals(result)) {
                //更新订单支付状态
                if (order.getReceiptAmount() + consumerTailPay.getActualTailAmount() >= order.getOrderAmount()) {
                    order.setPayStatus(OrderPayStatusEnum.SUCCESS.getCode());
                } else {
                    order.setPayStatus(OrderPayStatusEnum.TAIL_PAYING.getCode());
                }


                consumerTailPay.setExchangeRate(exchangeRate);
                consumerTailPay.setActualTailAmount(PriceUtil.convertToFen(actualTailAmount));
                Double cnActualAccount = Double.parseDouble(actualTailAmount) * Double.valueOf(consumerTailPay.getExchangeRate());
                consumerTailPay.setCnActualTailAmount(PriceUtil.convertToFen(cnActualAccount));

                consumerTailPay.setPayStatus(OrderPayStatusEnum.TAIL_PAYED.getCode());

                order.setReceiptAmount(order.getReceiptAmount() + consumerTailPay.getActualTailAmount());
                order.setCnReceiptAmount(order.getCnReceiptAmount() + consumerTailPay.getCnActualTailAmount());

                orderService.updateOrder(order);
                consumerTailPayService.updateConsumerTailPay(consumerTailPay);

                //保存订单流转记录
                OrderWorkFlow orderWorkFlow = new OrderWorkFlow();
                orderWorkFlow.setCreatedTime(new Date());
                orderWorkFlow.setNewOrderStatus(order.getOrderStatus());
                orderWorkFlow.setOldOrderStatus(order.getOrderStatus());
                orderWorkFlow.setOpUserName(user.getName());
                orderWorkFlow.setOpUserNo(userInfo.getUserNo());
                orderWorkFlow.setOpUserRole(RoleEnum.cashier.getDec());
                orderWorkFlow.setOrderNo(order.getOrderNo());
                orderWorkFlow.setType(OrderWorkFlowTypeEnum.M_ORDER.getCodeName());
                orderWorkFlow.setRemark(remark);
                orderWorkFlow.setResult("尾款审核通过，支付金额:" + PriceUtil.convertToYuanStr(consumerTailPay.getActualTailAmount()));
                orderWorkFlow.setUpdatedTime(new Date());
                orderWorkFlowService.save(orderWorkFlow);
            } else {
                return error("参数有误");
            }

        } catch (Exception ex) {
            logger.error("出纳确认订单尾款信息异常", ex);
            return error("出纳确认订单尾款信息异常");
        }
        return WebApiResponse.success("success");
    }

}
