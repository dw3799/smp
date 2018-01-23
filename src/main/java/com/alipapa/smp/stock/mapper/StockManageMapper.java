package com.alipapa.smp.stock.mapper;

import com.alipapa.smp.stock.pojo.StockManage;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StockManageMapper {
    int deleteByPrimaryKey(Long id);

    int insert(StockManage record);

    int insertSelective(StockManage record);

    StockManage selectByPrimaryKey(Long id);

    List<StockManage> selectAll();

    List<StockManage> selectByparams(@Param("type") String type, @Param("shelfno") String shelfno);

    int updateByPrimaryKeySelective(StockManage record);

    int updateByPrimaryKey(StockManage record);
}