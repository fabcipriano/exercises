/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.facio.test.service;

import com.facio.test.annotation.Logging;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author fabiano
 */
public class MyDummyComponent implements IMyDummyComponent<String, String>{
    private static final Logger LOG = LoggerFactory.getLogger(MyDummyComponent.class);

    @Override
    @Logging
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
