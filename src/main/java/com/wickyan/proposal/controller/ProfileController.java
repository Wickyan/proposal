//package com.wickyan.proposal.controller;
//
//import life.majiang.community.dto.PaginationDTO;
//import life.majiang.community.model.User;
//import life.majiang.community.service.NotificationService;
//import life.majiang.community.service.QuestionService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestParam;
//
//import javax.servlet.http.HttpServletRequest;
//
///**
// * Created by codedrinker on 2019/5/15.
// */
//@Controller
//public class ProfileController {
//    @Autowired
//    private QuestionService questionService;
//    @Autowired
//    private NotificationService notificationService;
//
//    @GetMapping("/profile/{action}")
//    public String profile(HttpServletRequest request,
//                          @PathVariable(name = "action") String action,
//                          Model model,
//                          @RequestParam(name = "page", defaultValue = "1") Integer page,
//                          @RequestParam(name = "size", defaultValue = "5") Integer size) {
//
//        User user = (User) request.getSession().getAttribute("user");
//        if (user == null) {
//            return "redirect:/";
//        }
//
//        if ("questions".equals(action)) {
//            model.addAttribute("section", "questions");
//            model.addAttribute("sectionName", "我的提问");
//            PaginationDTO paginationDTO = questionService.list(user.getId(), page, size);
//            model.addAttribute("pagination", paginationDTO);
//        } else if ("replies".equals(action)) {
//            PaginationDTO paginationDTO = notificationService.list(user.getId(), page, size);
//            model.addAttribute("section", "replies");
//            model.addAttribute("pagination", paginationDTO);
//            model.addAttribute("sectionName", "最新回复");
//        }
//        return "profile";
//    }
//}
package com.wickyan.proposal.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wickyan.proposal.dao.TopicDao;
import com.wickyan.proposal.entity.TopicEntity;
import com.wickyan.proposal.entity.UserEntity;
import com.wickyan.proposal.service.IndexService;
import com.wickyan.proposal.service.ProfileService;
import com.wickyan.proposal.service.TopicService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * Created by wickyan on 2020/3/14
 */
@Controller
public class ProfileController {


    @Autowired
    private TopicDao topicDao;
    @Autowired
    private TopicService topicService;
    @Autowired
    private IndexService indexService;


    @GetMapping({"/profile/{action}"})
    public String profile(@PathVariable(name = "action") String action,
                          @RequestParam(name = "page", defaultValue = "1") int current,
                          Model model,
                          Map<String, Object> map) {

        UserEntity userEntity = (UserEntity) SecurityUtils.getSubject().getPrincipal();
        // 查询待处理数量
        int countOfUntreated = topicService.selectCountOfUntreated(userEntity.getDeptId());
        model.addAttribute("countOfUntreated", countOfUntreated);

        model = indexService.SetMapOfDeptAndRole(model);
        //显示选中样式
        model.addAttribute("section", action);
        Page<TopicEntity> topicEntityPage = null;

        if ("myTopics".equals(action)) {
            model.addAttribute("sectionName", "我的提议");
            topicEntityPage = topicService.SelectTopicPageByUser(current, 5, userEntity.getUserId(), 0);
        } else if ("messages".equals(action)) {
            model.addAttribute("sectionName", "最新回复");
            topicEntityPage = topicService.SelectTopicPageByUser(current, 5, userEntity.getUserId(), 1);
        } else if ("untreated".equals(action)) {
            model.addAttribute("sectionName", "待处理");
            topicEntityPage = topicService.SelectTopicPageByDept(current, 5, userEntity.getDeptId(), 2);
        } else if ("treated".equals(action)) {
            model.addAttribute("sectionName", "已回复");
            topicEntityPage = topicService.SelectTopicPageByDept(current, 5, userEntity.getDeptId(), 1);
        }else {
            return "redirect:/index";
        }
        model.addAttribute("entityPage", topicEntityPage);
        return "profile";
    }
}
