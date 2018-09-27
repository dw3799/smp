package com.alipapa.smp.invoice.service.impl;

import com.alipapa.smp.common.enums.DeliverTypeEnum;
import com.alipapa.smp.common.enums.InvoiceOrderStatusEnum;
import com.alipapa.smp.invoice.mapper.InvoiceOrderMapper;
import com.alipapa.smp.invoice.pojo.InvoiceCostInfo;
import com.alipapa.smp.invoice.pojo.InvoiceOrder;
import com.alipapa.smp.invoice.pojo.InvoiceOrderExt;
import com.alipapa.smp.invoice.service.InvoiceCostInfoService;
import com.alipapa.smp.invoice.service.InvoiceOrderExtService;
import com.alipapa.smp.invoice.vo.InvoiceOrderVo;
import com.alipapa.smp.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class InvoiceOrderServiceProxy {

    @Resource
    private InvoiceOrderMapper invoiceOrderMapper;

    @Autowired
    private InvoiceOrderExtService invoiceOrderExtService;


    @Autowired
    private InvoiceCostInfoService invoiceCostInfoService;


    /**
     * 我的订单列表
     *
     * @param params
     * @return
     */
    public List<InvoiceOrderVo> listMyInvoiceOrderByParams(Map<String, Object> params, Integer start, Integer size) {
        Long totalCount = invoiceOrderMapper.listMyInvoiceOrderByParamCount(params);

        if (totalCount <= 0) {
            return null;
        }
        params.put("start", start);
        params.put("size", size);

        List<InvoiceOrder> invoiceOrderList = invoiceOrderMapper.listMyInvoiceOrderByParam(params);

        List<InvoiceOrderVo> orderVoList = this.convertInvoiceOrderVo(invoiceOrderList, totalCount);
        return orderVoList;
    }


    /**
     * VO转换
     *
     * @return
     */
    private List<InvoiceOrderVo> convertInvoiceOrderVo(List<InvoiceOrder> invoiceOrderList, Long totalCount) {
        List<InvoiceOrderVo> orderVoList = new ArrayList<>();

        if (!CollectionUtils.isEmpty(invoiceOrderList)) {
            for (InvoiceOrder invoiceOrder : invoiceOrderList) {
                InvoiceOrderVo orderVo = new InvoiceOrderVo();
                orderVo.setTotalCount(totalCount);
                orderVo.setBuyerUserName(invoiceOrder.getBuyerUserName());
                orderVo.setBuyerUserNo(invoiceOrder.getBuyerUserNo());
                orderVo.setConsumerCountry(invoiceOrder.getConsumerCountry());
                orderVo.setConsumerName(invoiceOrder.getConsumerName());
                orderVo.setConsumerNo(invoiceOrder.getConsumerNo());
                orderVo.setCreatedTime(DateUtil.formatToStrTimeV1(invoiceOrder.getCreatedTime()));
                orderVo.setId(invoiceOrder.getId());
                orderVo.setInvoiceOrderNo(invoiceOrder.getInvoiceNo());
                orderVo.setInvoiceStatus(InvoiceOrderStatusEnum.valueOf(invoiceOrder.getInvoiceStatus()).getDec());
                orderVo.setOrderNo(invoiceOrder.getOrderNo());
                orderVo.setSalerUserName(invoiceOrder.getSalerUserName());
                orderVo.setSalerUserNo(invoiceOrder.getSalerUserNo());


                InvoiceOrderExt invoiceOrderExt = invoiceOrderExtService.getInvoiceOrderExtBySubOrderNo(invoiceOrder.getInvoiceNo());
                if (invoiceOrderExt != null) {
                    orderVo.setCheckOutTime(DateUtil.formatToStrTimeV1(invoiceOrderExt.getCheckOutTime()));
                    orderVo.setDocTime(DateUtil.formatToStrTimeV1(invoiceOrderExt.getDocTime()));
                    orderVo.setFinApvTime(DateUtil.formatToStrTimeV1(invoiceOrderExt.getFinApvTime()));
                    orderVo.setSubmitTime(DateUtil.formatToStrTimeV1(invoiceOrderExt.getSubmitTime()));
                    orderVo.setStorageUserName(invoiceOrderExt.getStorageUserName());
                    orderVo.setStorageUserNo(invoiceOrderExt.getStorageUserNo());
                }
                orderVo.setDeliverType(DeliverTypeEnum.valueOf(invoiceOrder.getDeliverType()).getDec());


                InvoiceCostInfo invoiceCostInfo = invoiceCostInfoService.getInvoiceCostInfoBySubOrderNo(invoiceOrder.getInvoiceNo());
                if (invoiceCostInfo != null) {
                    orderVo.setTransportChannel(invoiceCostInfo.getTransportChannel());
                }
                orderVoList.add(orderVo);
            }
        }
        return orderVoList;
    }


}
