package com.wickyan.proposal.controller.admin;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wickyan.proposal.dao.TopicDao;
import com.wickyan.proposal.dto.ChartTopicDto;
import com.wickyan.proposal.entity.TopicEntity;
import com.wickyan.proposal.service.IndexService;
import com.wickyan.proposal.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by wickyan on 2020/4/2
 */
@Controller
public class AdminIndexController {

    @Autowired
    TopicDao topicDao;
    @Autowired
    IndexService indexService;
    @Autowired
    TopicService topicService;

    @GetMapping({"/admin", "/admin/index"})
    public String index(Model model, HttpSession session) {
        if(null == session.getAttribute("adminEntity")) {
            return "/admin/login";
        }
        model.addAttribute("adminPage", "index");
        return "admin/index";
    }

    @ResponseBody
    @GetMapping({"/admin/index/getChartTopicDtos"})
    public String chartTopicDtos(Model model) {
        List<ChartTopicDto> chartTopicDtos = topicDao.countOfTopicDept();
        return JSON.toJSONString(chartTopicDtos);
    }

    @ResponseBody
    @GetMapping({"/admin/index/getChartReplyTopicDtos"})
    public String chartTopicReplyDtos(Model model) {
        List<ChartTopicDto> chartTopicDtos = topicService.getReplyReat();
        String ss = JSON.toJSONString(chartTopicDtos,SerializerFeature.BeanToArray);
        System.out.println(ss);
        return ss;
    }
}