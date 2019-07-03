package com.ts.spring.service.impl;

import com.ts.spring.bean.SysUser;
import com.ts.spring.bean.SysUserRole;
import com.ts.spring.dao.SysUserRoleMapper;
import com.ts.spring.service.SysUserRoleService;
import com.ts.spring.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
@Service
public class SysUserRoleServiceImpl implements SysUserRoleService {
    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;
    @Override
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.REPEATABLE_READ,timeout = 1)
    public List<SysUserRole> listById(String id) {
        return sysUserRoleMapper.listById(id);
    }
}
