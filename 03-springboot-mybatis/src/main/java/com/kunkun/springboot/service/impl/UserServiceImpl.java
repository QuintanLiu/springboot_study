package com.kunkun.springboot.service.impl;

import com.kunkun.springboot.mapper.UserDOMapper;
import com.kunkun.springboot.model.UserDO;
import com.kunkun.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDOMapper userDOMapper;

    @Override
    public UserDO getById(Integer id) {
        return userDOMapper.selectByPrimaryKey(id);
    }
}
