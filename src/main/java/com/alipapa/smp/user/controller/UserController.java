package com.alipapa.smp.user.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alipapa.smp.common.request.UserInfo;
import com.alipapa.smp.common.request.UserStatus;
import com.alipapa.smp.user.pojo.Group;
import com.alipapa.smp.user.pojo.Role;
import com.alipapa.smp.user.pojo.User;
import com.alipapa.smp.user.pojo.UserRole;
import com.alipapa.smp.user.service.GroupService;
import com.alipapa.smp.user.service.RoleService;
import com.alipapa.smp.user.service.UserRoleService;
import com.alipapa.smp.user.service.UserService;
import com.alipapa.smp.user.vo.*;
import com.alipapa.smp.utils.DateUtil;
import com.alipapa.smp.utils.MD5;
import com.alipapa.smp.utils.SecurityUtil;
import com.alipapa.smp.utils.WebApiResponse;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

import static com.alipapa.smp.utils.WebApiResponse.error;

/**
 * 用户管理接口
 *
 * @author liuwei
 * @version 1.0
 * 2018-02-23
 */

@RestController
@CrossOrigin
@RequestMapping("/api/user")
public class UserController {
    private static Logger logger = LoggerFactory.getLogger(UserController.class);


    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private GroupService groupService;


    /**
     * 登录接口
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public WebApiResponse<LoginInfo> userLogin(@RequestParam("userNo") String userNo, @RequestParam("pwd") String pwd, @RequestParam("role") String roleName) {
        if (StringUtils.isBlank(userNo) || StringUtils.isBlank(pwd) || StringUtils.isBlank(roleName)) {
            logger.error("参数不能为空!");
            return error("参数不可以为空");
        }
        User user = userService.getUserByUserNo(userNo);
        if (user == null) {
            return error("用户名或密码错误");
        }

        Role role = roleService.getRoleByName(roleName);
        if (role == null) {
            return error("角色不存在");
        }

        UserRole userRole = userRoleService.getUserRoleByUserIdAndRoleId(user.getId(), role.getId());
        if (userRole == null) {
            return error("用户角色不存在");
        }

        if (!user.getPwd().equals(MD5.digist(pwd))) {
            return error("用户名或密码错误");
        }

        Map<String, Object> srcData = new HashMap<>();
        srcData.put("user", user.getId());
        srcData.put("role", role.getId());
        srcData.put("userRole", userRole.getId());
        try {
            String token = SecurityUtil.authentication(srcData);
            userRole.setToken(token);
            userRole.setExpireTime(DateUtil.addDays(1));
            userRoleService.updateUserRole(userRole);

            LoginInfo loginInfo = new LoginInfo();
            loginInfo.setToken(token);
            loginInfo.setUuid(user.getUuid());
            loginInfo.setRoleName(roleName);
            loginInfo.setUserRoleId(userRole.getId());
            loginInfo.setUserName(user.getName());
            loginInfo.setDescription(role.getDescription());
            return WebApiResponse.success(loginInfo);
        } catch (Exception e) {
            logger.error("登录异常", e);
            return error("登录异常");
        }
    }


    /**
     * 获取人员角色
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/listRole", method = RequestMethod.GET)
    public WebApiResponse<List<RoleVo>> listRole(@RequestParam("userNo") String userNo) {
        if (StringUtils.isBlank(userNo)) {
            logger.error("参数不能为空!");
            return error("参数不可以为空");
        }
        User user = userService.getUserByUserNo(userNo);

        if (user == null) {
            return error("用户名不存在");
        }

        List<UserRole> userRoleList = userRoleService.listRoleByUserId(user.getId());
        if (CollectionUtils.isEmpty(userRoleList)) {
            return error("请联系管理员设置角色");
        }

        List<RoleVo> roles = new ArrayList<>();
        for (UserRole userRole : userRoleList) {
            Role role = roleService.getRoleById(userRole.getRoleId());
            if (role != null) {
                RoleVo roleVo = new RoleVo();
                roleVo.setId(role.getId());
                roleVo.setRoleName(role.getRoleName());
                roleVo.setDescription(role.getDescription());
                roles.add(roleVo);
            }
        }
        return WebApiResponse.success(roles);
    }


    /**
     * 新建用户
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public WebApiResponse<String> addUser(HttpServletRequest request) {
        UserInfo userInfo = UserStatus.getUserInfo();

        try {
            String name = request.getParameter("name");
            String groupId = request.getParameter("groupId");
            String roleIds = request.getParameter("roleIds");
            String remark = request.getParameter("remark");

            if (StringUtils.isBlank(name) || StringUtils.isBlank(roleIds)) {
                return error("请输入必要信息");
            }

            if (userService.getUserByName(name) != null) {
                logger.error("员工姓名已存在: " + name);
                return error("员工姓名已存在");
            }

            Long userId = userService.getLatestUserId();

            logger.info("userId:" + userId);
            if (userId == null) {
                userId = 0l;
            }

            User newUser = new User();
            newUser.setUserNo(String.valueOf(DateUtil.getYear()) + String.format("%04d", userId + 1));
            newUser.setName(name);
            newUser.setCreateUser(userInfo.getUserNo());
            newUser.setUuid(UUID.randomUUID().toString());
            newUser.setPwd(MD5.digist("666666"));//默认密码
            newUser.setRemark(remark);
            newUser.setIsLeader(0);
            newUser.setCreatedTime(new Date());
            newUser.setUpdatedTime(new Date());
            if (StringUtils.isNotBlank(groupId) && Long.valueOf(groupId) > 0L) {
                Group group = groupService.getGroupById(Long.valueOf(groupId));
                if (group == null) {
                    throw new Exception("组不存在");
                }
                newUser.setGroupId(group.getId());
                newUser.setGroupNo(group.getGroupNo());
            }

            String[] roleIdArray = roleIds.split(";");

            userService.addUser(newUser, Arrays.asList(roleIdArray));
            return WebApiResponse.success("success");
        } catch (Exception ex) {
            logger.error("新建用户失败!", ex);
            return error("新建用户失败：" + ex.getMessage());
        }
    }


    /**
     * 用户查询
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/listUserRole", method = RequestMethod.GET)
    public WebApiResponse<List<UserVo>> listUserRole(@RequestParam(name = "pageSize", required = false) Integer pageSize,
                                                     @RequestParam(name = "pageNum", required = false) Integer pageNum,
                                                     @RequestParam(name = "userNo", required = false) String userNo,
                                                     @RequestParam(name = "roleName", required = false) String roleName,
                                                     @RequestParam(name = "name", required = false) String name) {

        if (pageSize == null) {
            pageSize = 30;
        }

        if (pageNum == null) {
            pageNum = 1;
        }

        Map<String, Object> params = new HashMap<>();
        if (StringUtils.isNotBlank(userNo)) {
            params.put("userNo", userNo);//对应数据库name
        }
        if (StringUtils.isNotBlank(roleName)) {
            params.put("roleName", roleName);

        }
        if (StringUtils.isNotBlank(name)) {
            params.put("name", name);//对应数据库Name
        }

        Integer start = (pageNum - 1) * pageSize;
        Integer size = pageSize;

        //获取用户角色信息
        List<UserVo> userVoList = userRoleService.findUserByParam(params, start, size);


        if (CollectionUtils.isEmpty(userVoList)) {
            return WebApiResponse.success(new ArrayList<>(), 0);

        }
        return WebApiResponse.success(userVoList, userVoList.get(0).getTotalCount());
    }


    /**
     * 重置用户密码
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/resetPwd", method = RequestMethod.POST)
    public WebApiResponse<String> resetUserPwd(@RequestParam("userNo") String userNo) {
        if (StringUtils.isBlank(userNo)) {
            logger.error("参数不能为空!");
            return error("参数不可以为空");
        }
        User user = userService.getUserByUserNo(userNo);

        if (user == null) {
            return WebApiResponse.error("用户名不存在");
        }

        user.setPwd(MD5.digist("666666"));//默认密码
        userService.updateUser(user);
        return WebApiResponse.success("success");
    }


    /**
     * 修改密码
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/updatePwd", method = RequestMethod.POST)
    public WebApiResponse<String> resetUserPwd(@RequestParam("userNo") String userNo, @RequestParam("pwd") String pwd) {
        if (StringUtils.isBlank(userNo) || StringUtils.isBlank(pwd)) {
            logger.error("参数不能为空!");
            return error("参数不可以为空");
        }

        if (!userNo.equals(UserStatus.getUserInfo().getUserNo())) {
            return WebApiResponse.error("没有权限");
        }
        User user = userService.getUserByUserNo(userNo);

        if (user == null) {
            return WebApiResponse.error("用户名不存在");
        }

        user.setPwd(MD5.digist(pwd));
        userService.updateByPrimaryKey(user);
        return WebApiResponse.success("success");
    }


    /**
     * 更新用户信息
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/updateUserInfo", method = RequestMethod.POST)
    public WebApiResponse<String> resetUserPwd(@RequestParam("userNo") String userNo,
                                               @RequestParam(name = "enName", required = false) String enName,
                                               @RequestParam(name = "mobile", required = false) String mobile,
                                               @RequestParam(name = "email1", required = false) String email1,
                                               @RequestParam(name = "email2", required = false) String email2) {
        if (StringUtils.isBlank(userNo)) {
            logger.error("参数userNo不能为空!");
            return error("参数userNo不可以为空");
        }

        if (!userNo.equals(UserStatus.getUserInfo().getUserNo())) {
            return WebApiResponse.error("没有权限");
        }
        User user = userService.getUserByUserNo(userNo);

        if (user == null) {
            return WebApiResponse.error("用户名不存在");
        }

        user.setEnName(enName);
        user.setMobile(mobile);
        user.setFirstEmail(email1);
        user.setSecondEmail(email2);
        userService.updateByPrimaryKey(user);
        return WebApiResponse.success("success");
    }


    /**
     * 更新用户信息
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/getUserInfo", method = RequestMethod.GET)
    public WebApiResponse<User> getUserInfo(@RequestParam("userNo") String userNo) {
        if (StringUtils.isBlank(userNo)) {
            logger.error("参数userNo不能为空!");
            return error("参数userNo不可以为空");
        }

/*        if (!userNo.equals(UserStatus.getUserInfo().getUserNo())) {
            return WebApiResponse.error("没有权限");
        }*/
        User user = userService.getUserByUserNo(userNo);
        return WebApiResponse.success(user);
    }


    /**
     * 修改员工角色组
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/updateUserRole", method = RequestMethod.POST)
    public WebApiResponse<String> updateUserRole(@RequestParam("id") Long id, @RequestParam(name = "groupId", required = false) Long groupId, @RequestParam(name = "roleId", required = false) Long roleId) {
        String roleName = UserStatus.getUserInfo().getRoleName();
        if (!"admin".equals(roleName)) {
            return error("没有权限");
        }

        if (id == null || id <= 0L) {
            logger.error("参数不能为空!");
            return error("参数不可以为空");
        }

        if ((roleId == null || roleId <= 0L) && (groupId == null || groupId <= 0L)) {
            return error("参数不可以为空");
        }


        UserRole userRole = userRoleService.getUserRoleByUserRoleId(id);

        if ("admin".equals(userRole.getRoleName())) {
            return error("admin权限不能被修改");
        }

        User user = userService.getUserById(userRole.getUserId());
        if (user == null) {
            return error("员工不存在！");
        }

        if (roleId != null) {
            Role role = roleService.getRoleById(roleId);
            Group group = groupService.getGroupById(groupId);

            if ((role != null && role.getId() == userRole.getRoleId()) && (group != null && group.getId() == user.getGroupId())) {
                return error("已存在该角色");
            }

            userRole.setRoleId(roleId);
            userRole.setRoleName(role.getRoleName());
            userRoleService.updateUserRole(userRole);
        }


        if (groupId != null) {
            if (user.getGroupId() == null || user.getGroupId() != groupId) {
                user.setGroupId(groupId);
                userService.updateUser(user);
            }
        }

        return WebApiResponse.success("success");
    }


    /**
     * 批量删除
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/batchDelUser", method = RequestMethod.POST)
    public WebApiResponse<String> batchDelUser(@RequestParam("userRoleIds") String userRoleIds) {
        String roleName = UserStatus.getUserInfo().getRoleName();
        if (!"admin".equals(roleName)) {
            return error("没有权限");
        }

        if (StringUtils.isBlank(userRoleIds)) {
            logger.error("参数不能为空!");
            return error("参数不可以为空");
        }

        String[] userRoleIdsArray = userRoleIds.split(";");
        for (String userRoleId : userRoleIdsArray) {
            UserRole userRole = userRoleService.getUserRoleByUserRoleId(Long.valueOf(userRoleId));

            Long userId = userRole.getUserId();
            boolean result = userRoleService.deleteUserRole(userRole);
            if (!result) {
                return WebApiResponse.error("删除异常");
            }
            List<UserRole> userRoleList = userRoleService.listRoleByUserId(userId);

            if (CollectionUtils.isEmpty(userRoleList)) {
                userService.deleteUser(userId);
            }
        }
        return WebApiResponse.success("delete success!");
    }


    /**
     * 角色列表
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/roleSelect", method = RequestMethod.GET)
    public WebApiResponse<List<RoleSelectVo>> roleSelect() {
        return WebApiResponse.success(roleService.listAllRoleSelect());
    }


    /**
     * 组列表
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/groupSelect", method = RequestMethod.GET)
    public WebApiResponse<List<GroupSelectVo>> groupSelect() {
        return WebApiResponse.success(groupService.listAllGroupSelect());
    }

    /*  public static void main(String[] args) {

        List<String> ss = new ArrayList();
        ss.add("qw");
        ss.add("ww");
        ss.add("aw");


        System.out.println(JSONObject.toJSONString(ss));

        System.out.println(String.format("%04d", 11));
    }*/
}