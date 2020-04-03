package com.wickyan.proposal.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 提案列表
 * Created by wickyan on 2020/4/4
 */
@Controller
public class AdminTopicController {

    @GetMapping({"/admin/topic"})
    public String index(Model model) {
        model.addAttribute("adminPage", "topic");

        return "admin/topic";
    }
}