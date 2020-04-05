package com.wickyan.proposal.service.admin;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wickyan.proposal.dao.ResendDao;
import com.wickyan.proposal.dao.UserDao;
import com.wickyan.proposal.entity.TopicEntity;
import com.wickyan.proposal.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by wickyan on 2020/4/5
 */
@Service("AdminEditorService")
public class AdminEditorService {
    @Autowired
    private UserDao userDao;

    public Page<UserEntity> SelectEditUserPageByDesc(int current, int size) {
        Page<UserEntity> page = new Page<>(current,size);
        QueryWrapper<UserEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("user_id")
                .eq("role", 3);
        userDao.selectPage(page, queryWrapper);

        page.getRecords().forEach(System.out::println);
        System.out.println(page.getCurrent());
        System.out.println(page.getPages());
        System.out.println(page.getSize());
        System.out.println(page.getTotal());
        System.out.println(page.hasNext());
        System.out.println(page.hasPrevious());
        return page;
    }
}
