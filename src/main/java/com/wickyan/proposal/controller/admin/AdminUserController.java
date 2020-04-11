package com.wickyan.proposal.controller.admin;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wickyan.proposal.dao.UserDao;
import com.wickyan.proposal.entity.DeptEntity;
import com.wickyan.proposal.entity.UserEntity;
import com.wickyan.proposal.service.DeptService;
import com.wickyan.proposal.service.UserService;
import com.wickyan.proposal.service.admin.AdminUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * 用户管理
 * Created by wickyan on 2020/4/4
 */
@Controller
public class AdminUserController {


    @Autowired
    private DeptService deptService;

    @Autowired
    private AdminUserService adminEditorService;

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserService userService;

    @GetMapping({"/admin/user/{role}"})
    public String user(@RequestParam(name = "page", defaultValue = "1") int current,
                       @PathVariable(name = "role") int role,
                       HttpSession session,
                       Model model) {

        //设置返回页面
        session.setAttribute("roleOfReturn", role);
        model.addAttribute("adminPage", "user");
        model.addAttribute("srole", role);

        //获取Role
        Map<Integer, String> mapOfRole = userService.getMapOfRole();
        model.addAttribute("mapOfRole", mapOfRole);

        Page<UserEntity> userEntityPage = adminEditorService.SelectEditUserPageByDesc(role, current, 5);
        model.addAttribute("entityPage", userEntityPage);

        //读取部门列表
        Map<Long, String> mapOfDept = deptService.getMapOfDept();
        model.addAttribute("mapOfDept", mapOfDept);

        return "admin/user";
    }

    @GetMapping({"/admin/user/edit/{userId}"})
    public String user(@PathVariable("userId") Long userId,
                       Model model) {
        model.addAttribute("adminPage", "user");

        //读取用户信息
        UserEntity userEntity = userDao.selectById(userId);
        model.addAttribute("userEntity", userEntity);
        //读取部门列表
        Map<Long, String> mapOfDept = deptService.getMapOfDept();
        model.addAttribute("mapOfDept", mapOfDept);

        return "admin/user-edit";
    }

    @PostMapping({"/admin/user/edit/{userId}"})
    public String PostUser(@PathVariable("userId") Long userId,
                           @RequestParam("name") String name,
                           @RequestParam("deptId") Long deptId,
                           @RequestParam("srole") int srole,
                           @RequestParam("mobil") String mobil,
                           @RequestParam("mail") String mail,
                           Model model) {
        //读取用户信息
        UserEntity userEntity = new UserEntity();
        userEntity.setUserId(userId);
        userEntity.setUserName(name);
        userEntity.setDeptId(deptId);
        userEntity.setMobil(mobil);
        userEntity.setMail(mail);
        userEntity.setRole(srole);
        int result = userDao.updateById(userEntity);
        System.out.println(result == 1 ? "用户更新成功" : "用户更新失败");

        return "redirect:/admin/user/edit/" + userId;
    }

    @GetMapping({"/admin/user/del/{userId}"})
    public String del(@PathVariable("userId") Long userId,
                      Model model,
                      HttpSession session) {
        int result = userDao.deleteById(userId);
        System.out.println(result == 1 ? "用户删除成功" : "用户删除失败");
        return "admin/user";
    }

}