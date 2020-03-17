package com.wickyan.proposal.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wickyan.proposal.dao.TopicDao;
import com.wickyan.proposal.entity.TopicEntity;
import com.wickyan.proposal.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by wickyan on 2020/3/14
 */
@Controller
public class IndexController {

    @Autowired
    TopicDao topicDao;
    @Autowired
    IndexService indexService;

    @GetMapping({"/", "/index"})
    public String index(@RequestParam(name = "page", defaultValue = "1") int current,
                        Model model) {
        Page<TopicEntity> topicEntityPage = indexService.SelectTopicPageByDesc(current, 5);
        //topicEntityPage.getRecords()
        model.addAttribute("topicEntityPage", topicEntityPage);
        return "index";
    }
}
