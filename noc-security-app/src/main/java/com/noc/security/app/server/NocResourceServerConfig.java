package com.noc.security.app.server;

import com.noc.security.core.authentication.mobile.SmsCodeAuthenticationSecurityConfig;
import com.noc.security.core.properties.SecurityConstants;
import com.noc.security.core.properties.SecurityProperties;
import com.noc.security.core.validate.code.ValidateCodeSecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
// 实现资源服务器
@EnableResourceServer
public class NocResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    private AuthenticationSuccessHandler nocAuthenticationSuccessHandler;

    @Autowired
    private AuthenticationFailureHandler nocAuthenctiationFailureHandler;

    @Autowired
    private SmsCodeAuthenticationSecurityConfig smsCodeAuthenticationSecurityConfig;

    @Autowired
    private ValidateCodeSecurityConfig validateCodeSecurityConfig;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.formLogin() // 表单登录
                .loginPage(SecurityConstants.DEFAULT_UNAUTHENTICATION_URL) // 自定义登录页面
                .loginProcessingUrl(SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_FORM) // 自定义表单提交请求
                .successHandler(nocAuthenticationSuccessHandler) // 自定义登录成功处理器
                .failureHandler(nocAuthenctiationFailureHandler); // 自定义登录失败处理器

        http.apply(validateCodeSecurityConfig)
                .and()
                .apply(smsCodeAuthenticationSecurityConfig) // 添加短信验证码配置
                .and()
                .authorizeRequests()
                .antMatchers(SecurityConstants.DEFAULT_UNAUTHENTICATION_URL,
                        SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_MOBILE,
                        securityProperties.getBrowser().getLoginPage(),
                        SecurityConstants.DEFAULT_VALIDATE_CODE_URL_PREFIX + "/*")
                .permitAll()// 需要配置自定义登录页面不需要授权
                .antMatchers(HttpMethod.GET,"/user/*").hasRole("ADMIN") // 需要admin权限
                .anyRequest()
                .authenticated() // 其他任何请求都需要授权
                .and().csrf().disable(); // 关闭跨站防护
    }
}
