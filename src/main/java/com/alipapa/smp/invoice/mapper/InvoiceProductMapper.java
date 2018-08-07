package com.alipapa.smp.invoice.mapper;

import com.alipapa.smp.invoice.pojo.InvoiceProduct;
import com.alipapa.smp.invoice.pojo.InvoiceProductExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface InvoiceProductMapper {
    long countByExample(InvoiceProductExample example);

    int deleteByExample(InvoiceProductExample example);

    int deleteByPrimaryKey(Long id);

    int insert(InvoiceProduct record);

    int insertSelective(InvoiceProduct record);

    List<InvoiceProduct> selectByExample(InvoiceProductExample example);

    InvoiceProduct selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") InvoiceProduct record, @Param("example") InvoiceProductExample example);

    int updateByExample(@Param("record") InvoiceProduct record, @Param("example") InvoiceProductExample example);

    int updateByPrimaryKeySelective(InvoiceProduct record);

    int updateByPrimaryKey(InvoiceProduct record);
}