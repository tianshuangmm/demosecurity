package com.ts.spring.dao;

import com.ts.spring.bean.SysUserRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
@Mapper
public interface SysUserRoleMapper {
    @Select("select * from sys_user_role  where user_id = #{id}")
    public List<SysUserRole> listById(String id);
}
