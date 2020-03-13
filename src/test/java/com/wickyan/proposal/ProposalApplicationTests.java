package com.wickyan.proposal;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wickyan.proposal.dao.UserDao;
import com.wickyan.proposal.entity.UserEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class ProposalApplicationTests {
    @Autowired
    private UserDao userDao;

    @Test
    void contextLoads() {
    }

    @Test
    void selectById() {

        List<UserEntity> users = userDao.findById(8L);
        for (UserEntity user :
                users) {
            System.out.println(user);
        }

    }


}

