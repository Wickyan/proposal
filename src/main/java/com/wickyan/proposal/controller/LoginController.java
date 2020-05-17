package com.wickyan.proposal.controller;

import com.wickyan.proposal.dao.UserDao;
import com.wickyan.proposal.entity.UserEntity;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * Created by wickyan on 2020/3/15
 */

@Controller
public class LoginController {

    @Autowired
    private UserDao userDao;

    @GetMapping(value = "/login")
    public String login() {
        return "login";
    }

    @PostMapping(value = "/login")    //RequestMapping + POST 可替换成一句
    public String doLogin(@RequestParam("userId") Long userId,
                          @RequestParam("password") String password,
                          @RequestParam(value = "rememberMe", defaultValue = "0") boolean rememberMe,
                          Map<String, Object> map, //HttpSession session,
                          Model model) {

        if (StringUtils.isEmpty(userId)) {
            map.put("msg", "学号或工号不能为空");
            return "login";
        }
        if (StringUtils.isEmpty(password)) {
            map.put("msg", "密码不能为空");
            return "login";
        }
        //获取当前用户
        Subject subject = SecurityUtils.getSubject();
        //封装用户登录数据
        UsernamePasswordToken token = new UsernamePasswordToken(userId.toString(), password);
       token.setRememberMe(rememberMe);


        try {
            subject.login(token);
            return "redirect:/index";        //密码正确跳转页面
        } catch (UnknownAccountException e) {//用户名不存在
            map.put("msg", "用户名不存在");
            model.addAttribute("userId", userId);//回显
            return "login";        //页面错误跳转回页面，并写入msg
        } catch (IncorrectCredentialsException e) {
            map.put("msg", "用户名或者密码错误");
            model.addAttribute("userId", userId);
            return "login";        //页面错误跳转回页面，并写入msg
        }


    }
    @RequestMapping("/noauth")
    public String unauthorized(){
        return "您没有此权限，请返回";
    }
}