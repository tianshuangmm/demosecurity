package com.ts.spring.service;

import com.ts.spring.bean.SysUserRole;

import java.util.ArrayList;
import java.util.List;

public interface SysUserRoleService {
    public List<SysUserRole> listById(String id);
}
