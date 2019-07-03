package com.ts.spring.dao;

import com.ts.spring.bean.SysRole;
import com.ts.spring.bean.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.mybatis.spring.annotation.MapperScan;

@Mapper
public interface SysRoleMapper {
    @Select("select * from sys_role where id = #{id}")
    public SysRole selectById(Integer id);

    @Select("select * from sys_role where name = #{name}")
    SysRole selectByName(String name);
}
