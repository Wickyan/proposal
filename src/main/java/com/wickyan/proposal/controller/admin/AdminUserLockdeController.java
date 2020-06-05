package com.wickyan.proposal.controller.admin;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wickyan.proposal.dao.UserDao;
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
public class AdminUserLockdeController {


    @Autowired
    private DeptService deptService;

    @Autowired
    private AdminUserService adminEditorService;

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserService userService;

    @GetMapping({"/admin/user-lockde"})
    public String user(@RequestParam(name = "page", defaultValue = "1") int current,
                       @RequestParam(name = "role", defaultValue = "0") int role,
                       @RequestParam(name = "search", defaultValue = "") String search,
                       Model model, HttpSession session) {
        if(null == session.getAttribute("adminEntity")) {
            return "admin/login";
        }
        model.addAttribute("adminPage", "user-lockde");

        //设置下拉框回显
        model.addAttribute("srole", role);

        //获取Role
        Map<Integer, String> mapOfRole = userService.getMapOfRole();
        model.addAttribute("mapOfRole", mapOfRole);

        Page<UserEntity> userEntityPage = adminEditorService.SelectEditUserPageByDesc(role, current, 5, true);
        model.addAttribute("entityPage", userEntityPage);

        //读取部门列表
        Map<Long, String> mapOfDept = deptService.getMapOfDept();
        model.addAttribute("mapOfDept", mapOfDept);

        return "admin/user-locked";
    }

    @ResponseBody
    @PutMapping({"/admin/user-lockde/reback/{userId}"})
    public String user(@PathVariable("userId") Long userId,
                       Model model) {
        //读取用户信息
        UserEntity userEntity = userDao.selectById(userId);
        userEntity.setLocked(0);
        int result = userDao.updateById(userEntity);
        String out = result == 1 ? "恢复成功" : "恢复失败，请联系管理员";
        System.out.println(out);
        return out;
    }

    @ResponseBody
    @DeleteMapping({"/admin/user-lockde/del/{userId}"})
    public String del(@PathVariable("userId") Long userId,
                      Model model,
                      HttpSession session) {
        int result = userDao.deleteById(userId);
        System.out.println(result == 1 ? "用户删除成功" : "用户删除失败");
        return result == 1 ? "用户删除成功" : "用户删除失败";
    }

}