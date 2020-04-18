package com.wickyan.proposal.service.admin;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wickyan.proposal.dao.ReplyDao;
import com.wickyan.proposal.dao.UserDao;
import com.wickyan.proposal.entity.ReplyEntity;
import com.wickyan.proposal.entity.TopicEntity;
import com.wickyan.proposal.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by wickyan on 2020/4/5
 */
@Service("AdminTopicService")
public class AdminTopicService {

    @Autowired
    private ReplyDao replyDao;

    public Page<TopicEntity> makeTopicListHaveRightReplayDept(Page<TopicEntity> topicEntityPage) {
        for (TopicEntity topicEntity : topicEntityPage.getRecords()) {
            if (topicEntity.getReplyId() != -1) {
                ReplyEntity replyEntity = replyDao.selectById(topicEntity.getReplyId());
                topicEntity.setDeptId(replyEntity.getDeptId());
            }
        }
        return topicEntityPage;
    }
}
