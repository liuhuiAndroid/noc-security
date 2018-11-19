package com.noc.security.rbac.service;

import com.noc.security.rbac.dataobject.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserService {

    /**
     * 查询所有用户
     */
    Page<User> findAll(Pageable pageable);

}
