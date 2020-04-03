package com.wickyan.proposal.controller.admin;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wickyan.proposal.dao.TopicDao;
import com.wickyan.proposal.entity.TopicEntity;
import com.wickyan.proposal.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by wickyan on 2020/4/2
 */
@Controller
public class AdminIndexController {

    @Autowired
    TopicDao topicDao;
    @Autowired
    IndexService indexService;

    @GetMapping({"/admin", "/admin/index"})
    public String index(Model model) {
        model.addAttribute("adminPage", "index");

        return "admin/index";
    }
}