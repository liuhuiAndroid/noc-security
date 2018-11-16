package com.noc.security.rbac.service.impl;

import com.noc.security.rbac.dataobject.User;
import com.noc.security.rbac.repository.UserRepository;
import com.noc.security.rbac.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

}
