package com.alipapa.smp.invoice.service;

import com.alipapa.smp.invoice.mapper.InvoiceDeliverInfoMapper;
import com.alipapa.smp.invoice.pojo.InvoiceDeliverInfo;
import com.alipapa.smp.invoice.pojo.InvoiceDeliverInfoExample;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;

@Service
public class InvoiceDeliverInfoService {

    @Resource
    private InvoiceDeliverInfoMapper invoiceDeliverInfoMapper;

    public boolean saveInvoiceDeliverInfo(InvoiceDeliverInfo invoiceDeliverInfo) {
        invoiceDeliverInfoMapper.insert(invoiceDeliverInfo);
        return true;
    }

    /**
     * 获取采购单费用
     *
     * @param invoiceOrderNo
     * @return
     */
    public InvoiceDeliverInfo getInvoiceDeliverInfoBySubOrderNo(String invoiceOrderNo) {
        InvoiceDeliverInfoExample example = new InvoiceDeliverInfoExample();
        InvoiceDeliverInfoExample.Criteria criteria = example.createCriteria();
        criteria.andInvoiceNoEqualTo(invoiceOrderNo);
        List<InvoiceDeliverInfo> invoiceCostInfoList = invoiceDeliverInfoMapper.selectByExample(example);

        if (CollectionUtils.isEmpty(invoiceCostInfoList)) {
            return null;
        }
        return invoiceCostInfoList.get(0);
    }


}
