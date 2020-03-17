package com.wickyan.proposal;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wickyan.proposal.dao.TopicDao;
import com.wickyan.proposal.dao.UserDao;
import com.wickyan.proposal.entity.TopicEntity;
import com.wickyan.proposal.entity.UserEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * Created by wickyan on 2020/3/13
 */
@SpringBootTest
public class CRUDTest {

    @Autowired
    private UserDao userDao;

    @Test
    public void testSelectList() {
        List<UserEntity> userEntities = userDao.selectList(null);
        userEntities.forEach(System.out::println);
    }

    @Test
    public void testInster() {
        UserEntity userEntity = new UserEntity();
        userEntity.setUserId(2212126L);
        userEntity.setUserName("放噶士大夫十大");
        userEntity.setDeptId(8L);
        userEntity.setIdcardNum("1234554563252354");
        userEntity.setMail("qasda");
        userEntity.setRole(1);
        userEntity.setMobil("12314543");
        userEntity.setUserPsw("ausdhcaihncoojsdiocjs");
        userDao.insert(userEntity);
    }

    @Test
    public void testUpdate() {
        List<UserEntity> userEntities = userDao.selectList(null);
//        System.out.println(userEntities.get(0));
//        userDao.updateById(userEntities.get(0));
        for (UserEntity user :
                userEntities) {
            userDao.updateById(user);
        }
    }

    @Test
    public void testDelete() {
        List<UserEntity> userEntities = userDao.selectList(null);
        userDao.deleteById(userEntities.get(0));
    }

    @Autowired
    TopicDao topicDao;
    @Test
    public void testSelectPage() {
        Page<TopicEntity> page = new Page<>(1,5);
        QueryWrapper<TopicEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("topic_id");
        topicDao.selectPage(page, queryWrapper);

        page.getRecords().forEach(System.out::println);
        System.out.println(page.getCurrent());
        System.out.println(page.getPages());
        System.out.println(page.getSize());
        System.out.println(page.getTotal());
        System.out.println(page.hasNext());
        System.out.println(page.hasPrevious());
    }


}
