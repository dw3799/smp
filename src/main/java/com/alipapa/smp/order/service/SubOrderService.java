package com.alipapa.smp.order.service;

import com.alipapa.smp.common.enums.OrderTypeEnum;
import com.alipapa.smp.order.mapper.AgentOrderDetailMapper;
import com.alipapa.smp.order.mapper.SelfOrderDetailMapper;
import com.alipapa.smp.order.mapper.SubOrderMapper;
import com.alipapa.smp.order.pojo.AgentOrderDetail;
import com.alipapa.smp.order.pojo.SelfOrderDetail;
import com.alipapa.smp.order.pojo.SubOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubOrderService {
    @Autowired
    private SubOrderMapper subOrderMapper;


    @Autowired
    private SelfOrderDetailMapper selfOrderDetailMapper;


    @Autowired
    private AgentOrderDetailMapper agentOrderDetailMapper;


    /**
     * 保存产品订单
     *
     * @param record
     * @return
     */
    public boolean saveSubOrder(SubOrder record, OrderTypeEnum orderTypeEnum) {
        subOrderMapper.insert(record);

        if (orderTypeEnum == OrderTypeEnum.SELF_ORDER) {
            SelfOrderDetail selfOrderDetail = record.getSelfOrderDetail();
            selfOrderDetailMapper.insert(selfOrderDetail);
        } else if (orderTypeEnum == OrderTypeEnum.AGENT_ORDER) {
            AgentOrderDetail agentOrderDetail = record.getAgentOrderDetail();
            agentOrderDetailMapper.insert(agentOrderDetail);
        }
        return true;
    }


}
