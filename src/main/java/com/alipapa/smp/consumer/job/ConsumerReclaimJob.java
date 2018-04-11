package com.alipapa.smp.consumer.job;

import org.springframework.scheduling.annotation.Scheduled;

public class ConsumerReclaimJob {
    
    @Scheduled(cron = "*/5 * * * * ?")
    public void test() {

    }
}
