package com.alipapa.smp.user.controller;

import com.alipapa.smp.stock.pojo.Person;
import com.alipapa.smp.stock.pojo.StockManage;
import com.alipapa.smp.stock.service.StockManageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import java.util.Date;
import java.util.List;

/**
 * 用户管理接口
 *
 * @author liuwei
 * @version 1.0
 * 2018-02-23
 */

@Controller
public class UserController {
    private static Logger logger = LoggerFactory.getLogger(UserController.class);


}
