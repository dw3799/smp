package com.alipapa.smp.invoice.mapper;

import com.alipapa.smp.invoice.pojo.InvoiceOrderExt;
import com.alipapa.smp.invoice.pojo.InvoiceOrderExtExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface InvoiceOrderExtMapper {
    long countByExample(InvoiceOrderExtExample example);

    int deleteByExample(InvoiceOrderExtExample example);

    int deleteByPrimaryKey(Long id);

    int insert(InvoiceOrderExt record);

    int insertSelective(InvoiceOrderExt record);

    List<InvoiceOrderExt> selectByExample(InvoiceOrderExtExample example);

    InvoiceOrderExt selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") InvoiceOrderExt record, @Param("example") InvoiceOrderExtExample example);

    int updateByExample(@Param("record") InvoiceOrderExt record, @Param("example") InvoiceOrderExtExample example);

    int updateByPrimaryKeySelective(InvoiceOrderExt record);

    int updateByPrimaryKey(InvoiceOrderExt record);
}