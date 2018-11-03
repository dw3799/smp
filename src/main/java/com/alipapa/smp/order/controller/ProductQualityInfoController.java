package com.alipapa.smp.order.controller;


import com.alipapa.smp.common.enums.*;
import com.alipapa.smp.common.request.UserInfo;
import com.alipapa.smp.common.request.UserStatus;
import com.alipapa.smp.invoice.pojo.ProductQualityInfo;
import com.alipapa.smp.invoice.service.ProductQualityInfoService;
import com.alipapa.smp.invoice.vo.QualityCheckInfoVo;
import com.alipapa.smp.order.pojo.*;
import com.alipapa.smp.order.service.*;
import com.alipapa.smp.utils.DateUtil;
import com.alipapa.smp.utils.StringUtil;
import com.alipapa.smp.utils.WebApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

import static com.alipapa.smp.utils.WebApiResponse.error;

/**
 * 质检接口
 *
 * @author liuwei
 * @version 2.0
 * 2018-09-23
 */
@RestController
@CrossOrigin
@RequestMapping("/api/subOrder")
public class ProductQualityInfoController {
    private static Logger logger = LoggerFactory.getLogger(SubOrderController.class);


    @Autowired
    private SubOrderService subOrderService;

    @Autowired
    private ProductQualityInfoService productQualityInfoService;

    @Autowired
    private MaterielOrderService materielOrderService;

    @Autowired
    private OrderWorkFlowService orderWorkFlowService;

    @Autowired
    private PurchaseOrderExtService purchaseOrderExtService;

    @Autowired
    private OrderFollowRecordService orderFollowRecordService;


