package com.alipapa.smp.consumer.controller;

import com.alipapa.smp.common.enums.CategoryCode;
import com.alipapa.smp.consumer.pojo.SysDict;
import com.alipapa.smp.consumer.service.SysDictService;
import com.alipapa.smp.consumer.vo.SysDictVo;
import com.alipapa.smp.utils.WebApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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




}
