/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.facio.aop;

import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * In this class we emulate a implementation of a Filter Servler.
 * Here we will put initialization of Hystrix (HystrixRequestContext) cache and MDC 
 * 
 * @author fabiano
 */
@Aspect
public class EmulateFilterServletAspect {
    Logger LOG = LoggerFactory.getLogger(EmulateFilterServletAspect.class);

    @Around("execution(* com.facio.service.*.*(..))")// the pointcut expression
    public Object anyServiceLayerMethod(ProceedingJoinPoint joinPoint) throws Throwable {
        Object result = null;
        LOG.info("calling Aspect...");
        
        LOG.debug("method called=" + joinPoint.getSignature().getName()
        + "; args=" + joinPoint.getArgs().toString());
        
        LOG.info(" ===== Initialize RequestContext =====");

        //This should be called to cache work
        HystrixRequestContext initializeContext = HystrixRequestContext.initializeContext();
        try {        
            result = joinPoint.proceed();
            LOG.debug("result in Aspect=" + result);
        } finally {
            LOG.info(" ===== Close RequestContext =====");
            if (initializeContext != null) {
                initializeContext.shutdown();
            }
            LOG.info("Aspect called.");
        }
        
        return result;        
    }
}
