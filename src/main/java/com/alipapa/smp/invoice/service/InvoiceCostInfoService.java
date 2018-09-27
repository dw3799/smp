package com.alipapa.smp.invoice.service;

import com.alipapa.smp.invoice.mapper.InvoiceCostInfoMapper;
import com.alipapa.smp.invoice.pojo.InvoiceCostInfo;
import com.alipapa.smp.invoice.pojo.InvoiceCostInfoExample;
import com.alipapa.smp.invoice.pojo.InvoiceOrderExt;
import com.alipapa.smp.invoice.pojo.InvoiceOrderExtExample;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;

@Service
public class InvoiceCostInfoService {

    @Resource
    private InvoiceCostInfoMapper invoiceCostInfoMapper;

    public boolean saveInvoiceCostInfo(InvoiceCostInfo invoiceCostInfo) {
        invoiceCostInfoMapper.insert(invoiceCostInfo);
        return true;
    }

    /**
     * 获取采购单费用
     *
     * @param invoiceOrderNo
     * @return
     */
    public InvoiceCostInfo getInvoiceCostInfoBySubOrderNo(String invoiceOrderNo) {
        InvoiceCostInfoExample example = new InvoiceCostInfoExample();
        InvoiceCostInfoExample.Criteria criteria = example.createCriteria();
        criteria.andInvoiceNoEqualTo(invoiceOrderNo);
        List<InvoiceCostInfo> invoiceCostInfoList = invoiceCostInfoMapper.selectByExample(example);

        if (CollectionUtils.isEmpty(invoiceCostInfoList)) {
            return null;
        }
        return invoiceCostInfoList.get(0);
    }


}
