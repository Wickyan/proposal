package com.wickyan.proposal.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 操作人管理
 * Created by wickyan on 2020/4/4
 */
@Controller
public class AdminEditorController {

    @GetMapping({"/admin/editor"})
    public String index(Model model) {
        model.addAttribute("adminPage", "editor");

        return "admin/editor";
    }
}