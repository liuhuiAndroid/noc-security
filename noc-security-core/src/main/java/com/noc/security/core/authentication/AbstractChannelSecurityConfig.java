package com.noc.security.core.authentication;

import com.noc.security.core.properties.SecurityConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

public class AbstractChannelSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AuthenticationSuccessHandler nocAuthenticationSuccessHandler;

    @Autowired
    private AuthenticationFailureHandler nocAuthenctiationFailureHandler;

    protected void applyPasswordAuthenticationConfig(HttpSecurity http) throws Exception {
        http.formLogin() // 表单登录
                .loginPage(SecurityConstants.DEFAULT_UNAUTHENTICATION_URL) // 自定义登录页面
                .loginProcessingUrl(SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_FORM) // 自定义表单提交请求
                .successHandler(nocAuthenticationSuccessHandler) // 自定义登录成功处理器
                .failureHandler(nocAuthenctiationFailureHandler); // 自定义登录失败处理器
    }

}
