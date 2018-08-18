package com.alipapa.smp.order.service;

import com.alipapa.smp.common.enums.OrderTypeEnum;
import com.alipapa.smp.order.mapper.AgentOrderDetailMapper;
import com.alipapa.smp.order.mapper.SelfOrderDetailMapper;
import com.alipapa.smp.order.mapper.SubOrderMapper;
import com.alipapa.smp.order.pojo.*;
import com.alipapa.smp.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

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


    /**
     * 修改产品订单
     *
     * @param record
     * @return
     */
    public boolean updateSubOrder(SubOrder record, OrderTypeEnum orderTypeEnum) {
        if (orderTypeEnum == OrderTypeEnum.SELF_ORDER) {
            SelfOrderDetail selfOrderDetail = record.getSelfOrderDetail();
            selfOrderDetailMapper.updateByPrimaryKey(selfOrderDetail);
        } else if (orderTypeEnum == OrderTypeEnum.AGENT_ORDER) {
            AgentOrderDetail agentOrderDetail = record.getAgentOrderDetail();
            agentOrderDetailMapper.updateByPrimaryKey(agentOrderDetail);
        }
        subOrderMapper.updateByPrimaryKey(record);
        return true;
    }


    /**
     * 修改产品订单
     *
     * @param record
     * @return
     */
    public boolean delSubOrder(SubOrder record, OrderTypeEnum orderTypeEnum) {
        if (orderTypeEnum == OrderTypeEnum.SELF_ORDER) {
            SelfOrderDetail selfOrderDetail = record.getSelfOrderDetail();
            selfOrderDetailMapper.deleteByPrimaryKey(selfOrderDetail.getId());
        } else if (orderTypeEnum == OrderTypeEnum.AGENT_ORDER) {
            AgentOrderDetail agentOrderDetail = record.getAgentOrderDetail();
            agentOrderDetailMapper.deleteByPrimaryKey(agentOrderDetail.getId());
        }
        subOrderMapper.deleteByPrimaryKey(record.getId());
        return true;
    }


    /**
     * 获取产品订单
     *
     * @param orderNo
     * @return
     */
    public List<SubOrder> listSubOrderByOrderNo(String orderNo, OrderTypeEnum orderTypeEnum) {
        SubOrderExample example = new SubOrderExample();
        SubOrderExample.Criteria criteria = example.createCriteria();
        criteria.andOrderNoEqualTo(orderNo);
        List<SubOrder> subOrderList = subOrderMapper.selectByExample(example);

        if (!CollectionUtils.isEmpty(subOrderList)) {
            for (SubOrder subOrder : subOrderList) {
                String subOrderNo = subOrder.getSubOrderNo();
                if (orderTypeEnum == OrderTypeEnum.SELF_ORDER) {
                    SelfOrderDetail selfOrderDetail = this.getSelfOrderDetailBySubOrderNo(subOrderNo);
                    subOrder.setSelfOrderDetail(selfOrderDetail);
                } else if (orderTypeEnum == OrderTypeEnum.AGENT_ORDER) {
                    AgentOrderDetail agentOrderDetail = this.getAgentOrderDetailBySubOrderNo(subOrderNo);
                    subOrder.setAgentOrderDetail(agentOrderDetail);
                }
            }
            return subOrderList;
        }
        return null;
    }


    /**
     * 获取产品订单
     *
     * @param orderNo
     * @return
     */
    public SubOrder getSubOrderBySubOrderNo(String orderNo, String subOrderNo, OrderTypeEnum orderTypeEnum) {
        if (StringUtil.isEmptyString(subOrderNo)) {
            return null;
        }

        SubOrderExample example = new SubOrderExample();
        SubOrderExample.Criteria criteria = example.createCriteria();
        criteria.andOrderNoEqualTo(orderNo);
        criteria.andSubOrderNoEqualTo(subOrderNo);
        List<SubOrder> subOrderList = subOrderMapper.selectByExample(example);

        if (!CollectionUtils.isEmpty(subOrderList)) {
            SubOrder subOrder = subOrderList.get(0);
            if (orderTypeEnum == OrderTypeEnum.SELF_ORDER) {
                SelfOrderDetail selfOrderDetail = this.getSelfOrderDetailBySubOrderNo(subOrderNo);
                subOrder.setSelfOrderDetail(selfOrderDetail);
            } else if (orderTypeEnum == OrderTypeEnum.AGENT_ORDER) {
                AgentOrderDetail agentOrderDetail = this.getAgentOrderDetailBySubOrderNo(subOrderNo);
                subOrder.setAgentOrderDetail(agentOrderDetail);
            }
            return subOrder;
        }
        return null;
    }


    /**
     * 自营产品明细
     *
     * @param subOrderNo
     * @return
     */
    private SelfOrderDetail getSelfOrderDetailBySubOrderNo(String subOrderNo) {
        SelfOrderDetailExample example = new SelfOrderDetailExample();
        SelfOrderDetailExample.Criteria criteria = example.createCriteria();
        criteria.andSubOrderNoEqualTo(subOrderNo);
        List<SelfOrderDetail> selfOrderDetailList = selfOrderDetailMapper.selectByExample(example);

        if (!CollectionUtils.isEmpty(selfOrderDetailList)) {
            return selfOrderDetailList.get(0);
        }
        return null;
    }


    /**
     * 代理产品明细
     *
     * @param subOrderNo
     * @return
     */
    private AgentOrderDetail getAgentOrderDetailBySubOrderNo(String subOrderNo) {
        AgentOrderDetailExample example = new AgentOrderDetailExample();
        AgentOrderDetailExample.Criteria criteria = example.createCriteria();
        criteria.andSubOrderNoEqualTo(subOrderNo);
        List<AgentOrderDetail> agentOrderDetailList = agentOrderDetailMapper.selectByExample(example);

        if (!CollectionUtils.isEmpty(agentOrderDetailList)) {
            return agentOrderDetailList.get(0);
        }
        return null;
    }


}