package com.wickyan.proposal;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wickyan.proposal.dao.TopicDao;
import com.wickyan.proposal.dao.UserDao;
import com.wickyan.proposal.dto.ChartTopicDto;
import com.wickyan.proposal.entity.TopicEntity;
import com.wickyan.proposal.entity.UserEntity;
import com.wickyan.proposal.service.ProfileService;
import com.wickyan.proposal.service.TopicService;
import com.wickyan.proposal.util.DelTagsUtil;
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
    @Autowired
    TopicService topicService;

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

    @Autowired
    private DelTagsUtil delTagsUtil;

    @Test
    void justTest2() {
        String htmlStr = "<script type>var i=1; alert(i)</script><style> .font1{font-size:12px}</style><span>少年中国说。</span>红日初升，其道大光。<h3>河出伏流，一泻汪洋。</h3>潜龙腾渊， 鳞爪飞扬。乳 虎啸  谷，百兽震惶。鹰隼试翼，风尘吸张。奇花初胎，矞矞皇皇。干将发硎，有作其芒。天戴其苍，地履其黄。纵有千古，横有" +
                "八荒。<a href=\"www.baidu.com\">前途似海，来日方长</a>。<h1>美哉我少年中国，与天不老！</h1><p>壮哉我中国少年，与国无疆！</p>";
        System.out.println(delTagsUtil.getTextFromHtml(htmlStr));
    }


}

