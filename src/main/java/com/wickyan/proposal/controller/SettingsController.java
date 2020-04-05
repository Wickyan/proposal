package com.wickyan.proposal.controller;

import com.wickyan.proposal.dao.DeptDao;
import com.wickyan.proposal.dao.UserDao;
import com.wickyan.proposal.entity.DeptEntity;
import com.wickyan.proposal.entity.UserEntity;
import com.wickyan.proposal.service.DeptService;
import com.wickyan.proposal.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * Created by wickyan on 2020/3/31
 */
@Controller
public class SettingsController {
    @Autowired
    private UserDao userDao;
    @Autowired
    private DeptDao deptDao;
    @Autowired
    private UserService userService;
    @Autowired
    private DeptService deptService;

    @GetMapping("/settings")
    public String personal(Model model) {
        UserEntity userEntity = (UserEntity) SecurityUtils.getSubject().getPrincipal();
        //读取部门列表
        Map<Long, String> mapOfDept = deptService.getMapOfDept();
        model.addAttribute("mapOfDept", mapOfDept);

        //获取Role
        Map<Integer, String> mapOfRole = userService.getMapOfRole();
        model.addAttribute("mapOfRole", mapOfRole);
        return "settings";
    }

    @PostMapping("/settings/{actions}")
    public String doPersonal(@PathVariable("actions") String actions,
                             @RequestParam("inputChange") String inputChange,
                             Map<String, Object> map, //HttpSession session,
                             Model model) {
        UserEntity userEntity = (UserEntity) SecurityUtils.getSubject().getPrincipal();
        if ("changeMail".equals(actions)) {
            userEntity.setMail(inputChange);
            int result = userDao.updateById(userEntity);
            System.out.println(result == 1 ? "更新邮箱成功" : "更新邮箱失败");
        } else if ("changeMobil".equals(actions)) {
            userEntity.setMobil(inputChange);
            int result = userDao.updateById(userEntity);
            System.out.println(result == 1 ? "更新手机号成功" : "更新手机号失败");
        } else if ("changePsw".equals(actions)) {
            if (inputChange.equals(userEntity.getUserPsw())) {
                model.addAttribute("pswRight", true);
                return "/settings#changePsw";
            }
            model.addAttribute("error", "密码错误");

            return "/settings#changePsw";
        }
        return "redirect:/settings";
    }
}
