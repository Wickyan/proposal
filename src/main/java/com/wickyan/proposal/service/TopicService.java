package com.wickyan.proposal.service;

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
}
