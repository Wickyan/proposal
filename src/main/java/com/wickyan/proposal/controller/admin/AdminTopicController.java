package com.wickyan.proposal.controller.admin;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wickyan.proposal.entity.TopicEntity;
import com.wickyan.proposal.entity.UserEntity;
import com.wickyan.proposal.service.DeptService;
import com.wickyan.proposal.service.TopicService;
import com.wickyan.proposal.service.admin.AdminTopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * 提案列表
 * Created by wickyan on 2020/4/4
 */
@Controller
public class AdminTopicController {

    @Autowired
    private TopicService topicService;

    @Autowired
    private DeptService deptService;
    @Autowired
    private AdminTopicService adminTopicService;

    @GetMapping({"/admin/topic"})
    public String index(@RequestParam(name = "page", defaultValue = "1") int current,
                        @RequestParam(name = "deptId", defaultValue = "0") int deptId,
                        @RequestParam(name = "status", defaultValue = "0") int status,
                        @RequestParam(name = "search", defaultValue = "") String search,
                        Model model) {
        model.addAttribute("adminPage", "topic");
        //部门选择回显
        model.addAttribute("deptId", deptId);
        model.addAttribute("status", status);


        Page<TopicEntity> topicEntityPage = topicService.SelectTopicPageByDesc(current, 5, false, deptId, status);
        topicEntityPage = adminTopicService.makeTopicListHaveRightReplayDept(topicEntityPage);

        model.addAttribute("entityPage", topicEntityPage);

        //读取部门列表
        Map<Long, String> mapOfDept = deptService.getMapOfDept();
        model.addAttribute("mapOfDept", mapOfDept);

        return "admin/topic";
    }
}