package com.alipapa.smp.order.controller;


import com.alipapa.smp.common.enums.OrderStatusEnum;
import com.alipapa.smp.common.enums.OrderTypeEnum;
import com.alipapa.smp.common.enums.RoleEnum;
import com.alipapa.smp.common.enums.SubOrderStatusEnum;
import com.alipapa.smp.common.request.UserInfo;
import com.alipapa.smp.common.request.UserStatus;
import com.alipapa.smp.order.service.impl.SubOrderServiceProxy;
import com.alipapa.smp.order.vo.OrderVo;
import com.alipapa.smp.order.vo.SubOrderVo;
import com.alipapa.smp.user.pojo.Group;
import com.alipapa.smp.user.pojo.User;
import com.alipapa.smp.user.service.GroupService;
import com.alipapa.smp.user.service.UserService;
import com.alipapa.smp.utils.StringUtil;
import com.alipapa.smp.utils.WebApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    private GroupService groupService;

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

}
