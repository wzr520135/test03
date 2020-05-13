package com.test03.config;

import lombok.Builder;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.cache.MemoryConstrainedCacheManager;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;

/**
 * @Auther wise wu
 * @Date 2020/5/12 15:30
 * @Description
 */
@Configuration
public class SpringShiroConfig {

    @Bean
    public SessionManager sessionManager() {
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        return sessionManager;
    }

    //shiro缓存
    @Bean
    public CacheManager shiroCacheMananger() {
        return new MemoryConstrainedCacheManager();
    }


    // 配置SecurityManager对象(shiro核心安全管理器对象)
    @Bean
    public SecurityManager securityManager(Realm realm, SessionManager sessionManager, CacheManager shiroCacheManager) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm( realm);
        securityManager.setCacheManager(shiroCacheManager);
        securityManager.setSessionManager(sessionManager);
        return securityManager;

    }
    /**
     * 配置ShiroFilterFactoryBean对象，通过此对象
     * 创建过滤器工厂，并指定过滤规则
     * @param securityManager
     * @return
     */
      @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {

        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();

        shiroFilterFactoryBean.setSecurityManager(securityManager);
        shiroFilterFactoryBean.setLoginUrl("/dologin");
        LinkedHashMap<String, String> map = new LinkedHashMap<>();

        map.put("/bower_components/**", "anon");
          map.put("/build/**", "anon");//anno
          map.put("/dist/**", "anon");
          map.put("/plugins/**", "anon");
          map.put("/js/**", "anon");
        map.put("/doLogout", "logout");
        map.put("/index", "anon");
        map.put("/user/doLogin", "anon");
        map.put("/**", "authc");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
           return shiroFilterFactoryBean;
    }

    // spring 管理次对象生命周期
   @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor(){
        return  new LifecycleBeanPostProcessor();
    }
    //置Advisor对象,此对象中会对切入点,通知等对象进行
    //	 *    相关描述,后续DefaultAdvisorAutoProxyCreator对象
    //	 *    会基于描述,为目标对象创建代理对象
    @Bean
    public AuthorizationAttributeSourceAdvisor
    authorizationAttributeSourceAdvisor(
            SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor advisor=
                new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager);
        return advisor;
    }

}
