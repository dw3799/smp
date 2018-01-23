package com.alipapa.smp.stock.service;

import com.alipapa.smp.stock.mapper.StockManageMapper;
import com.alipapa.smp.stock.pojo.StockManage;
import com.alipapa.smp.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author liuwei
 */
@Component("stockManageService")
public class StockManageServiceImpl implements StockManageService {


    @Autowired
    private StockManageMapper stockManageMapper;

    @Transactional
    @Override
    public boolean deleteByPrimaryKey(Long id) {
        if (id == null || id == 0L) {
            return false;
        }
        stockManageMapper.deleteByPrimaryKey(id);
        return true;
    }

    @Transactional
    @Override
    public boolean save(StockManage record) {
        if (record == null) {
            return false;
        }
        stockManageMapper.insert(record);
        return true;
    }

    @Override
    public StockManage selectById(Long id) {
        if (id == null || id == 0L) {
            return null;
        }
        return stockManageMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<StockManage> selectAll() {
        return stockManageMapper.selectAll();
    }

    @Override
    public List<StockManage> selectByparams(String type, String shelfno) {
        if (StringUtil.isEmptyString(type) && StringUtil.isEmptyString(shelfno)) {
            return null;
        }
        return stockManageMapper.selectByparams(type, shelfno);
    }

    @Override
    public boolean updateByPrimaryKey(StockManage record) {
        if (record == null) {
            return false;
        }
        stockManageMapper.updateByPrimaryKey(record);
        return true;
    }
}
