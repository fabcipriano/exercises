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
        } finally {
            LOG.debug("end interpected method=" + joinPoint.getSignature());
        }
        
        return result;
    }
    
    @Around("execution(public * com.facio.test.service.IMyDummyComponent.getValue(..))")
    public Object methodsGenericsLogging(final ProceedingJoinPoint joinPoint) throws Throwable {
        LOG.info("begin methodsGenericsLogging()");
        Object result = null;
        
        try {
            LOG.debug("begin INTERCEPTED method=" + joinPoint.getSignature());
            result = joinPoint.proceed();
        } finally {
            LOG.debug("end INTERCEPTED method=" + joinPoint.getSignature());
        }
        
        return result;
    }
}
