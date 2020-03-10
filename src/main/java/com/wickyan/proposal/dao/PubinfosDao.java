package com.wickyan.proposal.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wickyan.proposal.entity.PubinfosEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 公告表
 * Created by wickyan on 2020/3/10
 */
@Mapper
public interface PubinfosDao extends BaseMapper<PubinfosEntity> {
	
}
