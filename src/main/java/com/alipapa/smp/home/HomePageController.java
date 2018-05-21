package com.alipapa.smp.home;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


@RestController
public class HomePageController {

    @RequestMapping("/index")
    public ModelAndView homePage() {
        ModelAndView mv = new ModelAndView("html/index");
        return mv;
    }
}
