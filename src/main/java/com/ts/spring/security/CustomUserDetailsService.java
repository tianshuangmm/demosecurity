package com.ts.spring.security;

import com.ts.spring.bean.SysRole;
import com.ts.spring.bean.SysUser;
import com.ts.spring.bean.SysUserRole;
import com.ts.spring.service.SysRoleService;
import com.ts.spring.service.SysUserRoleService;
import com.ts.spring.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private SysRoleService sysRoleService;
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysUserRoleService sysUserRoleService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        //SysUser user = UserDetailsService
        SysUser sysUser = sysUserService.selectByName(s);
        if(sysUser!=null){
            List<SysUserRole> sysUserRoles = sysUserRoleService.listById(sysUser.getId().toString());
            if(sysUserRoles!=null&&sysUserRoles.size()>0){
                for (int i = 0; i < sysUserRoles.size(); i++) {
                    SysUserRole sysUserRole = sysUserRoles.get(i);
                    SysRole sysRole = sysRoleService.selectById(sysUserRole.getRoleId());
                    authorities.add(new SimpleGrantedAuthority(sysRole.getName()));
                }
            }
        }else{
            throw new UsernameNotFoundException("用户名不存在！");
        }
        return new User(sysUser.getName(),sysUser.getPassword(),authorities);
    }
}
