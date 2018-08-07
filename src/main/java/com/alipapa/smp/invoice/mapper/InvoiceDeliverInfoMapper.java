package com.alipapa.smp.invoice.mapper;

import com.alipapa.smp.invoice.pojo.InvoiceDeliverInfo;
import com.alipapa.smp.invoice.pojo.InvoiceDeliverInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface InvoiceDeliverInfoMapper {
    long countByExample(InvoiceDeliverInfoExample example);

    int deleteByExample(InvoiceDeliverInfoExample example);

    int deleteByPrimaryKey(Long id);

    int insert(InvoiceDeliverInfo record);

    int insertSelective(InvoiceDeliverInfo record);

    List<InvoiceDeliverInfo> selectByExample(InvoiceDeliverInfoExample example);

    InvoiceDeliverInfo selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") InvoiceDeliverInfo record, @Param("example") InvoiceDeliverInfoExample example);

    int updateByExample(@Param("record") InvoiceDeliverInfo record, @Param("example") InvoiceDeliverInfoExample example);

    int updateByPrimaryKeySelective(InvoiceDeliverInfo record);

    int updateByPrimaryKey(InvoiceDeliverInfo record);
}