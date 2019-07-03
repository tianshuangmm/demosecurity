package com.ts.spring.security.verify;

import org.springframework.security.web.authentication.WebAuthenticationDetails;

import javax.servlet.http.HttpServletRequest;
/**
 *获取用户登录时携带的额外信息
 */
public class CustomAuthenticationDetails extends WebAuthenticationDetails {
    private static final long serialVersionUID = 6975601077710753878L;
    private final String verifyCode;
    public CustomAuthenticationDetails(HttpServletRequest request) {
        super(request);
        this.verifyCode = request.getParameter("verifyCode");
    }
    public String getVerifyCode() {
        return verifyCode;
    }
}
