package com.wickyan.proposal.config;

import com.wickyan.proposal.dao.UserDao;
import com.wickyan.proposal.entity.UserEntity;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpSession;

/**
 * Created by wickyan on 2020/3/29
 */
public class UserRealm extends AuthorizingRealm {
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行了=>授权doGetAuthorizationInfo");

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        info.addStringPermission("user:add");
        //获取当前登录的对象
        Subject subject = SecurityUtils.getSubject();
        UserEntity userEntity = (UserEntity) subject.getPrincipal();
        String[] permission = {"", "student", "teacher", "editor"};
        info.addStringPermission(permission[userEntity.getRole()]);

        return info;
    }

    @Autowired
    private UserDao userDao;

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("执行了=>认证doGetAuthenticationInfo");
        UsernamePasswordToken userToken = (UsernamePasswordToken) token;

        //从token中取到用户名再去查用户密码
         UserEntity userEntity = userDao.selectById(Long.parseLong(userToken.getUsername()));



        if (userEntity==null){
            return null;
        }

        Subject currentSubject = SecurityUtils.getSubject();

        Session session = currentSubject.getSession();
        session.setAttribute("userEntity",userEntity);

       // HttpSession httpSession = currentSubject.get
       // httpSession.setAttribute("userEntity",userEntity);

        return new SimpleAuthenticationInfo(userEntity,userEntity.getUserPsw(),"");
    }
}
