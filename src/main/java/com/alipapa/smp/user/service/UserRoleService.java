package com.alipapa.smp.user.service;

import com.alipapa.smp.user.mapper.UserRoleMapper;
import com.alipapa.smp.user.pojo.UserRole;
import com.alipapa.smp.user.pojo.UserRoleExample;
import com.alipapa.smp.user.vo.UserVo;
import com.alipapa.smp.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Service
public class UserRoleService {
    @Autowired
    private UserRoleMapper userRoleMapper;

    /**
     * @param userId
     * @return
     */
    public List<UserRole> listRoleByUserId(Long userId) {
        if (userId == null) {
            return null;
        }

        UserRoleExample example = new UserRoleExample();
        UserRoleExample.Criteria criteria = example.createCriteria();
        criteria.andUserIdEqualTo(userId);
        return userRoleMapper.selectByExample(example);
    }

    /**
     * @param userId
     * @param roleId
     * @return
     */
    public UserRole getUserRoleByUserIdAndRoleId(Long userId, Long roleId) {
        UserRoleExample example = new UserRoleExample();
        UserRoleExample.Criteria criteria = example.createCriteria();
        criteria.andUserIdEqualTo(userId);
        criteria.andRoleIdEqualTo(roleId);

        List<UserRole> userRoleList = userRoleMapper.selectByExample(example);
        if (!CollectionUtils.isEmpty(userRoleList)) {
            return userRoleList.get(0);
        }
        return null;
    }

    /**
     * @param userRole
     * @return
     */
    public boolean updateUserRole(UserRole userRole) {
        if (userRole == null || userRole.getId() == null) {
            return false;
        }
        userRoleMapper.updateByPrimaryKey(userRole);
        return true;
    }

    /**
     * @param userRole
     * @return
     */
    public boolean deleteUserRole(UserRole userRole) {
        if (userRole == null || userRole.getId() == null) {
            return false;
        }
        userRoleMapper.deleteByPrimaryKey(userRole.getId());
        return true;
    }


    /**
     * @param token
     * @return
     */
    public UserRole getUserRoleByToken(String token) {
        UserRoleExample example = new UserRoleExample();
        UserRoleExample.Criteria criteria = example.createCriteria();
        criteria.andTokenEqualTo(token);

        List<UserRole> userRoleList = userRoleMapper.selectByExample(example);
        if (!CollectionUtils.isEmpty(userRoleList)) {
            return userRoleList.get(0);
        }
        return null;
    }

    /**
     * @param id
     * @return
     */
    public UserRole getUserRoleByUserRoleId(Long id) {
        if (id == null) {
            return null;
        }
        return userRoleMapper.selectByPrimaryKey(id);
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
