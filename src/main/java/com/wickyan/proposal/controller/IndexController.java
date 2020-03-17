package com.wickyan.proposal.controller;

import com.wickyan.proposal.dao.TopicDao;
import com.wickyan.proposal.entity.TopicEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by wickyan on 2020/3/14
 */
@Controller
public class IndexController {

    @Autowired
    TopicDao topicDao;

    @RequestMapping({"/", "/index"})
    public String index(Model model) {
        List<TopicEntity> topicEntities = topicDao.selectList(null);
        model.addAttribute("qusetions", topicEntities);
        return "index";
    }
}
