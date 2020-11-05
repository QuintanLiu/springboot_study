package com.kunkun.springboot.controller;

import com.kunkun.springboot.model.UserDO;
import com.kunkun.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/user")
    public Object user(){
        return userService.getById(10);
    }
}
