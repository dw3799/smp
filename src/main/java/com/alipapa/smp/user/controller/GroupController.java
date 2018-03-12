package com.alipapa.smp.user.controller;

import com.alipapa.smp.user.pojo.Group;
import com.alipapa.smp.user.pojo.User;
import com.alipapa.smp.user.service.GroupService;
import com.alipapa.smp.user.service.RoleService;
import com.alipapa.smp.user.service.UserRoleService;
import com.alipapa.smp.user.service.UserService;
import com.alipapa.smp.user.vo.FuzzyUserVo;
import com.alipapa.smp.user.vo.GroupVo;
import com.alipapa.smp.user.vo.GroupWithUserVo;
import com.alipapa.smp.utils.WebApiResponse;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.*;

import static com.alipapa.smp.utils.WebApiResponse.error;

/**
 * 组管理接口
 *
 * @author liuwei
 * @version 1.0
 * 2018-03-10
 */

@RestController
@RequestMapping("/api/group")
public class GroupController {
    private static Logger logger = LoggerFactory.getLogger(GroupController.class);

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private GroupService groupService;


    /**
     * 用户模糊查询
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/userFuzzyQuery", method = RequestMethod.GET)
    public WebApiResponse<List<FuzzyUserVo>> userFuzzyQuery(@RequestParam("searchString") String searchString) {
        if (StringUtils.isBlank(searchString)) {
            return WebApiResponse.success(new ArrayList<>());
        }

        List<FuzzyUserVo> fuzzyUserVoList = userService.userSearch(searchString);
        return WebApiResponse.success(fuzzyUserVoList);
    }

    /**
     * 新建组
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/addGroup", method = RequestMethod.POST)
    public WebApiResponse<String> addGroup(@RequestParam(name = "groupName") String groupName,
                                           @RequestParam(name = "leaderId") Long leaderId,
                                           @RequestParam(name = "members") String members) {
        if (StringUtils.isBlank(groupName) || leaderId == null || StringUtils.isBlank(members)) {
            logger.error("参数不能为空!");
            return error("参数不可以为空");
        }

        Group group = new Group();
        User leader = userService.getUserById(leaderId);
        if (leader == null) {
            return WebApiResponse.error("组长不存在");
        }
        group.setLeaderId(leaderId);
        group.setLeaderName(leader.getName());
        Long groupId = groupService.getLatestGroupId();
        String groupNo = "G" + String.format("%03d", groupId + 1);
        groupService.getLatestGroupId();
        group.setGroupNo(groupNo);
        group.setName(groupName);
        group.setCreatedTime(new Date());
        group.setUpdatedTime(new Date());
        boolean result = groupService.addGroup(group);

        if (!result) {
            return WebApiResponse.error("组创建失败");
        }

        Group savedGroup = groupService.getGroupByGroupNo(groupNo);

        if (group == null) {
            return WebApiResponse.error("组创建失败");
        }

        leader.setGroupId(savedGroup.getId());
        leader.setGroupNo(savedGroup.getGroupNo());
        userService.updateUser(leader);

        String[] userIds = members.split(";");
        for (String userId : userIds) {
            User member = userService.getUserById(Long.valueOf(userId));
            member.setGroupId(group.getId());
            member.setGroupNo(group.getGroupNo());
            userService.updateUser(member);
        }

        return WebApiResponse.success("success");
    }


    /**
     * 更新组
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/updateGroup", method = RequestMethod.POST)
    public WebApiResponse<String> updateGroup(@RequestParam(name = "groupId") Long groupId,
                                              @RequestParam(name = "groupName") String groupName,
                                              @RequestParam(name = "leaderId") Long leaderId,
                                              @RequestParam(name = "members") String members) {
        if (groupId == null || StringUtils.isBlank(groupName) || leaderId == null || StringUtils.isBlank(members)) {
            logger.error("参数不能为空!");
            return error("参数不可以为空");
        }

        Group group = groupService.getGroupById(groupId);
        if (group == null) {
            return WebApiResponse.error("组不存在");
        }
        group.setName(groupName);

        User leader = userService.getUserById(leaderId);
        if (leader == null) {
            return WebApiResponse.error("组长不存在");
        }

        group.setLeaderId(leaderId);
        group.setLeaderName(leader.getName());

        leader.setGroupId(group.getId());
        leader.setGroupNo(group.getGroupNo());

        String[] userIds = members.split(";");
        for (String userId : userIds) {
            User member = userService.getUserById(Long.valueOf(userId));
            member.setGroupId(group.getId());
            member.setGroupNo(group.getGroupNo());
            userService.updateUser(member);
        }

        userService.updateUser(leader);
        groupService.updateGroup(group);
        return WebApiResponse.success("success");
    }


    /**
     * 新建组
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/listGroup", method = RequestMethod.GET)
    public WebApiResponse<List<GroupVo>> listGroup(@RequestParam("pageSize") Integer pageSize,
                                                   @RequestParam("pageNum") Integer pageNum,
                                                   @RequestParam(name = "groupNo", required = false) String groupNo,
                                                   @RequestParam(name = "groupName", required = false) String groupName,
                                                   @RequestParam(name = "leaderId", required = false) String leaderId) {


        if (pageSize == null) {
            pageSize = 1;
        }

        if (pageNum == null) {
            pageNum = 30;
        }

        Map<String, Object> params = new HashMap<>();
        if (StringUtils.isNotBlank(groupNo)) {
            params.put("groupNo", groupNo);//对应数据库name
        }
        if (StringUtils.isNotBlank(groupName)) {
            params.put("groupName", groupName);

        }
        if (StringUtils.isNotBlank(leaderId)) {
            params.put("leaderId", leaderId);//对应数据库Name
        }

        Integer start = (pageNum - 1) * pageSize;
        Integer size = pageSize;

        List<GroupVo> groupVoList = groupService.listGroupByParams(params, start, size);

        return WebApiResponse.success(groupVoList);
    }


    /**
     * 获取组信息及用户
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/getGroup/{groupId}", method = RequestMethod.GET)
    public WebApiResponse<GroupWithUserVo> addGroup(@PathVariable Long groupId) {
        if (groupId == null) {
            logger.error("参数不能为空!");
            return error("参数不可以为空");
        }

        Group group = groupService.getGroupById(groupId);
        if (group == null) {
            return WebApiResponse.error("groupId错误");
        }
        GroupWithUserVo groupWithUserVo = new GroupWithUserVo();
        groupWithUserVo.setGroupId(group.getId());
        groupWithUserVo.setGroupName(group.getName());
        groupWithUserVo.setGroupNo(group.getGroupNo());
        //User leader = userService.getUserById(group.getLeaderId());
        groupWithUserVo.setLeaderId(group.getLeaderId());
        groupWithUserVo.setLeaderName(group.getLeaderName());

        List<FuzzyUserVo> members = new ArrayList<>();
        List<User> userList = userService.listUserByGroupId(group.getId());
        if (!CollectionUtils.isEmpty(userList)) {
            for (User user : userList) {
                if (user.getId() != group.getLeaderId()) {//组长不算入组员
                    FuzzyUserVo fuzzyUserVo = new FuzzyUserVo();
                    fuzzyUserVo.setUserNo(user.getUserNo());
                    fuzzyUserVo.setName(user.getName());
                    fuzzyUserVo.setUserId(user.getId());
                    members.add(fuzzyUserVo);
                }
            }
        }

        groupWithUserVo.setMembers(members);
        return WebApiResponse.success(groupWithUserVo);
    }


    /**
     * 主管下拉列表
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/leaderSelect", method = RequestMethod.GET)
    public WebApiResponse<List<FuzzyUserVo>> roleSelect() {
        return null;
    }


}
