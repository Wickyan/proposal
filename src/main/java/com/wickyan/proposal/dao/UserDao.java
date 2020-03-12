package com.wickyan.proposal.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wickyan.proposal.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 用户
 * Created by wickyan on 2020/3/10
 */
@Mapper
@Repository
public interface UserDao extends BaseMapper<UserEntity> {
	
}
