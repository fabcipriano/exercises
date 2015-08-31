/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.facio;

import com.facio.service.HeavyService;
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
        
        ApplicationContext ctx = new AnnotationConfigApplicationContext(MainConfig.class);
        
        HeavyService bean = ctx.getBean(HeavyService.class);
        
        LOG.info("calling hello...");
        bean.hello("Vai Planeta");
        LOG.info("hello called.");
    }
    
}
