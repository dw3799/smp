package com.alipapa.smp;

import com.alipapa.smp.common.LoginInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@SpringBootApplication
@ImportResource("classpath*:spring/*.xml")
@EnableTransactionManagement
@EnableScheduling //允许支持schedule定时任务
public class SmpFrontierApplication extends WebMvcConfigurationSupport {

    private static Logger logger = LoggerFactory.getLogger(SmpFrontierApplication.class);

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/api/**/*").excludePathPatterns("/api/user/login/*").excludePathPatterns("/api/user/listRole/*").excludePathPatterns("/api/product/downloadPic/*");
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**");
    }

    public static void main(String[] args) {
        logger.info("start spring boot application .......");
        SpringApplication application = new SpringApplication(SmpFrontierApplication.class);
        application.run(args);
        logger.info("spring boot application has started ...........");
    }

}