package com.ts.spring.service;

import com.ts.spring.bean.SysRole;

public interface SysRoleService {
    SysRole selectById(Integer id);
    SysRole selectByName(String name);

}
