package com.alipapa.smp.order.controller;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alipapa.smp.common.enums.*;
import com.alipapa.smp.common.request.UserInfo;
import com.alipapa.smp.common.request.UserStatus;
import com.alipapa.smp.invoice.pojo.ProductQualityInfo;
import com.alipapa.smp.invoice.service.ProductQualityInfoService;
import com.alipapa.smp.invoice.vo.QualityCheckInfoVo;
import com.alipapa.smp.order.pojo.*;
import com.alipapa.smp.order.service.MaterielOrderService;
import com.alipapa.smp.order.service.OrderWorkFlowService;
import com.alipapa.smp.order.service.PurchaseOrderExtService;
import com.alipapa.smp.order.service.SubOrderService;
import com.alipapa.smp.order.service.impl.SubOrderServiceProxy;
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


    @Autowired
    private SubOrderService subOrderService;


    @Autowired
    private MaterielOrderService materielOrderService;


    @Autowired
    private OrderWorkFlowService orderWorkFlowService;

    @Autowired
    private PurchaseOrderExtService purchaseOrderExtService;

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
     * 财务审核采购单尾款,重新计算金额
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/finApvSubOrderTailPay", method = RequestMethod.POST)
    public WebApiResponse<String> finApvSubOrderTailPay(HttpServletRequest request) {
        UserInfo userInfo = UserStatus.getUserInfo();

        try {
            String subOrderNo = request.getParameter("subOrderNo");
            String remark = request.getParameter("remark");

            if (StringUtil.isEmptyString(subOrderNo)) {
                return WebApiResponse.error("缺少必填参数");
            }

            SubOrder subOrder = subOrderService.getSubOrderBySubOrderNo(subOrderNo);
            if (subOrder == null) {
                return error("采购单不存在");
            }


            String remarkString = "";

            String materiels = request.getParameter("materiels");
            JSONArray materielsArray = JSONArray.parseArray(materiels);

            for (int i = 0; i < materielsArray.size(); i++) {
                JSONObject jsonObject = materielsArray.getJSONObject(i);

                Long materielOrderId = jsonObject.getLong("materielOrderId");
                String tailAmount = jsonObject.getString("tailAmount");

                if (materielOrderId == null || StringUtil.isEmptyString(tailAmount)) {
                    return error("缺少必填参数！");
                }

                MaterielOrder materielOrder = materielOrderService.selectMaterielOrderById(materielOrderId);
                materielOrder.setActualTailAmount(PriceUtil.convertToFen(tailAmount));


                if (materielOrder.getActualTailAmount() != materielOrder.getPurchaseAmount() - materielOrder.getPurchaseFrontAmount()) {
                    remarkString = remarkString + "  &&" + materielOrder.getProductName() + "的采购金额" + PriceUtil.convertToYuanStr(materielOrder.getPurchaseAmount()) + "修改为" + PriceUtil.convertToYuanStr(materielOrder.getActualTailAmount() + materielOrder.getPurchaseFrontAmount());
                }
                materielOrder.setPurchaseAmount(materielOrder.getActualTailAmount() + materielOrder.getPurchaseFrontAmount());
                materielOrder.setActualPurchaseAmount(materielOrder.getActualTailAmount() + materielOrder.getPurchaseFrontAmount());
                materielOrder.setPayStatus(MaterielOrderPayStatusEnum.CASH_TAIL_PAYING.getCode());
                materielOrderService.updateMaterielOrder(materielOrder);
            }


            List<MaterielOrder> materielOrderList = materielOrderService.listMaterielOrderBySubOrderNo(subOrderNo);

            Long totalPurchaseAmount = 0L;

            for (MaterielOrder materielOrder : materielOrderList) {
                if (materielOrder.getPayStatus() != MaterielOrderPayStatusEnum.CASH_TAIL_PAYING.getCode()) {
                    return error("存在未提交尾款的物料单");
                }
                totalPurchaseAmount = totalPurchaseAmount + materielOrder.getPurchaseAmount(); //更新实际付款金额
            }


            PurchaseOrderExt purchaseOrderExt = purchaseOrderExtService.getPurchaseOrderExtBySubOrderNo(subOrder.getSubOrderNo());
            if (purchaseOrderExt != null) {
                purchaseOrderExt.setFinTailTime(new Date());
                purchaseOrderExt.setUpdatedTime(new Date());
                purchaseOrderExtService.updatePurchaseOrderExt(purchaseOrderExt);
            }


            subOrder.setActualPurchaseAmount(totalPurchaseAmount);
            subOrder.setSubPayStatus(SubOrderPayStatusEnum.CASH_TAIL_PAYING.getCode());
            subOrderService.updateSubOrder(subOrder);

            //保存采购单流转记录
            OrderWorkFlow orderWorkFlow = new OrderWorkFlow();
            orderWorkFlow.setCreatedTime(new Date());
            //状态未变化，只修改支付状态
            orderWorkFlow.setNewOrderStatus(subOrder.getSubOrderStatus());
            orderWorkFlow.setOldOrderStatus(subOrder.getSubOrderStatus());
            orderWorkFlow.setOpUserName(userInfo.getUserName());
            orderWorkFlow.setOpUserNo(userInfo.getUserNo());
            orderWorkFlow.setOpUserRole(userInfo.getRoleName());
            orderWorkFlow.setOrderNo(subOrder.getSubOrderNo());
            orderWorkFlow.setType(OrderWorkFlowTypeEnum.SUB_ORDER.getCodeName());
            orderWorkFlow.setRemark(remark + remarkString);
            orderWorkFlow.setResult("尾款审核成功");
            orderWorkFlow.setUpdatedTime(new Date());
            orderWorkFlowService.save(orderWorkFlow);
            return WebApiResponse.success("success");
        } catch (Exception ex) {
            logger.error("财务审核采购单尾款异常", ex);
            return WebApiResponse.error("财务审核采购单尾款异常");
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


    /**
     * 出纳支付采购单尾款，修改支付状态
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/cashConfirmSubOrderTailPay", method = RequestMethod.POST)
    public WebApiResponse<String> cashConfirmSubOrderTailPay(HttpServletRequest request) {
        UserInfo userInfo = UserStatus.getUserInfo();

        try {
            String subOrderNo = request.getParameter("subOrderNo");
            String result = request.getParameter("result");
            String remark = request.getParameter("remark");

            if (StringUtil.isEmptyString(subOrderNo)) {
                return WebApiResponse.error("缺少必填参数");
            }

            if (StringUtil.isEmptyString(result) || !"Y".equals(result)) {
                return error("未支付");
            }

            SubOrder subOrder = subOrderService.getSubOrderBySubOrderNo(subOrderNo);
            if (subOrder == null) {
                return error("采购单不存在");
            }

            List<MaterielOrder> materielOrderList = materielOrderService.listMaterielOrderBySubOrderNo(subOrderNo);

            for (MaterielOrder materielOrder : materielOrderList) {
                materielOrder.setPayStatus(MaterielOrderPayStatusEnum.SUCCESS.getCode());
                materielOrderService.updateMaterielOrder(materielOrder);
            }

            PurchaseOrderExt purchaseOrderExt = purchaseOrderExtService.getPurchaseOrderExtBySubOrderNo(subOrder.getSubOrderNo());
            if (purchaseOrderExt != null) {
                purchaseOrderExt.setCashTailTime(new Date());
                purchaseOrderExt.setUpdatedTime(new Date());
                purchaseOrderExtService.updatePurchaseOrderExt(purchaseOrderExt);
            }

            subOrder.setSubPayStatus(SubOrderPayStatusEnum.SUCCESS.getCode());
            subOrderService.updateSubOrder(subOrder);

            //保存采购单流转记录
            OrderWorkFlow orderWorkFlow = new OrderWorkFlow();
            orderWorkFlow.setCreatedTime(new Date());
            //状态未变化，只修改支付状态
            orderWorkFlow.setNewOrderStatus(subOrder.getSubOrderStatus());
            orderWorkFlow.setOldOrderStatus(subOrder.getSubOrderStatus());
            orderWorkFlow.setOpUserName(userInfo.getUserName());
            orderWorkFlow.setOpUserNo(userInfo.getUserNo());
            orderWorkFlow.setOpUserRole(userInfo.getRoleName());
            orderWorkFlow.setOrderNo(subOrder.getSubOrderNo());
            orderWorkFlow.setType(OrderWorkFlowTypeEnum.SUB_ORDER.getCodeName());
            orderWorkFlow.setRemark(remark);
            orderWorkFlow.setResult("尾款支付成功");
            orderWorkFlow.setUpdatedTime(new Date());
            orderWorkFlowService.save(orderWorkFlow);
            return WebApiResponse.success("success");
        } catch (Exception ex) {
            logger.error("出纳支付采购单尾款异常", ex);
            return WebApiResponse.error("出纳支付采购单尾款异常");
        }
    }


}
