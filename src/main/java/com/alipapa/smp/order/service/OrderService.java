package com.alipapa.smp.order.service;

import com.alipapa.smp.common.enums.OrderCategoryCode;
import com.alipapa.smp.consumer.pojo.SysDict;
import com.alipapa.smp.consumer.service.SysDictService;
import com.alipapa.smp.order.mapper.OrderMapper;
import com.alipapa.smp.order.pojo.Order;
import com.alipapa.smp.order.pojo.OrderExample;
import com.alipapa.smp.utils.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Service
public class OrderService {
    private static Logger logger = LoggerFactory.getLogger(OrderService.class);

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private SysDictService sysDictService;

    /**
     * 保存订单
     *
     * @param record
     * @return
     */
    public boolean saveOrder(Order record) {
        orderMapper.insert(record);
        return true;
    }


    /**
     * 更新订单
     *
     * @param record
     * @return
     */
    @Transactional
    public boolean updateOrder(Order record) {
        orderMapper.updateByPrimaryKey(record);
        return true;
    }


    /**
     * @return
     */
    public List<Order> getOrderList(String consumerNo) {
        OrderExample example = new OrderExample();
        OrderExample.Criteria criteria = example.createCriteria();
        criteria.andConsumerNoEqualTo(consumerNo);
        example.setOrderByClause("submit_time desc");
        return orderMapper.selectByExample(example);
    }


    /**
     * @param orderNo
     * @return
     */
    public Order selectOrderByOrderNo(String orderNo) {
        if (StringUtil.isEmptyString(orderNo)) {
            return null;
        }
        OrderExample example = new OrderExample();
        OrderExample.Criteria criteria = example.createCriteria();
        criteria.andOrderNoEqualTo(orderNo);


        List<Order> orders = orderMapper.selectByExample(example);
        if (!CollectionUtils.isEmpty(orders)) {
            return orders.get(0);
        }
        return null;
    }


    /**
     * 单订单表分页查询
     *
     * @return
     */
    public List<Order> listOrderByStatus(Map<String, Object> params) {
        if (params == null) {
            params = new HashMap<>();
        }

        Iterator<Map.Entry<String, Object>> it = params.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, Object> entry = it.next();
            logger.info("key= " + entry.getKey() + " and value= " + entry.getValue());
        }

        List<Order> orderList = orderMapper.listOrderByParam(params);

        return orderList;
    }


    /**
     * 单订单表分页查询
     *
     * @param params
     * @return
     */
    public Long listOrderByStatusCount(Map<String, Object> params) {
        return orderMapper.listOrderByParamCount(params);
    }


    /**
     * 业务主管获取组内待审核订单
     *
     * @return
     */
    public List<Order> listMyOrderByParam(Map<String, Object> params) {
        if (params == null) {
            params = new HashMap<>();
        }

        Iterator<Map.Entry<String, Object>> it = params.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, Object> entry = it.next();
            logger.info("key= " + entry.getKey() + " and value= " + entry.getValue());
        }

        List<Order> orderList = orderMapper.listMyOrderByParam(params);

        return orderList;
    }


    /**
     * 业务主管获取组内待审核订单
     *
     * @param params
     * @return
     */
    public Long listMyOrderByParamCount(Map<String, Object> params) {
        return orderMapper.listMyOrderByParamCount(params);
    }


    /**
     * @return
     */
    public String getCurrencyDec(Order order) {
        List<SysDict> sysDictList = sysDictService.listSysDict(OrderCategoryCode.Currency.getCodeName(), order.getCurrency());
        String currencyDec = "UNKNOWN";
        if (!CollectionUtils.isEmpty(sysDictList)) {
            SysDict currencySysDict = sysDictList.get(0);
            currencyDec = currencySysDict.getDictValue();
        }

        return currencyDec;
    }
}