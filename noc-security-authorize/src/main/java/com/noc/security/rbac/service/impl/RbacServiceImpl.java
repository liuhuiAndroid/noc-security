package com.noc.security.rbac.service.impl;

import com.noc.security.rbac.service.RbacService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.Set;

@Component("rbacService")
public class RbacServiceImpl implements RbacService {

    private AntPathMatcher antPathMatcher = new AntPathMatcher();

    /**
     * 根据用户名，读取用户所拥有权限的所有URL
     * @param request
     * @param authentication
     * @return
     */
    @Override
    public boolean hasPermission(HttpServletRequest request, Authentication authentication) {
        Object principal = authentication.getPrincipal();
        boolean hasPermission = false;
        if (principal instanceof UserDetails) {
            String username = ((UserDetails)principal).getUsername();

            // TODO 数据库读取用户所拥有权限的所有URL
            Set<String> urls = new HashSet<>();

            for (String url : urls) {
                if(antPathMatcher.match(url, request.getRequestURI())){
                    hasPermission = true;
                    break;
                }
            }
        }
        return hasPermission;
    }

}
