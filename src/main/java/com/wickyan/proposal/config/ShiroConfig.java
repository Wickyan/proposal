package com.wickyan.proposal.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by wickyan on 2020/3/29
 */
@Configuration
public class ShiroConfig {

    // 3 ShiroFilterFactoryBean
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("getDefaultWebSecurityManager") DefaultWebSecurityManager defaultWebSecurityManager) {
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        //设置安全管理器
        bean.setSecurityManager(defaultWebSecurityManager);
        //添加shiro的内置过滤器
        /**
         * anon: 无需认证就可访问
         * authc：必须认证才能访问
         * user：必须拥有记住我功能才能访问
         * perms: 拥有对某个资源的权限才能访问
         * role:拥有某个角色权限才能访问
         */
        Map<String, String> filterMap = new LinkedHashMap<>();
        //登录才能访问
        filterMap.put("/hello", "user");
        filterMap.put("/settings*", "user");
        filterMap.put("/settings/*", "user");
        filterMap.put("/profile/**", "user");
        //filterMap.put("/profile/**", "perms[editor]");
        filterMap.put("/publish", "user");
        filterMap.put("/reply", "user");
        filterMap.put("/back", "user");
        filterMap.put("/resend", "user");
        bean.setFilterChainDefinitionMap(filterMap);
        bean.setLoginUrl("/login");
        //设置登出
        filterMap.put("/logout", "logout");
        //设置未授权页面
        bean.setUnauthorizedUrl("/noauth");
        return bean;
    }

    // 2 DefaultWebSecurityManager
    @Bean
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //关联UserRealm
        securityManager.setRealm(userRealm);
        securityManager.setRememberMeManager(cookieRememberMeManager());
        return securityManager;
    }

    // 1 创建realm对象，自定义类
    @Bean
    public UserRealm userRealm() {
        return new UserRealm();
    }
    //整合thymeleaf
    @Bean
    public ShiroDialect getShiroDialect(){
        return new ShiroDialect();
    }

    // 记住我
    @Bean
    public CookieRememberMeManager cookieRememberMeManager() {
        CookieRememberMeManager rememberMeManager = new CookieRememberMeManager();
        SimpleCookie cookie = new SimpleCookie("rememberMe");
        cookie.setMaxAge(10 * 24 * 60 * 60);
        rememberMeManager.setCookie(cookie);
        return rememberMeManager;
    }
}