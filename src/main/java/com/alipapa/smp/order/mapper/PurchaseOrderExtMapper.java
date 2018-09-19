package com.alipapa.smp.order.mapper;

import com.alipapa.smp.order.pojo.PurchaseOrderExt;
import com.alipapa.smp.order.pojo.PurchaseOrderExtExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PurchaseOrderExtMapper {
    long countByExample(PurchaseOrderExtExample example);

    int deleteByExample(PurchaseOrderExtExample example);

    int deleteByPrimaryKey(Long id);

    int insert(PurchaseOrderExt record);

    int insertSelective(PurchaseOrderExt record);

    List<PurchaseOrderExt> selectByExample(PurchaseOrderExtExample example);

    PurchaseOrderExt selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") PurchaseOrderExt record, @Param("example") PurchaseOrderExtExample example);

    int updateByExample(@Param("record") PurchaseOrderExt record, @Param("example") PurchaseOrderExtExample example);

    int updateByPrimaryKeySelective(PurchaseOrderExt record);

    int updateByPrimaryKey(PurchaseOrderExt record);
}