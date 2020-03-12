package com.wickyan.proposal;

import com.wickyan.proposal.dao.UserDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ProposalApplicationTests {

    @Test
    void contextLoads() {
    }
    @Autowired
    private UserDao userDao;
    @Test
    public void testSelectList(){

    }

}
