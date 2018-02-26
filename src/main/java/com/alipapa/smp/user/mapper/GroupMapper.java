package com.alipapa.smp.user.mapper;

import com.alipapa.smp.user.pojo.Group;

public interface GroupMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Group record);

    int insertSelective(Group record);

    Group selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Group record);

    int updateByPrimaryKey(Group record);
}