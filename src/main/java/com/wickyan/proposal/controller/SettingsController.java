package com.wickyan.proposal.controller;

import com.wickyan.proposal.dao.DeptDao;
import com.wickyan.proposal.dao.UserDao;
import com.wickyan.proposal.entity.DeptEntity;
import com.wickyan.proposal.entity.UserEntity;
import com.wickyan.proposal.service.DeptService;
import com.wickyan.proposal.service.IndexService;
import com.wickyan.proposal.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
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
    @Autowired
    private IndexService indexService;

    @GetMapping("/settings")
    public String personal(Model model,
                           @RequestParam(value = "error", required = false) String error) {
        model = indexService.SetMapOfDeptAndRole(model);

        model.addAttribute("error", error);

        return "settings";
    }

    @PostMapping("/settings/{actions}")
    public String doPersonal(@PathVariable("actions") String actions,
                             @RequestParam("inputChange") String inputChange,
                             Map<String, Object> map, HttpSession session,
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
            inputChange = new SimpleHash("sha1", inputChange, null, 3).toHex();
            if (inputChange.equals(userEntity.getUserPsw())) {
                session.setAttribute("psd", "密码正确");
                System.out.println("√密码正确");
                return "redirect:/getChangePsw";
            }
            System.out.println("X密码错误");
            return "redirect:/settings?error=" + "%E6%97%A7%E5%AF%86%E7%A0%81%E9%94%99%E8%AF%AF";
        }
        return "redirect:/settings";
    }

    //修改密码
    @GetMapping("/getChangePsw")
    public String changePsw(Map<String, Object> map,
                            HttpSession session,
                            Model model) {
        String psd = (String) session.getAttribute("psd");
        session.removeAttribute("psd");
        if ("密码正确".equals(psd)) {
            System.out.println("√√√密码正确");
            return "changepsw";
        }
        System.out.println("请先验证密码");
        return "redirect:/settings?error=" + "%E8%AF%B7%E5%85%88%E9%AA%8C%E8%AF%81%E5%AF%86%E7%A0%81";

    }

    @PostMapping("/postChangePsw")
    public String PostChangePsw(Map<String, Object> map,
                                Model model,
                                @RequestParam(value = "password", required = false) String password) {
        UserEntity userEntity = (UserEntity) SecurityUtils.getSubject().getPrincipal();
        password = new SimpleHash("sha1", password, null, 3).toHex();
        userEntity.setUserPsw(password);
        int result = userDao.updateById(userEntity);
        System.out.println(result == 1 ? "更新密码成功" : "更新密码失败");
        return "redirect:/settings";
    }
}
