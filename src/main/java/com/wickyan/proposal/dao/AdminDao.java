package com.wickyan.proposal.dao;

import com.wickyan.proposal.entity.AdminEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 管理员
 * Created by wickyan on 2020/3/10
 */
@Repository
@Mapper
public interface AdminDao extends BaseMapper<AdminEntity> {
	
}
