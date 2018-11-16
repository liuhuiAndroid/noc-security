package com.noc.security.rbac.controller;

import com.noc.security.rbac.dataobject.User;
import com.noc.security.rbac.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/noc/user")
public class NocUserController {

    @Autowired
    UserService userService;

    @GetMapping("/list")
    public List<User> list() {
        return userService.findAll();
    }

}
