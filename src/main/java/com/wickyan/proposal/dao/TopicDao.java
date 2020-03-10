package com.wickyan.proposal.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wickyan.proposal.entity.TopicEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 提案
 * Created by wickyan on 2020/3/10
 */
@Mapper
public interface TopicDao extends BaseMapper<TopicEntity> {
	
}