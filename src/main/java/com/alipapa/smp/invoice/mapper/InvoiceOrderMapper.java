package com.alipapa.smp.invoice.mapper;

import com.alipapa.smp.invoice.pojo.InvoiceOrder;
import com.alipapa.smp.invoice.pojo.InvoiceOrderExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface InvoiceOrderMapper {
    long countByExample(InvoiceOrderExample example);

    int deleteByExample(InvoiceOrderExample example);

    int deleteByPrimaryKey(Long id);

    int insert(InvoiceOrder record);

    int insertSelective(InvoiceOrder record);

    List<InvoiceOrder> selectByExample(InvoiceOrderExample example);

    InvoiceOrder selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") InvoiceOrder record, @Param("example") InvoiceOrderExample example);

    int updateByExample(@Param("record") InvoiceOrder record, @Param("example") InvoiceOrderExample example);

    int updateByPrimaryKeySelective(InvoiceOrder record);

    int updateByPrimaryKey(InvoiceOrder record);
}