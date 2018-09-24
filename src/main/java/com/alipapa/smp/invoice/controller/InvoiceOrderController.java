package com.alipapa.smp.invoice.controller;


import com.alibaba.fastjson.JSONObject;
import com.alipapa.smp.common.enums.SubOrderStatusEnum;
import com.alipapa.smp.common.request.UserInfo;
import com.alipapa.smp.common.request.UserStatus;
import com.alipapa.smp.order.controller.SubOrderController;
import com.alipapa.smp.order.service.impl.SubOrderServiceProxy;
import com.alipapa.smp.order.vo.SubOrderVo;
import com.alipapa.smp.utils.WebApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.alipapa.smp.utils.WebApiResponse.error;

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


    @Autowired
    private SubOrderServiceProxy subOrderServiceProxy;

    /**
     * 待提交发货单的采购单列表
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/listUnSubmitInvoiceSubOrder", method = RequestMethod.GET)
    public WebApiResponse<List<SubOrderVo>> listUnSubmitInvoiceSubOrder(@RequestParam(name = "pageSize", required = false) Integer pageSize,
                                                                        @RequestParam(name = "pageNum", required = false) Integer pageNum) {
        UserInfo userInfo = UserStatus.getUserInfo();

        try {
            if (pageSize == null) {
                pageSize = 30;
            }

            if (pageNum == null) {
                pageNum = 1;
            }

            Integer start = (pageNum - 1) * pageSize;
            Integer size = pageSize;

            List<JSONObject> orderVoList = subOrderServiceProxy.listSubOrderBySaler(SubOrderStatusEnum.INVOICE_APPLY, userInfo.getUserNo(), start, size);
            if (!CollectionUtils.isEmpty(orderVoList)) {
                WebApiResponse response = WebApiResponse.success(orderVoList);
                response.setTotalCount(orderVoList.get(0).getLong("totalCount"));
                return response;
            }
            return WebApiResponse.success(null);
        } catch (Exception ex) {
            logger.error("待提交发货单的采购单列表异常", ex);
            return error("待提交发货单的采购单列表异常");
        }
    }


}
