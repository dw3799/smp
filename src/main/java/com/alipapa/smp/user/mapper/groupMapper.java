package com.alipapa.smp.user.mapper;

import com.alipapa.smp.user.pojo.group;

public interface groupMapper {
    int deleteByPrimaryKey(Long id);

    int insert(group record);

    int insertSelective(group record);

    group selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(group record);

    int updateByPrimaryKey(group record);
}