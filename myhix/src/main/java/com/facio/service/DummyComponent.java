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
public class DummyComponent implements IDummyComponent <String, String> {
    
    private static final Logger LOG = LoggerFactory.getLogger(DummyComponent.class);

    @Override
    @HystrixCommand(fallbackMethod = "defaultValue")
    public String getValue(String key, String value) {
        LOG.info("begin getValue()");
        if(true)
            throw new RuntimeException("vai pro Fallback ...");
        return "value";
    }

    @Override
    public String defaultValue(String key, String value) {
        LOG.info("begin defaultValue()");
        return "default";
    }
    
}
