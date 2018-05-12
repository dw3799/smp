package com.alipapa.smp.consumer.controller;

import com.alipapa.smp.common.enums.CategoryCode;
import com.alipapa.smp.common.enums.DisCardRulesEnum;
import com.alipapa.smp.common.enums.ReclaimRulesEnum;
import com.alipapa.smp.common.enums.RoleEnum;
import com.alipapa.smp.common.request.UserInfo;
import com.alipapa.smp.common.request.UserStatus;
import com.alipapa.smp.consumer.pojo.SysDict;
import com.alipapa.smp.consumer.service.SysDictService;
import com.alipapa.smp.consumer.vo.RuleVo;
import com.alipapa.smp.user.controller.GroupController;
import com.alipapa.smp.utils.StringUtil;
import com.alipapa.smp.utils.WebApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 规则配置接口
 *
 * @author liuwei
 * @version 1.0
 * 2018-04-18
 */

@RestController
@CrossOrigin
@RequestMapping("/api/rule")
public class RuleConfigController {
    private static Logger logger = LoggerFactory.getLogger(RuleConfigController.class);

    @Autowired
    private SysDictService sysDictService;

    /**
     * 规则列表
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/list-rules", method = RequestMethod.GET)
    public WebApiResponse<List<RuleVo>> listRules() {
        UserInfo userInfo = UserStatus.getUserInfo();

        if (!RoleEnum.admin.getCodeName().equals(userInfo.getRoleName())) {
            return WebApiResponse.error("没有权限！");
        }

        List<RuleVo> ruleVoList = new ArrayList<>();
        int length = 0;
        List<SysDict> reclaimRulesList = sysDictService.listAllRuleConfig(CategoryCode.reclaim_rules.getCodeName());
        if (!CollectionUtils.isEmpty(reclaimRulesList)) {
            for (SysDict sysDict : reclaimRulesList) {
                RuleVo ruleVo = new RuleVo();
                ruleVo.setId(sysDict.getId());
                ruleVo.setDays(sysDict.getDictValue());
                ruleVo.setRuleCode(sysDict.getCategoryCode());
                ReclaimRulesEnum reclaimRulesEnum = ReclaimRulesEnum.getValueByCodeName(sysDict.getDictText());
                if (reclaimRulesEnum != null) {
                    ruleVo.setRuleDetail(reclaimRulesEnum.getDec());
                }
                ruleVo.setRuleType(sysDict.getDictText());
                ruleVo.setSort(sysDict.getSort());
                ruleVo.setStatus(sysDict.getStatus());
                ruleVoList.add(ruleVo);
            }
            length = reclaimRulesList.size();
        }

        List<SysDict> discardRulesList = sysDictService.listAllRuleConfig(CategoryCode.discarding_rules.getCodeName());
        if (!CollectionUtils.isEmpty(reclaimRulesList)) {
            for (SysDict sysDict : discardRulesList) {
                RuleVo ruleVo = new RuleVo();
                ruleVo.setId(sysDict.getId());
                ruleVo.setDays(sysDict.getDictValue());
                ruleVo.setRuleCode(sysDict.getCategoryCode());
                DisCardRulesEnum disCardRulesEnum = DisCardRulesEnum.getValueByCodeName(sysDict.getDictText());
                if (disCardRulesEnum != null) {
                    ruleVo.setRuleDetail(disCardRulesEnum.getDec());
                }
                ruleVo.setRuleType(sysDict.getDictText());
                ruleVo.setSort(length + sysDict.getSort());
                ruleVo.setStatus(sysDict.getStatus());
                ruleVoList.add(ruleVo);
            }
        }

        return WebApiResponse.success(ruleVoList);
    }


    /**
     * 更新规则
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/update-rule", method = RequestMethod.POST)
    public WebApiResponse<String> updateRules(@RequestParam("ruleId") Long ruleId, @RequestParam(name = "status", required = false) Integer status, @RequestParam(name = "days", required = false) String days) {
        UserInfo userInfo = UserStatus.getUserInfo();

        if (!RoleEnum.admin.getCodeName().equals(userInfo.getRoleName())) {
            return WebApiResponse.error("没有权限！");
        }

        if (ruleId == null) {
            return WebApiResponse.error("参数异常！");
        }

        SysDict sysDict = sysDictService.getSysDictById(ruleId);

        if (sysDict == null) {
            return WebApiResponse.error("规则不存在！");
        }

        if (!CategoryCode.reclaim_rules.getCodeName().equals(sysDict.getCategoryCode()) && !CategoryCode.discarding_rules.getCodeName().equals(sysDict.getCategoryCode())) {
            return WebApiResponse.error("规则不存在！");
        }


        if (status != null) {
            if (status == 1) {
                sysDict.setStatus(status);
            } else {
                sysDict.setStatus(0);
            }
        }

        if (StringUtil.isNotEmptyString(days)) {
            sysDict.setDictValue(days);
        }

        sysDictService.updateSysDict(sysDict);
        return WebApiResponse.success("success");
    }

}
