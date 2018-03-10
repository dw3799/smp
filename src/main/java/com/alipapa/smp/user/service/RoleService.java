package com.alipapa.smp.user.service;

import com.alipapa.smp.user.mapper.RoleMapper;
import com.alipapa.smp.user.pojo.Role;
import com.alipapa.smp.user.pojo.RoleExample;
import com.alipapa.smp.user.vo.RoleSelectVo;
import com.alipapa.smp.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleService {

    @Autowired
    private RoleMapper roleMapper;

    /**
     * 根据角色名称获取角色
     *
     * @param roleName
     * @return
     */
    public Role getRoleByName(String roleName) {
        if (StringUtil.isEmptyString(roleName)) {
            return null;
        }
        RoleExample example = new RoleExample();
        RoleExample.Criteria criteria = example.createCriteria();
        criteria.andRoleNameEqualTo(roleName);

        List<Role> roleList = roleMapper.selectByExample(example);

        if (!CollectionUtils.isEmpty(roleList)) {
            return roleList.get(0);
        }

        return null;
    }

    /**
     * @param id
     * @return
     */
    public Role getRoleById(Long id) {
        return roleMapper.selectByPrimaryKey(id);
    }

    /**
     * 获取角色下拉列表
     *
     * @return
     */
    public List<RoleSelectVo> listAllRoleSelect() {
        RoleExample example = new RoleExample();
        List<Role> roleList = roleMapper.selectByExample(example);

        List<RoleSelectVo> roleSelectVoList = new ArrayList<>();

        if (!CollectionUtils.isEmpty(roleList)) {
            for (Role role : roleList) {
                if (!"admin".equals(role.getRoleName())) {
                    RoleSelectVo roleSelectVo = new RoleSelectVo();
                    roleSelectVo.setRoleId(role.getId());
                    roleSelectVo.setRoleName(role.getRoleName());
                    roleSelectVo.setDescription(role.getDescription());
                    roleSelectVoList.add(roleSelectVo);
                }
            }
        }
        return roleSelectVoList;
    }
}
