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
import org.springframework.web.bind.annotation.*;

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

    @GetMapping({"/admin/user"})
    public String user(@RequestParam(name = "page", defaultValue = "1") int current,
                       @RequestParam(name = "role", defaultValue = "0") int role,
                       @RequestParam(name = "search", defaultValue = "") String search,
                       HttpSession session,
                       Model model) {

        model.addAttribute("adminPage", "user");
        //设置下拉框回显
        model.addAttribute("srole", role);

        //获取Role
        Map<Integer, String> mapOfRole = userService.getMapOfRole();
        model.addAttribute("mapOfRole", mapOfRole);

        Page<UserEntity> userEntityPage = adminEditorService.SelectEditUserPageByDesc(role, current, 5, false);
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

    @ResponseBody
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

        return result == 1 ? "更新成功" : "更新失败，稍后再试";
    }
    @ResponseBody
    @PutMapping({"/admin/user/lock/{userId}"})
    public String lockdeUser(@PathVariable("userId") Long userId,
                       Model model) {
        //读取用户信息
        UserEntity userEntity = userDao.selectById(userId);
        userEntity.setLocked(1);
        int result = userDao.updateById(userEntity);
        String out = result == 1 ? "冻结成功" : "冻结失败，请联系管理员";
        System.out.println(out);
        return out;
    }

    @GetMapping({"/admin/user/new"})
    public String userNew(Model model) {
        model.addAttribute("adminPage", "user");

        //读取部门列表
        Map<Long, String> mapOfDept = deptService.getMapOfDept();
        model.addAttribute("mapOfDept", mapOfDept);
        return "admin/user-new";
    }

    @ResponseBody
    @PostMapping({"/admin/user/new"})
    public String PostNewUser(@RequestParam("userId") Long userId,
                              @RequestParam("name") String name,
                              @RequestParam("deptId") Long deptId,
                              @RequestParam("idcardNum") String idcardNum,
                              @RequestParam("srole") int srole,
                              @RequestParam("mobil") String mobil,
                              @RequestParam("mail") String mail,
                              Model model) {
        //读取用户信息
        UserEntity userEntity = new UserEntity();
        userEntity.setUserId(userId);
        userEntity.setUserName(name);
        userEntity.setDeptId(deptId);
        userEntity.setIdcardNum(idcardNum);
        int length = idcardNum.length();
        String userPsw = idcardNum.substring(length - 6, length);
        userEntity.setUserPsw(userPsw);
        userEntity.setMobil(mobil);
        userEntity.setMail(mail);
        userEntity.setRole(srole);
        int result = userDao.insert(userEntity);
        System.out.println(result == 1 ? "用户添加成功" : "用户添加失败");

        return result == 1 ? "添加成功" : "添加失败，用户ID冲突";
    }
}