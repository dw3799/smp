package com.alipapa.smp.order.controller;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alipapa.smp.common.Constant;
import com.alipapa.smp.common.enums.*;
import com.alipapa.smp.common.request.UserInfo;
import com.alipapa.smp.common.request.UserStatus;
import com.alipapa.smp.consumer.pojo.Consumer;
import com.alipapa.smp.consumer.pojo.SysDict;
import com.alipapa.smp.consumer.service.ConsumerService;
import com.alipapa.smp.consumer.service.SysDictService;
import com.alipapa.smp.consumer.service.UserConsumerRelationService;
import com.alipapa.smp.consumer.vo.SysDictVo;
import com.alipapa.smp.order.pojo.AgentOrderDetail;
import com.alipapa.smp.order.pojo.Order;
import com.alipapa.smp.order.pojo.SelfOrderDetail;
import com.alipapa.smp.order.pojo.SubOrder;
import com.alipapa.smp.order.service.impl.OrderServiceProxy;
import com.alipapa.smp.order.vo.ConsumerOrderCount;
import com.alipapa.smp.order.vo.ConsumerOrderVo;
import com.alipapa.smp.order.vo.OrderVo;
import com.alipapa.smp.order.vo.TailPayOrderVo;
import com.alipapa.smp.product.pojo.Product;
import com.alipapa.smp.product.pojo.ProductCategory;
import com.alipapa.smp.product.pojo.ProductPicture;
import com.alipapa.smp.product.service.ProductCategoryService;
import com.alipapa.smp.product.service.ProductPictureService;
import com.alipapa.smp.product.service.ProductService;
import com.alipapa.smp.user.pojo.Group;
import com.alipapa.smp.user.pojo.Role;
import com.alipapa.smp.user.pojo.User;
import com.alipapa.smp.user.service.GroupService;
import com.alipapa.smp.user.service.RoleService;
import com.alipapa.smp.user.service.UserService;
import com.alipapa.smp.user.vo.FuzzyUserVo;
import com.alipapa.smp.user.vo.GroupSelectVo;
import com.alipapa.smp.utils.OrderNumberGenerator;
import com.alipapa.smp.utils.PriceUtil;
import com.alipapa.smp.utils.StringUtil;
import com.alipapa.smp.utils.WebApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

import static com.alipapa.smp.utils.WebApiResponse.error;

/**
 * 订单尾款支付接口
 *
 * @author liuwei
 * @version 2.0
 * 2018-09-11
 */
@RestController
@CrossOrigin
@RequestMapping("/api/order")
public class OrderTailPayController {
    private static Logger logger = LoggerFactory.getLogger(OrderTailPayController.class);


    @Autowired
    private SysDictService sysDictService;


    @Autowired
    private UserService userService;

    @Autowired
    private GroupService groupService;

    @Autowired
    private RoleService roleService;


    @Autowired
    private ConsumerService consumerService;

    @Autowired
    private UserConsumerRelationService userConsumerRelationService;

    @Autowired
    private OrderServiceProxy orderServiceProxy;

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductCategoryService productCategoryService;

    @Autowired
    private ProductPictureService productPictureService;

    /**
     * 待支付尾款列表
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/listTailPayOrder", method = RequestMethod.GET)
    public WebApiResponse<List<TailPayOrderVo>> listTailPayOrder(@RequestParam(name = "pageSize", required = false) Integer pageSize,
                                                                 @RequestParam(name = "pageNum", required = false) Integer pageNum) {
        UserInfo userInfo = UserStatus.getUserInfo();

        if (pageSize == null) {
            pageSize = 30;
        }

        if (pageNum == null) {
            pageNum = 1;
        }

        Integer start = (pageNum - 1) * pageSize;
        Integer size = pageSize;

        List<TailPayOrderVo> orderVoList = orderServiceProxy.listTailPayOrderBySalerUserNo(userInfo.getUserNo(), start, size);
        if (!CollectionUtils.isEmpty(orderVoList)) {
            WebApiResponse response = WebApiResponse.success(orderVoList);
            response.setTotalCount(orderVoList.get(0).getTotalCount());
            return response;
        }
        return WebApiResponse.success(null);
    }


    /**
     * 获取待出纳审核尾款订单列表
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/listCasherTailPayApvOrder", method = RequestMethod.GET)
    public WebApiResponse<List<TailPayOrderVo>> listCasherTailPayApvOrder(@RequestParam(name = "pageSize", required = false) Integer pageSize,
                                                                          @RequestParam(name = "pageNum", required = false) Integer pageNum) {
        UserInfo userInfo = UserStatus.getUserInfo();
        if (!RoleEnum.admin.getCodeName().equals(userInfo.getRoleName())) {
            if (userInfo.getRoleName().equals(RoleEnum.cashier.getCodeName())) {
                return error("没有权限");
            }
        }
        if (pageSize == null) {
            pageSize = 30;
        }

        if (pageNum == null) {
            pageNum = 1;
        }

        Integer start = (pageNum - 1) * pageSize;
        Integer size = pageSize;

        List<TailPayOrderVo> orderVoList = orderServiceProxy.listCasherTailPayApvOrder(start, size);
        if (!CollectionUtils.isEmpty(orderVoList)) {
            WebApiResponse response = WebApiResponse.success(orderVoList);
            response.setTotalCount(orderVoList.get(0).getTotalCount());
            return response;
        }
        return WebApiResponse.success(null);
    }


    /**
     * 待财务审核尾款订单列表
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/listFinTailApvOrder", method = RequestMethod.GET)
    public WebApiResponse<List<TailPayOrderVo>> listFinTailApvOrder(@RequestParam(name = "pageSize", required = false) Integer pageSize,
                                                                    @RequestParam(name = "pageNum", required = false) Integer pageNum) {
        UserInfo userInfo = UserStatus.getUserInfo();
        if (!RoleEnum.admin.getCodeName().equals(userInfo.getRoleName())) {
            if (userInfo.getRoleName().equals(RoleEnum.financial.getCodeName())) {
                return error("没有权限");
            }
        }

        if (pageSize == null) {
            pageSize = 30;
        }

        if (pageNum == null) {
            pageNum = 1;
        }

        Integer start = (pageNum - 1) * pageSize;
        Integer size = pageSize;

        List<TailPayOrderVo> orderVoList = orderServiceProxy.listFinTailApvOrder(start, size);
        if (!CollectionUtils.isEmpty(orderVoList)) {
            WebApiResponse response = WebApiResponse.success(orderVoList);
            response.setTotalCount(orderVoList.get(0).getTotalCount());
            return response;
        }
        return WebApiResponse.success(null);
    }

}
