package com.ts.spring.service;

import com.ts.spring.bean.SysPermission;

import java.util.List;

public interface SysPermissionService {
    List<SysPermission> listByRoleId(String roleId);
}
