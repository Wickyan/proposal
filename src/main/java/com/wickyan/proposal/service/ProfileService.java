package com.wickyan.proposal.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wickyan.proposal.dao.TopicDao;
import com.wickyan.proposal.entity.TopicEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by wickyan on 2020/3/17
 */
@Service("ProfileService")
public class ProfileService {
    @Autowired
    TopicDao topicDao;

    public Page<TopicEntity> SelectTopicPageByUserIdDesc(Long userId, int current, int size) {
        Page<TopicEntity> page = new Page<>(current, size);
        QueryWrapper<TopicEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .orderByDesc("topic_id")
                .eq("user_id", userId);
        topicDao.selectPage(page, queryWrapper);

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
