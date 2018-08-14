package com.alipapa.smp.order.service;

import com.alipapa.smp.order.mapper.OrderFollowRecordMapper;
import com.alipapa.smp.order.pojo.OrderFollowRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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


}
