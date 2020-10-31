package com.yj.dao;

import com.yj.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
public interface UserDao {

        List<User> findAll();
}
