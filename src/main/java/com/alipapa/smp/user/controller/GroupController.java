package com.alipapa.smp.user.controller;

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
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
     * 新建组
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/userFuzzyQuery", method = RequestMethod.GET)
    public WebApiResponse<List<FuzzyUserVo>> userFuzzyQuery(@RequestParam("searchString") String searchString) {
        List<FuzzyUserVo> fuzzyUserVoList = new ArrayList<>();

        return null;
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
        return WebApiResponse.success("");
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
        return WebApiResponse.success("");
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


        return WebApiResponse.success(null);
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
        return WebApiResponse.success(null);
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
