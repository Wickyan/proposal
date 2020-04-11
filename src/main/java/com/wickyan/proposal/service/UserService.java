package com.wickyan.proposal.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wickyan.proposal.dao.TopicDao;
import com.wickyan.proposal.entity.DeptEntity;
import com.wickyan.proposal.entity.TopicEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wickyan on 2020/3/17
 */
@Service("UserService")
public class UserService {

    public Map<Integer, String> getMapOfRole() {
        Map<Integer, String> role = new HashMap<>();
        role.put(1, "学生");
        role.put(2, "教师");
        role.put(3, "回复人");
        return role;
    }

}
