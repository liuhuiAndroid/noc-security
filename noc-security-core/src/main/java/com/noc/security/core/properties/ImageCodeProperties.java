package com.noc.security.core.properties;

/**
 * 图形验证码配置
 */
public class ImageCodeProperties {

    private int width = 67;
    private int height = 23;
    // 验证码长度
    private int length = 4;
    // 过期时间
    private int expireIn = 600;
    // 拦截的url，用逗号隔开
    private String url;

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getExpireIn() {
        return expireIn;
    }

    public void setExpireIn(int expireIn) {
        this.expireIn = expireIn;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
