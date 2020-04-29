package com.wickyan.proposal.controller;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.wickyan.proposal.dao.*;
import com.wickyan.proposal.entity.*;
import com.wickyan.proposal.service.DeptService;
import com.wickyan.proposal.service.ResendService;
import com.wickyan.proposal.service.TopicService;
import com.wickyan.proposal.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
    @Autowired
    private DeptService deptService;
    @Autowired
    private UserService userService;


    @GetMapping("/topic/{topicId}")
    public String topic(@PathVariable("topicId") Long topicId,
                        Model model) {
        //读取topic内容
        TopicEntity topicEntity = topicDao.selectById(topicId);
        model.addAttribute("topicEntity", topicEntity);
        //读取发布者信息
        UserEntity topicUser = userDao.selectById(topicEntity.getUserId());
        model.addAttribute("topicUser", topicUser);

        //读取部门列表
        Map<Long, String> mapOfDept = deptService.getMapOfDept();
        model.addAttribute("mapOfDept", mapOfDept);
        //阅读数增加
        topicService.inc(topicEntity);


        List<ResendEntity> resendEntities = resendService.selectResendByTopicId(topicId);
        /**判断当前用户显示：移交 or 退回
         * (仅当 没有退回 && 不是首次移交 的时候 才允许送回提案）
         * true-移交 false-送回
         */
        boolean resendOrBack = true;
        if (resendEntities.get(0).getBackUserId() == 0 && resendEntities.get(0).getResendCount() != 0) {
            resendOrBack = false;
        }
        model.addAttribute("resendOrBack", resendOrBack);
        System.out.println(resendEntities.get(0).getBackUserId() + "@@" + resendEntities.get(0).getResendCount() + "@@" + resendOrBack + "#######################");

        //设置当前所在部门ID
        Long currentDeptId = topicEntity.getDeptId();
        if (resendEntities.get(0).getBackUserId() == 0) {
            currentDeptId = resendEntities.get(0).getDeptId();
        }
        model.addAttribute("currentDeptId", currentDeptId);

        //检索移交过程
        Collections.reverse(resendEntities);
        model.addAttribute("resendEntities", resendEntities);

        //查询回复
        if (topicEntity.getReplyId() != -1) {
            ReplyEntity replyEntity = replyDao.selectById(topicEntity.getReplyId());

            model.addAttribute("replyEntity", replyEntity);
        }
        //获取Role
        Map<Integer, String> mapOfRole = userService.getMapOfRole();
        model.addAttribute("mapOfRole", mapOfRole);
        return "topic";
    }

    @Autowired
    private ReplyDao replyDao;

    @PostMapping("/topic/{topicId}/reply")
    public String replyTopic(@PathVariable("topicId") Long topicId,
                             @RequestParam(value = "replayText") String replayText,
                             Model model) {
        //读取topic内容
        if (StringUtils.isBlank(replayText)) {
            model.addAttribute("error", "回复内容不能为空");
            return "topic";
        }
        //获取当前用户
        UserEntity userEntity = (UserEntity) SecurityUtils.getSubject().getPrincipal();
        //添加回复
        ReplyEntity replyEntity = new ReplyEntity();
        replyEntity.setTopicId(topicId);
        replyEntity.setDeptId(userEntity.getDeptId());
        replyEntity.setUserId(userEntity.getUserId());
        replyEntity.setReplyTime(new Date());
        replyEntity.setReplyText(replayText);
        replyDao.insertReplyReturnLastInsertId(replyEntity);
        //更新topic
        TopicEntity topicEntity = topicDao.selectById(topicId);
        topicEntity.setReplyId(replyEntity.getReplyId());
        topicDao.updateById(topicEntity);
        //更新resend
        return "redirect:/topic/{topicId}";
    }

    @Autowired
    private ResendDao resendDao;
    @Autowired
    private ResendService resendService;

    @PostMapping("/topic/{topicId}/resend")
    public String resendTopic(@PathVariable("topicId") Long topicId,
                              @RequestParam(value = "resendReason") String resendReason,
                              @RequestParam(value = "deptId") Long deptId,
                              Model model) {
        if (StringUtils.isBlank(resendReason)) {
            model.addAttribute("error1", "移交原因不能为空");
            return this.topic(topicId, model);
        }
        if (0 == deptId) {
            model.addAttribute("error1", "移交部门不能为空");
            return this.topic(topicId, model);
        }
        //获取操作员id
        UserEntity userEntity = (UserEntity) SecurityUtils.getSubject().getPrincipal();
        System.out.println("11111111111111111111@@@@@@@@@@@@@@@");
        Long userId = userEntity.getUserId();
        //查询上一个resend_count
        List<ResendEntity> resendEntities = resendService.selectResendByTopicId(topicId);

        System.out.println("查询上一个resend_count@@@@@@@@@@@@@@@");
        int resendCount = resendEntities.get(0).getResendCount();
        //增加新的resend
        ResendEntity resendEntity = new ResendEntity();
        resendEntity.setTopicId(topicId);
        resendEntity.setDeptId(deptId);
        resendEntity.setResendCount(resendCount + 1);
        resendEntity.setUserId(userId);
        resendEntity.setResendReason(resendReason);

        resendDao.insert(resendEntity);

        // 更新topic的接受部门
        TopicEntity topicEntity = topicDao.selectById(topicId);
        topicEntity.setDeptId(deptId);
        int result = topicDao.updateById(topicEntity);
        System.out.println(1 == result ? "移交成功" : "移交失败");
        return "redirect:/topic/{topicId}";


    }

    @PostMapping("/topic/{topicId}/back")
    public String backTopic(@PathVariable("topicId") Long topicId,
                            @RequestParam(value = "resendReason") String backReason,
                            Model model) {
        //获取操作员id
        UserEntity userEntity = (UserEntity) SecurityUtils.getSubject().getPrincipal();
        //获取resend
        List<ResendEntity> resendEntities = resendService.selectResendByTopicId(topicId);
        ResendEntity resendEntity = resendEntities.get(0);


        resendEntity.setBackReason(backReason);
        resendEntity.setBackUserId(userEntity.getUserId());


        resendDao.updateById(resendEntity);

        return "redirect:/topic/{topicId}";


    }
}