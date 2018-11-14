package com.noc.web.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.noc.dto.User;
import com.noc.dto.UserQueryCondition;
import com.noc.exception.UserNotExistException;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping
    @JsonView(User.UserSimpleView.class)
    @ApiOperation(value = "用户查询服务")
    public List<User> query(UserQueryCondition condition,
                            @PageableDefault(page = 1, size = 10, sort = "username,asc") Pageable pageable) {
        // ReflectionToStringBuilder ToString 工具
        System.out.println(ReflectionToStringBuilder.toString(condition, ToStringStyle.MULTI_LINE_STYLE));
        System.out.println(ReflectionToStringBuilder.toString(pageable, ToStringStyle.MULTI_LINE_STYLE));
        return Arrays.asList(new User(), new User(), new User());
    }

    @GetMapping("/{id:\\d+}")
    @JsonView(User.UserSimpleView.class)
    public User getInfo(@ApiParam(value = "用户id") @PathVariable String id) {
//        throw new RuntimeException("user not exist");
        throw new UserNotExistException(id);

//        System.out.println("进入getInfo服务");
//        User user = new User();
//        user.setUsername("noc");
//        return user;
    }

    @PostMapping
    @ApiOperation(value = "创建用户")
    // @Valid注解实现参数校验，BindingResult使校验失败，还能带着错误信息进入方法体中
    public User create(@Valid @RequestBody User user, BindingResult errors) {
        if (errors.hasErrors()) {
            errors.getAllErrors().stream().forEach(error ->
                    System.out.println(error.getDefaultMessage()));
        }

        System.out.println(user.getId());
        System.out.println(user.getUsername());
        System.out.println(user.getPassword());
        System.out.println(user.getBirthday());
        user.setId("1");
        user.setBirthday(user.getBirthday());
        return user;
    }

    @PutMapping("/{id:\\d+}")
    public User update(@Valid @RequestBody User user, BindingResult errors) {
        if (errors.hasErrors()) {
            errors.getAllErrors().stream().forEach(error -> {
                FieldError fieldError = (FieldError) error;
                String message = fieldError.getField() + " " + fieldError.getDefaultMessage();
                System.out.println(message);
            });
        }

        System.out.println(user.getId());
        System.out.println(user.getUsername());
        System.out.println(user.getPassword());
        System.out.println(user.getBirthday());
        user.setId("1");
        return user;
    }

    @DeleteMapping("/{id:\\d+}")
    public void delete(@ApiParam(value = "用户id") @PathVariable String id) {
        System.out.println(id);
    }

    @GetMapping("/me")
    public Object getCurrentUser() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    @GetMapping("/me/2")
    public Object getCurrentUser2(Authentication user) {
        return user;
    }

    @GetMapping("/me/3")
    public Object getCurrentUser3(@AuthenticationPrincipal UserDetails user) {
        return user;
    }

}
