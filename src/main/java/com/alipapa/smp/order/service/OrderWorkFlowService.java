package com.alipapa.smp.order.service;

import com.alipapa.smp.common.enums.RoleEnum;
import com.alipapa.smp.order.mapper.OrderWorkFlowMapper;
import com.alipapa.smp.order.pojo.OrderWorkFlow;
import com.alipapa.smp.order.pojo.OrderWorkFlowExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;

@Service
public class OrderWorkFlowService {

    @Autowired
    private OrderWorkFlowMapper orderWorkFlowMapper;

    /**
     * 保存流转记录
     *
     * @param record
     * @return
     */
    public boolean save(OrderWorkFlow record) {
        if (record.getUpdatedTime() == null) {
            record.setUpdatedTime(new Date());
        }
        RoleEnum roleEnum = RoleEnum.getValue(record.getOpUserRole());
        if (roleEnum != null) {
            record.setOpUserRole(roleEnum.getDec());
        }
        orderWorkFlowMapper.insert(record);
        return true;
    }


    /**
     * 流转记录查询
     *
     * @param orderNo,type
     * @return
     */
    public List<OrderWorkFlow> listOrderWorkFlowByParams(String orderNo, String type) {
        OrderWorkFlowExample example = new OrderWorkFlowExample();
        OrderWorkFlowExample.Criteria criteria = example.createCriteria();
        criteria.andOrderNoEqualTo(orderNo);
        criteria.andTypeEqualTo(type);
        example.setOrderByClause("created_time desc");
        List<OrderWorkFlow> orderWorkFlowList = orderWorkFlowMapper.selectByExample(example);

        if (!CollectionUtils.isEmpty(orderWorkFlowList)) {
            for (OrderWorkFlow orderWorkFlow : orderWorkFlowList) {
                RoleEnum roleEnum = RoleEnum.getValue(orderWorkFlow.getOpUserRole());
                if (roleEnum != null) {
                    orderWorkFlow.setOpUserRole(roleEnum.getDec());
                }
            }
        }
        return orderWorkFlowList;
    }


}