    /**
     * 保存/提交采购单质检信息
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/saveQualityCheckInfo", method = RequestMethod.POST)
    public WebApiResponse<String> saveQualityCheckInfo(@RequestParam(name = "subOrderNo") String subOrderNo,
                                                       @RequestParam(name = "result") String result,
                                                       @RequestParam(name = "opType") String opType,
                                                       @RequestParam(name = "arrivalTime", required = false) String arrivalTime,
                                                       @RequestParam(name = "checkNumber", required = false) Integer checkNumber,
                                                       @RequestParam(name = "badNumber", required = false) Integer badNumber,
                                                       @RequestParam(name = "printingQuality", required = false) String printingQuality,
                                                       @RequestParam(name = "packagingQuality", required = false) String packagingQuality,
                                                       @RequestParam(name = "suturingQuality", required = false) String suturingQuality,
                                                       @RequestParam(name = "remark", required = false) String remark) {
        UserInfo userInfo = UserStatus.getUserInfo();
        try {


            logger.info("subOrderNo=" + subOrderNo);
            logger.info("result=" + result);
            logger.info("opType=" + opType);
            logger.info("arrivalTime=" + arrivalTime);
            logger.info("checkNumber=" + checkNumber);
            logger.info("badNumber=" + badNumber);
            logger.info("printingQuality=" + printingQuality);
            logger.info("packagingQuality=" + packagingQuality);
            logger.info("suturingQuality=" + suturingQuality);
            logger.info("remark=" + remark);


            if (StringUtil.isEmptyString(subOrderNo) || StringUtil.isEmptyString(result) || StringUtil.isEmptyString(opType)) {
                return error("缺少必传参数");
            }


            if (StringUtil.isEmptyString(arrivalTime) || badNumber == null || checkNumber == null) {
                return error("缺少必传参数");
            }

            if (!"N".equals(result) && !"Y".equals(result)) {
                return error("参数有误!");
            }

            SubOrder subOrder = subOrderService.getSubOrderBySubOrderNo(subOrderNo);
            if (subOrder == null) {
                return WebApiResponse.error("采购单不存在");
            }

            if (!RoleEnum.admin.getCodeName().equals(userInfo.getRoleName())) {
                if (!RoleEnum.storage.getCodeName().equals(userInfo.getRoleName())) {
                    return error("没有权限");
                }
            }

            OrderOPerateTypeEnum orderOPerateTypeEnum = OrderOPerateTypeEnum.valueOf(opType);
            if (orderOPerateTypeEnum == null) {
                return error("操作类型有误");
            }

            if (subOrder.getSubOrderStatus() != SubOrderStatusEnum.QUALITY_CHECK.getCode()) {
                return error("当前状态不能质检！");
            }

            ProductQualityInfo productQualityInfo = productQualityInfoService.getProductQualityInfoBySubOrderNo(subOrderNo);


            if (productQualityInfo == null) {
                productQualityInfo = new ProductQualityInfo();
            }


            productQualityInfo.setCheckNumber(checkNumber);
            productQualityInfo.setBadNumber(badNumber);
            if (productQualityInfo.getId() == null) {
                productQualityInfo.setCreatedTime(new Date());
            }


            productQualityInfo.setArrivalTime(DateUtil.parseObjToDate(arrivalTime));
            productQualityInfo.setOpUserName(userInfo.getUserName());
            productQualityInfo.setOpUserNo(userInfo.getUserNo());
            productQualityInfo.setOpUserRole(userInfo.getRoleName());
            productQualityInfo.setOrderNo(subOrder.getOrderNo());
            productQualityInfo.setPackagingQuality(packagingQuality);
            productQualityInfo.setPrintingQuality(printingQuality);
            productQualityInfo.setRemark(remark);
            productQualityInfo.setSubOrderNo(subOrderNo);
            productQualityInfo.setSuturingQuality(suturingQuality);
            productQualityInfo.setUpdatedTime(new Date());


            try {
                Long arrivalTimeLong = Long.valueOf(DateUtil.formatToStr(productQualityInfo.getArrivalTime()));
                Long nowLong = Long.valueOf(DateUtil.formatToStr(new Date()));

                if (nowLong < arrivalTimeLong) {
                    return error("实际到货时间不能晚于当天");
                }
            } catch (Exception ee) {
            }


            if (orderOPerateTypeEnum == OrderOPerateTypeEnum.SUBMIT) { //提交
                if ("N".equals(result)) {
                    productQualityInfo.setCheckStatus(1);
                    productQualityInfoService.saveProductQualityInfo(productQualityInfo);

                    subOrder.setSubOrderStatus(SubOrderStatusEnum.BUYER_ORDER.getCode());

                    List<MaterielOrder> materielOrderList = materielOrderService.listMaterielOrderBySubOrderNo(subOrderNo);
                    for (MaterielOrder materielOrder : materielOrderList) {
                        //物料单跟单记录
                        OrderFollowRecord orderFollowRecord = new OrderFollowRecord();
                        orderFollowRecord.setUpdatedTime(new Date());
                        orderFollowRecord.setTitle("跟单记录,采购单质检不通过");
                        orderFollowRecord.setSubOrderNo(materielOrder.getSubOrderNo());
                        orderFollowRecord.setRemark("物料单状态由" + MaterielOrderStatusEnum.valueOf(materielOrder.getMaterielOrderStatus()).getDec() + "变为" + MaterielOrderStatusEnum.DISCARDED.getDec());
                        orderFollowRecord.setSort(MaterielOrderStatusEnum.DISCARDED.getCode());
                        orderFollowRecord.setOrderNo(materielOrder.getSubOrderNo());
                        orderFollowRecord.setMaterielOrderNo(String.valueOf(materielOrder.getId()));
                        orderFollowRecord.setOpUserName(userInfo.getUserName());
                        orderFollowRecord.setOpUserNo(userInfo.getUserNo());
                        orderFollowRecord.setCreatedTime(new Date());

                        materielOrder.setMaterielOrderStatus(MaterielOrderStatusEnum.DISCARDED.getCode());

                        materielOrderService.updateMaterielOrder(materielOrder);
                        orderFollowRecordService.save(orderFollowRecord);
                    }
                    subOrderService.updateSubOrder(subOrder);

                    //保存采购订单流转记录
                    OrderWorkFlow orderWorkFlow = new OrderWorkFlow();
                    orderWorkFlow.setCreatedTime(new Date());
                    orderWorkFlow.setNewOrderStatus(subOrder.getSubOrderStatus());
                    orderWorkFlow.setOldOrderStatus(SubOrderStatusEnum.QUALITY_CHECK.getCode());
                    orderWorkFlow.setOpUserName(userInfo.getUserName());
                    orderWorkFlow.setOpUserNo(userInfo.getUserNo());
                    orderWorkFlow.setOpUserRole(userInfo.getRoleName());
                    orderWorkFlow.setOrderNo(subOrder.getSubOrderNo());
                    orderWorkFlow.setType(OrderWorkFlowTypeEnum.SUB_ORDER.getCodeName());
                    orderWorkFlow.setRemark(remark);
                    orderWorkFlow.setResult("保存并提交质检信息，质检不通过");
                    orderWorkFlow.setUpdatedTime(new Date());
                    orderWorkFlowService.save(orderWorkFlow);
                } else if ("Y".equals(result)) {
                    productQualityInfo.setCheckStatus(0);
                    productQualityInfoService.saveProductQualityInfo(productQualityInfo);

                    subOrder.setSubOrderStatus(SubOrderStatusEnum.INVOICE_APPLY.getCode());
                    subOrder.setSubPayStatus(SubOrderPayStatusEnum.FIN_TAIL_APV.getCode());

                    List<MaterielOrder> materielOrderList = materielOrderService.listMaterielOrderBySubOrderNo(subOrderNo);
                    for (MaterielOrder materielOrder : materielOrderList) {
                        //物料单跟单记录
                        OrderFollowRecord orderFollowRecord = new OrderFollowRecord();
                        orderFollowRecord.setUpdatedTime(new Date());
                        orderFollowRecord.setTitle("跟单记录,质检完成");
                        orderFollowRecord.setSubOrderNo(materielOrder.getSubOrderNo());
                        orderFollowRecord.setRemark("物料单状态由" + MaterielOrderStatusEnum.valueOf(materielOrder.getMaterielOrderStatus()).getDec() + "变为" + MaterielOrderStatusEnum.FACTORY_COMPLETE.getDec());
                        orderFollowRecord.setSort(MaterielOrderStatusEnum.FACTORY_COMPLETE.getCode());
                        orderFollowRecord.setOrderNo(materielOrder.getSubOrderNo());
                        orderFollowRecord.setMaterielOrderNo(String.valueOf(materielOrder.getId()));
                        orderFollowRecord.setOpUserName(userInfo.getUserName());
                        orderFollowRecord.setOpUserNo(userInfo.getUserNo());
                        orderFollowRecord.setCreatedTime(new Date());

                        materielOrder.setMaterielOrderStatus(MaterielOrderStatusEnum.FACTORY_COMPLETE.getCode());
                        materielOrder.setPayStatus(MaterielOrderPayStatusEnum.FIN_TAIL_APV.getCode());

                        materielOrderService.updateMaterielOrder(materielOrder);
                        orderFollowRecordService.save(orderFollowRecord);
                    }

                    PurchaseOrderExt purchaseOrderExt = purchaseOrderExtService.getPurchaseOrderExtBySubOrderNo(subOrder.getSubOrderNo());
                    if (purchaseOrderExt != null) {
                        purchaseOrderExt.setCompleteQualityCheckTime(new Date());
                        purchaseOrderExt.setUpdatedTime(new Date());
                        purchaseOrderExtService.updatePurchaseOrderExt(purchaseOrderExt);
                    }

                    subOrderService.updateSubOrder(subOrder);

                    //保存订单流转记录
                    OrderWorkFlow orderWorkFlow = new OrderWorkFlow();
                    orderWorkFlow.setCreatedTime(new Date());
                    orderWorkFlow.setNewOrderStatus(subOrder.getSubOrderStatus());
                    orderWorkFlow.setOldOrderStatus(SubOrderStatusEnum.QUALITY_CHECK.getCode());
                    orderWorkFlow.setOpUserName(userInfo.getUserName());
                    orderWorkFlow.setOpUserNo(userInfo.getUserNo());
                    orderWorkFlow.setOpUserRole(userInfo.getRoleName());
                    orderWorkFlow.setOrderNo(subOrder.getSubOrderNo());
                    orderWorkFlow.setType(OrderWorkFlowTypeEnum.SUB_ORDER.getCodeName());
                    orderWorkFlow.setRemark(remark);
                    orderWorkFlow.setResult("保存并提交质检信息,质检通过");
                    orderWorkFlow.setUpdatedTime(new Date());
                    orderWorkFlowService.save(orderWorkFlow);
                }
            } else { //暂时保存
                if ("N".equals(result)) {
                    productQualityInfo.setCheckStatus(1);
                    productQualityInfoService.saveProductQualityInfo(productQualityInfo);

                    //保存采购订单流转记录
                    OrderWorkFlow orderWorkFlow = new OrderWorkFlow();
                    orderWorkFlow.setCreatedTime(new Date());
                    orderWorkFlow.setNewOrderStatus(subOrder.getSubOrderStatus());
                    orderWorkFlow.setOldOrderStatus(SubOrderStatusEnum.QUALITY_CHECK.getCode());
                    orderWorkFlow.setOpUserName(userInfo.getUserName());
                    orderWorkFlow.setOpUserNo(userInfo.getUserNo());
                    orderWorkFlow.setOpUserRole(userInfo.getRoleName());
                    orderWorkFlow.setOrderNo(subOrder.getSubOrderNo());
                    orderWorkFlow.setType(OrderWorkFlowTypeEnum.SUB_ORDER.getCodeName());
                    orderWorkFlow.setRemark(remark);
                    orderWorkFlow.setResult("暂存质检信息，质检不通过");
                    orderWorkFlow.setUpdatedTime(new Date());
                    orderWorkFlowService.save(orderWorkFlow);
                } else if ("Y".equals(result)) {
                    productQualityInfo.setCheckStatus(0);
                    productQualityInfoService.saveProductQualityInfo(productQualityInfo);

                    //保存订单流转记录
                    OrderWorkFlow orderWorkFlow = new OrderWorkFlow();
                    orderWorkFlow.setCreatedTime(new Date());
                    orderWorkFlow.setNewOrderStatus(subOrder.getSubOrderStatus());
                    orderWorkFlow.setOldOrderStatus(SubOrderStatusEnum.QUALITY_CHECK.getCode());
                    orderWorkFlow.setOpUserName(userInfo.getUserName());
                    orderWorkFlow.setOpUserNo(userInfo.getUserNo());
                    orderWorkFlow.setOpUserRole(userInfo.getRoleName());
                    orderWorkFlow.setOrderNo(subOrder.getSubOrderNo());
                    orderWorkFlow.setType(OrderWorkFlowTypeEnum.SUB_ORDER.getCodeName());
                    orderWorkFlow.setRemark(remark);
                    orderWorkFlow.setResult("暂存质检信息,质检通过");
                    orderWorkFlow.setUpdatedTime(new Date());
                    orderWorkFlowService.save(orderWorkFlow);
                }
            }
        } catch (Exception ex) {
            logger.error("保存/提交采购单质检信息异常", ex);
            return error("保存/提交采购单质检信息异常");
        }
        return WebApiResponse.success("success");
    }


    /**
     * 获取采购单质检信息
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/getQualityCheckInfo", method = RequestMethod.GET)
    public WebApiResponse<QualityCheckInfoVo> getQualityCheckInfo(@RequestParam(name = "subOrderNo") String subOrderNo) {

        UserInfo userInfo = UserStatus.getUserInfo();
        try {
            if (StringUtil.isEmptyString(subOrderNo)) {
                return error("缺少必传参数");
            }

            SubOrder subOrder = subOrderService.getSubOrderBySubOrderNo(subOrderNo);
            if (subOrder == null) {
                return WebApiResponse.error("采购单不存在");
            }

            ProductQualityInfo productQualityInfo = productQualityInfoService.getProductQualityInfoBySubOrderNo(subOrderNo);

            if (productQualityInfo == null) {
                return error("产品质检信息不存在");
            }

            QualityCheckInfoVo qualityCheckInfoVo = new QualityCheckInfoVo();
            qualityCheckInfoVo.setId(productQualityInfo.getId());
            qualityCheckInfoVo.setArrivalTime(DateUtil.formatToStrTime(productQualityInfo.getArrivalTime()));
            qualityCheckInfoVo.setBadNumber(productQualityInfo.getBadNumber());
            qualityCheckInfoVo.setCheckNumber(productQualityInfo.getCheckNumber());
            qualityCheckInfoVo.setOrderNo(productQualityInfo.getOrderNo());
            qualityCheckInfoVo.setPackagingQuality(productQualityInfo.getPackagingQuality());
            qualityCheckInfoVo.setPrintingQuality(productQualityInfo.getPrintingQuality());
            qualityCheckInfoVo.setRemark(productQualityInfo.getRemark());

            if (productQualityInfo.getCheckStatus() == 0) {
                qualityCheckInfoVo.setResult("Y");
            } else {
                qualityCheckInfoVo.setResult("N");
            }
            qualityCheckInfoVo.setSubOrderNo(subOrderNo);
            qualityCheckInfoVo.setSuturingQuality(productQualityInfo.getSuturingQuality());

            return WebApiResponse.success(qualityCheckInfoVo);
        } catch (Exception ex) {
            logger.error("获取采购单质检信息异常", ex);
            return error("获取采购单质检信息异常");
        }
    }

}
