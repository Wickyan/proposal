package com.wickyan.proposal.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 用户管理
 * Created by wickyan on 2020/4/4
 */
@Controller
public class AdminUserController {

    @GetMapping({"/admin/user"})
    public String index(Model model) {
        model.addAttribute("adminPage", "user");

        return "admin/user";
    }
}