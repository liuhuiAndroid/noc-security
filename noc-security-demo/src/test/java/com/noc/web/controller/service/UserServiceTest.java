package com.noc.web.controller.service;

import com.noc.DemoApplication;
import com.noc.security.rbac.dataobject.User;
import com.noc.security.rbac.service.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
public class UserServiceTest {

    @Autowired
    UserService userService;

    @Test
    public void findByProductStatus() {
        List<User> list = userService.findAll();
        System.err.println(list);
        Assert.assertTrue(list.size() > 0);
    }
}
