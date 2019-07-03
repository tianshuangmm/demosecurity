package com.ts.spring.security.verify;

import com.ts.spring.security.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {
    @Autowired
    private CustomUserDetailsService customUserDetailsService;
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String inputName = authentication.getName().trim();
        String inputPassword = authentication.getCredentials().toString().trim();
        CustomAuthenticationDetails customAuthenticationDetails = (CustomAuthenticationDetails) authentication.getDetails();
        if(customAuthenticationDetails!=null&&validateVerify(customAuthenticationDetails.getVerifyCode())){
            UserDetails userDetails = customUserDetailsService.loadUserByUsername(inputName);
            if(userDetails!=null&&userDetails.getPassword()!=null&&userDetails.getPassword().equals(inputPassword)){
                return new UsernamePasswordAuthenticationToken(inputName,inputPassword,userDetails.getAuthorities());
            }else{
                throw new BadCredentialsException("密码错误");
            }
        }else{
            throw new DisabledException("验证码输入错误！");
        }
        //return null;
    }
    private boolean validateVerify(String inputVerify) {
        //获取当前线程绑定的request对象
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        // 不分区大小写
        // 这个validateCode是在servlet中存入session的名字
        String validateCode = ((String) request.getSession().getAttribute("validateCode")).toLowerCase();
        inputVerify = inputVerify.toLowerCase();

        System.out.println("验证码：" + validateCode + "用户输入：" + inputVerify);

        return validateCode.equals(inputVerify);
    }


    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(UsernamePasswordAuthenticationToken.class);
    }
}
