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
    TopicDao topicDao;
    @Autowired
    ProfileService ProfileService;


    @GetMapping({"/profile/{action}"})
    public String profile(@PathVariable(name = "action") String action,
                          @RequestParam(name = "page", defaultValue = "1") int current,
                          Model model,
                          HttpSession session,
                          Map<String, Object> map) {
        UserEntity userEntity = (UserEntity) session.getAttribute("userEntity");
        if (null == userEntity) {
            map.put("msg", "您还没有登录");
            return "redirect:/login";        //页面错误跳转回页面，并写入msg
        }


        if ("myTopics".equals(action)) {
            model.addAttribute("section", "myTopics");
            Page<TopicEntity> topicEntityPage =
                    ProfileService.SelectTopicPageByUserIdDesc(userEntity.getUserId(), current, 5);
            topicEntityPage.getRecords();
            model.addAttribute("topicEntityPage", topicEntityPage);
            return "profile";
        } else if ("messages".equals(action)) {

            model.addAttribute("section", "messages");
            Page<TopicEntity> topicEntityPage =
                    ProfileService.SelectTopicPageByUserIdDesc(userEntity.getUserId(), current, 5);
            topicEntityPage.getRecords();
            model.addAttribute("topicEntityPage", topicEntityPage);
            return "profile";
        }
        return "profile";
    }
}
