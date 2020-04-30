package com.wickyan.proposal.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wickyan.proposal.dto.ChartTopicDto;
import com.wickyan.proposal.entity.TopicEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * 提案
 * Created by wickyan on 2020/3/10
 */
@Repository
@Mapper
public interface TopicDao extends BaseMapper<TopicEntity> {
    Long lastInsertId();
    Long insertTopicReturnLastInsertId(TopicEntity topicEntity);
    List<ChartTopicDto> countOfTopicDept();
}
