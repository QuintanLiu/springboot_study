package com.kunkun.springboot.service;

import com.kunkun.springboot.dao.UserDAO;
import com.kunkun.springboot.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserDAO userDAO;

    public User getUser(int id){
        return userDAO.selectById(id);
    }
}
