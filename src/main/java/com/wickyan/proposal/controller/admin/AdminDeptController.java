package com.wickyan.proposal.controller.admin;

import com.wickyan.proposal.dao.TopicDao;
import com.wickyan.proposal.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by wickyan on 2020/4/4
 */
@Controller
public class AdminDeptController {

    @GetMapping({"/admin/department"})
    public String index(Model model) {
        model.addAttribute("adminPage", "department");

        return "admin/department";
    }
}