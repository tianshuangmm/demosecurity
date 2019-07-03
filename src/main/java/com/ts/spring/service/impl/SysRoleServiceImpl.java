package com.ts.spring.service.impl;

import com.ts.spring.bean.SysRole;
import com.ts.spring.dao.SysRoleMapper;
import com.ts.spring.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
@Service
public class SysRoleServiceImpl implements SysRoleService {
    @Autowired
    private SysRoleMapper sysRoleMapper;
    @Override
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.REPEATABLE_READ,timeout = 6)
    public SysRole selectById(Integer id) {
        return sysRoleMapper.selectById(id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.READ_COMMITTED,timeout = 6)
    public SysRole selectByName(String name) {
        return sysRoleMapper.selectByName(name);
    }
}
