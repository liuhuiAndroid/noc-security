package com.noc.security.browser;

import com.noc.security.core.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

/**
 * WebSecurityConfigurerAdapter Web应用安全配置适配器
 */
@Configuration
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    private AuthenticationSuccessHandler nocAuthenticationSuccessHandler;

    @Autowired
    private AuthenticationFailureHandler nocAuthenctiationFailureHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http.httpBasic() // 默认httpBasic登录
        http.formLogin() // 表单登录
                .loginPage("/authentication/require") // 自定义登录页面
                .loginProcessingUrl("/authentication/form") // 自定义表单提交请求
                .successHandler(nocAuthenticationSuccessHandler) // 自定义登录成功处理器
                .failureHandler(nocAuthenctiationFailureHandler) // 自定义登录失败处理器
                .and()
                .authorizeRequests()
                .antMatchers("/authentication/require",
                        securityProperties.getBrowser().getLoginPage()).permitAll() // 需要配置自定义登录页面不需要授权
                .anyRequest()
                .authenticated() // 其他任何请求都需要授权
                .and().csrf().disable(); // 关闭跨站防护
    }

    /**
     * PasswordEncoder用于处理密码加密解密
     *
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
