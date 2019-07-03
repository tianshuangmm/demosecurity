package com.ts.spring.dao;

import com.ts.spring.bean.SysPermission;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
@Mapper
public interface SysPermissionMapper {
    @Select("select * from sys_permission where  role_id = #{roleId}")
    public List<SysPermission> listByRoleId(String roleId);
}
