package com.alipapa.smp.stock.controller;

import com.alipapa.smp.stock.mapper.StockManageMapper;
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
 * 库存管理接口
 *
 * @author liuwei
 * @version 1.0
 */

@Controller
public class StockController {
    private static Logger logger = LoggerFactory.getLogger(StockController.class);

    @Autowired
    private StockManageService stockManageService;


    @RequestMapping(value = {"/stock", "/index", "/"})
    public String stock(Model model, @RequestParam(value = "type", required = false) String type, @RequestParam(value = "shelfno", required = false) String shelfno) {
        logger.info("stock: stock");
        logger.info("stock: type=" + type + " shelfno=" + shelfno);

        Person single = new Person("WelCome Brothers&Sisters!", 20);
        if (type == null && shelfno == null) {
            List<StockManage> stockManageList = stockManageService.selectAll();
            model.addAttribute("stockList", stockManageList);
        } else {
            List<StockManage> stockManageList = stockManageService.selectByparams(type, shelfno);
            model.addAttribute("stockList", stockManageList);
        }

        model.addAttribute("singlePerson", single);

        return "stockManage";
    }

    @RequestMapping(value = "/search", produces = {MediaType.APPLICATION_JSON_VALUE})
    public String search(Model model, @RequestParam(value = "type", required = false) String type, @RequestParam(value = "shelfno", required = false) String shelfno) {
        Person single = new Person("WelCome Brothers&Sisters!", 20);
        logger.info("search: type=" + type + " shelfno=" + shelfno);

        model.addAttribute("code", "ok");

        if (type == null) {
            type = "";
        }
        if (shelfno == null) {
            shelfno = "";
        }
        model.addAttribute("url", "/stock.do?type=" + type + "&shelfno=" + shelfno);

        return "jsonView";
    }


    @RequestMapping(value = "/create", produces = {MediaType.APPLICATION_JSON_VALUE})
    public String createStock(Model model, @RequestParam("type") String type, @RequestParam("colour") String colour, @RequestParam("qty") Integer qty, @RequestParam("shelfno") String shelfno, @RequestParam("remark") String remark) {
        logger.info("createStock: type=" + type + " colour=" + colour + " qty=" + qty + " shelfno=" + shelfno + " remark=" + remark);
        StockManage stockManage = new StockManage();
        stockManage.setType(type);
        stockManage.setColour(colour);
        stockManage.setQty(qty);
        stockManage.setShelfno(shelfno);
        stockManage.setRemark(remark);
        stockManage.setCreatedTime(new Date());
        stockManage.setUpdatedTime(new Date());
        stockManageService.save(stockManage);
        model.addAttribute("code", "ok");
        model.addAttribute("url", "/stock.do");

        return "jsonView";
    }


    @RequestMapping(value = "/update", produces = {MediaType.APPLICATION_JSON_VALUE})
    public String updateStock(Model model, @RequestParam("id") Long id, @RequestParam("type") String type, @RequestParam("colour") String colour, @RequestParam("qty") Integer qty, @RequestParam("shelfno") String shelfno, @RequestParam("remark") String remark) {
        logger.info("updateStock: type=" + type + " colour=" + colour + " qty=" + qty + " shelfno=" + shelfno + " remark=" + remark);
        StockManage stockManage = stockManageService.selectById(id);

        if (stockManage == null) {
            model.addAttribute("code", "error");
            return "jsonView";
        }

        stockManage.setQty(qty);
        stockManage.setRemark(remark);

        stockManageService.updateByPrimaryKey(stockManage);
        model.addAttribute("code", "ok");
        return "jsonView";
    }


    @RequestMapping(value = "/delete", produces = {MediaType.APPLICATION_JSON_VALUE})
    public String deleteDtock(Model model, @RequestParam("id") String id) {
        logger.info("deleteDtock:id=" + id);
        stockManageService.deleteByPrimaryKey(Long.valueOf(id));
        model.addAttribute("code", "ok");
        return "jsonView";
    }

    @RequestMapping(value = "/json", produces = {MediaType.APPLICATION_JSON_VALUE})
    public String json(Model model, @RequestParam("id") String id) {
        StockManage stockManage = stockManageService.selectById(Long.valueOf(id));
        if (stockManage != null) {
            model.addAttribute("single", stockManage);
        }

        return "jsonView";
    }

    @Bean
    public MappingJackson2JsonView jsonView() {
        MappingJackson2JsonView jsonView = new MappingJackson2JsonView();
        return jsonView;
    }
}
