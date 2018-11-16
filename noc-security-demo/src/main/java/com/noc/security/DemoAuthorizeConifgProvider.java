package com.noc.security;

import com.noc.security.core.authorize.AuthorizeConfigProvider;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.stereotype.Component;

@Component
public class DemoAuthorizeConifgProvider implements AuthorizeConfigProvider {

    @Override
    public void config(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config) {
        config.antMatchers("/demo.html").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/user/*")
                .hasRole("ADMIN"); // 需要admin权限
    }

}
