package com.alipapa.smp.consumer.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alipapa.smp.common.enums.CategoryCode;
import com.alipapa.smp.common.request.UserInfo;
import com.alipapa.smp.common.request.UserStatus;
import com.alipapa.smp.consumer.pojo.Consumer;
import com.alipapa.smp.consumer.pojo.SysDict;
import com.alipapa.smp.consumer.service.ConsumerService;
import com.alipapa.smp.consumer.service.SysDictService;
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

import java.util.Date;
import java.util.List;

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
     * 新建用户
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
                return error("用户数据解析失败");
            }

            //不能为空
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

            if (!CollectionUtils.isEmpty(consumerService.listConsumerByNameAndEmail(name, email))) {
                return WebApiResponse.error("客户已存在");
            }

            Consumer consumer = new Consumer();
            //YYYYMMDD+随机四位数
            Long consumerId = consumerService.getLatestConsumerId();

            consumer.setConsumerNo(DateUtil.formatToStr(new Date()) + String.format("%04d", consumerId + 1));
            consumer.setCreatedTime(new Date());
            consumer.setUpdatedTime(new Date());
            consumer.setBelongUser(userInfo.getUserNo());
            User user = userService.getUserById(userInfo.getUserId());
            consumer.setBelongGroup(user.getGroupNo());
            consumer.setCreateUser(userInfo.getUserNo());


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

            boolean result = consumerService.addConsumer(consumer);
            if (result) {
                return WebApiResponse.success("success");
            }
        } catch (Exception ex) {
            return error("添加客户异常");
        }
        return WebApiResponse.error("添加客户失败");
    }

}
