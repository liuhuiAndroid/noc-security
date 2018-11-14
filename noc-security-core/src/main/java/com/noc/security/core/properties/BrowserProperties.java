package com.noc.security.core.properties;

public class BrowserProperties {

    // 默认登录页
    private String loginPage = "/noc-signIn.html";

    private LoginResponseType loginType = LoginResponseType.JSON;

    // 记住我功能有效时间，一周
    private int rememberMeSeconds = 604800;

    public String getLoginPage() {
        return loginPage;
    }

    public void setLoginPage(String loginPage) {
        this.loginPage = loginPage;
    }

    public LoginResponseType getLoginType() {
        return loginType;
    }

    public void setLoginType(LoginResponseType loginType) {
        this.loginType = loginType;
    }

    public int getRememberMeSeconds() {
        return rememberMeSeconds;
    }

    public void setRememberMeSeconds(int rememberMeSeconds) {
        this.rememberMeSeconds = rememberMeSeconds;
    }
}
