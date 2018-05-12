package com.alipapa.smp.consumer.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alipapa.smp.common.enums.CategoryCode;
import com.alipapa.smp.common.enums.ConsumerScopeEnum;
import com.alipapa.smp.common.enums.ConsumerSearchTypeEnum;
import com.alipapa.smp.common.enums.FellowUpRulesEnum;
import com.alipapa.smp.common.request.UserInfo;
import com.alipapa.smp.common.request.UserStatus;
import com.alipapa.smp.consumer.pojo.Consumer;
import com.alipapa.smp.consumer.pojo.ConsumerFollowRecord;
import com.alipapa.smp.consumer.pojo.SysDict;
import com.alipapa.smp.consumer.pojo.UserConsumerRelation;
import com.alipapa.smp.consumer.service.ConsumerFollowRecordService;
import com.alipapa.smp.consumer.service.ConsumerService;
import com.alipapa.smp.consumer.service.SysDictService;
import com.alipapa.smp.consumer.service.UserConsumerRelationService;
import com.alipapa.smp.consumer.vo.*;
import com.alipapa.smp.user.pojo.Group;
import com.alipapa.smp.user.pojo.User;
import com.alipapa.smp.user.service.GroupService;
import com.alipapa.smp.user.service.UserService;
import com.alipapa.smp.user.vo.FuzzyUserVo;
import com.alipapa.smp.user.vo.GroupSelectVo;
import com.alipapa.smp.utils.DateUtil;
import com.alipapa.smp.utils.StringUtil;
import com.alipapa.smp.utils.WebApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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
@CrossOrigin
@RequestMapping("/api/consumer")
public class ConsumerController {
    private static Logger logger = LoggerFactory.getLogger(ConsumerController.class);

    @Autowired
    private SysDictService sysDictService;


    @Autowired
    private ConsumerService consumerService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserConsumerRelationService userConsumerRelationService;

    @Autowired
    private GroupService groupService;

    @Autowired
    private ConsumerFollowRecordService consumerFollowRecordService;

