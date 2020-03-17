package com.wickyan.proposal.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wickyan.proposal.dao.TopicDao;
import com.wickyan.proposal.entity.TopicEntity;
import com.wickyan.proposal.service.PublishService;
import org.springframework.stereotype.Service;


/**
 * 提案
 * Created by wickyan on 2020/3/13
 */

@Service("topicService")
public class PublishServiceImpl extends ServiceImpl<TopicDao, TopicEntity> implements PublishService {

}
