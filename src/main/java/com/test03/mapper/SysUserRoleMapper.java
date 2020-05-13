package com.test03.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.test03.pojo.SysUserRole;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysUserRoleMapper extends BaseMapper<SysUserRole> {
    List<Integer> findRoleIdsByUserId(long id);
}
