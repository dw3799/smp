package com.alipapa.smp.stock.service;

import com.alipapa.smp.stock.pojo.StockManage;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author liuwei
 */
public interface StockManageService {

    boolean deleteByPrimaryKey(Long id);

    boolean save(StockManage record);

    StockManage selectById(Long id);

    List<StockManage> selectAll();

    List<StockManage> selectByparams(String type, String shelfno);

    boolean updateByPrimaryKey(StockManage record);
}
