/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.facio.test.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author fabiano
 */
@Aspect
public class LogginAspect {
    private Logger LOG = LoggerFactory.getLogger(LogginAspect.class);

    
    @Pointcut("@annotation(com.facio.test.annotation.Logging)")
    public void loggingAnnotationPointcut() {
    }

    @Around("loggingAnnotationPointcut()")
    public Object methodsAnnotatedWithLogging(final ProceedingJoinPoint joinPoint) throws Throwable {
        LOG.info("begin methodsAnnotatedWithLogging()");
        Object result = null;
        
        try {
            LOG.debug("begin interpected method=" + joinPoint.getSignature());
            result = joinPoint.proceed();
        } catch (Exception e) {
            LOG.debug("Exception intercpeted", e);
            throw e;
        } finally {
            LOG.debug("end interpected method=" + joinPoint.getSignature());
        }
        
        return result;
    }
    
}
