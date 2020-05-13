package com.test03.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.test03.pojo.SysMenus;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysMenuMaper extends BaseMapper<SysMenus> {
    List<String> findPermissions(Integer[] toArray);
}
