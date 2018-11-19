package com.noc.security.rbac.repository;

import com.noc.security.rbac.dataobject.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {

    Page<User> findAll(Pageable pageable);

}
