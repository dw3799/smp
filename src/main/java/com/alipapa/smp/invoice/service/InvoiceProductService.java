package com.alipapa.smp.invoice.service;

import com.alipapa.smp.common.enums.InvoiceOrderStatusEnum;
import com.alipapa.smp.invoice.mapper.InvoiceProductMapper;
import com.alipapa.smp.invoice.pojo.InvoiceOrder;
import com.alipapa.smp.invoice.pojo.InvoiceProduct;
import com.alipapa.smp.invoice.pojo.InvoiceProductExample;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;

@Service
public class InvoiceProductService {

    @Resource
    private InvoiceProductMapper invoiceProductMapper;

    @Resource
    private InvoiceOrderService invoiceOrderService;


    /**
     * 获取发货单产品
     *
     * @param invoiceOrderNo
     * @return
     */
    public List<InvoiceProduct> listInvoiceProductByInvoiceOrderNo(String invoiceOrderNo) {
        InvoiceProductExample example = new InvoiceProductExample();
        InvoiceProductExample.Criteria criteria = example.createCriteria();
        criteria.andInvoiceNoEqualTo(invoiceOrderNo);
        List<InvoiceProduct> invoiceProductList = invoiceProductMapper.selectByExample(example);

        if (CollectionUtils.isEmpty(invoiceProductList)) {
            return null;
        }
        return invoiceProductList;
    }


    /**
     * 获取发货单产品
     *
     * @param orderNo
     * @return
     */
    public InvoiceProduct getInvoiceProductByParam(String orderNo, String subOrderNo) {
        InvoiceProductExample example = new InvoiceProductExample();
        InvoiceProductExample.Criteria criteria = example.createCriteria();
        criteria.andOrderNoEqualTo(orderNo);
        criteria.andSubOrderNoEqualTo(subOrderNo);

        List<InvoiceProduct> invoiceProductList = invoiceProductMapper.selectByExample(example);

        if (CollectionUtils.isEmpty(invoiceProductList)) {
            return null;
        }

        for (InvoiceProduct invoiceProduct : invoiceProductList) {
            InvoiceOrder invoiceOrder = invoiceOrderService.selectInvoiceOrderByInvoiceOrderNo(invoiceProduct.getInvoiceNo());
            if (invoiceOrder != null && invoiceOrder.getInvoiceStatus() != InvoiceOrderStatusEnum.DISCARD.getCode()) {
                return invoiceProduct;
            }
        }

        return null;
    }


}
