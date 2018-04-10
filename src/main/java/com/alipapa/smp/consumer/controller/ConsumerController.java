package com.alipapa.smp.consumer.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alipapa.smp.common.enums.CategoryCode;
import com.alipapa.smp.common.enums.ConsumerScope;
import com.alipapa.smp.common.request.UserInfo;
import com.alipapa.smp.common.request.UserStatus;
import com.alipapa.smp.consumer.pojo.Consumer;
import com.alipapa.smp.consumer.pojo.SysDict;
import com.alipapa.smp.consumer.pojo.UserConsumerRelation;
import com.alipapa.smp.consumer.service.ConsumerService;
import com.alipapa.smp.consumer.service.SysDictService;
import com.alipapa.smp.consumer.service.UserConsumerRelationService;
import com.alipapa.smp.consumer.vo.ConsumerDetailVo;
import com.alipapa.smp.consumer.vo.ConsumerVo;
import com.alipapa.smp.consumer.vo.SysDictVo;
import com.alipapa.smp.user.pojo.User;
import com.alipapa.smp.user.service.UserService;
import com.alipapa.smp.utils.DateUtil;
import com.alipapa.smp.utils.StringUtil;
import com.alipapa.smp.utils.WebApiResponse;
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

        List<SysDictVo> sysDictVoList = null;
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
            if (consumer.getScope() == ConsumerScope.Public.getCodeName()) {
                response.setData("2");
                response.setError("客户已在资源池中，无须重复创建，请去抢本客户");
            } else {
                response.setData("3");
                response.setError("若已存在，不在资源池，有人跟进");
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
        if (consumer != null && (consumer.getScope() == ConsumerScope.Private.getCodeName() || consumer.getScope() == ConsumerScope.Protected.getCodeName())) {
            //创建关联关系
            UserConsumerRelation userConsumerRelation = new UserConsumerRelation();
            userConsumerRelation.setConsumerId(consumer.getId());
            userConsumerRelation.setConsumerNo(consumer.getConsumerNo());
            userConsumerRelation.setUserId(userInfo.getUserId());
            userConsumerRelation.setUserNo(userInfo.getUserNo());
            userConsumerRelation.setIsDel(0);
            userConsumerRelation.setHasOrder(0);
            userConsumerRelation.setDealOrder(0);
            userConsumerRelation.setCreatedTime(new Date());
            userConsumerRelation.setUpdatedTime(new Date());
            userConsumerRelationService.addUserConsumerRelation(userConsumerRelation);
            return WebApiResponse.success("success");
        }
        return null;
    }

    /**
     * 新建客户
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/addConsumer", method = RequestMethod.POST)
    public WebApiResponse<String> addConsumer(@RequestBody String jsonStr) {
        UserInfo userInfo = UserStatus.getUserInfo();

        if (jsonStr == null) {
            logger.error("提交的json格式数据不可以为空!");
            return error("输入的信息不可以为空");
        }
        try {
            JSONObject json = JSON.parseObject(jsonStr);
            if (json == null) {
                logger.error("客户提交的数据解析失败: " + jsonStr);
                return error("客户数据解析失败");
            }

            //不能为空
            String name = json.getString("name");
            String country = json.getString("country");
            String mainBusiness = json.getString("mainBusiness");
            String source = json.getString("source");
            String type = json.getString("type");
            String email = json.getString("email");

            String intention = json.getString("intention");

            //可为空
            String facebook = json.getString("facebook");
            String whatsapp = json.getString("whatsapp");
            String linkedin = json.getString("linkedin");
            String wechat = json.getString("wechat");
            String qq = json.getString("qq");
            String contacts = json.getString("contacts");
            String companyAddress = json.getString("companyAddress");
            String companyWebsite = json.getString("companyWebsite");

            //收货地址
            String consignee = json.getString("consignee");
            String telMobile = json.getString("telMobile");
            String postalCode = json.getString("postalCode");
            String receivingAddress = json.getString("receivingAddress");


            if (StringUtil.isEmptyString(name) || StringUtil.isEmptyString(country) || StringUtil.isEmptyString(mainBusiness) || StringUtil.isEmptyString(source)
                    || StringUtil.isEmptyString(type) || StringUtil.isEmptyString(email)) {
                return error("缺少必填参数");
            }

            //判断客户是否已存在
            Consumer preConsumer = consumerService.getConsumerByNameAndEmail(name, email);
            if (preConsumer != null) {
                if (preConsumer.getScope() == ConsumerScope.Public.getCodeName()) {
                    return WebApiResponse.error("客户已在资源池中，无须重复创建，请去抢本客户");
                } else {
                    return WebApiResponse.error("若已存在，不在资源池，有人跟进");
                }
            }

            Consumer consumer = new Consumer();
            //YYYYMMDD+随机四位数
            Long consumerId = consumerService.getLatestConsumerId();
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
            consumer.setScope(ConsumerScope.Private.getCodeName());
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
                userConsumerRelation.setCreatedTime(new Date());
                userConsumerRelation.setUpdatedTime(new Date());
                userConsumerRelationService.addUserConsumerRelation(userConsumerRelation);
                return WebApiResponse.success("success");
            }
        } catch (Exception ex) {
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
        return WebApiResponse.success(consumerVo);
    }


    /**
     * 更新客户信息
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/updateConsumer", method = RequestMethod.POST)
    public WebApiResponse<String> updateConsumer(@RequestBody String jsonStr) {
        //UserInfo userInfo = UserStatus.getUserInfo();

        if (jsonStr == null) {
            logger.error("提交的json格式数据不可以为空!");
            return error("输入的信息不可以为空");
        }
        try {
            JSONObject json = JSON.parseObject(jsonStr);
            if (json == null) {
                logger.error("客户提交的数据解析失败: " + jsonStr);
                return error("客户数据解析失败");
            }

            //不能为空
            Long id = json.getLong("consumerId");
            String consumerNo = json.getString("consumerNo");

            if (StringUtil.isEmptyString(consumerNo) || id == null) {
                return error("缺少必填参数");
            }

            Consumer consumer = consumerService.getConsumerById(id);
            if (consumer == null) {
                return error("客户不存在");
            }

            String name = json.getString("name");
            String country = json.getString("country");
            String mainBusiness = json.getString("mainBusiness");
            String source = json.getString("source");
            String type = json.getString("type");
            String email = json.getString("email");

            //可为空
            String facebook = json.getString("facebook");
            String whatsapp = json.getString("whatsapp");
            String linkedin = json.getString("linkedin");
            String wechat = json.getString("wechat");
            String qq = json.getString("qq");
            String contacts = json.getString("contacts");
            String companyAddress = json.getString("companyAddress");
            String companyWebsite = json.getString("companyWebsite");

            //收货地址
            String consignee = json.getString("consignee");
            String telMobile = json.getString("telMobile");
            String postalCode = json.getString("postalCode");
            String receivingAddress = json.getString("receivingAddress");


            if (StringUtil.isEmptyString(name) || StringUtil.isEmptyString(country) || StringUtil.isEmptyString(mainBusiness) || StringUtil.isEmptyString(source)
                    || StringUtil.isEmptyString(type) || StringUtil.isEmptyString(email)) {
                return error("缺少必填参数");
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
     * 客户信息查询，注意用户权限
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/listConsumer", method = RequestMethod.GET)
    public WebApiResponse<List<ConsumerDetailVo>> listConsumer(@RequestBody String jsonStr) {

        Integer pageSize = null;
        Integer pageNum = null;
        Map<String, Object> params = new HashMap<>();


        if (jsonStr != null) {
            JSONObject json = JSON.parseObject(jsonStr);
            if (json == null) {
                logger.error("客户提交的数据解析失败: " + jsonStr);
                return error("客户数据解析失败");
            }

            pageSize = json.getInteger("pageSize");
            pageNum = json.getInteger("pageNum");

            String source = json.getString("source");
            if (!StringUtil.isEmptyString(source)) {
                params.put("source", source);
            }

            String type = json.getString("type");
            if (!StringUtil.isEmptyString(type)) {
                params.put("type", type);
            }

            String country = json.getString("country");
            if (!StringUtil.isEmptyString(country)) {
                params.put("country", country);
            }

            String level = json.getString("level");
            if (!StringUtil.isEmptyString(level)) {
                params.put("level", level);
            }

            String name = json.getString("name");
            if (!StringUtil.isEmptyString(name)) {
                params.put("name", name);
            }

            String consumerNo = json.getString("consumerNo");
            if (!StringUtil.isEmptyString(consumerNo)) {
                params.put("consumerNo", consumerNo);
            }


            String hasOrder = json.getString("hasOrder");
            if (!StringUtil.isEmptyString(hasOrder)) {
                params.put("hasOrder", hasOrder);
            }

            Long groupId = json.getLong("groupId");
            if (groupId != null) {
                params.put("groupId", groupId);
            }

            Long salerId = json.getLong("salerId");
            if (salerId != null) {
                params.put("salerId", salerId);
            }

            String startTime = json.getString("startTime");
            if (!StringUtil.isEmptyString(startTime)) {
                params.put("startTime", DateUtil.formatFromString(startTime, DateUtil.FormatString));
            }

            String endTime = json.getString("endTime");
            if (!StringUtil.isEmptyString(endTime)) {
                params.put("endTime", DateUtil.formatFromString(endTime, DateUtil.FormatString));
            }
        }

        if (pageSize == null) {
            pageSize = 1;
        }
        if (pageNum == null) {
            pageNum = 30;
        }

        Integer start = (pageNum - 1) * pageSize;
        Integer size = pageSize;

        List<ConsumerDetailVo> consumerDetailVoList = consumerService.listConsumerDetailVoByParams(params, start, size);
        if (CollectionUtils.isEmpty(consumerDetailVoList)) {
            return WebApiResponse.success(new ArrayList<>());
        }
        return WebApiResponse.success(consumerDetailVoList);
    }

}
