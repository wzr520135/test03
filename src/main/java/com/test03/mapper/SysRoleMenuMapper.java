package com.test03.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.test03.pojo.SysRoleMenu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysRoleMenuMapper  extends BaseMapper<SysRoleMenu> {
    List<Integer> findMenuIdsByRoleIds(Integer[] toArray);
}
