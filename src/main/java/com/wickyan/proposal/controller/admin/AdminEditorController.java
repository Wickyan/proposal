package com.wickyan.proposal.controller.admin;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wickyan.proposal.dao.UserDao;
import com.wickyan.proposal.entity.UserEntity;
import com.wickyan.proposal.service.admin.AdminEditorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 操作人管理
 * Created by wickyan on 2020/4/4
 */
@Controller
public class AdminEditorController {
    @Autowired
    private UserDao userDao;
    @Autowired
    private AdminEditorService adminEditorService;

    @GetMapping({"/admin/editor"})
    public String editor(@RequestParam(name = "page", defaultValue = "1") int current,
                         Model model) {
        model.addAttribute("adminPage", "editor");
        Page<UserEntity> userEntityPage = adminEditorService.SelectEditUserPageByDesc(current, 5);
        model.addAttribute("userEntityPage", userEntityPage);
        return "admin/editor";
    }
}