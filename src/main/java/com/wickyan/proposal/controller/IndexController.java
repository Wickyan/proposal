package com.wickyan.proposal.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wickyan.proposal.dao.TopicDao;
import com.wickyan.proposal.entity.TopicEntity;
import com.wickyan.proposal.entity.UserEntity;
import com.wickyan.proposal.service.IndexService;
import com.wickyan.proposal.service.TopicService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by wickyan on 2020/3/14
 */
@Controller
public class IndexController {

    @Autowired
    TopicDao topicDao;
    @Autowired
    TopicService topicService;
    @Autowired
    private IndexService indexService;

    @GetMapping({"/", "/index"})
    public String index(@RequestParam(name = "page", defaultValue = "1") int current,
                        Model model) {

        UserEntity userEntity = (UserEntity) SecurityUtils.getSubject().getPrincipal();
        // 查询待处理数量
        if (null != userEntity) {
            int countOfUntreated = topicService.selectCountOfUntreated(userEntity.getDeptId());
            model.addAttribute("countOfUntreated", countOfUntreated);
        }

        model = indexService.SetMapOfDeptAndRole(model);
        Page<TopicEntity> topicEntityPage = topicService.SelectTopicPageByDesc(current, 5, false);
        topicEntityPage = topicService.getTextFromTopicEntity(topicEntityPage);
        //topicEntityPage.getRecords()
        model.addAttribute("entityPage", topicEntityPage);
        return "index";
    }
}
