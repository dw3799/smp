package com.alipapa.smp.consumer.service;

import com.alipapa.smp.consumer.mapper.ConsumerMapper;
import com.alipapa.smp.consumer.pojo.Consumer;
import com.alipapa.smp.consumer.pojo.ConsumerExample;
import com.alipapa.smp.consumer.pojo.ConsumerExt;
import com.alipapa.smp.consumer.vo.ConsumerDetailVo;
import com.alipapa.smp.consumer.vo.SalerConsumerDetailVo;
import com.alipapa.smp.order.service.impl.OrderServiceProxy;
import com.alipapa.smp.order.vo.ConsumerOrderCount;
import com.alipapa.smp.utils.DateUtil;
import com.alipapa.smp.utils.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.*;

@Service
public class ConsumerService {
    private static Logger logger = LoggerFactory.getLogger(ConsumerService.class);

    @Autowired
    private OrderServiceProxy orderServiceProxy;


    @Resource
    private ConsumerMapper consumerMapper;

    /**
     * @param id
     * @return
     */
    public Consumer getConsumerById(Long id) {
        return consumerMapper.selectByPrimaryKey(id);
    }

    /**
     * @param consumer
     * @return
     */
    public boolean addConsumer(Consumer consumer) {
        consumerMapper.insert(consumer);
        return true;
    }

    /**
     * @param consumer
     * @return
     */
    public boolean updateConsumer(Consumer consumer) {
        consumerMapper.updateByPrimaryKeySelective(consumer);
        return true;
    }


    /**
     * @param name,email
     * @return
     */
    public List<Consumer> listConsumerByNameAndEmail(String name, String email) {
        if (StringUtil.isEmptyString(name) || StringUtil.isEmptyString(email)) {
            return null;
        }
        ConsumerExample example = new ConsumerExample();
        ConsumerExample.Criteria criteria = example.createCriteria();
        criteria.andNameEqualTo(name);
        criteria.andEmailEqualTo(email);
        return consumerMapper.selectByExample(example);
    }


    /**
     * @param consumerNo
     * @return
     */
    public Consumer getConsumerByConsumerNo(String consumerNo) {
        if (StringUtil.isEmptyString(consumerNo)) {
            return null;
        }
        ConsumerExample example = new ConsumerExample();
        ConsumerExample.Criteria criteria = example.createCriteria();
        criteria.andConsumerNoEqualTo(consumerNo);
        List<Consumer> consumerList = consumerMapper.selectByExample(example);

        if (CollectionUtils.isEmpty(consumerList)) {
            return null;
        }
        return consumerList.get(0);
    }


    /**
     * @param name,email
     * @return
     */
    public Consumer getConsumerByNameAndEmail(String name, String email) {
        List<Consumer> consumerList = this.listConsumerByNameAndEmail(name, email);

        if (!CollectionUtils.isEmpty(consumerList)) {
            return consumerList.get(0);
        }
        return null;
    }


    /**
     * 获取最新的用户ID
     *
     * @return
     */
    public Long getLatestConsumerId() {
        return

                consumerMapper.selectMaxId();
    }


    /**
     * 总数
     *
     * @param params
     * @return
     */
    public Long findSalerConsumerByParamCount(Map<String, Object> params) {
        if (params == null) {
            params = new HashMap<>();
        }
        Long count = consumerMapper.findSalerConsumerByParamCount(params);
        return count;
    }


    /**
     * 潜在客户/存量客户查询
     *
     * @return
     */
    public List<SalerConsumerDetailVo> listSalerConsumerDetailVoByParams(Map<String, Object> params, Integer start, Integer size, String userNo) {
        if (params == null) {
            params = new HashMap<>();
        }
        Long count = consumerMapper.findSalerConsumerByParamCount(params);

        Iterator<Map.Entry<String, Object>> it = params.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, Object> entry = it.next();
            logger.info("key= " + entry.getKey() + " and value= " + entry.getValue());
        }


