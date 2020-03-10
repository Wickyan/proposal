package com.wickyan.proposal.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wickyan.proposal.entity.ReplyEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 回复
 * Created by wickyan on 2020/3/10
 */
@Mapper
public interface ReplyDao extends BaseMapper<ReplyEntity> {
	
}
