package com.noc.security.rbac.service;

import com.noc.security.rbac.dataobject.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserService {

    /**
     * 查询所有用户
     */
    List<User> findAll();

}
