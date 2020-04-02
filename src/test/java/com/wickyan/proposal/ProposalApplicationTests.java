package com.wickyan.proposal;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wickyan.proposal.dao.TopicDao;
import com.wickyan.proposal.dao.UserDao;
import com.wickyan.proposal.entity.TopicEntity;
import com.wickyan.proposal.entity.UserEntity;
import com.wickyan.proposal.service.ProfileService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class ProposalApplicationTests {
    @Autowired
    private ProfileService ProfileService;
    @Autowired
    UserDao userDao;
    @Autowired
    TopicDao topicDao;
    @Test
    void contextLoads() {
        ProfileService.SelectTopicPageByUserIdDesc(22160425L, 1, 5);
    }

    @Test
    void justTest() {
        UserEntity userEntity = userDao.selectById(22160424L);
        userEntity.setMail("213");
        System.out.println(userEntity);
        int result = userDao.updateById(userEntity);
        System.out.println(result + "#########");
    }
    @Test
    void justTest2() {
        TopicEntity topicEntity = topicDao.selectById(1L);
        topicEntity.setDeptId(1L);
        int result = topicDao.updateById(topicEntity);
        System.out.println(result + "#########");
    }


}

