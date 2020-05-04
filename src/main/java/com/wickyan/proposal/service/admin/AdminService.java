package com.wickyan.proposal.service.admin;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wickyan.proposal.dao.AdminDao;
import com.wickyan.proposal.dao.UserDao;
import com.wickyan.proposal.entity.AdminEntity;
import com.wickyan.proposal.entity.UserEntity;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * Created by wickyan on 2020/5/4
 */
@Service("AdminService")
public class AdminService {

    @Autowired
    private AdminDao adminDao;
    public AdminEntity selectOneByAdminName(String adminName) {
        QueryWrapper<AdminEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("admin_name", adminName);

        AdminEntity adminEntity = adminDao.selectOne(queryWrapper);


        return adminEntity;
    }
    public void refreshUser(String name) {
        Session session = SecurityUtils.getSubject().getSession();
        session.removeAttribute("userEntity");
        UserEntity userEntity = new UserEntity();
        userEntity.setUserId(0L);
        userEntity.setUserName(name);
        userEntity.setDeptId(0L);
        userEntity.setMobil("管理员");
        userEntity.setMail("管理员");
        userEntity.setRole(3);



        session.setAttribute("userEntity", userEntity);
    }
    public boolean withoutAdminLogin(HttpSession session) {
        return null == session.getAttribute("adminEntity");
    }

}
