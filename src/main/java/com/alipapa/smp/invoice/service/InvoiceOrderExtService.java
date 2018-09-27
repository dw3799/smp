package com.alipapa.smp.invoice.service;


import com.alipapa.smp.invoice.mapper.InvoiceOrderExtMapper;
import com.alipapa.smp.invoice.pojo.InvoiceOrderExt;
import com.alipapa.smp.invoice.pojo.InvoiceOrderExtExample;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;

@Service
public class InvoiceOrderExtService {

    @Resource
    private InvoiceOrderExtMapper invoiceOrderExtMapper;


    public boolean saveInvoiceOrderExt(InvoiceOrderExt invoiceOrderExt) {
        invoiceOrderExtMapper.insert(invoiceOrderExt);
        return true;
    }


    public boolean updateInvoiceOrderExt(InvoiceOrderExt invoiceOrderExt) {
        invoiceOrderExtMapper.updateByPrimaryKey(invoiceOrderExt);
        return true;
    }


    /**
     * 获取采购单信息
     *
     * @param invoiceOrderNo
     * @return
     */
    public InvoiceOrderExt getInvoiceOrderExtBySubOrderNo(String invoiceOrderNo) {
        InvoiceOrderExtExample example = new InvoiceOrderExtExample();
        InvoiceOrderExtExample.Criteria criteria = example.createCriteria();
        criteria.andInvoiceOrderNoEqualTo(invoiceOrderNo);
        List<InvoiceOrderExt> invoiceOrderExtList = invoiceOrderExtMapper.selectByExample(example);

        if (CollectionUtils.isEmpty(invoiceOrderExtList)) {
            return null;
        }
        return invoiceOrderExtList.get(0);
    }


}
