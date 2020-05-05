package com.wickyan.proposal.controller.admin;

import com.wickyan.proposal.dao.UserDao;
import com.wickyan.proposal.entity.AdminEntity;
import com.wickyan.proposal.service.admin.AdminService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.SimpleHash;
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
public class AdminLoginController {

    @Autowired
    private UserDao userDao;
    @Autowired
    private AdminService adminService;

    @GetMapping(value = "/admin/login")
    public String login() {
        return "admin/login";
    }

    @PostMapping(value = "/admin/login")    //RequestMapping + POST 可替换成一句
    public String doLogin(@RequestParam("userName") String userName,
                          @RequestParam("password") String password,
                          Map<String, Object> map, //HttpSession session,
                          Model model,
                          HttpSession session) {
        password = new SimpleHash("sha1", password, null, 3).toHex();

        if (StringUtils.isEmpty(userName)) {
            map.put("msg", "用户名不能为空");
            return "admin/login";
        }
        if (StringUtils.isEmpty(password)) {
            map.put("msg", "密码不能为空");
            return "admin/login";
        }


        AdminEntity adminEntity = adminService.selectOneByAdminName(userName);
        if (null == adminEntity) {
            map.put("msg", "用户名不存在");
            return "/admin/login";  //页面错误跳转回页面，并写入msg
        }
        if (adminEntity.getAdminPsw().equals(password)) {
            session.setAttribute("adminEntity", adminEntity);
            adminService.refreshUser(adminEntity.getAdminName());
            return "redirect:/admin";            // 登录成功
        }
        map.put("msg", "用户名或者密码错误");
        model.addAttribute("userName", userName);
        return "/admin/login";        //页面错误跳转回页面，并写入msg


    }
    @GetMapping(value = "/admin/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("adminEntity");
        SecurityUtils.getSubject().getSession().removeAttribute("userEntity");
        return "redirect:/index";
    }

}