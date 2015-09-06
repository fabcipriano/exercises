/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.facio.test.conf;

import com.facio.test.aspect.LogginAspect;
import com.facio.test.service.MyDummyComponent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 *
 * @author fabiano
 */
@Configuration
@EnableAspectJAutoProxy
public class AopConfig {
    private static final Logger LOG = LoggerFactory.getLogger(AopConfig.class);

    @Bean
    public MyDummyComponent dummy() {
        LOG.debug("begin dummy()");

        MyDummyComponent result = new MyDummyComponent();
        LOG.debug("creating dummy result=" + result);
        return result;
    }
    
    @Bean
    public LogginAspect loggingAspect() {
        LOG.debug("begin loggingAspect()");
        return new LogginAspect();
    }

}
