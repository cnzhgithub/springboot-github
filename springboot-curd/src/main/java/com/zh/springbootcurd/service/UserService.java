package com.zh.springbootcurd.service;

import com.zh.springbootcurd.dto.GithubUser;
import com.zh.springbootcurd.mapper.UserMapper;
import com.zh.springbootcurd.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.event.PaintEvent;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public void createOrUpdate(User user) {
        User db = userMapper.findByAccountid(user.getAccount_id());
        if (db != null) {
            System.out.println(user.getToken());
            db.setToken(user.getToken());
            db.setName(user.getName());
            db.setAvatar_url(user.getAvatar_url());
            db.setGmt_create(System.currentTimeMillis());
            db.setGmt_modified(db.getGmt_create());
            userMapper.update(db);
        } else {
            user.setGmt_create(System.currentTimeMillis());
            user.setGmt_modified(user.getGmt_create());
            userMapper.insert(user);
        }
    }
}
