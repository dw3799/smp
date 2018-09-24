package com.alipapa.smp.invoice.controller;


import com.alipapa.smp.order.controller.SubOrderController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 发货单接口
 *
 * @author liuwei
 * @version 2.0
 * 2018-09-25
 */
@RestController
@CrossOrigin
@RequestMapping("/api/invoice")
public class InvoiceOrderController {
    private static Logger logger = LoggerFactory.getLogger(InvoiceOrderController.class);


}
