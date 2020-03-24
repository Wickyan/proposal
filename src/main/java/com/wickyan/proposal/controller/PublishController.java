package com.wickyan.proposal.controller;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.wickyan.proposal.dao.DeptDao;
import com.wickyan.proposal.dao.ResendDao;
import com.wickyan.proposal.dao.TopicDao;
import com.wickyan.proposal.entity.DeptEntity;
import com.wickyan.proposal.entity.ResendEntity;
import com.wickyan.proposal.entity.TopicEntity;
import com.wickyan.proposal.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * Created by wickyan on 2020/3/14
 */
@Controller
public class PublishController {

    @Autowired
    private TopicDao topicDao;
    @Autowired
    private DeptDao deptDao;
    @Autowired
    private ResendDao resendDao;

    @GetMapping("/new")
    public String getnew() {
        return "new";
    }
    @GetMapping("/publish")
    public String publish(Model model,
                        HttpSession session,
                        Map<String, Object> map) {
        Object userEntity = session.getAttribute("userEntity");
        if (null == userEntity) {
            map.put("msg", "您还没有登录");
            return "login";        //页面错误跳转回页面，并写入msg
        }
        List<DeptEntity> deptEntities = deptDao.selectList(null);
        model.addAttribute("deptEntities", deptEntities);
        return "publish";
    }


    @PostMapping("/publish")
    public String doPublish(
            @RequestParam(value = "topicTitle", required = false) String topicTitle,
            @RequestParam(value = "topicText", required = false) String topicText,
            @RequestParam(value = "deptId", required = false) Long deptId,
            Model model,
            Map<String, Object> map,
            HttpSession session) {
        UserEntity userEntity = (UserEntity)session.getAttribute("userEntity");
        if (null == userEntity) {
            map.put("msg", "您还没有登录");
            return "login";        //页面错误跳转回页面，并写入msg
        }
        TopicEntity topicEntity = new TopicEntity();
        topicEntity.setTopicTitle(topicTitle);
        topicEntity.setTopicText(topicText);
        topicEntity.setDeptId(deptId);
        topicEntity.setUserId(userEntity.getUserId());
        System.out.println(topicEntity);

        List<DeptEntity> deptEntities = deptDao.selectList(null);
        model.addAttribute("deptEntities", deptEntities);

        model.addAttribute("topicTitle", topicTitle);
        model.addAttribute("topicText", topicText);
        model.addAttribute("deptId", deptId);


        if (StringUtils.isBlank(topicTitle)) {
            model.addAttribute("error", "标题不能为空");
            return "publish";
        }
        if (StringUtils.isBlank(topicText)) {
            model.addAttribute("error", "问题描述不能为空");
            return "publish";
        }
        if (0 == deptId) {
            model.addAttribute("error", "部门不能为空");
            return "publish";
        }

        //增加新话题
        topicDao.insert(topicEntity);
        //增加新的resend
        ResendEntity resendEntity = new ResendEntity();
        resendEntity.setTopicId(topicDao.lastInsertId());
        resendEntity.setDeptId(deptId);
        resendDao.insert(resendEntity);
        return "publish";
    }

    @RequestMapping("/hello")
    public String hello() {
        return "hello";
    }
}
