package com.test03.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.R;
import com.test03.mapper.SysRolesMapper;
import com.test03.mapper.SysUserRoleMapper;
import com.test03.mapper.UserMapper;
import com.test03.pojo.SysRoles;
import com.test03.pojo.SysUserRole;
import com.test03.pojo.User;
import com.test03.vo.JsonResult;
import org.apache.ibatis.annotations.Param;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther wise wu
 * @Date 2020/5/11 17:10
 * @Description
 */
@Controller
@RequestMapping("/user/")
public class UserController {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;
    @Autowired
    private SysRolesMapper sysRolesMapper;

    @RequestMapping("doLogin")
    @ResponseBody
    public JsonResult doLogin(User user) {
        System.out.println("测试登录");
       // AuthenticationToken token=new UsernamePasswordToken(user.getUsername(),user.getPassword());
        Subject subject= SecurityUtils.getSubject();
        UsernamePasswordToken token=
                new UsernamePasswordToken(user.getUsername(), user.getPassword());
        subject.login(token);//提交给securityManager

     /*   QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", user.getUsername())
                .eq("password", user.getPassword());
        User user1 = userMapper.selectOne(queryWrapper);*/
        System.out.println("登录成功");

        JsonResult success = JsonResult.success(user.getUsername());
        System.out.println(success);
        return success;
    }

    @RequestMapping("userrole")
    @ResponseBody
    public JsonResult dofindRoleByUserRoleId(User user) {
        Map<String, Object> userRoleMap = new HashMap<>();
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", user.getUsername());
        User user1 = userMapper.selectOne(queryWrapper);
        long user1Id = user1.getId();
        QueryWrapper<SysUserRole> queryWrapper2 = new QueryWrapper<>();
        queryWrapper2.eq("user_id", user1Id);

        List<SysUserRole> sysUserRoleList =
                sysUserRoleMapper.selectList(queryWrapper2);
        System.out.println(sysUserRoleList);
        List<String> roleNameList = new ArrayList<>();
        for (SysUserRole sysUserRole : sysUserRoleList) {
            SysRoles sysRoles = sysRolesMapper.selectById(sysUserRole.getRoleId());
            String name = sysRoles.getName();
            roleNameList.add(name);
        }
       userRoleMap .put("username", user.getUsername());
      userRoleMap.put("roles", roleNameList);

        return JsonResult.success(userRoleMap);
    }
/* <a href="/user/update">update </a>
           <a href="/user/view">  查看 </a>*/

       @RequiresPermissions("sys:user:update")
      @RequestMapping("update")
        public String update(){

             return  "update";
        }
        @RequiresPermissions("sys:user:view")
         @RequestMapping("view")
         public String  view (){

          return  "user";
         }
}
