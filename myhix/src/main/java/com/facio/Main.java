/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.facio;

import com.facio.service.HeavyService;
import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 *
 * @author fabiano
 */
public class Main {
    private static Logger LOG = LoggerFactory.getLogger(Main.class);
    
    public static void main(String[] args) {
        LOG.info("start main and loading application context...");
        int i = 0;
        
        ApplicationContext ctx = new AnnotationConfigApplicationContext(MainConfig.class);
        
        HeavyService bean = ctx.getBean(HeavyService.class);
        
        LOG.info(" ===== Initialize RequestContext =====");
        HystrixRequestContext initializeContext = HystrixRequestContext.initializeContext();
        try {
        
            for (i = 0; i < 10; i++) {
                LOG.info("calling hello... for the [" + i + "] time");
                String hello = bean.hello("Vai Planeta");
                LOG.debug("Say.:" + hello);
                LOG.info("hello called.");
            }
        } finally {
            LOG.info(" ===== Close RequestContext =====");
            if (initializeContext != null) {
                initializeContext.shutdown();
            }
        }
    }
    
}
