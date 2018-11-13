package com.noc.security.core.validate.code;

import org.springframework.security.core.AuthenticationException;

/**
 * 针对验证码的异常
 * AuthenticationException：spring security 抛出异常的基类
 */
public class ValidateCodeException extends AuthenticationException {

    public ValidateCodeException(String msg) {
        super(msg);
    }

}