        logger.info("findSalerConsumerByParamCount:" + count);
        List<SalerConsumerDetailVo> consumerDetailVoList = new ArrayList<>();
        HashSet<String> consumerIdSet = new HashSet<>();
        if (count != null && count > 0) {
            params.put("start", start);
            params.put("size", size);
            List<ConsumerExt> consumerList = consumerMapper.findSalerConsumerByParam(params);

            if (!CollectionUtils.isEmpty(consumerList)) {
                logger.info("consumerList:" + consumerList.size());

                for (ConsumerExt consumer : consumerList) {
/*                    if (consumerIdSet.contains(String.valueOf(consumer.getId()))) {
                        continue;
                    }
                    consumerIdSet.add(String.valueOf(consumer.getId()));*/
                    SalerConsumerDetailVo consumerDetailVo = new SalerConsumerDetailVo();
                    consumerDetailVo.setConsumerNo(consumer.getConsumerNo());
                    consumerDetailVo.setName(consumer.getName());
                    consumerDetailVo.setConsumerId(consumer.getId());

                    consumerDetailVo.setCountry(consumer.getCountry());
                    consumerDetailVo.setHasOrder(consumer.getHasOrder());
                    consumerDetailVo.setLevel(consumer.getLevel());
                    consumerDetailVo.setEmail(consumer.getEmail());

                    if (userNo.equals(consumer.getUserNo())) {
                        consumerDetailVo.setIsDiscard(1);
                    } else {
                        consumerDetailVo.setIsDiscard(0);
                    }
                    consumerDetailVo.setContactTime(DateUtil.formatToStr(consumer.getFollowTime()));
                    consumerDetailVo.setNextContactTime(DateUtil.formatToStr(consumer.getNextFollowTime()));


                    //TODO from订单管理
                    ConsumerOrderCount consumerOrderCount = orderServiceProxy.getConsumerOrderCount(consumer.getConsumerNo());

                    if (consumerOrderCount != null && consumerOrderCount.getDealOrderCount() != null) {
                        consumerDetailVo.setTotalOrder(consumerOrderCount.getDealOrderCount());
                        consumerDetailVo.setOrderAmount(consumerOrderCount.getDealOrderAmount());
                        consumerDetailVo.setHistoryOrder(consumerOrderCount.getDealOrderCount() + "笔" + "/" + consumerOrderCount.getDealOrderAmount());
                    }

                    consumerDetailVo.setTotalCount(count);
                    consumerDetailVoList.add(consumerDetailVo);
                }
            }
        }
        return consumerDetailVoList;
    }


    /**
     * 业务主管及管理员客户查询
     *
     * @return
     */
    public List<SalerConsumerDetailVo> listSalerManageConsumerDetailVoByParams(Map<String, Object> params, Integer start, Integer size, String userNo) {
        if (params == null) {
            params = new HashMap<>();
        }
        Long count = consumerMapper.findSalerManagerConsumerByParamCount(params);

        Iterator<Map.Entry<String, Object>> it = params.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, Object> entry = it.next();
            logger.info("findSalerManagerConsumerByParamCount: key= " + entry.getKey() + " and value= " + entry.getValue());
        }

        logger.info("findSalerManagerConsumerByParamCount:" + count);
        List<SalerConsumerDetailVo> consumerDetailVoList = new ArrayList<>();

        if (count != null && count > 0) {
            params.put("start", start);
            params.put("size", size);
            List<ConsumerExt> consumerList = consumerMapper.findSalerManagerConsumerByParam(params);

            if (!CollectionUtils.isEmpty(consumerList)) {
                logger.info("consumerList:" + consumerList.size());

                for (ConsumerExt consumer : consumerList) {
                    SalerConsumerDetailVo consumerDetailVo = new SalerConsumerDetailVo();
                    consumerDetailVo.setConsumerNo(consumer.getConsumerNo());
                    consumerDetailVo.setName(consumer.getName());
                    consumerDetailVo.setConsumerId(consumer.getId());

                    consumerDetailVo.setCountry(consumer.getCountry());
                    consumerDetailVo.setHasOrder(consumer.getHasOrder());
                    consumerDetailVo.setLevel(consumer.getLevel());
                    consumerDetailVo.setEmail(consumer.getEmail());

                    if (userNo.equals(consumer.getUserNo())) {
                        consumerDetailVo.setIsDiscard(1);
                    } else {
                        consumerDetailVo.setIsDiscard(0);
                    }
                    consumerDetailVo.setContactTime(DateUtil.formatToStr(consumer.getFollowTime()));
                    consumerDetailVo.setNextContactTime(DateUtil.formatToStr(consumer.getNextFollowTime()));


                    //TODO from订单管理
                    ConsumerOrderCount consumerOrderCount = orderServiceProxy.getConsumerOrderCount(consumer.getConsumerNo());

                    if (consumerOrderCount != null && consumerOrderCount.getDealOrderCount() != null) {
                        consumerDetailVo.setTotalOrder(consumerOrderCount.getDealOrderCount());
                        consumerDetailVo.setOrderAmount(consumerOrderCount.getDealOrderAmount());
                        consumerDetailVo.setHistoryOrder(consumerOrderCount.getDealOrderCount() + "笔" + "/" + consumerOrderCount.getDealOrderAmount());
                    }

                    consumerDetailVo.setTotalCount(count);
                    consumerDetailVoList.add(consumerDetailVo);
                }
            }
        }
        return consumerDetailVoList;
    }


    /**
     * 客户查询
     *
     * @return
     */
    public List<ConsumerDetailVo> listConsumerDetailVoByParams(Map<String, Object> params, Integer start, Integer size) {
        if (params == null) {
            params = new HashMap<>();
        }
        Long count = consumerMapper.findConsumerByParamCount(params);

        List<ConsumerDetailVo> consumerDetailVoList = new ArrayList<>();
        if (count != null && count > 0) {
            params.put("start", start);
            params.put("size", size);
            List<Consumer> consumerList = consumerMapper.findConsumerByParam(params);
            if (!CollectionUtils.isEmpty(consumerList)) {
                for (Consumer consumer : consumerList) {
                    ConsumerDetailVo consumerDetailVo = new ConsumerDetailVo();
                    consumerDetailVo.setConsumerId(consumer.getId());
                    consumerDetailVo.setConsumerNo(consumer.getConsumerNo());
                    consumerDetailVo.setName(consumer.getName());
                    consumerDetailVo.setEmail(consumer.getEmail());
                    consumerDetailVo.setCountry(consumer.getCountry());
                    consumerDetailVo.setLevel(consumer.getLevel());
                    consumerDetailVo.setMainBusiness(consumer.getMainBusiness());
                    consumerDetailVo.setSource(consumer.getSource());
                    consumerDetailVo.setType(consumer.getType());
                    consumerDetailVo.setIntention(consumer.getIntention());
                    consumerDetailVo.setIntentionQuantity(consumer.getIntentionQuantity());
                    //TODO from订单管理
                    ConsumerOrderCount consumerOrderCount = orderServiceProxy.getConsumerOrderCount(consumer.getConsumerNo());

                    if (consumerOrderCount != null && consumerOrderCount.getDealOrderCount() != null) {
                        consumerDetailVo.setTotalOrder(consumerOrderCount.getDealOrderCount());
                        consumerDetailVo.setOrderAmount(consumerOrderCount.getDealOrderAmount());
                        consumerDetailVo.setHistoryOrder(consumerOrderCount.getDealOrderCount() + "笔" + "/" + consumerOrderCount.getDealOrderAmount());
                    }
                    consumerDetailVo.setTotalCount(count);
                    consumerDetailVoList.add(consumerDetailVo);
                }
            }
        }
        return consumerDetailVoList;
    }


}
