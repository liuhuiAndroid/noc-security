package com.noc.security.core.properties;

public class BrowserProperties {

    // 默认登录页
    private String loginPage = "/noc-signIn.html";

    private LoginType loginType = LoginType.JSON;

    // 记住我功能有效时间，一周
    private int rememberMeSeconds = 604800;

    public String getLoginPage() {
        return loginPage;
    }

    public void setLoginPage(String loginPage) {
        this.loginPage = loginPage;
    }

    public LoginType getLoginType() {
        return loginType;
    }

    public void setLoginType(LoginType loginType) {
        this.loginType = loginType;
    }

    public int getRememberMeSeconds() {
        return rememberMeSeconds;
    }

    public void setRememberMeSeconds(int rememberMeSeconds) {
        this.rememberMeSeconds = rememberMeSeconds;
    }
}
