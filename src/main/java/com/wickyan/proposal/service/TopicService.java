package com.wickyan.proposal.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wickyan.proposal.dao.TopicDao;
import com.wickyan.proposal.entity.TopicEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by wickyan on 2020/3/20
 */
@Service("TopicService")
public class TopicService {

    @Autowired
    private TopicDao topicDao;

    public void inc(TopicEntity topicEntity) {
        topicEntity.setReadCount(topicEntity.getReadCount() + 1);
        topicDao.updateById(topicEntity);
    }

    public Page<TopicEntity> SelectTopicPageByDesc(int current, int size, boolean locked) {
        //dept == 0 查询全所有部门
        return SelectTopicPageByDesc(current, size, locked, 0, 0);
    }

    public Page<TopicEntity> SelectTopicPageByDesc(int current, int size, boolean locked, int deptId, int status) {
        int lockNumber = locked ? 1 : 0;
        Page<TopicEntity> page = new Page<>(current, size);
        QueryWrapper<TopicEntity> queryWrapper = new QueryWrapper<>();

        queryWrapper.orderByDesc("topic_id")
                .eq("locked", lockNumber);
        if (0 != deptId) {
            queryWrapper.eq("dept_id", deptId);
        }
        if(1 == status){
            //已经回复
            queryWrapper.ne("reply_id", -1);
        }else if(2 == status){
            //未回复
            queryWrapper.eq("reply_id", -1);
        }
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
