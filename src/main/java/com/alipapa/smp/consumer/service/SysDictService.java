package com.alipapa.smp.consumer.service;

import com.alipapa.smp.common.enums.CategoryCode;
import com.alipapa.smp.common.enums.FellowUpRulesEnum;
import com.alipapa.smp.common.request.UserInfo;
import com.alipapa.smp.consumer.mapper.SysDictMapper;
import com.alipapa.smp.consumer.mapper.UserConsumerRelationMapper;
import com.alipapa.smp.consumer.pojo.SysDict;
import com.alipapa.smp.consumer.pojo.SysDictExample;
import com.alipapa.smp.consumer.pojo.UserConsumerRelation;
import com.alipapa.smp.consumer.pojo.UserConsumerRelationExample;
import com.alipapa.smp.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;

@Service
public class SysDictService {
    @Autowired
    private SysDictMapper sysDictMapper;


    @Autowired
    private UserConsumerRelationService userConsumerRelationService;


    /**
     * @param id
     * @return
     */
    public SysDict getSysDictById(Long id) {
        return sysDictMapper.selectByPrimaryKey(id);
    }

    /**
     * @param sysDict
     * @return
     */
    public boolean updateSysDict(SysDict sysDict) {
        sysDictMapper.updateByPrimaryKey(sysDict);
        return true;
    }


    /**
     * @param categoryCode
     * @return
     */
    public List<SysDict> listSysDict(String categoryCode) {
        SysDictExample example = new SysDictExample();
        SysDictExample.Criteria criteria = example.createCriteria();
        criteria.andCategoryCodeEqualTo(categoryCode);
        criteria.andStatusEqualTo(1);//1有效 0无效
        example.setOrderByClause("sort");
        return sysDictMapper.selectByExample(example);
    }

    /**
     * @param categoryCode
     * @param dictText
     * @return
     */
    public List<SysDict> listSysDict(String categoryCode, String dictText) {
        SysDictExample example = new SysDictExample();
        SysDictExample.Criteria criteria = example.createCriteria();
        criteria.andCategoryCodeEqualTo(categoryCode);
        criteria.andStatusEqualTo(1);//1有效 0无效
        criteria.andDictTextEqualTo(dictText);
        example.setOrderByClause("sort");
        return sysDictMapper.selectByExample(example);
    }

    /**
     * 创建客户/抢客户时抛弃规则校验
     *
     * @param userInfo
     * @return
     */
    public boolean checkDiscardingRules(UserInfo userInfo, Long consumerId) {
        UserConsumerRelation discardRelation = userConsumerRelationService.getRelationByConsumerIsDel(consumerId, userInfo.getUserId(), FellowUpRulesEnum.Discard.getCode());//抛弃
        if (discardRelation != null) {
            List<SysDict> sysDictList = this.listSysDict(CategoryCode.discarding_rules.getCodeName(), FellowUpRulesEnum.Discard.getCodeName());
            if (!CollectionUtils.isEmpty(sysDictList)) {
                String timeDay = sysDictList.get(0).getDictValue();
                Date levelTime = DateUtil.getSomeDayDateToTime(discardRelation.getFollowTime(), Integer.valueOf(timeDay));
                if (levelTime.after(new Date())) {
                    return false;
                }
            }
        }
        return true;
    }


    /**
     * 创建客户/抢客户时回收规则校验
     *
     * @param userInfo
     * @return
     */
    public boolean checkReclaimRules(UserInfo userInfo, Long consumerId) {
        UserConsumerRelation reclaimRelation = userConsumerRelationService.getRelationByConsumerIsDel(consumerId, userInfo.getUserId(), FellowUpRulesEnum.Reclaim.getCode());//回收
        if (reclaimRelation != null) {
            List<SysDict> sysDictList = this.listSysDict(CategoryCode.discarding_rules.getCodeName(), FellowUpRulesEnum.Reclaim.getCodeName());
            if (!CollectionUtils.isEmpty(sysDictList)) {
                String timeDay = sysDictList.get(0).getDictValue();
                Date levelTime = DateUtil.getSomeDayDateToTime(reclaimRelation.getFollowTime(), Integer.valueOf(timeDay));
                if (levelTime.after(new Date())) {
                    return false;
                }
            }
        }
        return true;
    }


    /**
     * 获取所有可用不可用的配置
     *
     * @param categoryCode
     * @return
     */
    public List<SysDict> listAllRuleConfig(String categoryCode) {
        SysDictExample example = new SysDictExample();
        SysDictExample.Criteria criteria = example.createCriteria();
        criteria.andCategoryCodeEqualTo(categoryCode);
        example.setOrderByClause("sort");
        return sysDictMapper.selectByExample(example);
    }


}
