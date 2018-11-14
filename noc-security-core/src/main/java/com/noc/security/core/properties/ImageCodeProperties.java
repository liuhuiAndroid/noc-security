package com.noc.security.core.properties;

/**
 * 图形验证码配置
 */
public class ImageCodeProperties extends SmsCodeProperties{

    public ImageCodeProperties() {
        // 验证码长度默认为4
        setLength(4);
    }

    private int width = 67;
    private int height = 23;

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

}
