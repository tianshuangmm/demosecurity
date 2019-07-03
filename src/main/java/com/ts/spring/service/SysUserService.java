package com.ts.spring.service;

import com.ts.spring.bean.SysUser;

public interface SysUserService {
    SysUser selectById(String id);
    SysUser selectByName(String name);
}
