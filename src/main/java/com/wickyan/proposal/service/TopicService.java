package com.wickyan.proposal.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wickyan.proposal.dao.TopicDao;
import com.wickyan.proposal.entity.TopicEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

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
        return SelectTopicPageByDesc(current, size, locked, true, 0L, 0L, 0);
    }

    // 不按照用户名查询
    public Page<TopicEntity> SelectTopicPageByDesc(int current, int size, boolean locked, boolean audited, Long deptId, int status) {
        return SelectTopicPageByDesc(current, size, locked, audited, deptId, 0L, status);
    }
    // 查询有效提议
    /**
     * 1 按照部门查询
     * @param status 0 全部, 1 已经回复, 2 未回复
     */
    public Page<TopicEntity> SelectTopicPageByDept(int current, int size, Long deptId, int status) {
        return SelectTopicPageByDesc(current, size, false, true, deptId, 0L, status);
    }
    /**
     * 2 按照用户查询
     * @param status 0 全部, 1 已经回复, 2 未回复
     */
    public Page<TopicEntity> SelectTopicPageByUser(int current, int size, Long userId, int status) {
        return SelectTopicPageByDesc(current, size, false, true, 0L, userId, status);
    }


    public Page<TopicEntity> SelectTopicPageByDesc(int current, int size, boolean locked, boolean audited, Long deptId, Long userId, int status) {
        //1:被冻结 0:未被冻结
        int lockNumber = locked ? 1 : 0;
        //1:审核通过 0:未经审核
        int auditNumber = audited ? 1 : 0;
        Page<TopicEntity> page = new Page<>(current, size);
        QueryWrapper<TopicEntity> queryWrapper = new QueryWrapper<>();

        queryWrapper.orderByDesc("topic_id")
                .eq("locked", lockNumber);
        //如未冻结，则需要考虑是否审核(已冻结页面不需要考虑是否审核)
        if (!locked) {
            queryWrapper.eq("audited", auditNumber);
        }
        if (0 != userId) {
            queryWrapper.eq("user_id", userId);
        }
        if (0 != deptId) {
            queryWrapper.eq("dept_id", deptId);
        }
        if (1 == status) {
            //已经回复
            queryWrapper.ne("reply_id", -1);
        } else if (2 == status) {
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
