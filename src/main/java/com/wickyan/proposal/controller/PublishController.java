package com.wickyan.proposal.controller;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.wickyan.proposal.dao.DeptDao;
import com.wickyan.proposal.dao.TopicDao;
import com.wickyan.proposal.entity.TopicEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by wickyan on 2020/3/14
 */
@Controller
public class PublishController {

    @Autowired
    private TopicDao topicDao;
    @Autowired
    private DeptDao deptDao;


    @GetMapping("/new")
    public String getnew() {
        return "new";
    }
    @GetMapping("/publish")
    public String topic(Model model) {
        return "publish";
    }
    @PostMapping("/publish")
    public String pushTopic(
            @RequestParam(value = "topicTitle", required = false) String topicTitle,
            @RequestParam(value = "topicText", required = false) String topicText,
            @RequestParam(value = "deptId", required = false) Long deptId,

            Model model) {
        TopicEntity topicEntity = new TopicEntity();
        topicEntity.setTopicTitle(topicTitle);
        topicEntity.setTopicText(topicText);
        topicEntity.setDeptId(deptId);
        System.out.println(topicEntity);


        model.addAttribute("topicTitle", topicTitle);
        model.addAttribute("topicText", topicText);
        model.addAttribute("deptId", deptId);
        if (StringUtils.isBlank(topicTitle)) {
            model.addAttribute("error", "标题不能为空");
            return "publish";
        }
        if (StringUtils.isBlank(topicText)) {
            model.addAttribute("error", "问题描述不能为空");
            return "publish";
        }
        if (0 == deptId) {
            model.addAttribute("error", "部门不能为空");
            return "publish";
        }


        topicDao.insert(topicEntity);
        return "publish";
    }

    @RequestMapping("/hello")
    public String hello() {
        return "hello";
    }
}
