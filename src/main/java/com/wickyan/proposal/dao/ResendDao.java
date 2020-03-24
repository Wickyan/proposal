package com.wickyan.proposal.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wickyan.proposal.entity.ResendEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 交送
 * Created by wickyan on 2020/3/10
 */
@Repository
@Mapper
public interface ResendDao extends BaseMapper<ResendEntity> {
	
}
