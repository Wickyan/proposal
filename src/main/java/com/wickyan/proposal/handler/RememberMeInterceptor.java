package com.wickyan.proposal.handler;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;


/**
 * Created by wickyan on 2020/5/3
 */

public class RememberMeInterceptor implements HandlerInterceptor {

    private final Logger logger = Logger.getLogger(RememberMeInterceptor.class);



    public RememberMeInterceptor() {
        // TODO Auto-generated constructor stub
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        // 获取session中的subject
        Subject user = SecurityUtils.getSubject();

        // 判断是不是通过记住我登录
        if( !user.isAuthenticated() && user.isRemembered()) {
            logger.debug("remembered me, put user in session");
            user.getSession().setAttribute("userEntity", user.getPrincipal());
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        // TODO Auto-generated method stub
    }
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        // TODO Auto-generated method stub
    }

}