    /**
     * 客户相关下拉列表
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/consumerSelect", method = RequestMethod.GET)
    public WebApiResponse<List<SysDictVo>> consumerSelect(@RequestParam("categoryCode") Integer categoryCode) {
        if (categoryCode == null || CategoryCode.valueOf(categoryCode) == null) {
            return WebApiResponse.error("参数有误！");
        }

        List<SysDictVo> sysDictVoList = new ArrayList<>();
        if (CategoryCode.order.getCode() == categoryCode) {
            SysDictVo sysDictVo = new SysDictVo();
            sysDictVo.setId(100l);
            sysDictVo.setCategoryCode(CategoryCode.order.getCodeName());
            sysDictVo.setDictText("有订单");
            sysDictVo.setDictValue("Y");
            sysDictVoList.add(sysDictVo);

            SysDictVo sysDictVo1 = new SysDictVo();
            sysDictVo1.setId(200l);
            sysDictVo1.setCategoryCode(CategoryCode.order.getCodeName());
            sysDictVo1.setDictText("无订单");
            sysDictVo1.setDictValue("N");
            sysDictVoList.add(sysDictVo1);
            return WebApiResponse.success(sysDictVoList);
        }


        List<SysDict> sysDictList = sysDictService.listSysDict(CategoryCode.valueOf(categoryCode).getCodeName());
        if (!CollectionUtils.isEmpty(sysDictList)) {
            for (SysDict sysDict : sysDictList) {
                SysDictVo sysDictVo = new SysDictVo();
                sysDictVo.setId(sysDict.getId());
                sysDictVo.setCategoryCode(sysDict.getCategoryCode());
                sysDictVo.setDictText(sysDict.getDictText());
                sysDictVo.setDictValue(sysDict.getDictValue());
                sysDictVoList.add(sysDictVo);
            }
        }
        return WebApiResponse.success(sysDictVoList);

    }


    /**
     * 判断客户是否存在
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/isExist", method = RequestMethod.GET)
    public WebApiResponse<String> isExistConsumer(@RequestParam("name") String name, @RequestParam("email") String email) {
        if (StringUtil.isEmptyString(name) || StringUtil.isEmptyString(email)) {
            return error("缺少必填参数");
        }
        WebApiResponse response = new WebApiResponse();
        response.setCode(0);

        Consumer consumer = consumerService.getConsumerByNameAndEmail(name, email);
        if (consumer != null) {
            if (consumer.getScope() == ConsumerScopeEnum.Public.getCodeName()) {
                response.setData("2");
                response.setError("客户已在公共资源池中，无须重复创建，请去抢本客户");
            } else {
                response.setData("3");
                response.setError("已存在，且不在公共资源池，是否继续跟进");
            }
        } else {
            response.setData("1");
            response.setError("");
        }
        return response;
    }


    /**
     * 弹出提示框“本客户已其他跟进人，是否继续跟进”，点击【是】，业务主管跳转至【我的客户】界面，业务员角色跳转至【潜在客户】页面；点击【否】，提示框消失，返回至客户创建界面。
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/stillFollowUp", method = RequestMethod.GET)
    public WebApiResponse<String> stillFollowUp(@RequestParam("name") String name, @RequestParam("email") String email) {
        UserInfo userInfo = UserStatus.getUserInfo();

        if (StringUtil.isEmptyString(name) || StringUtil.isEmptyString(email)) {
            return error("缺少必填参数");
        }

        Consumer consumer = consumerService.getConsumerByNameAndEmail(name, email);
        logger.info("consumer:" + consumer.getConsumerNo() + ",scope=" + consumer.getScope());
        if (consumer != null && (ConsumerScopeEnum.Private.getCodeName().equals(consumer.getScope()) || ConsumerScopeEnum.Protected.getCodeName().equals(consumer.getScope()))) {
            //2:抛弃规则判断
            if (!sysDictService.checkDiscardingRules(userInfo, consumer.getId())) {
                return error("根据抛弃规则，暂时无法跟进！");
            }

            //3:抛弃规则判断
            if (!sysDictService.checkReclaimRules(userInfo, consumer.getId())) {
                return error("根据回收规则，暂时无法跟进！");
            }

            User user = userService.getUserById(userInfo.getUserId());
            boolean result = relateUserAndConsumer(consumer, user);


            if (result && ConsumerScopeEnum.Private.getCodeName().equals(consumer.getScope())) {
                consumer.setScope(ConsumerScopeEnum.Protected.getCodeName());
                consumerService.updateConsumer(consumer);
            }
            return WebApiResponse.success("1");
        }
        return WebApiResponse.success("2");
    }

    /**
     * 新建客户
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/addConsumer", method = RequestMethod.POST)
    public WebApiResponse<String> addConsumer(HttpServletRequest request) {
        UserInfo userInfo = UserStatus.getUserInfo();

 /*       if (jsonStr == null) {
            logger.error("提交的json格式数据不可以为空!");
            return error("输入的信息不可以为空");
        }*/
        try {
/*            JSONObject json = JSON.parseObject(jsonStr);
            if (json == null) {
                logger.error("客户提交的数据解析失败: " + jsonStr);
                return error("客户数据解析失败");
            }*/

            //不能为空
            String name = request.getParameter("name");
            String country = request.getParameter("country");
            String mainBusiness = request.getParameter("mainBusiness");
            String source = request.getParameter("source");
            String type = request.getParameter("type");
            String email = request.getParameter("email");

            String intention = request.getParameter("intention");

            //可为空
            String facebook = request.getParameter("facebook");
            String whatsapp = request.getParameter("whatsapp");
            String linkedin = request.getParameter("linkedin");
            String wechat = request.getParameter("wechat");
            String qq = request.getParameter("qq");
            String contacts = request.getParameter("contacts");
            String companyAddress = request.getParameter("companyAddress");
            String companyWebsite = request.getParameter("companyWebsite");

            //收货地址
            String consignee = request.getParameter("consignee");
            String telMobile = request.getParameter("telMobile");
            String postalCode = request.getParameter("postalCode");
            String receivingAddress = request.getParameter("receivingAddress");


            if (StringUtil.isEmptyString(name) || StringUtil.isEmptyString(country) || StringUtil.isEmptyString(mainBusiness) || StringUtil.isEmptyString(source)
                    || StringUtil.isEmptyString(type) || StringUtil.isEmptyString(email)) {
                return error("缺少必填参数");
            }

            //1：判断客户是否已存在
            Consumer preConsumer = consumerService.getConsumerByNameAndEmail(name, email);
            if (preConsumer != null) {
                if (preConsumer.getScope() == ConsumerScopeEnum.Public.getCodeName()) {
                    return WebApiResponse.error("客户已在资源池中，无须重复创建，请去抢本客户");
                } else {
                    return WebApiResponse.error("若已存在，不在资源池，有人跟进");
                }
            }

            Consumer consumer = new Consumer();
            //YYYYMMDD+随机四位数
            Long consumerId = consumerService.getLatestConsumerId();
            if (consumerId == null) {
                consumerId = 0l;
            }
            consumer.setConsumerNo(DateUtil.formatToStr(new Date()) + String.format("%04d", consumerId + 1));

            //拓传参数
            consumer.setName(name);
            consumer.setCountry(country);
            consumer.setMainBusiness(mainBusiness);
            consumer.setSource(source);
            consumer.setType(type);
            consumer.setEmail(email);
            consumer.setIntention(intention);

            consumer.setFacebook(facebook);
            consumer.setWhatsapp(whatsapp);
            consumer.setLinkedin(linkedin);
            consumer.setWechat(wechat);
            consumer.setQq(qq);
            consumer.setContacts(contacts);
            consumer.setCompanyAddress(companyAddress);
            consumer.setCompanyWebsite(companyWebsite);

            consumer.setConsignee(consignee);
            consumer.setTelMobile(telMobile);
            consumer.setPostalCode(postalCode);
            consumer.setReceivingAddress(receivingAddress);

            consumer.setCreatedTime(new Date());
            consumer.setUpdatedTime(new Date());
            consumer.setCreateUser(userInfo.getUserNo());
            consumer.setScope(ConsumerScopeEnum.Private.getCodeName());
            boolean result = consumerService.addConsumer(consumer);

            if (result) {
                //创建关联关系
                Consumer dbConsumer = consumerService.getConsumerByNameAndEmail(name, email);
                UserConsumerRelation userConsumerRelation = new UserConsumerRelation();
                userConsumerRelation.setConsumerId(dbConsumer.getId());
                userConsumerRelation.setConsumerNo(dbConsumer.getConsumerNo());
                userConsumerRelation.setUserId(userInfo.getUserId());
                userConsumerRelation.setUserNo(userInfo.getUserNo());
                userConsumerRelation.setIsDel(0);
                userConsumerRelation.setHasOrder(0);
                userConsumerRelation.setDealOrder(0);
                userConsumerRelation.setFollowTime(new Date());
                userConsumerRelation.setCreatedTime(new Date());
                userConsumerRelation.setUpdatedTime(new Date());
                userConsumerRelationService.addUserConsumerRelation(userConsumerRelation);
                return WebApiResponse.success("success");
            }
        } catch (Exception ex) {
            logger.error("", ex);
            return error("添加客户异常");
        }
        return WebApiResponse.error("添加客户失败");
    }

    /**
     * 客户详情
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/consumerDetail/{consumerId}", method = RequestMethod.GET)
    public WebApiResponse<ConsumerVo> consumerSelect(@PathVariable("consumerId") Long consumerId) {
        UserInfo userInfo = UserStatus.getUserInfo();

        if (consumerId == null) {
            return WebApiResponse.error("consumerId不能为空！");
        }
        Consumer consumer = consumerService.getConsumerById(consumerId);
        if (consumer == null) {
            return WebApiResponse.error("客户不存在！");
        }
        ConsumerVo consumerVo = new ConsumerVo();
        consumerVo.setCompanyAddress(consumer.getCompanyAddress());
        consumerVo.setCompanyWebsite(consumer.getCompanyWebsite());
        consumerVo.setConsignee(consumer.getConsignee());
        consumerVo.setContacts(consumer.getContacts());
        consumerVo.setConsumerNo(consumer.getConsumerNo());
        consumerVo.setCountry(consumer.getCountry());
        consumerVo.setCreatedTime(DateUtil.formatToStrTime(consumer.getCreatedTime()));
        consumerVo.setEmail(consumer.getEmail());
        consumerVo.setFacebook(consumer.getFacebook());
        consumerVo.setId(consumerId);
        consumerVo.setLevel(consumer.getLevel());
        consumerVo.setLinkedin(consumer.getLinkedin());
        consumerVo.setMainBusiness(consumer.getMainBusiness());
        consumerVo.setName(consumer.getName());
        consumerVo.setPostalCode(consumer.getPostalCode());
        consumerVo.setQq(consumer.getQq());
        consumerVo.setSource(consumer.getSource());
        consumerVo.setTelMobile(consumer.getTelMobile());
        consumerVo.setReceivingAddress(consumer.getReceivingAddress());
        consumerVo.setType(consumer.getType());
        consumerVo.setUpdatedTime(DateUtil.formatToStrTime(consumer.getUpdatedTime()));
        consumerVo.setWechat(consumer.getWechat());
        consumerVo.setWhatsapp(consumer.getWhatsapp());
        consumerVo.setIntention(consumer.getIntention());

        if ("admin".equals(userInfo.getRoleName())) {
            consumerVo.setIsCanEdit(1);
        } else {
            consumerVo.setIsCanEdit(0);
        }
        return WebApiResponse.success(consumerVo);
    }


    /**
     * 更新客户信息
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/updateConsumer", method = RequestMethod.POST)
    public WebApiResponse<String> updateConsumer(HttpServletRequest request) {
        UserInfo userInfo = UserStatus.getUserInfo();

        try {
            //不能为空
            String idString = request.getParameter("consumerId");


            String consumerNo = request.getParameter("consumerNo");

            if (StringUtil.isEmptyString(consumerNo) || StringUtil.isEmptyString(consumerNo)) {
                return error("缺少必填参数");
            }

            Long id = Long.valueOf(idString);


            Consumer consumer = consumerService.getConsumerById(id);
            if (consumer == null) {
                return error("客户不存在");
            }

            if (!consumer.getConsumerNo().equals(consumerNo)) {
                return error("客户不存在");
            }

            String name = request.getParameter("name");
            String country = request.getParameter("country");
            String mainBusiness = request.getParameter("mainBusiness");
            String source = request.getParameter("source");
            String type = request.getParameter("type");
            String email = request.getParameter("email");
            String intention = request.getParameter("intention");

            //可为空
            String facebook = request.getParameter("facebook");
            String whatsapp = request.getParameter("whatsapp");
            String linkedin = request.getParameter("linkedin");
            String wechat = request.getParameter("wechat");
            String qq = request.getParameter("qq");
            String contacts = request.getParameter("contacts");
            String companyAddress = request.getParameter("companyAddress");
            String companyWebsite = request.getParameter("companyWebsite");

            //收货地址
            String consignee = request.getParameter("consignee");
            String telMobile = request.getParameter("telMobile");
            String postalCode = request.getParameter("postalCode");
            String receivingAddress = request.getParameter("receivingAddress");

            //客户更新参数校验
            if ("admin".equals(userInfo.getRoleName())) {//管理员可编辑
                if (StringUtil.isEmptyString(name) || StringUtil.isEmptyString(country) || StringUtil.isEmptyString(mainBusiness) || StringUtil.isEmptyString(source)
                        || StringUtil.isEmptyString(type) || StringUtil.isEmptyString(email)) {
                    return error("缺少必填参数");
                }
            } else {//其他人员只能补充
                if (StringUtil.isNotEmptyString(name) && StringUtil.isNotEmptyString(consumer.getName()) && !name.equals(consumer.getName())) {
                    return error("无权限修改客户信息:" + name);
                }
                if (StringUtil.isNotEmptyString(country) && StringUtil.isNotEmptyString(consumer.getCountry()) && !country.equals(consumer.getCountry())) {
                    return error("无权限修改客户信息:" + country);
                }
                if (StringUtil.isNotEmptyString(mainBusiness) && StringUtil.isNotEmptyString(consumer.getMainBusiness()) && !mainBusiness.equals(consumer.getMainBusiness())) {
                    return error("无权限修改客户信息:" + mainBusiness);
                }
                if (StringUtil.isNotEmptyString(email) && StringUtil.isNotEmptyString(consumer.getEmail()) && !email.equals(consumer.getEmail())) {
                    return error("无权限修改客户信息:" + email);
                }
                if (StringUtil.isNotEmptyString(intention) && StringUtil.isNotEmptyString(consumer.getIntention()) && !intention.equals(consumer.getIntention())) {
                    return error("无权限修改客户信息:" + intention);
                }

                if (StringUtil.isNotEmptyString(facebook) && StringUtil.isNotEmptyString(consumer.getFacebook()) && !facebook.equals(consumer.getFacebook())) {
                    return error("无权限修改客户信息:" + facebook);
                }

                if (StringUtil.isNotEmptyString(whatsapp) && StringUtil.isNotEmptyString(consumer.getWhatsapp()) && !whatsapp.equals(consumer.getWhatsapp())) {
                    return error("无权限修改客户信息:" + whatsapp);
                }

                if (StringUtil.isNotEmptyString(whatsapp) && StringUtil.isNotEmptyString(consumer.getWhatsapp()) && !whatsapp.equals(consumer.getWhatsapp())) {
                    return error("无权限修改客户信息:" + whatsapp);
                }

                if (StringUtil.isNotEmptyString(linkedin) && StringUtil.isNotEmptyString(consumer.getLinkedin()) && !linkedin.equals(consumer.getLinkedin())) {
                    return error("无权限修改客户信息:" + linkedin);
                }

                if (StringUtil.isNotEmptyString(wechat) && StringUtil.isNotEmptyString(consumer.getWechat()) && !wechat.equals(consumer.getWechat())) {
                    return error("无权限修改客户信息:" + wechat);
                }

                if (StringUtil.isNotEmptyString(qq) && StringUtil.isNotEmptyString(consumer.getQq()) && !qq.equals(consumer.getQq())) {
                    return error("无权限修改客户信息:" + qq);
                }

                if (StringUtil.isNotEmptyString(companyAddress) && StringUtil.isNotEmptyString(consumer.getCompanyAddress()) && !companyAddress.equals(consumer.getCompanyAddress())) {
                    return error("无权限修改客户信息:" + companyAddress);
                }

                if (StringUtil.isNotEmptyString(companyWebsite) && StringUtil.isNotEmptyString(consumer.getCompanyWebsite()) && !companyWebsite.equals(consumer.getCompanyWebsite())) {
                    return error("无权限修改客户信息:" + companyWebsite);
                }

                if (StringUtil.isNotEmptyString(consignee) && StringUtil.isNotEmptyString(consumer.getConsignee()) && !consignee.equals(consumer.getConsignee())) {
                    return error("无权限修改客户信息:" + consignee);
                }

                if (StringUtil.isNotEmptyString(telMobile) && StringUtil.isNotEmptyString(consumer.getTelMobile()) && !telMobile.equals(consumer.getTelMobile())) {
                    return error("无权限修改客户信息:" + telMobile);
                }

                if (StringUtil.isNotEmptyString(postalCode) && StringUtil.isNotEmptyString(consumer.getPostalCode()) && !postalCode.equals(consumer.getPostalCode())) {
                    return error("无权限修改客户信息:" + postalCode);
                }

                if (StringUtil.isNotEmptyString(receivingAddress) && StringUtil.isNotEmptyString(consumer.getReceivingAddress()) && !receivingAddress.equals(consumer.getReceivingAddress())) {
                    return error("无权限修改客户信息:" + receivingAddress);
                }
            }

            consumer.setConsumerNo(consumerNo);
            consumer.setUpdatedTime(new Date());

            //拓传参数
            consumer.setName(name);
            consumer.setCountry(country);
            consumer.setMainBusiness(mainBusiness);
            consumer.setSource(source);
            consumer.setType(type);
            consumer.setEmail(email);
            consumer.setIntention(intention);

            consumer.setFacebook(facebook);
            consumer.setWhatsapp(whatsapp);
            consumer.setLinkedin(linkedin);
            consumer.setWechat(wechat);
            consumer.setQq(qq);
            consumer.setContacts(contacts);
            consumer.setCompanyAddress(companyAddress);
            consumer.setCompanyWebsite(companyWebsite);

            consumer.setConsignee(consignee);
            consumer.setTelMobile(telMobile);
            consumer.setPostalCode(postalCode);
            consumer.setReceivingAddress(receivingAddress);

            boolean result = consumerService.updateConsumer(consumer);
            if (result) {
                return WebApiResponse.success("success");
            }
        } catch (Exception ex) {
            return error("更新客户信息异常");
        }
        return WebApiResponse.error("更新客户信息失败");
    }

    /**
     * 我的客户查询-组列表
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/groupCondition", method = RequestMethod.GET)
    public WebApiResponse<List<GroupSelectVo>> groupSelect() {
        UserInfo userInfo = UserStatus.getUserInfo();

        if ("admin".equals(userInfo.getRoleName())) {//管理员返回所有组
            return WebApiResponse.success(groupService.listAllGroupSelect());
        }

        User user = userService.getUserById(userInfo.getUserId());
        if (1 == user.getIsLeader()) {
            Group group = groupService.getGroupById(user.getGroupId());
            GroupSelectVo groupSelectVo = new GroupSelectVo();
            groupSelectVo.setGroupId(group.getId());
            groupSelectVo.setGroupNo(group.getGroupNo());
            groupSelectVo.setGroupName(group.getName());
            List<GroupSelectVo> groupSelectVoList = new ArrayList<>();
            groupSelectVoList.add(groupSelectVo);
            return WebApiResponse.success(groupSelectVoList);
        }

        return WebApiResponse.success(null);
    }


    /**
     * 我的客户查询-组员列表
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/memberCondition", method = RequestMethod.GET)
    public WebApiResponse<List<FuzzyUserVo>> memberCondition() {
        UserInfo userInfo = UserStatus.getUserInfo();
        if ("admin".equals(userInfo.getRoleName())) {//管理员返回所有人员
            List<FuzzyUserVo> fuzzyUserVoList = userService.userSearch(null);
            return WebApiResponse.success(fuzzyUserVoList);
        }

        User user = userService.getUserById(userInfo.getUserId());
        if (1 == user.getIsLeader()) {
            Group group = groupService.getGroupById(user.getGroupId());
            List<User> userList = userService.listUserByGroupId(group.getId());

            List<FuzzyUserVo> fuzzyUserVoList = new ArrayList<>();
            for (User member : userList) {
                FuzzyUserVo fuzzyUserVo = new FuzzyUserVo();
                fuzzyUserVo.setUserId(member.getId());
                fuzzyUserVo.setUserNo(member.getUserNo());
                fuzzyUserVo.setName(member.getName());
                fuzzyUserVoList.add(fuzzyUserVo);
            }
            return WebApiResponse.success(fuzzyUserVoList);
        } else {
            FuzzyUserVo fuzzyUserVo = new FuzzyUserVo();
            fuzzyUserVo.setUserId(user.getId());
            fuzzyUserVo.setUserNo(user.getUserNo());
            fuzzyUserVo.setName(user.getName());
            List<FuzzyUserVo> fuzzyUserVoList = new ArrayList<>();
            fuzzyUserVoList.add(fuzzyUserVo);
            return WebApiResponse.success(fuzzyUserVoList);
        }
    }


    /**
     * 业务员，潜在客户/成交客户/我的客户查询
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/listMyConsumer", method = RequestMethod.GET)
    public WebApiResponse<List<SalerConsumerDetailVo>> listPotentialOrDealConsumer(HttpServletRequest request) {
        UserInfo userInfo = UserStatus.getUserInfo();

        Integer pageSize = null;
        Integer pageNum = null;
        Map<String, Object> params = new HashMap<>();


        String searchTypeString = request.getParameter("searchType");

        if (StringUtil.isEmptyString(searchTypeString)) {
            return WebApiResponse.error("查询类型不能为空！");
        }


        Long searchType = Long.valueOf(searchTypeString);

        if (ConsumerSearchTypeEnum.Potential.getCode() == searchType) {
            params.put("dealOrder", 0);
            //用户ID
            params.put("userId", userInfo.getUserId());
        } else if (ConsumerSearchTypeEnum.Deal.getCode() == searchType) {
            params.put("dealOrder", 1);
            //用户ID
            params.put("userId", userInfo.getUserId());
        } else if (ConsumerSearchTypeEnum.MyConsumer.getCode() == searchType) {
            //员工ID
            String userIdString = request.getParameter("userId");
            if (StringUtil.isNotEmptyString(userIdString)) {
                params.put("userId", Long.valueOf(userIdString));
            }
            //组ID
            String groupIdString = request.getParameter("groupId");
            if (StringUtil.isNotEmptyString(groupIdString)) {
                params.put("groupId", Long.valueOf(groupIdString));
            }
        } else {
            return WebApiResponse.error("查询参数异常");
        }

        //客户编号
        String consumerNo = request.getParameter("consumerNo");
        if (!StringUtil.isEmptyString(consumerNo)) {
            params.put("consumerNo", consumerNo);
        }

        //客户姓名
        String name = request.getParameter("name");
        if (!StringUtil.isEmptyString(name)) {
            params.put("name", name);
        }

        //客户国籍
        String country = request.getParameter("country");
        if (!StringUtil.isEmptyString(country)) {
            params.put("country", country);
        }

        //客户等级
        String level = request.getParameter("level");
        if (!StringUtil.isEmptyString(level)) {
            params.put("level", level);
        }

        //是否有订单
        String hasOrder = request.getParameter("hasOrder");
        if (!StringUtil.isEmptyString(hasOrder)) {
            params.put("hasOrder", hasOrder);
        }

        //上次联系时间开始
        String preContactTimeStart = request.getParameter("preContactTimeStart");
        if (!StringUtil.isEmptyString(preContactTimeStart)) {
            params.put("preContactTimeStart", preContactTimeStart);
        }

        //上次联系时间结束
        String preContactTimeEnd = request.getParameter("preContactTimeEnd");
        if (!StringUtil.isEmptyString(preContactTimeEnd)) {
            params.put("preContactTimeEnd", preContactTimeEnd);
        }

        //下次联系时间开始
        String nextContactTimeStart = request.getParameter("nextContactTimeStart");
        if (!StringUtil.isEmptyString(nextContactTimeStart)) {
            params.put("nextContactTimeStart", nextContactTimeStart);
        }

        //下次联系时间结束
        String nextContactTimeEnd = request.getParameter("nextContactTimeEnd");
        if (!StringUtil.isEmptyString(nextContactTimeEnd)) {
            params.put("nextContactTimeEnd", nextContactTimeEnd);
        }


        String pageSizeString = request.getParameter("pageSize");
        if (StringUtil.isNotEmptyString(pageSizeString)) {
            pageSize = Integer.valueOf(pageSizeString);
        }
        String pageNumString = request.getParameter("pageNum");
        if (StringUtil.isNotEmptyString(pageNumString)) {
            pageNum = Integer.valueOf(pageNumString);
        }
        if (pageSize == null) {
            pageSize = 30;
        }
        if (pageNum == null) {
            pageNum = 1;
        }


        Integer start = (pageNum - 1) * pageSize;
        Integer size = pageSize;

        List<SalerConsumerDetailVo> consumerDetailVoList = consumerService.listSalerConsumerDetailVoByParams(params, start, size, userInfo.getUserNo());
        if (CollectionUtils.isEmpty(consumerDetailVoList)) {
            return WebApiResponse.success(new ArrayList<>());
        }
        return WebApiResponse.success(consumerDetailVoList);
    }


    /**
     * 抢客户、公共资源池客户信息查询
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/listPublicConsumer", method = RequestMethod.GET)
    public WebApiResponse<List<ConsumerDetailVo>> listConsumer(HttpServletRequest request) {

        Integer pageSize = null;
        Integer pageNum = null;
        Map<String, Object> params = new HashMap<>();


        String source = request.getParameter("source");
        if (!StringUtil.isEmptyString(source)) {
            params.put("source", source);
        }


        String type = request.getParameter("type");
        if (!StringUtil.isEmptyString(type)) {
            params.put("type", type);
        }

        //客户国籍
        String country = request.getParameter("country");
        if (!StringUtil.isEmptyString(country)) {
            params.put("country", country);
        }


        //客户等级
        String level = request.getParameter("level");
        if (!StringUtil.isEmptyString(level)) {
            params.put("level", level);
        }

        //客户姓名
        String name = request.getParameter("name");
        if (!StringUtil.isEmptyString(name)) {
            params.put("name", name);
        }


        //客户邮箱
        String email = request.getParameter("email");
        if (!StringUtil.isEmptyString(email)) {
            params.put("email", email);
        }

        //客户意向
        String intention = request.getParameter("intention");
        if (!StringUtil.isEmptyString(intention)) {
            params.put("intention", intention);
        }


        String pageSizeString = request.getParameter("pageSize");
        if (StringUtil.isNotEmptyString(pageSizeString)) {
            pageSize = Integer.valueOf(pageSizeString);
        }
        String pageNumString = request.getParameter("pageNum");
        if (StringUtil.isNotEmptyString(pageNumString)) {
            pageNum = Integer.valueOf(pageNumString);
        }
        if (pageSize == null) {
            pageSize = 30;
        }
        if (pageNum == null) {
            pageNum = 1;
        }
        Integer start = (pageNum - 1) * pageSize;
        Integer size = pageSize;

        List<ConsumerDetailVo> consumerDetailVoList = consumerService.listConsumerDetailVoByParams(params, start, size);
        if (CollectionUtils.isEmpty(consumerDetailVoList)) {
            return WebApiResponse.success(new ArrayList<>());
        }
        return WebApiResponse.success(consumerDetailVoList);
    }


    /**
     * 客户跟进记录
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/follow-record", method = RequestMethod.GET)
    public WebApiResponse<List<FollowRecordVo>> listFollowRecords(@RequestParam("consumerId") Long consumerId, @RequestParam(name = "userId", required = false) Long userId) {
        UserInfo userInfo = UserStatus.getUserInfo();
        User user = userService.getUserById(userInfo.getUserId());

        List<ConsumerFollowRecord> consumerFollowRecordList = null;

        if (userId == null) {//未指定用户时查询所有
            if ("admin".equals(userInfo.getRoleName())) {
                consumerFollowRecordList = consumerFollowRecordService.listConsumerFollowRecord(consumerId, null);
            } else if (1 == user.getIsLeader()) {
                consumerFollowRecordList = consumerFollowRecordService.listGroupConsumerFollowRecord(consumerId, userInfo.getUserId(), user.getGroupId());
            } else {
                consumerFollowRecordList = consumerFollowRecordService.listConsumerFollowRecord(consumerId, userInfo.getUserId());
            }
        } else {
            consumerFollowRecordList = consumerFollowRecordService.listConsumerFollowRecord(consumerId, userId);
        }

        if (!CollectionUtils.isEmpty(consumerFollowRecordList)) {
            List<FollowRecordVo> followRecordVoList = new ArrayList<>();
            for (ConsumerFollowRecord consumerFollowRecord : consumerFollowRecordList) {
                FollowRecordVo followRecordVo = new FollowRecordVo();
                followRecordVo.setFollowRecordId(consumerFollowRecord.getId());
                followRecordVo.setConsumerId(consumerFollowRecord.getConsumerId());
                followRecordVo.setFollowUser(consumerFollowRecord.getUserNo());
                followRecordVo.setContent(consumerFollowRecord.getContent());
                followRecordVoList.add(followRecordVo);
            }
            return WebApiResponse.success(followRecordVoList);
        }

        return WebApiResponse.success(null);
    }


    /**
     * 跟进人下拉列表
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/follower-select", method = RequestMethod.GET)
    public WebApiResponse<List<FuzzyUserVo>> listFollowerSelect(@RequestParam("consumerId") Long consumerId) {
        UserInfo userInfo = UserStatus.getUserInfo();
        User user = userService.getUserById(userInfo.getUserId());

        if ("admin".equals(userInfo.getRoleName())) {//管理员返回所有人员
            List<FuzzyUserVo> fuzzyUserVoList = userService.listConsumerFollowers(consumerId, null);
            return WebApiResponse.success(fuzzyUserVoList);
        } else if (1 == user.getIsLeader()) {
            Group group = groupService.getGroupById(user.getGroupId());
            List<FuzzyUserVo> fuzzyUserVoList = userService.listConsumerFollowers(consumerId, group.getId());
            return WebApiResponse.success(fuzzyUserVoList);
        } else {
            FuzzyUserVo fuzzyUserVo = new FuzzyUserVo();
            fuzzyUserVo.setUserId(user.getId());
            fuzzyUserVo.setUserNo(user.getUserNo());
            fuzzyUserVo.setName(user.getName());
            List<FuzzyUserVo> fuzzyUserVoList = new ArrayList<>();
            fuzzyUserVoList.add(fuzzyUserVo);
            return WebApiResponse.success(fuzzyUserVoList);
        }
    }


    /**
     * 保存跟进记录
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/saveFollowRecord", method = RequestMethod.POST)
    public WebApiResponse<String> saveFollowRecord(@RequestParam("consumerId") Long consumerId, @RequestParam("content") String content, @RequestParam("day") String day) {
        UserInfo userInfo = UserStatus.getUserInfo();
        User user = userService.getUserById(userInfo.getUserId());

        if (consumerId == null || StringUtil.isEmptyString(content) || StringUtil.isEmptyString(day)) {
            return WebApiResponse.error("参数异常！");
        }

        Consumer consumer = consumerService.getConsumerById(consumerId);
        if (user == null || consumer == null) {
            return WebApiResponse.error("参数异常！");
        }

        UserConsumerRelation userConsumerRelation = userConsumerRelationService.getRelationByConsumerIsDel(consumerId, userInfo.getUserId(), FellowUpRulesEnum.Normal.getCode());

        if (userConsumerRelation == null) {
            return WebApiResponse.error("您未跟进该客户，无权限记录！");
        }
        ConsumerFollowRecord consumerFollowRecord = new ConsumerFollowRecord();
        consumerFollowRecord.setConsumerNo(consumer.getConsumerNo());
        consumerFollowRecord.setConsumerId(consumerId);
        consumerFollowRecord.setContent(content);
        consumerFollowRecord.setFollowTime(DateUtil.formatToStr(new Date()));
        consumerFollowRecord.setCreatedTime(new Date());
        consumerFollowRecord.setUserId(user.getId());
        consumerFollowRecord.setUserNo(user.getUserNo());
        consumerFollowRecord.setUserName(user.getName());
        consumerFollowRecord.setNextFollowTime(DateUtil.getSomeDay(new Date(), Integer.valueOf(day), "yyyyMMdd"));
        consumerFollowRecord.setUpdatedTime(new Date());
        consumerFollowRecordService.insert(consumerFollowRecord);

        userConsumerRelation.setFollowTime(new Date());
        userConsumerRelation.setNextFollowTime(DateUtil.getSomeDayDateToTime(new Date(), Integer.valueOf(day)));
        userConsumerRelationService.updateUserConsumerRelation(userConsumerRelation);
        return WebApiResponse.success("success");
    }


    /**
     * 抛弃客户
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/discard-consumer", method = RequestMethod.GET)
    public WebApiResponse<String> discardConsumer(@RequestParam("consumerId") Long consumerId) {
        UserInfo userInfo = UserStatus.getUserInfo();
        User user = userService.getUserById(userInfo.getUserId());
        if (consumerId == null) {
            return WebApiResponse.error("参数异常！");
        }

        Consumer consumer = consumerService.getConsumerById(consumerId);
        if (user == null || consumer == null) {
            return WebApiResponse.error("参数异常！");
        }

        UserConsumerRelation userConsumerRelation = userConsumerRelationService.getRelationByConsumerIsDel(consumerId, userInfo.getUserId(), FellowUpRulesEnum.Normal.getCode());

        if (userConsumerRelation == null) {
            return WebApiResponse.error("您未跟进该客户，不能抛弃！");
        }
        //更新客户关系表，抛弃状态
        userConsumerRelation.setIsDel(FellowUpRulesEnum.Discard.getCode());
        userConsumerRelation.setFollowTime(new Date());
        userConsumerRelation.setUpdatedTime(new Date());
        userConsumerRelationService.updateUserConsumerRelation(userConsumerRelation);

        //更新客户scope
        List<UserConsumerRelation> userConsumerRelationList = userConsumerRelationService.listAllValidRelationByConsumerId(consumerId);
        if (CollectionUtils.isEmpty(userConsumerRelationList)) {
            //无人跟进
            consumer.setScope(ConsumerScopeEnum.Public.getCodeName());
        } else if (userConsumerRelationList.size() <= 1 && ConsumerScopeEnum.Protected.getCodeName().equals(consumer.getScope())) {
            //非公共资源池多人跟进变成单人跟进
            consumer.setScope(ConsumerScopeEnum.Private.getCodeName());
        }
        consumerService.updateConsumer(consumer);
        return WebApiResponse.success("success");
    }


    /**
     * 从公共资源池抢客户
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/grab-consumer", method = RequestMethod.GET)
    public WebApiResponse<String> grabConsumer(@RequestParam("consumerId") Long consumerId) {
        UserInfo userInfo = UserStatus.getUserInfo();
        User user = userService.getUserById(userInfo.getUserId());
        if (consumerId == null) {
            return WebApiResponse.error("参数异常！");
        }

        Consumer consumer = consumerService.getConsumerById(consumerId);
        if (user == null || consumer == null) {
            return WebApiResponse.error("参数异常！");
        }

        if (!ConsumerScopeEnum.Public.getCodeName().equals(consumer.getScope())) {
            return WebApiResponse.error("该客户非公共资源池客户！");
        }

        //1:抛弃规则判断
        if (!sysDictService.checkDiscardingRules(userInfo, consumer.getId())) {
            return error("根据抛弃规则，暂时无法跟进！");
        }

        //2:抛弃规则判断
        if (!sysDictService.checkReclaimRules(userInfo, consumer.getId())) {
            return error("根据回收规则，暂时无法跟进！");
        }

        boolean result = relateUserAndConsumer(consumer, user);
        if (!result) {
            return error("客户跟进中！");
        }
        return WebApiResponse.success("success");
    }


    /**
     * 资源池客户分配
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/assign-consumer", method = RequestMethod.GET)
    public WebApiResponse<String> assignConsumer(@RequestParam("consumerIds") String consumerIds, @RequestParam("userIds") String userIds) {
        UserInfo userInfo = UserStatus.getUserInfo();
        User user = userService.getUserById(userInfo.getUserId());
        if (StringUtil.isEmptyString(consumerIds) || StringUtil.isEmptyString(userIds)) {
            return WebApiResponse.error("参数异常！");
        }

        if ("admin".equals(userInfo.getRoleName())) {
            return WebApiResponse.error("管理员不允许操作！");
        } else if (1 != user.getIsLeader()) {
            return WebApiResponse.error("没有权限！");
        }

        String[] userIdArray = userIds.split(";");
        for (String userId : userIdArray) {
            User member = userService.getUserById(Long.valueOf(userId));
            if (member == null) {
                continue;
            }
            String[] consumerIdArray = consumerIds.split(";");
            for (String consumerId : consumerIdArray) {
                Consumer consumer = consumerService.getConsumerById(Long.valueOf(consumerId));
                if (consumer == null) {
                    logger.error("客户不存在，consumerId=" + consumerId);
                    continue;
                }
                relateUserAndConsumer(consumer, member);
            }
        }
        return WebApiResponse.success("success");
    }

    /**
     * 关联用户及客户
     *
     * @param consumer
     * @param user
     * @return
     */
    private boolean relateUserAndConsumer(Consumer consumer, User user) {
        UserConsumerRelation userConsumerRelation = userConsumerRelationService.getRelationByConsumerIsDel(consumer.getId(), user.getId(), FellowUpRulesEnum.Normal.getCode());
        if (userConsumerRelation != null) {
            return false;
        }

        //过期关联
        UserConsumerRelation invalidRelation = userConsumerRelationService.getRelationByConsumerIsDel(consumer.getId(), user.getId(), null);
        if (invalidRelation != null) {
            invalidRelation.setIsDel(0);
            invalidRelation.setConsumerId(consumer.getId());
            invalidRelation.setConsumerNo(consumer.getConsumerNo());
            invalidRelation.setUserId(user.getId());
            invalidRelation.setUserNo(user.getUserNo());
            invalidRelation.setIsDel(0);
            invalidRelation.setHasOrder(0);
            invalidRelation.setDealOrder(0);
            invalidRelation.setFollowTime(new Date());
            invalidRelation.setNextFollowTime(null);
            invalidRelation.setUpdatedTime(new Date());
            userConsumerRelationService.updateUserConsumerRelation(invalidRelation);
        } else {
            //创建关联关系
            UserConsumerRelation newUserConsumerRelation = new UserConsumerRelation();
            newUserConsumerRelation.setConsumerId(consumer.getId());
            newUserConsumerRelation.setConsumerNo(consumer.getConsumerNo());
            newUserConsumerRelation.setUserId(user.getId());
            newUserConsumerRelation.setUserNo(user.getUserNo());
            newUserConsumerRelation.setIsDel(0);
            newUserConsumerRelation.setHasOrder(0);
            newUserConsumerRelation.setDealOrder(0);
            newUserConsumerRelation.setFollowTime(new Date());
            newUserConsumerRelation.setCreatedTime(new Date());
            newUserConsumerRelation.setUpdatedTime(new Date());
            userConsumerRelationService.addUserConsumerRelation(newUserConsumerRelation);
        }
        return true;
    }


    /**
     * 待跟进客户
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/listFollowRemindConsumer", method = RequestMethod.GET)
    public WebApiResponse<List<SalerConsumerDetailVo>> listFollowRemindConsumer() {
        UserInfo userInfo = UserStatus.getUserInfo();

        Integer pageSize = null;
        Integer pageNum = null;
        Map<String, Object> params = new HashMap<>();

        //员工ID
        params.put("userId", userInfo.getUserId());

        String dateString = DateUtil.formatToStr(new Date());
        Date date = DateUtil.getSomeDayDateToTime(DateUtil.formatFromString(dateString, "yyyyMMdd"), 1);
        params.put("nextContactTimeEnd", DateUtil.formatToStrTimeV1(date));


        if (pageSize == null) {
            pageSize = 30;
        }
        if (pageNum == null) {
            pageNum = 1;
        }


        Integer start = (pageNum - 1) * pageSize;
        Integer size = pageSize;

        List<SalerConsumerDetailVo> consumerDetailVoList = consumerService.listSalerConsumerDetailVoByParams(params, start, size, userInfo.getUserNo());
        if (CollectionUtils.isEmpty(consumerDetailVoList)) {
            return WebApiResponse.success(new ArrayList<>());
        }
        return WebApiResponse.success(consumerDetailVoList);
    }


    /**
     * 待跟进客户总数
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/countFollowRemindConsumer", method = RequestMethod.GET)
    public WebApiResponse<Long> countFollowRemindConsumer() {
        UserInfo userInfo = UserStatus.getUserInfo();

        Map<String, Object> params = new HashMap<>();

        //员工ID
        params.put("userId", userInfo.getUserId());
        String dateString = DateUtil.formatToStr(new Date());
        Date date = DateUtil.getSomeDayDateToTime(DateUtil.formatFromString(dateString, "yyyyMMdd"), 1);
        params.put("nextContactTimeEnd", DateUtil.formatToStrTimeV1(date));

        Long count = consumerService.findSalerConsumerByParamCount(params);
        return WebApiResponse.success(count);
    }


}
