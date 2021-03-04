package com.happy.ssmdemo;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
class SsmdemoApplicationTests {

    @Autowired
    private UserMapper userMapper;

    @Test
    @Rollback
    public void test() throws Exception {
        //userMapper.insert("AAA", 20);
        //User u = userMapper.findByName("AAA");
        //Assert.assertEquals(20, u.getAge().intValue());

        List<User> userList = userMapper.findAll();
        System.out.println("用户列表");
        for(User user : userList) {
            System.out.println("name[" + user.getName() + "], age:[" + user.getAge() + "]");
        }
    }
}
