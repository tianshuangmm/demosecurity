package com.ts.spring.service.impl;

import com.ts.spring.bean.SysUser;
import com.ts.spring.dao.SysUserMapper;
import com.ts.spring.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
@Service
public class SysUserServiceImpl implements SysUserService {
    @Autowired
    private SysUserMapper sysUserMapper;
    @Override
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.REPEATABLE_READ,timeout = 6)
    public SysUser selectById(String id) {
        /*sysUserMapper.selectById(id);
        int i=11/0;*/
        return sysUserMapper.selectById(id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.REPEATABLE_READ,timeout = 6)
    public SysUser selectByName(String name) {
        return sysUserMapper.selectByName(name);
    }
}
