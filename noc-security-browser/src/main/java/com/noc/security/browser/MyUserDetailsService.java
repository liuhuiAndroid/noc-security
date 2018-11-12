package com.noc.security.browser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class MyUserDetailsService implements UserDetailsService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // TODO 注入DAO对象，根据用户名从数据库查询用户信息
        logger.info("登录用户名：" + username);
        // 返回UserDetails实现类，可以使用用户表的实体对象实现UserDetails 接口
        // TODO 根据查找到的用户信息判断用户是否被冻结、账户是否删除等
        return new User(username, passwordEncoder.encode(username + "125"),
                true, // 账户可用，是否删除
                true, // 用户是否过期
                true, // 密码是否过期
                true,  // 账户是否锁定冻结
                // 把逗号隔开的字符串转GrantedAuthority集合，拥有哪些权限
                AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
    }
}
