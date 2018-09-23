package com.alipapa.smp.order.service;

import com.alipapa.smp.common.enums.OrderPayStatusEnum;
import com.alipapa.smp.common.enums.OrderWorkFlowTypeEnum;
import com.alipapa.smp.common.enums.RoleEnum;
import com.alipapa.smp.order.mapper.ConsumerTailPayMapper;
import com.alipapa.smp.order.pojo.*;
import com.alipapa.smp.utils.PriceUtil;
import com.alipapa.smp.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class ConsumerTailPayService {

    @Autowired
    private OrderWorkFlowService orderWorkFlowService;

    @Autowired
    private ConsumerTailPayMapper consumerTailPayMapper;


    /**
     * 保存尾款
     *
     * @param consumerTailPay
     * @return
     */
    @Transactional
    public boolean saveConsumerTailPay(ConsumerTailPay consumerTailPay, Order order) {
        OrderWorkFlow orderWorkFlow = new OrderWorkFlow();
        orderWorkFlow.setCreatedTime(new Date());
        orderWorkFlow.setNewOrderStatus(order.getOrderStatus());
        orderWorkFlow.setOldOrderStatus(order.getOrderStatus());
        orderWorkFlow.setOpUserName(order.getSalerUserName());
        orderWorkFlow.setOpUserNo(order.getSalerUserNo());
        orderWorkFlow.setOpUserRole(RoleEnum.saler.getDec());
        orderWorkFlow.setOrderNo(order.getOrderNo());
        orderWorkFlow.setType(OrderWorkFlowTypeEnum.M_ORDER.getCodeName());
        orderWorkFlow.setResult("提交成功");
        orderWorkFlow.setUpdatedTime(new Date());
        consumerTailPayMapper.insert(consumerTailPay);
        orderWorkFlow.setRemark("提交订单尾款" + PriceUtil.convertToYuanStr(consumerTailPay.getTailAmount()));
        orderWorkFlowService.save(orderWorkFlow);
        return true;
    }


    /**
     * 获取待确认的尾款
     *
     * @param orderNo
     * @return
     */
    public List<ConsumerTailPay> selectInRiewConsumerTailPayByOrderNo(String orderNo) {
        if (StringUtil.isEmptyString(orderNo)) {
            return null;
        }
        ConsumerTailPayExample example = new ConsumerTailPayExample();
        ConsumerTailPayExample.Criteria criteria = example.createCriteria();
        criteria.andOrderNoEqualTo(orderNo);
        criteria.andPayStatusEqualTo(OrderPayStatusEnum.TAIL_CASH_APV.getCode());

        List<ConsumerTailPay> consumerTailPayList = consumerTailPayMapper.selectByExample(example);
        return consumerTailPayList;
    }


    /**
     * 更新订单尾款信息
     *
     * @param consumerTailPay
     * @return
     */
    public boolean updateConsumerTailPay(ConsumerTailPay consumerTailPay) {
        consumerTailPayMapper.updateByPrimaryKey(consumerTailPay);
        return true;
    }

}
