package com.ts.spring.security.permission;

import com.ts.spring.bean.SysPermission;
import com.ts.spring.bean.SysRole;
import com.ts.spring.service.SysPermissionService;
import com.ts.spring.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import javax.swing.text.StyledEditorKit;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
@Component
public class CustomPermissionEvaluator implements PermissionEvaluator {
    @Autowired
    private SysRoleService sysRoleService;
    @Autowired
    private SysPermissionService sysPermissionService;
    @Override
    public boolean hasPermission(Authentication authentication, Object o, Object o1) {
        //获取loadUserByUsername（）方法的结果
        User principal = (User) authentication.getPrincipal();
        //获得loadUserByUsername（）中注入的角色
        Collection<GrantedAuthority> authorities = principal.getAuthorities();
        for (GrantedAuthority grantedAuthority:authorities) {
            String authority = grantedAuthority.getAuthority();
            SysRole sysRole = sysRoleService.selectByName(authority);
            List<SysPermission> sysPermissions = sysPermissionService.listByRoleId(sysRole.getId().toString());
            if(sysPermissions!=null&&sysPermissions.size()>0){
                for (SysPermission sysPermission:sysPermissions) {
                    if(sysPermission.getUrl().equals(o)&&sysPermission.getPermission().contains(o1.toString())){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable serializable, String s, Object o) {
        return false;
    }
}
