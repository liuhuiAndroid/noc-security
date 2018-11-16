package com.noc.security.core.authorize;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class NocAuthorizeConfigManager implements AuthorizeConfigManager {

    /**
     * 收集所有的AuthorizeConfigProvider
     */
    @Autowired
    private Set<AuthorizeConfigProvider> authorizeConfigProviders;

    @Override
    public void config(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config) {
        for (AuthorizeConfigProvider authorizeConfigProvider : authorizeConfigProviders) {
            authorizeConfigProvider.config(config);
        }
        // 其他任何请求都需要授权
        config.anyRequest().authenticated();
    }

}