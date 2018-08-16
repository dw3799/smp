package com.alipapa.smp.order.controller;


import com.alipapa.smp.common.enums.OrderStatusEnum;
import com.alipapa.smp.common.enums.OrderTypeEnum;
import com.alipapa.smp.common.enums.RoleEnum;
import com.alipapa.smp.common.request.UserInfo;
import com.alipapa.smp.common.request.UserStatus;
import com.alipapa.smp.order.pojo.Order;
import com.alipapa.smp.order.service.OrderService;
import com.alipapa.smp.order.vo.BasicOrderInfo;
import com.alipapa.smp.utils.DateUtil;
import com.alipapa.smp.utils.StringUtil;
import com.alipapa.smp.utils.WebApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    }


}