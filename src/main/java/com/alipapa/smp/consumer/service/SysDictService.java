package com.alipapa.smp.consumer.service;

import com.alipapa.smp.consumer.mapper.SysDictMapper;
import com.alipapa.smp.consumer.pojo.SysDict;
import com.alipapa.smp.consumer.pojo.SysDictExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysDictService {
    @Autowired
    private SysDictMapper sysDictMapper;

    public List<SysDict> listSysDict(String categoryCode) {
        SysDictExample example = new SysDictExample();
        SysDictExample.Criteria criteria = example.createCriteria();
        criteria.andCategoryCodeEqualTo(categoryCode);
        example.setOrderByClause("sort");
        return sysDictMapper.selectByExample(example);
    }
}
