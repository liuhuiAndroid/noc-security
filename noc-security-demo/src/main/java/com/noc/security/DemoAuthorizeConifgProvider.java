package com.noc.security;

import com.noc.security.core.authorize.AuthorizeConfigProvider;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.stereotype.Component;

@Component
// 需要最后才调用
@Order(Integer.MAX_VALUE)
public class DemoAuthorizeConifgProvider implements AuthorizeConfigProvider {

    @Override
    public void config(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config) {
        config.antMatchers("/noc/user/*").permitAll() // 测试vue权限接口
                // 和 NocAuthorizeConfigManager 冲突了，需要注释NocAuthorizeConfigManager中的 config.anyRequest().authenticated(); 否则会被覆盖
                .anyRequest().access("@rbacService.hasPermission(request,authentication)"); // rbac

//                .antMatchers("/demo.html").hasRole("ADMIN")
//                .antMatchers(HttpMethod.GET, "/user/*")
//                .hasRole("ADMIN"); // 需要admin权限
    }

}
