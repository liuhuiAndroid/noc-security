package com.noc.security.rbac.RbacService;

import org.springframework.security.core.Authentication;
import javax.servlet.http.HttpServletRequest;

public interface RbacService {

    /**
     * 是否能访问
     * @param request
     * @param authentication
     * @return
     */
    boolean hasPermission(HttpServletRequest request, Authentication authentication);

}