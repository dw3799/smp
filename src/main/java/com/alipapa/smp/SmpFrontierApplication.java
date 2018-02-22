package com.alipapa.smp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@ImportResource("classpath*:spring/*.xml")
@EnableTransactionManagement
public class SmpFrontierApplication {

    private static Logger logger = LoggerFactory.getLogger(SmpFrontierApplication.class);

    public static void main(String[] args) {
        logger.info("start spring boot application .......");
        SpringApplication application = new SpringApplication(SmpFrontierApplication.class);
        application.run(args);
        logger.info("spring boot application has started ...........");
    }

}