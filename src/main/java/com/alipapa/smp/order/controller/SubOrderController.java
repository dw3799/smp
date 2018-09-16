package com.alipapa.smp.order.controller;

import com.alipapa.smp.common.enums.SubOrderStatusEnum;
import com.alipapa.smp.common.request.UserInfo;
import com.alipapa.smp.common.request.UserStatus;
import com.alipapa.smp.order.service.OrderService;
import com.alipapa.smp.order.service.OrderWorkFlowService;
import com.alipapa.smp.order.service.SubOrderService;
import com.alipapa.smp.order.service.impl.SubOrderServiceProxy;
import com.alipapa.smp.order.vo.SubOrderVo;
import com.alipapa.smp.utils.WebApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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


    /**
     * 采购获取待提交采购单
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/listUnSubmitSubOrder", method = RequestMethod.GET)
    public WebApiResponse<List<SubOrderVo>> listFinFrontApvOrder(@RequestParam(name = "pageSize", required = false) Integer pageSize,
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


}
