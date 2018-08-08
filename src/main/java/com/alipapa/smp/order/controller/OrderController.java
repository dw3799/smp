package com.alipapa.smp.order.controller;


import com.alipapa.smp.common.enums.CategoryCode;
import com.alipapa.smp.common.enums.OrderCategoryCode;
import com.alipapa.smp.consumer.controller.ConsumerController;
import com.alipapa.smp.consumer.pojo.SysDict;
import com.alipapa.smp.consumer.service.SysDictService;
import com.alipapa.smp.consumer.vo.SysDictVo;
import com.alipapa.smp.utils.WebApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 订单管理接口
 *
 * @author liuwei
 * @version 2.0
 * 2018-08-08
 */

@RestController
@CrossOrigin
@RequestMapping("/api/order")
public class OrderController {
    private static Logger logger = LoggerFactory.getLogger(ConsumerController.class);

    @Autowired
    private SysDictService sysDictService;

    /**
     * 订单相关下拉列表
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/orderSelect", method = RequestMethod.GET)
    public WebApiResponse<List<SysDictVo>> orderSelect(@RequestParam("categoryCode") Integer categoryCode) {
        if (categoryCode == null || CategoryCode.valueOf(categoryCode) == null) {
            return WebApiResponse.error("参数有误！");
        }
        List<SysDictVo> sysDictVoList = new ArrayList<>();

        List<SysDict> sysDictList = sysDictService.listSysDictLikeText(OrderCategoryCode.valueOf(categoryCode).getCodeName(), null);
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
