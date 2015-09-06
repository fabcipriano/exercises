/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.facio.test.service;

import com.facio.test.conf.AopConfig;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 * @author fabiano
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AopConfig.class})
public class DummyComponentTest {
    
    private static final Logger LOG = LoggerFactory.getLogger(DummyComponentTest.class);
    private IMyDummyComponent<String, String> dummy = null;
    
    public DummyComponentTest() {
    }

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Autowired
    private ApplicationContext applicationContext;

    @Before
    public void setUp() throws Exception {
        dummy = applicationContext.getBean(IMyDummyComponent.class);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getValue method, of class DummyComponent.
     */
    @Test
    public void testGetValue() {
        LOG.info("getValue");
        String key = "";
        String value = "";
        
        String expResult = "";
        
        assertNotNull(dummy);
        thrown.expect(RuntimeException.class);
        thrown.expectMessage("vai pro Fallback ...");
        
        String result = dummy.getValue(key, value);
    }

    /**
     * Test of defaultValue method, of class DummyComponent.
     */
    @Test
    public void testDefaultValue() {
        LOG.info("begin defaultValue()");
        
        assertNotNull(dummy);
        
        String result = dummy.defaultValue("chave", "valor");
        assertEquals("default", result);
    }
    
    @Test
    public void testDummyAopJointPoint() {
        LOG.info("begin testDummyAopJointPoint()");
        assertNotNull(dummy);
        
        try {
            dummy.getValue("a", "b");
        } catch(Exception e) {
            //ignore
        }        
    }
    
}
