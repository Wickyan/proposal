package com.wickyan.proposal.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wickyan.proposal.dao.ResendDao;
import com.wickyan.proposal.dao.TopicDao;
import com.wickyan.proposal.entity.ResendEntity;
import com.wickyan.proposal.entity.TopicEntity;
import com.wickyan.proposal.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by wickyan on 2020/3/17
 */
@Service("ResendService")
public class ResendService {
    @Autowired
    private ResendDao resendDao;

    public List<ResendEntity> selectResendByTopicId(Long topicId) {
        QueryWrapper<ResendEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("topic_id", "topicId")
                .orderByDesc("resend_id");

        List<ResendEntity> resendEntities = resendDao.selectList(queryWrapper);
        return resendEntities;
    }
}
