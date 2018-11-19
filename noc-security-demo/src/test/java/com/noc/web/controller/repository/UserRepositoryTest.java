package com.noc.web.controller.repository;

import com.noc.DemoApplication;
import com.noc.security.rbac.dataobject.User;
import com.noc.security.rbac.repository.UserRepository;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
public class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Test
    public void findByProductStatus() {
        List<User> list = userRepository.findAll();
        System.err.println(list);
        Assert.assertTrue(list.size() > 0);
    }

    @Test
    public void findByPageable() {
        Pageable pageable = new PageRequest(1, 2);
        Page<User> userPage = userRepository.findAll(pageable);
        if (userPage != null) {
            System.err.println(ReflectionToStringBuilder.toString(userPage));
            Assert.assertTrue(userPage.getContent().size() > 0);
        }
    }

}
