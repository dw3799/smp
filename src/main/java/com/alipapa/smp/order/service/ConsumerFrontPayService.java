package com.alipapa.smp.order.service;

import com.alipapa.smp.common.enums.OrderStatusEnum;
import com.alipapa.smp.common.enums.OrderWorkFlowTypeEnum;
import com.alipapa.smp.common.enums.RoleEnum;
import com.alipapa.smp.order.mapper.ConsumerFrontPayMapper;
import com.alipapa.smp.order.pojo.ConsumerFrontPay;
import com.alipapa.smp.order.pojo.ConsumerFrontPayExample;
import com.alipapa.smp.order.pojo.Order;
import com.alipapa.smp.order.pojo.OrderWorkFlow;
import com.alipapa.smp.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;

@Service
public class ConsumerFrontPayService {

    @Autowired
    private OrderWorkFlowService orderWorkFlowService;

    @Autowired
    private ConsumerFrontPayMapper consumerFrontPayMapper;


    /**
     * 保存定金信息
     *
     * @param consumerFrontPay
     * @return
     */
    public boolean saveOrCreateConsumerFrontPay(Order order, ConsumerFrontPay consumerFrontPay) {
        //保存订单流转记录
        OrderWorkFlow orderWorkFlow = new OrderWorkFlow();
        orderWorkFlow.setCreatedTime(new Date());
        orderWorkFlow.setNewOrderStatus(order.getOrderStatus());
        orderWorkFlow.setOldOrderStatus(OrderStatusEnum.UN_FRONT_PAY.getCode());
        orderWorkFlow.setOpUserName(order.getSalerUserName());
        orderWorkFlow.setOpUserNo(order.getSalerUserNo());
        orderWorkFlow.setOpUserRole(RoleEnum.saler.getDec());
        orderWorkFlow.setOrderNo(order.getOrderNo());
        orderWorkFlow.setType(OrderWorkFlowTypeEnum.M_ORDER.getCodeName());

        if (consumerFrontPay.getId() != null) {
            consumerFrontPayMapper.updateByPrimaryKey(consumerFrontPay);
            if (order.getOrderStatus() == OrderStatusEnum.UN_FRONT_PAY.getCode()) {
                orderWorkFlow.setRemark("更新定金信息");
            } else if (order.getOrderStatus() == OrderStatusEnum.CASH_FRONT_APV.getCode()) {
                orderWorkFlow.setRemark("更新并提交定金信息");
            }
        } else {
            consumerFrontPayMapper.insert(consumerFrontPay);
            if (order.getOrderStatus() == OrderStatusEnum.UN_FRONT_PAY.getCode()) {
                orderWorkFlow.setRemark("保存定金信息");
            } else if (order.getOrderStatus() == OrderStatusEnum.CASH_FRONT_APV.getCode()) {
                orderWorkFlow.setRemark("保存并提交定金信息");
            }
        }

        orderWorkFlow.setResult("成功");
        orderWorkFlow.setUpdatedTime(new Date());
        orderWorkFlowService.save(orderWorkFlow);
        return true;
    }


    /**
     * @param orderNo
     * @return
     */
    public ConsumerFrontPay selectConsumerFrontPayByOrderNo(String orderNo) {
        if (StringUtil.isEmptyString(orderNo)) {
            return null;
        }
        ConsumerFrontPayExample example = new ConsumerFrontPayExample();
        ConsumerFrontPayExample.Criteria criteria = example.createCriteria();
        criteria.andOrderNoEqualTo(orderNo);

        List<ConsumerFrontPay> consumerFrontPayList = consumerFrontPayMapper.selectByExample(example);
        if (!CollectionUtils.isEmpty(consumerFrontPayList)) {
            return consumerFrontPayList.get(0);
        }
        return null;
    }


    /**
     * 补充定金信息
     *
     * @param consumerFrontPay
     * @return
     */
    public boolean updateConsumerFrontPay(ConsumerFrontPay consumerFrontPay) {
        consumerFrontPayMapper.updateByPrimaryKey(consumerFrontPay);
        return true;
    }

}
