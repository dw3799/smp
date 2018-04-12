package com.alipapa.smp.consumer.service;

import com.alipapa.smp.consumer.mapper.ConsumerMapper;
import com.alipapa.smp.consumer.pojo.Consumer;
import com.alipapa.smp.consumer.pojo.ConsumerExample;
import com.alipapa.smp.consumer.vo.ConsumerDetailVo;
import com.alipapa.smp.consumer.vo.SalerConsumerDetailVo;
import com.alipapa.smp.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ConsumerService {

    @Autowired
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
        return consumerMapper.selectMaxId();
    }


    /**
     * 潜在客户/存量客户查询
     *
     * @return
     */
    public List<SalerConsumerDetailVo> listSalerConsumerDetailVoByParams(Map<String, Object> params, Integer start, Integer size) {
        if (params == null) {
            params = new HashMap<>();
        }
        Long count = consumerMapper.findSalerConsumerByParamCount(params);

        List<SalerConsumerDetailVo> consumerDetailVoList = new ArrayList<>();
        if (count != null && count > 0) {
            params.put("start", start);
            params.put("size", size);
            List<Consumer> consumerList = consumerMapper.findSalerConsumerByParam(params);
            if (!CollectionUtils.isEmpty(consumerList)) {
                for (Consumer consumer : consumerList) {
                    SalerConsumerDetailVo consumerDetailVo = new SalerConsumerDetailVo();
                    consumerDetailVo.setConsumerNo(consumer.getConsumerNo());
                    consumerDetailVo.setName(consumer.getName());

                    consumerDetailVo.setCountry(consumer.getCountry());
                    consumerDetailVo.setHasOrder(consumer.getHasOrder());
                    consumerDetailVo.setLevel(consumer.getLevel());

                    //TODO from订单管理
                    consumerDetailVo.setTotalOrder(null);
                    consumerDetailVo.setOrderAmount(null);

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
                    consumerDetailVo.setConsumerNo(consumer.getConsumerNo());
                    consumerDetailVo.setName(consumer.getName());

                    consumerDetailVo.setCountry(consumer.getCountry());
                    consumerDetailVo.setHasOrder(consumer.getHasOrder());
                    consumerDetailVo.setMainBusiness(consumer.getMainBusiness());
                    consumerDetailVo.setSource(consumer.getSource());
                    consumerDetailVo.setType(consumer.getType());

                    //TODO from订单管理
                    consumerDetailVo.setTotalOrder(null);
                    consumerDetailVo.setOrderAmount(null);

                    consumerDetailVo.setTotalCount(count);
                    consumerDetailVoList.add(consumerDetailVo);
                }
            }
        }
        return consumerDetailVoList;
    }


}
