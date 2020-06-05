package com.wickyan.proposal.controller.admin;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wickyan.proposal.dao.TopicDao;
import com.wickyan.proposal.entity.TopicEntity;
import com.wickyan.proposal.entity.UserEntity;
import com.wickyan.proposal.service.DeptService;
import com.wickyan.proposal.service.TopicService;
import com.wickyan.proposal.service.admin.AdminTopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
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
    @Autowired
    private TopicDao topicDao;

    @GetMapping({"/admin/topic"})
    public String topic(@RequestParam(name = "page", defaultValue = "1") int current,
                        @RequestParam(name = "deptId", defaultValue = "0") Long deptId,
                        @RequestParam(name = "status", defaultValue = "0") int status,
                        @RequestParam(name = "search", defaultValue = "") String search,
                        Model model, HttpSession session) {
        if (null == session.getAttribute("adminEntity")) {
            return "admin/login";
        }
        model.addAttribute("adminPage", "topic");
        //部门选择回显
        model.addAttribute("deptId", deptId);
        model.addAttribute("status", status);


        Page<TopicEntity> topicEntityPage = topicService.SelectTopicPageByDesc(current, 5, false, true, deptId, status);
        topicEntityPage = adminTopicService.makeTopicListHaveRightReplayDept(topicEntityPage);

        model.addAttribute("entityPage", topicEntityPage);

        //读取部门列表
        Map<Long, String> mapOfDept = deptService.getMapOfDept();
        model.addAttribute("mapOfDept", mapOfDept);

        return "admin/topic";
    }

    /**
     * 未审核提议
     */
    @GetMapping({"/admin/topic-unaudited"})
    public String topicUnaudited(@RequestParam(name = "page", defaultValue = "1") int current,
                                 @RequestParam(name = "deptId", defaultValue = "0") Long deptId,
                                 @RequestParam(name = "status", defaultValue = "0") int status,
                                 @RequestParam(name = "search", defaultValue = "") String search,
                                 Model model, HttpSession session) {
        if (null == session.getAttribute("adminEntity")) {
            return "admin/login";
        }
        model.addAttribute("adminPage", "topic-unaudited");
        //部门选择回显
        model.addAttribute("deptId", deptId);
        model.addAttribute("status", status);


        Page<TopicEntity> topicEntityPage = topicService.SelectTopicPageByDesc(current, 5, false, false, deptId, status);
        topicEntityPage = adminTopicService.makeTopicListHaveRightReplayDept(topicEntityPage);

        model.addAttribute("entityPage", topicEntityPage);

        //读取部门列表
        Map<Long, String> mapOfDept = deptService.getMapOfDept();
        model.addAttribute("mapOfDept", mapOfDept);

        return "admin/topic-unaudited";
    }

    /**
     * 通过审核
     */
    @ResponseBody
    @PutMapping({"/admin/topic-unaudited/accept/{topicId}"})
    public String topicAccept(@PathVariable("topicId") Long topicId,
                              Model model) {
        //读取用户信息
        TopicEntity topicEntity = topicDao.selectById(topicId);
        topicEntity.setAudited(1);
        int result = topicDao.updateById(topicEntity);
        String out = result == 1 ? "审核成功" : "审核失败，请联系管理员";
        System.out.println(out);
        return out;
    }

    /**
     * 冻结提议
     */
    @ResponseBody
    @PutMapping({"/admin/topic/lock/{topicId}"})
    public String topicLock(@PathVariable("topicId") Long topicId,
                            Model model) {
        //读取用户信息
        TopicEntity topicEntity = topicDao.selectById(topicId);
        topicEntity.setLocked(1);
        int result = topicDao.updateById(topicEntity);
        String out = result == 1 ? "冻结成功" : "冻结失败，请联系管理员";
        System.out.println(out);
        return out;
    }

    /**
     * 已冻结提议
     */
    @GetMapping({"/admin/topic-locked"})
    public String topicLocked(@RequestParam(name = "page", defaultValue = "1") int current,
                              @RequestParam(name = "deptId", defaultValue = "0") Long deptId,
                              @RequestParam(name = "status", defaultValue = "0") int status,
                              @RequestParam(name = "search", defaultValue = "") String search,
                              Model model) {
        model.addAttribute("adminPage", "topic-locked");
        //部门选择回显
        model.addAttribute("deptId", deptId);
        model.addAttribute("status", status);


        Page<TopicEntity> topicEntityPage = topicService.SelectTopicPageByDesc(current, 5, true, true, deptId, status);
        topicEntityPage = adminTopicService.makeTopicListHaveRightReplayDept(topicEntityPage);

        model.addAttribute("entityPage", topicEntityPage);

        //读取部门列表
        Map<Long, String> mapOfDept = deptService.getMapOfDept();
        model.addAttribute("mapOfDept", mapOfDept);

        return "admin/topic-locked";
    }

    /**
     * 恢复提议
     */
    @ResponseBody
    @PutMapping({"/admin/topic-locked/back/{topicId}"})
    public String topicBack(@PathVariable("topicId") Long topicId,
                            Model model) {
        //读取用户信息
        TopicEntity topicEntity = topicDao.selectById(topicId);
        topicEntity.setLocked(0);
        int result = topicDao.updateById(topicEntity);
        String out = result == 1 ? "恢复成功" : "恢复失败，请联系管理员";
        System.out.println(out);
        return out;
    }

    /**
     * 删除提议
     */
    @ResponseBody
    @DeleteMapping({"/admin/topic-locked/del/{topicId}"})
    public String topicDel(@PathVariable("topicId") Long topicId,
                           Model model) {

        int result = topicDao.deleteById(topicId);
        String out = result == 1 ? "删除成功" : "删除失败，请联系管理员";
        System.out.println(out);
        return out;
    }
}