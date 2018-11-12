package com.noc.security.core.properties;


import org.springframework.boot.context.properties.ConfigurationProperties;

// 读取配置文件中所有以noc.security为前缀的配置项
@ConfigurationProperties(prefix = "noc.security")
public class SecurityProperties {

    private BrowserProperties browser = new BrowserProperties();

    public BrowserProperties getBrowser() {
        return browser;
    }

    public void setBrowser(BrowserProperties browser) {
        this.browser = browser;
    }

}
