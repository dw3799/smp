package com.alipapa.smp.consumer.service;

import com.alipapa.smp.common.enums.CategoryCode;
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

    /**
     * 抛弃
     */
    private static final String Discard = "Discard";

    /**
     * 回收
     */
    private static final String Reclaim = "Reclaim";


    @Autowired
    private SysDictMapper sysDictMapper;


    @Autowired
    private UserConsumerRelationService userConsumerRelationService;


    public List<SysDict> listSysDict(String categoryCode) {
        SysDictExample example = new SysDictExample();
        SysDictExample.Criteria criteria = example.createCriteria();
        criteria.andCategoryCodeEqualTo(categoryCode);
        criteria.andStatusEqualTo(1);//1有效 0无效
        example.setOrderByClause("sort");
        return sysDictMapper.selectByExample(example);
    }


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
     * 创建客户时抛弃规则校验
     *
     * @param userInfo
     * @return
     */
    public boolean checkDiscardingRules(UserInfo userInfo, Long consumerId) {
        UserConsumerRelation paoQi = userConsumerRelationService.getRelationByConsumerIsDel(consumerId, userInfo.getUserId(), 2);//抛弃
        if (paoQi != null) {
            List<SysDict> sysDictList = this.listSysDict(CategoryCode.discarding_rules.getCodeName(), Discard);
            if (!CollectionUtils.isEmpty(sysDictList)) {
                String timeDay = sysDictList.get(0).getDictValue();
                Date levelTime = DateUtil.getSomeDayDateToTime(paoQi.getUpdatedTime(), Integer.valueOf(timeDay));
                if (levelTime.after(new Date())) {
                    return false;
                }
            }
        }
        return true;
    }


    /**
     * 创建客户时回收规则校验
     *
     * @param userInfo
     * @return
     */
    public boolean checkReclaimRules(UserInfo userInfo, Long consumerId) {
        UserConsumerRelation huiShou = userConsumerRelationService.getRelationByConsumerIsDel(consumerId, userInfo.getUserId(), 1);//回收
        if (huiShou != null) {
            List<SysDict> sysDictList = this.listSysDict(CategoryCode.discarding_rules.getCodeName(), Reclaim);
            if (!CollectionUtils.isEmpty(sysDictList)) {
                String timeDay = sysDictList.get(0).getDictValue();
                Date levelTime = DateUtil.getSomeDayDateToTime(huiShou.getUpdatedTime(), Integer.valueOf(timeDay));
                if (levelTime.after(new Date())) {
                    return false;
                }
            }
        }
        return true;
    }


}
