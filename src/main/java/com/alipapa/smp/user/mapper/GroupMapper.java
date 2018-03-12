package com.alipapa.smp.user.mapper;

import com.alipapa.smp.user.pojo.Group;
import com.alipapa.smp.user.pojo.GroupExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface GroupMapper {
    long countByExample(GroupExample example);

    int deleteByExample(GroupExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Group record);

    int insertSelective(Group record);

    List<Group> selectByExample(GroupExample example);

    Group selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Group record, @Param("example") GroupExample example);

    int updateByExample(@Param("record") Group record, @Param("example") GroupExample example);

    int updateByPrimaryKeySelective(Group record);

    int updateByPrimaryKey(Group record);

    List<Group> findGroupByParam(Map<String, Object> params);

    long findGroupByParamCount(Map<String, Object> params);

    Long selectMaxId();
}