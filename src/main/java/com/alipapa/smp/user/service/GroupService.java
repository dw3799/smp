package com.alipapa.smp.user.service;

import com.alipapa.smp.user.mapper.GroupMapper;
import com.alipapa.smp.user.pojo.Group;
import com.alipapa.smp.user.pojo.GroupExample;
import com.alipapa.smp.user.vo.GroupSelectVo;
import com.alipapa.smp.user.vo.GroupVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class GroupService {

    @Autowired
    private GroupMapper groupMapper;

    /**
     * @param id
     * @return
     */
    public Group getGroupById(Long id) {
        return groupMapper.selectByPrimaryKey(id);
    }

    /**
     * @param groupNo
     * @return
     */
    public Group getGroupByGroupNo(String groupNo) {
        GroupExample example = new GroupExample();
        GroupExample.Criteria criteria = example.createCriteria();
        criteria.andGroupNoEqualTo(groupNo);

        List<Group> groupList = groupMapper.selectByExample(example);
        if (!CollectionUtils.isEmpty(groupList)) {
            return groupList.get(0);
        }
        return null;
    }


    /**
     * @param groupName
     * @return
     */
    public List<Group> getGroupByGroupName(String groupName) {
        GroupExample example = new GroupExample();
        GroupExample.Criteria criteria = example.createCriteria();
        criteria.andNameEqualTo(groupName);

        return groupMapper.selectByExample(example);
    }

    /**
     * @param leaderId
     * @return
     */
    public List<Group> getGroupByLeaderId(Long leaderId) {
        GroupExample example = new GroupExample();
        GroupExample.Criteria criteria = example.createCriteria();
        criteria.andLeaderIdEqualTo(leaderId);

        return groupMapper.selectByExample(example);
    }

    /**
     * @param group
     * @return
     */
    public boolean updateGroup(Group group) {
        List<Group> leadGroupList = this.getGroupByLeaderId(group.getLeaderId());
        if (!CollectionUtils.isEmpty(leadGroupList)) {
            for (Group leadGroup : leadGroupList) {
                if (leadGroup.getId() != group.getId()) {
                    leadGroup.setLeaderId(null);
                    leadGroup.setLeaderName(null);
                    groupMapper.updateByPrimaryKey(leadGroup);
                }
            }
        }
        groupMapper.updateByPrimaryKey(group);
        return true;
    }


    /**
     * @param group
     * @return
     */
    public boolean addGroup(Group group) {
        List<Group> leadGroupList = this.getGroupByLeaderId(group.getLeaderId());
        if (!CollectionUtils.isEmpty(leadGroupList)) {
            for (Group leadGroup : leadGroupList) {
                leadGroup.setLeaderId(null);
                leadGroup.setLeaderName(null);
                groupMapper.updateByPrimaryKey(leadGroup);
            }
        }

        groupMapper.insert(group);
        return true;
    }



    /**
     * 获取组下拉列表
     *
     * @return
     */
    public List<GroupSelectVo> listAllGroupSelect() {
        GroupExample example = new GroupExample();
        example.setOrderByClause("created_time");

        List<Group> groupList = groupMapper.selectByExample(example);
        List<GroupSelectVo> groupSelectVoList = new ArrayList<>();

        if (!CollectionUtils.isEmpty(groupList)) {

            for (Group group : groupList) {
                GroupSelectVo groupSelectVo = new GroupSelectVo();
                groupSelectVo.setGroupId(group.getId());
                groupSelectVo.setGroupNo(group.getGroupNo());
                groupSelectVo.setGroupName(group.getName());
                groupSelectVoList.add(groupSelectVo);
            }
        }
        return groupSelectVoList;
    }


    /**
     * 获取组下拉列表
     *
     * @return
     */
    public List<Group> listOrinGroup() {
        GroupExample example = new GroupExample();
        example.setOrderByClause("created_time");
        return groupMapper.selectByExample(example);
    }



    /**
     * 获取组下拉列表
     *
     * @return
     */
    public List<GroupVo> listGroupByParams(Map<String, Object> params, Integer start, Integer size) {
        Long count = groupMapper.findGroupByParamCount(params);

        List<GroupVo> groupVoList = new ArrayList<>();
        if (count != null && count > 0) {
            params.put("start", start);
            params.put("size", size);
            List<Group> groupList = groupMapper.findGroupByParam(params);
            if (!CollectionUtils.isEmpty(groupList)) {
                for (Group group : groupList) {
                    GroupVo groupVo = new GroupVo();
                    groupVo.setGroupId(group.getId());
                    groupVo.setGroupName(group.getName());
                    groupVo.setGroupNo(group.getGroupNo());
                    groupVo.setLeaderName(group.getLeaderName());
                    groupVo.setCount(count);
                    groupVoList.add(groupVo);
                }
            }
        }
        return groupVoList;
    }



    /**
     * @return
     */
    public Long getLatestGroupId() {
        return groupMapper.selectMaxId();
    }


}
