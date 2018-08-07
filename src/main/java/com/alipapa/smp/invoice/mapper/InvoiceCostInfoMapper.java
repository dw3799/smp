package com.alipapa.smp.invoice.mapper;

import com.alipapa.smp.invoice.pojo.InvoiceCostInfo;
import com.alipapa.smp.invoice.pojo.InvoiceCostInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface InvoiceCostInfoMapper {
    long countByExample(InvoiceCostInfoExample example);

    int deleteByExample(InvoiceCostInfoExample example);

    int deleteByPrimaryKey(Long id);

    int insert(InvoiceCostInfo record);

    int insertSelective(InvoiceCostInfo record);

    List<InvoiceCostInfo> selectByExample(InvoiceCostInfoExample example);

    InvoiceCostInfo selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") InvoiceCostInfo record, @Param("example") InvoiceCostInfoExample example);

    int updateByExample(@Param("record") InvoiceCostInfo record, @Param("example") InvoiceCostInfoExample example);

    int updateByPrimaryKeySelective(InvoiceCostInfo record);

    int updateByPrimaryKey(InvoiceCostInfo record);
}