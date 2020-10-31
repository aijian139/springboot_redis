package com.yj.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.yj.dao.UserDao;
import com.yj.entity.User;
import com.yj.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RedisTemplate redisTemplate;


    @Override
    public List<User> findAll() {
        List<User> list = null;
        String key = "findAll";
        if(redisTemplate.hasKey(key)){
            String obj = (String) redisTemplate.opsForValue().get(key);
            list = JSON.parseObject(obj,new TypeReference<List<User>>(){});
        }else {
            list = userDao.findAll();
            String toJSONString = JSON.toJSONString(list);
            redisTemplate.opsForValue().set(key, toJSONString);
        }

        return list;
    }

    @Override
    public List<User> find() {
        return userDao.findAll();
    }


}
