/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.facio.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheKey;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author fabiano
 */
public class HeavyService {
    Logger LOG = LoggerFactory.getLogger(HeavyService.class);
    
    @CacheResult
    @HystrixCommand(fallbackMethod = "helloFallBack", 
            commandProperties = {
                @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000")
        })
    public String hello(@CacheKey String name) {
        String result = null;
        LOG.info("begin hello()");
        result = "Hello " + name;
        try {
            Thread.sleep(3000);
        } catch (InterruptedException ex) {
            LOG.warn("Interrupted", ex);
        }
        LOG.debug("result=" + name);
        LOG.info("end hello()");
        return result;
    }
    
    public String helloFallBack(String name) {
        LOG.info("begin helloFallBack()");
        LOG.debug("result=" + name);
        LOG.info("end helloFallBack()");

        LOG.warn("FALLBACK");
        return null;
    }
}
