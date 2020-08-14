package com.zhang.config;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * @Author:Zpg
 * @Date:2020/4/6 13:52
 * @Version:1.0
 * @Description: 判断当前用户是否具备某些角色
 */
@Component
public class CustomUrlDecisionManager implements AccessDecisionManager {

    @Override
    public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException {
        // object是请求对象，configAttributes就是MyFilter中返回的数据
        for (ConfigAttribute configAttribute : configAttributes) {
            String needRole = configAttribute.getAttribute();
            // 如果请求的角色是ROLE_LOGIN，那么只要判断是否登录就可以了
            if ("ROLE_LOGIN".equals(needRole)){
                // 如果当前用用户是匿名用户实例的话，说明没有登录
                if (authentication instanceof AnonymousAuthenticationToken){
                    throw new AccessDeniedException("尚未登录，请登录！");
                }else {
                    return;
                }
            }
            // 获取当前登录用户的角色
            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
            for (GrantedAuthority authority : authorities) {
                // 如果用户包含需要的角色直接结束，否则抛异常
                if (authority.getAuthority().equals(needRole)){
                    return;
                }
            }
            throw new AccessDeniedException("权限不足，请联系管理员！");
        }
    }

    @Override
    public boolean supports(ConfigAttribute attribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }
}
