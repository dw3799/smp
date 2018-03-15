package com.alipapa.smp.consumer.controller;

import com.alipapa.smp.common.enums.CategoryCode;
import com.alipapa.smp.consumer.pojo.Consumer;
import com.alipapa.smp.consumer.pojo.SysDict;
import com.alipapa.smp.consumer.service.ConsumerService;
import com.alipapa.smp.consumer.service.SysDictService;
import com.alipapa.smp.consumer.vo.ConsumerVo;
import com.alipapa.smp.consumer.vo.SysDictVo;
import com.alipapa.smp.utils.DateUtil;
import com.alipapa.smp.utils.WebApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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


}
