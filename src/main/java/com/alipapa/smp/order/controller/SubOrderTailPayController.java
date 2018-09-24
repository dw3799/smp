package com.alipapa.smp.order.controller;


import com.alibaba.fastjson.JSONObject;
import com.alipapa.smp.common.enums.*;
import com.alipapa.smp.common.request.UserInfo;
import com.alipapa.smp.common.request.UserStatus;
import com.alipapa.smp.invoice.pojo.ProductQualityInfo;
import com.alipapa.smp.invoice.service.ProductQualityInfoService;
import com.alipapa.smp.invoice.vo.QualityCheckInfoVo;
import com.alipapa.smp.order.pojo.MaterielOrder;
import com.alipapa.smp.order.pojo.OrderWorkFlow;
import com.alipapa.smp.order.pojo.PurchaseOrderExt;
import com.alipapa.smp.order.pojo.SubOrder;
import com.alipapa.smp.order.service.MaterielOrderService;
import com.alipapa.smp.order.service.OrderWorkFlowService;
import com.alipapa.smp.order.service.PurchaseOrderExtService;
import com.alipapa.smp.order.service.SubOrderService;
import com.alipapa.smp.order.service.impl.SubOrderServiceProxy;
import com.alipapa.smp.user.service.UserService;
import com.alipapa.smp.utils.DateUtil;
import com.alipapa.smp.utils.StringUtil;
import com.alipapa.smp.utils.WebApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

import static com.alipapa.smp.utils.WebApiResponse.error;

/**
 * 采购单尾款接口
 *
 * @author liuwei
 * @version 2.0
 * 2018-09-24
 */
@RestController
@CrossOrigin
@RequestMapping("/api/subOrder")
public class SubOrderTailPayController {
    private static Logger logger = LoggerFactory.getLogger(SubOrderTailPayController.class);

    @Autowired
    private SubOrderServiceProxy subOrderServiceProxy;


    /**
     * 获取待财务审核采购单尾款
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/listFinTailPaySubOrder", method = RequestMethod.GET)
    public WebApiResponse<List<JSONObject>> listFinTailPaySubOrder(@RequestParam(name = "pageSize", required = false) Integer pageSize,
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

            List<JSONObject> jsonObjectList = subOrderServiceProxy.listTailPaySubOrderByPayStatus(SubOrderPayStatusEnum.FIN_TAIL_APV, start, size);

            if (!CollectionUtils.isEmpty(jsonObjectList)) {
                WebApiResponse response = WebApiResponse.success(jsonObjectList);
                response.setTotalCount(jsonObjectList.get(0).getLong("totalCount"));
                return response;
            }
            return WebApiResponse.success(null);
        } catch (Exception ex) {
            logger.error("获取待财务审核采购单尾款异常", ex);
            return WebApiResponse.error("获取待财务审核采购单尾款异常");
        }
    }


    /**
     * 获取待出纳支付采购单尾款
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/listCashTailPaySubOrder", method = RequestMethod.GET)
    public WebApiResponse<List<JSONObject>> listCashTailPaySubOrder(@RequestParam(name = "pageSize", required = false) Integer pageSize,
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

            List<JSONObject> jsonObjectList = subOrderServiceProxy.listTailPaySubOrderByPayStatus(SubOrderPayStatusEnum.CASH_TAIL_PAYING, start, size);

            if (!CollectionUtils.isEmpty(jsonObjectList)) {
                WebApiResponse response = WebApiResponse.success(jsonObjectList);
                response.setTotalCount(jsonObjectList.get(0).getLong("totalCount"));
                return response;
            }
            return WebApiResponse.success(null);
        } catch (Exception ex) {
            logger.error("获取待出纳支付采购单尾款异常", ex);
            return WebApiResponse.error("获取待出纳支付采购单尾款异常");
        }
    }


}
