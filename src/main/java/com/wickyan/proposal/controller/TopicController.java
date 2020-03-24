package com.wickyan.proposal.controller;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.wickyan.proposal.dao.DeptDao;
import com.wickyan.proposal.dao.ResendDao;
import com.wickyan.proposal.dao.TopicDao;
import com.wickyan.proposal.dao.UserDao;
import com.wickyan.proposal.entity.DeptEntity;
import com.wickyan.proposal.entity.ResendEntity;
import com.wickyan.proposal.entity.TopicEntity;
import com.wickyan.proposal.entity.UserEntity;
import com.wickyan.proposal.service.ResendService;
import com.wickyan.proposal.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

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
        DeptEntity deptEntity1 = deptDao.selectById(topicUser.getDeptId());
        model.addAttribute("pubDeptName", deptEntity1.getDeptName());
        //读取发布者部门信息
        DeptEntity deptEntity2 = deptDao.selectById(topicEntity.getDeptId());
        model.addAttribute("accDeptName", deptEntity2.getDeptName());

        //读取发布者部门信息
        List<DeptEntity> deptEntities = deptDao.selectList(null);
        model.addAttribute("deptEntities", deptEntities);
        //阅读数增加
        topicService.inc(topicEntity);
        return "topic";
    }

    @PostMapping("/topic/{topicId}/reply")
    public String replyTopic(@PathVariable("topicId") Long topicId,
                             @RequestParam(value = "replayText") String replayText,
                             Model model) {
        //读取topic内容
        if (StringUtils.isBlank(replayText)) {
            model.addAttribute("error", "标题不能为空");
            return "topic";
        }
        TopicEntity topicEntity = topicDao.selectById(topicId);

        System.out.println("22222222222222222@@@@@@@@@@@@@@@");
        return "topic";
    }

    @Autowired
    private ResendDao resendDao;
    @Autowired
    private ResendService resendService;

    @PostMapping("/topic/{topicId}/resend")
    public String resendTopic(@PathVariable("topicId") Long topicId,
                              @RequestParam(value = "resendReason") String resendReason,
                              @RequestParam(value = "deptId") Long deptId,
                              Model model,
                              HttpSession session) {
        if (StringUtils.isBlank(resendReason)) {
            model.addAttribute("error1", "移交原因不能为空");
            return this.topic(topicId, model);
        }
        if (0 == deptId) {
            model.addAttribute("error1", "移交部门不能为空");
            return this.topic(topicId, model);
        }
        //获取操作员id
        UserEntity userEntity = (UserEntity) session.getAttribute("userEntity");

        System.out.println("11111111111111111111@@@@@@@@@@@@@@@");
        Long userId = userEntity.getUserId();
        //查询上一个resend_count
        List<ResendEntity> resendEntities = resendService.selectResendByTopicId(topicId);

        System.out.println("222222222222222222@@@@@@@@@@@@@@@");
        int resendCount =  resendEntities.get(0).getResendCount();
        //增加新的resend
        ResendEntity resendEntity = new ResendEntity();
        resendEntity.setTopicId(topicId);
        resendEntity.setDeptId(deptId);
        resendEntity.setResendCount(resendCount);
        resendEntity.setUserId(userId);
        resendEntity.setResendReason(resendReason);

        resendDao.insert(resendEntity);

        return "redirect:/topic/{topicId}";


    }
}