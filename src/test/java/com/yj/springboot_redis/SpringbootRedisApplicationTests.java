package com.yj.springboot_redis;

import com.yj.entity.User;
import com.yj.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class SpringbootRedisApplicationTests {

    @Autowired
    private UserService userService;

    @Test
    void findAll() {
        List<User> list = userService.findAll();
        list.forEach( System.out::println);
    }

    @Test
    void find() {
        List<User> list = userService.find();
        list.forEach( System.out::println);
    }

}
