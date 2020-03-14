package com.wickyan.proposal.controller;

import com.wickyan.proposal.dao.UserDao;
import com.wickyan.proposal.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
                          Map<String, Object> map, HttpSession session,
                          Model model) {
        if (StringUtils.isEmpty(userId)) {
            map.put("msg", "学号或工号不能为空");
            return "login";
        }
        if (StringUtils.isEmpty(password)) {
            map.put("msg", "密码不能为空");
            return "login";
        }
        UserEntity userEntity = userDao.selectById(userId);
        System.out.println(userEntity);

        if (null == userEntity) {
            map.put("msg", "用户名不存在");
            model.addAttribute("userId", userId);
            return "login";        //页面错误跳转回页面，并写入msg
        } else if (userEntity.getUserPsw().equals(password)) {
            session.setAttribute("userEntity", userEntity);
            return "redirect:/index";        //密码正确跳转页面
        } else {
            map.put("msg", "用户名或者密码错误");
            model.addAttribute("userId", userId);
            return "login";        //页面错误跳转回页面，并写入msg
        }
    }
}