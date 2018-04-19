package com.alipapa.smp.consumer.job;

import com.alipapa.smp.common.enums.CategoryCode;
import com.alipapa.smp.common.enums.ConsumerScopeEnum;
import com.alipapa.smp.common.enums.FellowUpRulesEnum;
import com.alipapa.smp.common.enums.ReclaimRulesEnum;
import com.alipapa.smp.consumer.pojo.Consumer;
import com.alipapa.smp.consumer.pojo.SysDict;
import com.alipapa.smp.consumer.pojo.UserConsumerRelation;
import com.alipapa.smp.consumer.service.ConsumerService;
import com.alipapa.smp.consumer.service.SysDictService;
import com.alipapa.smp.consumer.service.UserConsumerRelationService;
import com.alipapa.smp.utils.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;

/**
 * 客户回收Job
 */
@Component
public class ConsumerReclaimJob {
    private static Logger logger = LoggerFactory.getLogger(ConsumerReclaimJob.class);

    @Autowired
    private SysDictService sysDictService;

    @Autowired
    private UserConsumerRelationService userConsumerRelationService;

    @Autowired
    private ConsumerService consumerService;

    @Scheduled(cron = "0 0 23 * * ? ")
    public void doExecute() {
        //1:新客户，非公，单人私有回收规则
        List<SysDict> sysDictList_NEW_PRIVATE_ONE = sysDictService.listSysDict(CategoryCode.reclaim_rules.getCodeName(), ReclaimRulesEnum.NEW_PRIVATE_ONE.getCodeName());

        if (!CollectionUtils.isEmpty(sysDictList_NEW_PRIVATE_ONE)) {
            int total = 0;

            String day = sysDictList_NEW_PRIVATE_ONE.get(0).getDictValue();
            Date followTime = DateUtil.getSomeDayDateToTime(new Date(), 0 - Integer.valueOf(day));
            List<UserConsumerRelation> userConsumerRelationList = userConsumerRelationService.listNewValidRelationByFollowTime(followTime);
            for (UserConsumerRelation userConsumerRelation : userConsumerRelationList) {
                Consumer consumer = consumerService.getConsumerById(userConsumerRelation.getId());
                if (consumer == null) {
                    continue;
                }
                if (!ConsumerScopeEnum.Public.getCodeName().equals(consumer.getScope())) {
                    long count = userConsumerRelationService.countAllValidRelationByConsumerId(consumer.getId());
                    if (count <= 1) {
                        userConsumerRelation.setIsDel(FellowUpRulesEnum.Reclaim.getCode());
                        userConsumerRelation.setFollowTime(new Date());
                        userConsumerRelationService.updateUserConsumerRelation(userConsumerRelation);

                        consumer.setScope(ConsumerScopeEnum.Public.getCodeName());
                        consumerService.updateConsumer(consumer);
                        total++;
                    }
                }
            }
            logger.info(ReclaimRulesEnum.NEW_PRIVATE_ONE.getDec() + ":" + total);
        }


        //2:新客户，非公，多人私有回收规则
        List<SysDict> sysDictList_NEW_PROTECTED_MANY = sysDictService.listSysDict(CategoryCode.reclaim_rules.getCodeName(), ReclaimRulesEnum.NEW_PROTECTED_MANY.getCodeName());
        if (!CollectionUtils.isEmpty(sysDictList_NEW_PROTECTED_MANY)) {
            int total = 0;
            String day = sysDictList_NEW_PROTECTED_MANY.get(0).getDictValue();
            Date followTime = DateUtil.getSomeDayDateToTime(new Date(), 0 - Integer.valueOf(day));
            List<UserConsumerRelation> userConsumerRelationList = userConsumerRelationService.listNewValidRelationByFollowTime(followTime);
            for (UserConsumerRelation userConsumerRelation : userConsumerRelationList) {
                Consumer consumer = consumerService.getConsumerById(userConsumerRelation.getId());
                if (consumer == null) {
                    continue;
                }
                if (!ConsumerScopeEnum.Public.getCodeName().equals(consumer.getScope())) {
                    long count = userConsumerRelationService.countAllValidRelationByConsumerId(consumer.getId());
                    if (count > 2) {
                        userConsumerRelation.setIsDel(FellowUpRulesEnum.Reclaim.getCode());
                        userConsumerRelation.setFollowTime(new Date());
                        userConsumerRelationService.updateUserConsumerRelation(userConsumerRelation);
                        total++;
                    } else if (count == 2) {
                        userConsumerRelation.setIsDel(FellowUpRulesEnum.Reclaim.getCode());
                        userConsumerRelation.setFollowTime(new Date());
                        userConsumerRelationService.updateUserConsumerRelation(userConsumerRelation);
                        consumer.setScope(ConsumerScopeEnum.Private.getCodeName());
                        consumerService.updateConsumer(consumer);
                        total++;
                    } else if (count < 2) {
                        userConsumerRelation.setIsDel(FellowUpRulesEnum.Reclaim.getCode());
                        userConsumerRelation.setFollowTime(new Date());
                        userConsumerRelationService.updateUserConsumerRelation(userConsumerRelation);
                        consumer.setScope(ConsumerScopeEnum.Public.getCodeName());
                        consumerService.updateConsumer(consumer);
                        total++;
                    }
                }
            }
            logger.info(ReclaimRulesEnum.NEW_PROTECTED_MANY.getDec() + ":" + total);
        }


        //3:新客户，公，单人回收规则
        List<SysDict> sysDictList_NEW_PUBLIC_ONE = sysDictService.listSysDict(CategoryCode.reclaim_rules.getCodeName(), ReclaimRulesEnum.NEW_PUBLIC_ONE.getCodeName());
        if (!CollectionUtils.isEmpty(sysDictList_NEW_PUBLIC_ONE)) {
            int total = 0;
            String day = sysDictList_NEW_PUBLIC_ONE.get(0).getDictValue();
            Date followTime = DateUtil.getSomeDayDateToTime(new Date(), 0 - Integer.valueOf(day));
            List<UserConsumerRelation> userConsumerRelationList = userConsumerRelationService.listNewValidRelationByFollowTime(followTime);
            for (UserConsumerRelation userConsumerRelation : userConsumerRelationList) {
                Consumer consumer = consumerService.getConsumerById(userConsumerRelation.getId());
                if (consumer == null) {
                    continue;
                }
                if (ConsumerScopeEnum.Public.getCodeName().equals(consumer.getScope())) {
                    long count = userConsumerRelationService.countAllValidRelationByConsumerId(consumer.getId());
                    if (count < 2) {
                        userConsumerRelation.setIsDel(FellowUpRulesEnum.Reclaim.getCode());
                        userConsumerRelation.setFollowTime(new Date());
                        userConsumerRelationService.updateUserConsumerRelation(userConsumerRelation);
                        total++;
                    }
                }
            }
            logger.info(ReclaimRulesEnum.NEW_PUBLIC_ONE.getDec() + ":" + total);
        }


        //4:新客户，公，多人回收规则
        List<SysDict> sysDictList_NEW_PUBLIC_MANY = sysDictService.listSysDict(CategoryCode.reclaim_rules.getCodeName(), ReclaimRulesEnum.NEW_PUBLIC_MANY.getCodeName());
        if (!CollectionUtils.isEmpty(sysDictList_NEW_PUBLIC_MANY)) {
            int total = 0;
            String day = sysDictList_NEW_PUBLIC_MANY.get(0).getDictValue();
            Date followTime = DateUtil.getSomeDayDateToTime(new Date(), 0 - Integer.valueOf(day));
            List<UserConsumerRelation> userConsumerRelationList = userConsumerRelationService.listNewValidRelationByFollowTime(followTime);
            for (UserConsumerRelation userConsumerRelation : userConsumerRelationList) {
                Consumer consumer = consumerService.getConsumerById(userConsumerRelation.getId());
                if (consumer == null) {
                    continue;
                }
                if (ConsumerScopeEnum.Public.getCodeName().equals(consumer.getScope())) {
                    userConsumerRelation.setIsDel(FellowUpRulesEnum.Reclaim.getCode());
                    userConsumerRelation.setFollowTime(new Date());
                    userConsumerRelationService.updateUserConsumerRelation(userConsumerRelation);
                    total++;
                }
            }
            logger.info(ReclaimRulesEnum.NEW_PUBLIC_MANY.getDec() + ":" + total);
        }


        //5:老客户，both，单人回收规则
        List<SysDict> sysDictList_DEAL_BOTH_ONE = sysDictService.listSysDict(CategoryCode.reclaim_rules.getCodeName(), ReclaimRulesEnum.DEAL_BOTH_ONE.getCodeName());
        if (!CollectionUtils.isEmpty(sysDictList_DEAL_BOTH_ONE)) {
            int total = 0;
            String day = sysDictList_DEAL_BOTH_ONE.get(0).getDictValue();
            Date followTime = DateUtil.getSomeDayDateToTime(new Date(), 0 - Integer.valueOf(day));
            List<UserConsumerRelation> userConsumerRelationList = userConsumerRelationService.listDealValidRelationByFollowTime(followTime);
            for (UserConsumerRelation userConsumerRelation : userConsumerRelationList) {
                Consumer consumer = consumerService.getConsumerById(userConsumerRelation.getId());
                if (consumer == null) {
                    continue;
                }

                long count = userConsumerRelationService.countAllValidRelationByConsumerId(consumer.getId());
                if (count <= 1) {
                    userConsumerRelation.setIsDel(FellowUpRulesEnum.Reclaim.getCode());
                    userConsumerRelation.setFollowTime(new Date());
                    userConsumerRelation.setHasOrder(0);
                    userConsumerRelation.setDealOrder(0);
                    userConsumerRelationService.updateUserConsumerRelation(userConsumerRelation);

                    consumer.setScope(ConsumerScopeEnum.Public.getCodeName());
                    consumerService.updateConsumer(consumer);
                    total++;
                }

            }
            logger.info(ReclaimRulesEnum.DEAL_BOTH_ONE.getDec() + ":" + total);
        }


        //6:老客户，both，多人回收规则
        List<SysDict> sysDictList_DEAL_BOTH_MANY = sysDictService.listSysDict(CategoryCode.reclaim_rules.getCodeName(), ReclaimRulesEnum.DEAL_BOTH_MANY.getCodeName());
        if (!CollectionUtils.isEmpty(sysDictList_NEW_PUBLIC_MANY)) {
            int total = 0;
            String day = sysDictList_DEAL_BOTH_MANY.get(0).getDictValue();
            Date followTime = DateUtil.getSomeDayDateToTime(new Date(), 0 - Integer.valueOf(day));
            List<UserConsumerRelation> userConsumerRelationList = userConsumerRelationService.listNewValidRelationByFollowTime(followTime);
            for (UserConsumerRelation userConsumerRelation : userConsumerRelationList) {
                Consumer consumer = consumerService.getConsumerById(userConsumerRelation.getId());
                if (consumer == null) {
                    continue;
                }
                long count = userConsumerRelationService.countAllValidRelationByConsumerId(consumer.getId());
                if (count > 2) {
                    userConsumerRelation.setIsDel(FellowUpRulesEnum.Reclaim.getCode());
                    userConsumerRelation.setFollowTime(new Date());
                    userConsumerRelation.setHasOrder(0);
                    userConsumerRelation.setDealOrder(0);
                    userConsumerRelationService.updateUserConsumerRelation(userConsumerRelation);
                    total++;
                } else if (count == 2) {
                    userConsumerRelation.setIsDel(FellowUpRulesEnum.Reclaim.getCode());
                    userConsumerRelation.setFollowTime(new Date());
                    userConsumerRelation.setHasOrder(0);
                    userConsumerRelation.setDealOrder(0);
                    userConsumerRelationService.updateUserConsumerRelation(userConsumerRelation);

                    consumer.setScope(ConsumerScopeEnum.Private.getCodeName());
                    consumerService.updateConsumer(consumer);
                    total++;
                } else if (count < 2) {
                    userConsumerRelation.setIsDel(FellowUpRulesEnum.Reclaim.getCode());
                    userConsumerRelation.setFollowTime(new Date());
                    userConsumerRelation.setHasOrder(0);
                    userConsumerRelation.setDealOrder(0);
                    userConsumerRelationService.updateUserConsumerRelation(userConsumerRelation);

                    consumer.setScope(ConsumerScopeEnum.Public.getCodeName());
                    consumerService.updateConsumer(consumer);
                    total++;
                }
            }
            logger.info(ReclaimRulesEnum.DEAL_BOTH_MANY.getDec() + ":" + total);
        }
    }
}
