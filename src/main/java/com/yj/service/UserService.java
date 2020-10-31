package com.yj.service;

import com.yj.entity.User;

import java.util.List;

public interface UserService {

    List<User> findAll();

    List<User> find();
}
