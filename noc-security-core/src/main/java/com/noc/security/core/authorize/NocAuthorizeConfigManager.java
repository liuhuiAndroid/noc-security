package com.noc.security.core.authorize;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
public class NocAuthorizeConfigManager implements AuthorizeConfigManager {

    /**
     * 收集所有的AuthorizeConfigProvider
     */
    @Autowired
    private List<AuthorizeConfigProvider> authorizeConfigProviders;

    @Override
    public void config(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config) {
        for (AuthorizeConfigProvider authorizeConfigProvider : authorizeConfigProviders) {
            authorizeConfigProvider.config(config);
        }
        // 其他任何请求都需要授权
//        config.anyRequest().authenticated();
        // 和 DemoAuthorizeConifgProvider 冲突了，需要注释 config.anyRequest().authenticated();
        // 否则 DemoAuthorizeConifgProvider 中 .anyRequest()会被覆盖
    }

}