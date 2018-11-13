package com.noc.security.core.validate.code;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * 提供一个接口，把逻辑做成可配置的
 */
public interface ValidateCodeGenerator {

    ImageCode generate(ServletWebRequest request);

}
