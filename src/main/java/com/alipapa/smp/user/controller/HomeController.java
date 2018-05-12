package com.alipapa.smp.user.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Home Controller
 *
 * @author maibahe
 * @version 1.0
 * @date 2018-03-19
 */

@RestController
@CrossOrigin
public class HomeController {
    private static Logger logger = LoggerFactory.getLogger(HomeController.class);

    @RequestMapping("/check_active.html")
    public String check_backend_active() {
        return "I'm ok !";
    }

}
