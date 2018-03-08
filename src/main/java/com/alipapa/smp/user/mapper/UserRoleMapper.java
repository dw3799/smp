package com.alipapa.smp.user.mapper;

import com.alipapa.smp.user.pojo.UserRole;
import com.alipapa.smp.user.pojo.UserRoleExample;
import com.alipapa.smp.user.vo.UserVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface UserRoleMapper {
    long countByExample(UserRoleExample example);

    int deleteByExample(UserRoleExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UserRole record);

    int insertSelective(UserRole record);

    List<UserRole> selectByExample(UserRoleExample example);

    UserRole selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") UserRole record, @Param("example") UserRoleExample example);

    int updateByExample(@Param("record") UserRole record, @Param("example") UserRoleExample example);

    int updateByPrimaryKeySelective(UserRole record);

    int updateByPrimaryKey(UserRole record);

    List<UserVo> findUserByParam(Map<String, Object> params);

    Long findUserByParamCount(Map<String, Object> params);
}