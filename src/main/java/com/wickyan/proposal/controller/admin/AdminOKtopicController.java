package com.wickyan.proposal.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 已完成提案
 * Created by wickyan on 2020/4/4
 */
@Controller
public class AdminOKtopicController {

    @GetMapping({"/admin/OKtopic"})
    public String index(Model model) {
        model.addAttribute("adminPage", "OKtopic");

        return "admin/OKtopic";
    }
}