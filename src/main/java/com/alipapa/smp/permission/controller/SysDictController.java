package com.alipapa.smp.permission.controller;

import com.alipapa.smp.common.enums.*;
import com.alipapa.smp.common.request.UserInfo;
import com.alipapa.smp.common.request.UserStatus;
import com.alipapa.smp.consumer.pojo.SysDict;
import com.alipapa.smp.consumer.service.SysDictService;
import com.alipapa.smp.consumer.vo.RuleVo;
import com.alipapa.smp.consumer.vo.SysDictVo;
import com.alipapa.smp.permission.vo.SysDictModelVo;
import com.alipapa.smp.utils.StringUtil;
import com.alipapa.smp.utils.WebApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 字典值管理接口
 *
 * @author liuwei
 * @version 1.0
 * 2018-09-05
 */

@RestController
@CrossOrigin
@RequestMapping("/api/sysDict")
public class SysDictController {
    private static Logger logger = LoggerFactory.getLogger(SysDictController.class);

    @Autowired
    private SysDictService sysDictService;

    /**
     * 字典列表
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public WebApiResponse<List<SysDictModelVo>> listSysDictVo(@RequestParam("categoryCode") Integer categoryCode) {
        if (categoryCode == null || SysDictManageEnum.valueOf(categoryCode) == null) {
            return WebApiResponse.error("参数有误！");
        }
        try {
            SysDictManageEnum sysDictManageEnum = SysDictManageEnum.valueOf(categoryCode);
            List<SysDictModelVo> sysDictVoList = new ArrayList<>();

            List<SysDict> sysDictList = sysDictService.listSysDictLikeText(SysDictManageEnum.valueOf(categoryCode).getCodeName(), null);
            if (!CollectionUtils.isEmpty(sysDictList)) {
                for (SysDict sysDict : sysDictList) {
                    SysDictModelVo sysDictVo = new SysDictModelVo();
                    sysDictVo.setId(sysDict.getId());
                    sysDictVo.setCategoryCode(sysDict.getCategoryCode());
                    sysDictVo.setDictText(sysDict.getDictText());
                    sysDictVo.setCategoryTitle(sysDictManageEnum.getDec());
                    sysDictVo.setDictValue(sysDict.getDictValue());
                    sysDictVoList.add(sysDictVo);
                }
            }
            return WebApiResponse.success(sysDictVoList);
        } catch (Exception ex) {
            logger.error("获取字典列表异常", ex);
            return WebApiResponse.error("获取字典列表异常！");
        }
    }


    /**
     * 删除字典
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public WebApiResponse<String> delete(@RequestParam("id") Long id) {
        if (id == null) {
            return WebApiResponse.error("参数有误！");
        }
        try {

            SysDict sysDict = sysDictService.getSysDictById(id);
            if (sysDict == null) {
                return WebApiResponse.error("字典不存在！");
            }
            sysDictService.delete(sysDict);
            return WebApiResponse.success("success");
        } catch (Exception ex) {
            logger.error("删除字典异常", ex);
            return WebApiResponse.error("删除字典异常！");
        }
    }


    /**
     * 保存字典，新增或更新
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public WebApiResponse<String> save(@RequestParam(value = "id", required = false) Long id,
                                       @RequestParam(value = "categoryCode") String categoryCode,
                                       @RequestParam(value = "dictText") String dictText,
                                       @RequestParam(value = "dictValue") String dictValue) {
        if (StringUtil.isEmptyString(categoryCode) || StringUtil.isEmptyString(dictText) || StringUtil.isEmptyString(dictValue)) {
            return WebApiResponse.error("参数有误！");
        }


        try {
            SysDictManageEnum sysDictManageEnum = SysDictManageEnum.valueOf(categoryCode);

            if (sysDictManageEnum == null) {
                return WebApiResponse.error("字典类别不存在！");

            }

            SysDict sysDict = null;
            if (id != null) {
                sysDict = sysDictService.getSysDictById(id);
                if (sysDict == null) {
                    return WebApiResponse.error("字典不存在！");
                }
                sysDict.setUpdatedTime(new Date());
                sysDict.setSort(0);
                sysDict.setReadonly(1);
                sysDict.setDictText(dictText);
                sysDict.setCategoryCode(sysDictManageEnum.getCodeName());
                sysDict.setStatus(1);
                sysDict.setDictValue(dictValue);
                sysDictService.updateSysDict(sysDict);
            } else {
                sysDict = new SysDict();
                sysDict.setUpdatedTime(new Date());
                sysDict.setSort(0);
                sysDict.setReadonly(1);
                sysDict.setDictText(dictText);
                sysDict.setCategoryCode(sysDictManageEnum.getCodeName());
                sysDict.setStatus(1);
                sysDict.setDictValue(dictValue);
                sysDict.setCreatedTime(new Date());
                sysDictService.insertSysDict(sysDict);
            }
            return WebApiResponse.success("success");
        } catch (Exception ex) {
            logger.error("保存字典异常", ex);
            return WebApiResponse.error("保存字典异常！");
        }
    }


}
