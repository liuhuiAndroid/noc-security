package com.noc.security.browser;

import com.noc.security.core.authentication.mobile.SmsCodeAuthenticationSecurityConfig;
import com.noc.security.core.authentication.mobile.SmsCodeFilter;
import com.noc.security.core.properties.SecurityProperties;
import com.noc.security.core.validate.code.ValidateCodeFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

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

    @Autowired
    private SmsCodeAuthenticationSecurityConfig smsCodeAuthenticationSecurityConfig;

    @Autowired
    private UserDetailsService userDetailsService;

    /**
     * 数据源
     */
    @Autowired
    private DataSource dataSource;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 图形验证码配置
        ValidateCodeFilter validateCodeFilter = new ValidateCodeFilter();
        validateCodeFilter.setAuthenticationFailureHandler(nocAuthenctiationFailureHandler);
        validateCodeFilter.setSecurityProperties(securityProperties);
        // 调用初始化方法
        validateCodeFilter.afterPropertiesSet();
        // 短信验证码配置
        SmsCodeFilter smsCodeFilter = new SmsCodeFilter();
        smsCodeFilter.setAuthenticationFailureHandler(nocAuthenctiationFailureHandler);
        smsCodeFilter.setSecurityProperties(securityProperties);
        smsCodeFilter.afterPropertiesSet();

        http.addFilterBefore(smsCodeFilter, UsernamePasswordAuthenticationFilter.class) // 配置自定义的短信验证码过滤器
                .addFilterBefore(validateCodeFilter, UsernamePasswordAuthenticationFilter.class) // 配置自定义的图形验证码过滤器
                .formLogin() // 表单登录
                .loginPage("/authentication/require") // 自定义登录页面
                .loginProcessingUrl("/authentication/form") // 自定义表单提交请求
                .successHandler(nocAuthenticationSuccessHandler) // 自定义登录成功处理器
                .failureHandler(nocAuthenctiationFailureHandler) // 自定义登录失败处理器
                .and()
                .rememberMe() // 记住我功能
                .tokenRepository(persistentTokenRepository()) // 记住我功能配置TokenRepository
                .tokenValiditySeconds(securityProperties.getBrowser().getRememberMeSeconds()) // 记住我功能配置有效期
                .userDetailsService(userDetailsService) // 记住我功能配置UserDetailsService
                .and()
                .authorizeRequests()
                .antMatchers("/authentication/require",
                        securityProperties.getBrowser().getLoginPage(),
                        "/code/*").permitAll() // 需要配置自定义登录页面不需要授权
                .anyRequest()
                .authenticated() // 其他任何请求都需要授权
                .and().csrf().disable() // 关闭跨站防护
                .apply(smsCodeAuthenticationSecurityConfig); // 添加短信验证码配置
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

    /**
     * 配置TokenRepository与数据库交互
     *
     * @return
     */
    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
        tokenRepository.setDataSource(dataSource);
        // 可以拿 JdbcTokenRepositoryImpl.CREATE_TABLE_SQL 脚本在数据库执行生成 persistent_logins 表
        // 启动时自动创建表
//		tokenRepository.setCreateTableOnStartup(true);
        return tokenRepository;
    }

}
