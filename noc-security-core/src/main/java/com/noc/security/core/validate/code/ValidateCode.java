package com.noc.security.core.validate.code;

import java.time.LocalDateTime;

/**
 * 封装图片验证码信息
 */
public class ValidateCode {

    // 图片验证码
    private String code;

    // 过期时间
    private LocalDateTime expireTime;

    /**
     *
     * @param code 图片验证码
     * @param expireIn 多少秒之后过期
     */
    public ValidateCode(String code, int expireIn){
        this.code = code;
        this.expireTime = LocalDateTime.now().plusSeconds(expireIn);
    }

    public ValidateCode(String code, LocalDateTime expireTime){
        this.code = code;
        this.expireTime = expireTime;
    }

    public boolean isExpried() {
        return LocalDateTime.now().isAfter(expireTime);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public LocalDateTime getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(LocalDateTime expireTime) {
        this.expireTime = expireTime;
    }

}
