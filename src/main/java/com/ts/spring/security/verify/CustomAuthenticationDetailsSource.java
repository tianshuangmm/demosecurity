package com.ts.spring.security.verify;

import org.springframework.security.authentication.AuthenticationDetailsSource;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
@Component
/**
 * 该接口用于在springSecurity登录构成中对用户的登录信息的详细信息进行填充
 */
public class CustomAuthenticationDetailsSource implements AuthenticationDetailsSource<HttpServletRequest,WebAuthenticationDetails> {

    @Override
    public WebAuthenticationDetails buildDetails(HttpServletRequest request) {
        return new CustomAuthenticationDetails(request);
    }
}
