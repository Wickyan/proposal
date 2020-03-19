package com.wickyan.proposal;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wickyan.proposal.dao.UserDao;
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

    @Test
    void contextLoads() {
        ProfileService.SelectTopicPageByUserIdDesc(22160425L, 1, 5);
    }



}

