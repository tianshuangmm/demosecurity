package com.ts.spring.service.impl;

import com.ts.spring.bean.SysPermission;
import com.ts.spring.dao.SysPermissionMapper;
import com.ts.spring.service.SysPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class SysPermissionServiceImpl implements SysPermissionService {
    @Autowired
    private SysPermissionMapper syspermissionMapper;
    @Override
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.READ_COMMITTED,timeout = 6)
    public List<SysPermission> listByRoleId(String roleId) {
        return syspermissionMapper.listByRoleId(roleId);
    }
}
