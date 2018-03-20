package com.alipapa.smp.permission.mapper;

import com.alipapa.smp.permission.pojo.PermissionItem;
import com.alipapa.smp.permission.pojo.PermissionItemExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PermissionItemMapper {
    long countByExample(PermissionItemExample example);

    int deleteByExample(PermissionItemExample example);

    int deleteByPrimaryKey(Long id);

    int insert(PermissionItem record);

    int insertSelective(PermissionItem record);

    List<PermissionItem> selectByExample(PermissionItemExample example);

    PermissionItem selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") PermissionItem record, @Param("example") PermissionItemExample example);

    int updateByExample(@Param("record") PermissionItem record, @Param("example") PermissionItemExample example);

    int updateByPrimaryKeySelective(PermissionItem record);

    int updateByPrimaryKey(PermissionItem record);
}