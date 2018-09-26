package com.alipapa.smp.invoice.service;

import com.alipapa.smp.common.enums.OrderStatusEnum;
import com.alipapa.smp.common.enums.OrderWorkFlowTypeEnum;
import com.alipapa.smp.common.enums.SubOrderStatusEnum;
import com.alipapa.smp.common.request.UserInfo;
import com.alipapa.smp.invoice.mapper.InvoiceOrderMapper;
import com.alipapa.smp.invoice.mapper.InvoiceProductMapper;
import com.alipapa.smp.invoice.pojo.InvoiceOrder;
import com.alipapa.smp.invoice.pojo.InvoiceProduct;
import com.alipapa.smp.order.pojo.Order;
import com.alipapa.smp.order.pojo.OrderWorkFlow;
import com.alipapa.smp.order.pojo.SubOrder;
import com.alipapa.smp.order.service.OrderWorkFlowService;
import com.alipapa.smp.order.service.SubOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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


    /**
     * 创建发货单
     *
     * @param invoiceOrder
     * @param subOrderList
     * @return
     */
    @Transactional
    public boolean saveInvoiceOrder(Order order, InvoiceOrder invoiceOrder, List<SubOrder> subOrderList, UserInfo userInfo) {
        invoiceOrderMapper.insert(invoiceOrder);

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

        //保存采购单流转记录
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


}
