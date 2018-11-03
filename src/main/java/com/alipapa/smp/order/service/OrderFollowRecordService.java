package com.alipapa.smp.order.service;

import com.alipapa.smp.order.mapper.OrderFollowRecordMapper;
import com.alipapa.smp.order.pojo.MaterielOrder;
import com.alipapa.smp.order.pojo.MaterielOrderExample;
import com.alipapa.smp.order.pojo.OrderFollowRecord;
import com.alipapa.smp.order.pojo.OrderFollowRecordExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class OrderFollowRecordService {
    @Autowired
    private OrderFollowRecordMapper orderFollowRecordMapper;

    /**
     * 跟单记录
     *
     * @param record
     * @return
     */
    public boolean save(OrderFollowRecord record) {
        orderFollowRecordMapper.insert(record);
        return true;
    }


    /**
     * 按状态查跟单记录
     *
     * @param sort
     * @return
     */
    public OrderFollowRecord getOrderFollowRecordBySort(Long id, Integer sort) {
        OrderFollowRecordExample example = new OrderFollowRecordExample();
        OrderFollowRecordExample.Criteria criteria = example.createCriteria();

        criteria.andMaterielOrderNoEqualTo(String.valueOf(id));
        criteria.andSortEqualTo(sort);

        example.setOrderByClause("updated_time desc");

        List<OrderFollowRecord> orderFollowRecordList = orderFollowRecordMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(orderFollowRecordList)) {
            return null;
        }
        return orderFollowRecordList.get(0);
    }


    /**
     * 按状态查跟单记录
     *
     * @param
     * @return
     */
    public OrderFollowRecord getLatestOrderFollowRecord(String subOrderNo) {
        OrderFollowRecordExample example = new OrderFollowRecordExample();
        OrderFollowRecordExample.Criteria criteria = example.createCriteria();

        criteria.andSubOrderNoEqualTo(String.valueOf(subOrderNo));

        example.setOrderByClause("updated_time desc");

        List<OrderFollowRecord> orderFollowRecordList = orderFollowRecordMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(orderFollowRecordList)) {
            return null;
        }
        return orderFollowRecordList.get(0);
    }


}
