/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.facio;

import com.facio.aop.EmulateFilterServletAspect;
import com.facio.service.HeavyService;
import com.netflix.hystrix.contrib.javanica.aop.aspectj.HystrixCommandAspect;
import java.lang.management.ManagementFactory;
import java.net.URL;
import javax.management.MBeanServer;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.management.ManagementService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 *
 * @author fabiano
 */
@Configuration
@EnableAspectJAutoProxy
@EnableCaching
class MainConfig {
    Logger LOG = LoggerFactory.getLogger(MainConfig.class);
    
    @Bean
    public HeavyService helloWorld() {
        return new HeavyService();
    }
    
    @Bean
    public EmulateFilterServletAspect filterAspect() {
        return new EmulateFilterServletAspect();
    }
    
    @Bean
    public HystrixCommandAspect hystrixAspect() {
        return new HystrixCommandAspect();
    }
    
    @Bean
    public EhCacheCacheManager ehCacheManager() {
        URL url = getClass().getResource("/ehcache.xml");
        LOG.debug("ecache url=" + url);
        
        CacheManager cacheManager = CacheManager.newInstance(url);
        LOG.debug(" === registering MBeanServer === ");
        MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();
        ManagementService.registerMBeans(cacheManager, mBeanServer, true, true, 
                true, true);        
        return new EhCacheCacheManager(cacheManager);
    }
}
