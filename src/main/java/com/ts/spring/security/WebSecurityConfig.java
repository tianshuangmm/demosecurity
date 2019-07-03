package com.ts.spring.security;

import com.ts.spring.security.session.CustomExpiredSessionStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

@Configuration//配置类
@EnableWebSecurity//开启Security服务
@EnableGlobalMethodSecurity(prePostEnabled = true)//开启全局Security注解
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private CustomUserDetailsService UserDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //super.configure(http);
        http.sessionManagement().invalidSessionUrl("/login/invalid")
                //最大登录用户为1
                .maximumSessions(1)
                //是否保留已经登录的用户，true新用户无法登录，为false旧用户被踢出
                .maxSessionsPreventsLogin(false)
                //旧用户被踢出后处理办法
                .expiredSessionStrategy(new CustomExpiredSessionStrategy());
        http.authorizeRequests()
                .antMatchers("/login","/getVerifyCode","/login/invalid").permitAll()
                //拦截所有请求
                .anyRequest().authenticated()
                //设置登录成功页面
                .and().formLogin().loginPage("/login")
                .defaultSuccessUrl("/").permitAll()
                //登录失败跳转页面
                .failureUrl("/login/error")
                //设置登出
                .and().logout().permitAll()
                //cookie存储
                .and().rememberMe()
                       .tokenRepository(persistentTokenRepository())
                       .tokenValiditySeconds(60)
                       .userDetailsService(UserDetailsService);
        http.csrf().disable();


    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //super.configure(auth);
        auth.userDetailsService(UserDetailsService).passwordEncoder(new PasswordEncoder() {
            @Override
            public String encode(CharSequence charSequence) {
                System.out.println(charSequence.toString());
                return charSequence.toString();
            }

            @Override
            public boolean matches(CharSequence charSequence, String s) {
                System.out.println(charSequence.toString());
                System.out.println(s);
                return s.equals(charSequence.toString());
            }
        });
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        //设置拦截忽略文件夹，可以对静态资源放行
        //super.configure(web);
        web.ignoring().antMatchers("/css/**","/js/**");
    }
    @Autowired
    private DataSource dataSource;
    @Bean
    public PersistentTokenRepository persistentTokenRepository(){
        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
        jdbcTokenRepository.setDataSource(dataSource);
        return jdbcTokenRepository;
    }
}
