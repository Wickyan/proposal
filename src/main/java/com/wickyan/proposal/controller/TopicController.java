package com.wickyan.proposal.controller;

import com.wickyan.proposal.dao.DeptDao;
import com.wickyan.proposal.dao.TopicDao;
import com.wickyan.proposal.dao.UserDao;
import com.wickyan.proposal.entity.DeptEntity;
import com.wickyan.proposal.entity.TopicEntity;
import com.wickyan.proposal.entity.UserEntity;
import com.wickyan.proposal.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Created by wickyan on 2020/3/20
 */
@Controller
public class TopicController {

    @Autowired
    private TopicDao topicDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private DeptDao deptDao;
    @Autowired
    private TopicService topicService;

    @GetMapping("/topic/{topicId}")
    public String topic(@PathVariable("topicId") Long topicId,
                        Model model) {
        //读取topic内容
        TopicEntity topicEntity = topicDao.selectById(topicId);
        model.addAttribute("topicEntity", topicEntity);
        //读取发布者信息
        UserEntity topicUser = userDao.selectById(topicEntity.getUserId());
        model.addAttribute("topicUser", topicUser);
        //读取发布者部门信息
        DeptEntity deptEntity = deptDao.selectById(topicUser.getDeptId());
        model.addAttribute("deptEntity", deptEntity);
        //阅读数增加
        topicService.inc(topicEntity);



        return "topic";
    }
}
