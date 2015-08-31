/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.facio.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author fabiano
 */
public class HeavyService {
    Logger LOG = LoggerFactory.getLogger(HeavyService.class);
    
    @HystrixCommand
    public String hello(String name) {
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
}
