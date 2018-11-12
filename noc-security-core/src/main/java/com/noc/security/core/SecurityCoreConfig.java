package com.noc.security.core;

import com.noc.security.core.properties.SecurityProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

// 配置类
@Configuration
// 使配置读取器SecurityProperties生效
@EnableConfigurationProperties(SecurityProperties.class)
public class SecurityCoreConfig {

}