package com.ts.spring.dao;

import com.ts.spring.bean.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface SysUserMapper {
    @Select("select * from sys_user where id =#{id}")
    public SysUser selectById(String id);
    @Select("select * from sys_user where name = #{name}")
    SysUser selectByName(String name);
}
