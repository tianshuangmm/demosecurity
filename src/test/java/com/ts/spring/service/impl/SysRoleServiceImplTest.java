package com.ts.spring.service.impl;

import com.ts.spring.bean.SysRole;
import com.ts.spring.bean.SysUser;
import com.ts.spring.bean.SysUserRole;
import com.ts.spring.service.SysRoleService;
import com.ts.spring.service.SysUserRoleService;
import com.ts.spring.service.SysUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
@MapperScan("com.ts.spring.dao")
public class SysRoleServiceImplTest {
    @Autowired
    private SysRoleService sysRoleService;
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysUserRoleService sysUserRoleService;


    @Test
    public void p(){
        SysRole sysRole = sysRoleService.selectById(1);
        System.out.println(sysRole);
        SysUser sysUser = sysUserService.selectById("1");
        System.out.println(sysUser);
        List<SysUserRole> sysUserRoles = sysUserRoleService.listById("1");
        System.out.println(sysUserRoles);

    }

}