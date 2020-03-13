package com.wickyan.proposal.service.impl;

/**
 * Created by wickyan on 2020/3/13
 */
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.wickyan.proposal.dao.UserDao;
import com.wickyan.proposal.entity.UserEntity;
import com.wickyan.proposal.service.UserService;


@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserDao, UserEntity> implements UserService {


}
