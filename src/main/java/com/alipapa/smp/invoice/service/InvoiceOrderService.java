package com.alipapa.smp.invoice.service;

import com.alipapa.smp.common.enums.OrderStatusEnum;
import com.alipapa.smp.common.enums.OrderWorkFlowTypeEnum;
import com.alipapa.smp.common.enums.SubOrderStatusEnum;
import com.alipapa.smp.common.request.UserInfo;
import com.alipapa.smp.invoice.mapper.InvoiceOrderMapper;
import com.alipapa.smp.invoice.mapper.InvoiceProductMapper;
import com.alipapa.smp.invoice.pojo.*;
import com.alipapa.smp.order.pojo.Order;
import com.alipapa.smp.order.pojo.OrderWorkFlow;
import com.alipapa.smp.order.pojo.SubOrder;
import com.alipapa.smp.order.service.OrderWorkFlowService;
import com.alipapa.smp.order.service.SubOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class InvoiceOrderService {

    @Resource
    private InvoiceOrderMapper invoiceOrderMapper;

    @Resource
    private InvoiceProductMapper invoiceProductMapper;

    @Autowired
    private SubOrderService subOrderService;

    @Autowired
    private OrderWorkFlowService orderWorkFlowService;

    @Autowired
    private InvoiceOrderExtService invoiceOrderExtService;


    /**
     * 创建发货单
     *
     * @param invoiceOrder
     * @param subOrderList
     * @return
     */
    @Transactional
    public boolean saveInvoiceOrder(Order order, InvoiceOrder invoiceOrder, List<SubOrder> subOrderList, UserInfo userInfo) {
        InvoiceOrderExt invoiceOrderExt = new InvoiceOrderExt();
        invoiceOrderExt.setOrderNo(invoiceOrder.getOrderNo());
        invoiceOrderExt.setInvoiceOrderNo(invoiceOrder.getInvoiceNo());
        invoiceOrderExt.setSubmitTime(new Date());
        invoiceOrderExt.setCreatedTime(new Date());
        invoiceOrderExt.setUpdatedTime(new Date());

        //保存发货单流转记录
        OrderWorkFlow invoiceOrderWorkFlow = new OrderWorkFlow();
        invoiceOrderWorkFlow.setCreatedTime(new Date());
        invoiceOrderWorkFlow.setNewOrderStatus(invoiceOrder.getInvoiceStatus());
        invoiceOrderWorkFlow.setOldOrderStatus(invoiceOrder.getInvoiceStatus());
        invoiceOrderWorkFlow.setOpUserName(userInfo.getUserName());
        invoiceOrderWorkFlow.setOpUserNo(userInfo.getUserNo());
        invoiceOrderWorkFlow.setOpUserRole(userInfo.getRoleName());
        invoiceOrderWorkFlow.setOrderNo(invoiceOrder.getInvoiceNo());
        invoiceOrderWorkFlow.setType(OrderWorkFlowTypeEnum.IV_ORDER.getCodeName());
        invoiceOrderWorkFlow.setRemark("已提交发货单");
        invoiceOrderWorkFlow.setResult("发货单创建成功");
        invoiceOrderWorkFlow.setUpdatedTime(new Date());
        orderWorkFlowService.save(invoiceOrderWorkFlow);

        invoiceOrderMapper.insert(invoiceOrder);
        invoiceOrderExtService.saveInvoiceOrderExt(invoiceOrderExt);

        String remarkString = null;
        for (SubOrder subOrder : subOrderList) {
            InvoiceProduct invoiceProduct = new InvoiceProduct();
            invoiceProduct.setCreatedTime(new Date());
            invoiceProduct.setInvoiceNo(invoiceOrder.getInvoiceNo());
            invoiceProduct.setOrderNo(invoiceOrder.getOrderNo());
            invoiceProduct.setProductCategory(subOrder.getProductCategory());
            invoiceProduct.setProductCategoryId(subOrder.getProductCategoryId());
            invoiceProduct.setProductId(subOrder.getProductId());
            invoiceProduct.setProductName(subOrder.getProductName());
            invoiceProduct.setSubOrderNo(subOrder.getSubOrderNo());
            invoiceProduct.setUpdatedTime(new Date());
            invoiceProductMapper.insert(invoiceProduct);


            subOrder.setSubOrderStatus(SubOrderStatusEnum.FIN_INVOICE_APV.getCode());
            subOrderService.updateSubOrder(subOrder);

            //保存采购单流转记录
            OrderWorkFlow orderWorkFlow = new OrderWorkFlow();
            orderWorkFlow.setCreatedTime(new Date());
            orderWorkFlow.setNewOrderStatus(subOrder.getSubOrderStatus());
            orderWorkFlow.setOldOrderStatus(SubOrderStatusEnum.INVOICE_APPLY.getCode());
            orderWorkFlow.setOpUserName(userInfo.getUserName());
            orderWorkFlow.setOpUserNo(userInfo.getUserNo());
            orderWorkFlow.setOpUserRole(userInfo.getRoleName());
            orderWorkFlow.setOrderNo(subOrder.getSubOrderNo());
            orderWorkFlow.setType(OrderWorkFlowTypeEnum.SUB_ORDER.getCodeName());
            orderWorkFlow.setRemark(subOrder.getProductName() + "已提交发货单");
            orderWorkFlow.setResult("提交发货单成功");
            orderWorkFlow.setUpdatedTime(new Date());
            orderWorkFlowService.save(orderWorkFlow);

            remarkString = remarkString + subOrder.getProductName() + "、";
        }

        //保存订单流转记录
        OrderWorkFlow orderWorkFlow = new OrderWorkFlow();
        orderWorkFlow.setCreatedTime(new Date());
        orderWorkFlow.setNewOrderStatus(order.getOrderStatus());
        orderWorkFlow.setOldOrderStatus(OrderStatusEnum.DELIVERY.getCode());
        orderWorkFlow.setOpUserName(userInfo.getUserName());
        orderWorkFlow.setOpUserNo(userInfo.getUserNo());
        orderWorkFlow.setOpUserRole(userInfo.getRoleName());
        orderWorkFlow.setOrderNo(order.getOrderNo());
        orderWorkFlow.setType(OrderWorkFlowTypeEnum.M_ORDER.getCodeName());
        orderWorkFlow.setRemark(remarkString.substring(0, remarkString.length() - 1) + "已提交发货单");
        orderWorkFlow.setResult("提交发货单成功");
        orderWorkFlow.setUpdatedTime(new Date());
        orderWorkFlowService.save(orderWorkFlow);
        return true;
    }


    public boolean updateInvoiceOrder(InvoiceOrder invoiceOrder) {
        invoiceOrderMapper.updateByPrimaryKey(invoiceOrder);
        return true;
    }


    public InvoiceOrder selectInvoiceOrderByInvoiceOrderNo(String invoiceOrderNo) {
        InvoiceOrderExample example = new InvoiceOrderExample();
        InvoiceOrderExample.Criteria criteria = example.createCriteria();
        criteria.andInvoiceNoEqualTo(invoiceOrderNo);
        List<InvoiceOrder> invoiceOrderList = invoiceOrderMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(invoiceOrderList)) {
            return null;
        }
        return invoiceOrderList.get(0);
    }

}
