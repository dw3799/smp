package com.alipapa.smp.user.service;

import com.alipapa.smp.user.mapper.UserRoleMapper;
import com.alipapa.smp.user.pojo.UserRole;
import com.alipapa.smp.user.vo.UserVo;
import com.alipapa.smp.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class UserRoleService {
    @Autowired
    private UserRoleMapper userRoleMapper;

    public List<UserRole> listRoleByUserId(Long userId) {
        return null;
    }

    public UserRole getUserRoleByUserIdAndRoleId(Long userId, Long roleId) {
        return null;
    }

    public boolean updateUserRole(UserRole role) {
        return false;
    }

    public UserRole getUserRoleByToken(String token) {
        return null;
    }

    /**
     * 用户分页查询
     *
     * @param params
     * @param start
     * @param size
     * @return
     */
    public List<UserVo> findUserByParam(Map<String, Object> params, Integer start, Integer size) {
        if (params == null) {
            return null;
        }
        Long count = this.findUserByParamCount(params);

        if (count <= 0) {
            return null;
        }

        params.put("start", start);
        params.put("size", size);

        List<UserVo> userVoList = userRoleMapper.findUserByParam(params);

        if (!CollectionUtils.isEmpty(userVoList)) {
            for (UserVo userVo : userVoList) {
                userVo.setCreatedTimeString(DateUtil.formatToYMDTime(userVo.getCreatedTime()));
                userVo.setTotalCount(count);
            }
            return userVoList;
        }
        return new ArrayList<>();
    }

    /**
     * 总数，分页使用
     *
     * @param params
     * @return
     */
    public Long findUserByParamCount(Map<String, Object> params) {
        if (params == null) {
            return 0l;
        }
        return userRoleMapper.findUserByParamCount(params);
    }
}
