package com.test03.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.test03.mapper.SysMenuMaper;
import com.test03.mapper.SysRoleMenuMapper;
import com.test03.mapper.SysUserRoleMapper;
import com.test03.mapper.UserMapper;
import com.test03.pojo.SysRoleMenu;
import com.test03.pojo.SysUserRole;
import com.test03.pojo.User;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;


@Service
public class ShiroUserRealm extends AuthorizingRealm{
	@Autowired
	private UserMapper sysUserDao;

	@Override
	public CredentialsMatcher getCredentialsMatcher() {
		/*//构建凭证匹配对象
		HashedCredentialsMatcher cMatcher=
		new HashedCredentialsMatcher();
		//设置加密算法
		return cMatcher;*/
		SimpleCredentialsMatcher  credentialsMatcher= new SimpleCredentialsMatcher();

		    return credentialsMatcher;



	}
	/**
	 * 通过此方法获取用户认证信息,并进行封装,然后返回给
	 *SecurityManager对象
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
	   AuthenticationToken token) throws AuthenticationException {
		//1.获取用户登录时提交用户信息
		UsernamePasswordToken uToken=
		(UsernamePasswordToken)token;
		String username=uToken.getUsername();
		//2.基于用户名查找用户
		QueryWrapper<User> queryWrapper=new QueryWrapper<>();
		    queryWrapper.eq("username", username);
			            // .eq("password",uToken.getPassword()) ;


		User user = sysUserDao.selectOne(queryWrapper);
		/*User user=
		sysUserDao.findUserByUserName(username);*/
		//3.判定用户是否存在
		if(user==null)
			throw new UnknownAccountException();
		//4.判定用户是否被禁用
		//5.封装认证信息
		SimpleAuthenticationInfo info=
		new SimpleAuthenticationInfo(
				user,//principal 用户身份
				user.getPassword(),//hashedCredentials
				 //credentialsSalt
				getName());//realmName
		return info;//返回给SecurityManager
	}
	@Autowired
	private SysUserRoleMapper  sysUserRoleMapper;
	@Autowired
	private SysRoleMenuMapper sysRoleMenuMapper;
	@Autowired
	private SysMenuMaper sysMenuMaper;
    /**
          * 获取登录用户的权限信息并进行封装.
     */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		System.out.println("==doGetAuthorizationInfo==");
		//1.获取登录用户信息
		User user=(User) principals.getPrimaryPrincipal();
		System.out.println("登录用户的用户名: "+user);
		//2.基于登录用户id获取角色id并进行校验.
		QueryWrapper<SysUserRole> queryWraper =new QueryWrapper<>();
		        queryWraper.eq("user_id", user.getId());
		List<SysUserRole> sysUserRoles = sysUserRoleMapper.selectList(queryWraper);
		  List<Integer> roleIds=new ArrayList<>();
		for (SysUserRole sysUserRole : sysUserRoles) {
			Integer roleId = Math.toIntExact(sysUserRole.getRoleId());
			 roleIds.add(roleId);
		}

		System.out.println("用户的角色 id"+roleIds);
		/*List<Integer> roleIds=
		sysUserRoleMapper.findRoleIdsByUserId(user.getId());*/
		if(roleIds==null||roleIds.size()==0)
			throw new AuthorizationException();
		//3.基于角色id获取对应菜单id并进行校验
		Integer[] array= {};

		List<Integer> menuIds=
		sysRoleMenuMapper.findMenuIdsByRoleIds(
				roleIds.toArray(array));
		System.out.println("角色菜单权限的id"+menuIds);
		if(menuIds==null||menuIds.size()==0)
			throw new AuthorizationException();
		//4.基于菜单id获取对应的菜单权限标识(permission)
		List<String> permissions=
		sysMenuMaper.findPermissions(menuIds.toArray(array));
		if(permissions==null||permissions.size()==0)
			throw new AuthorizationException();
		//5.封装用户权限信息,并将此信息返回给SecurityManager
		Set<String> stringPermissions=new HashSet<>();
		for(String per:permissions) {
			if(!StringUtils.isEmpty(per)) {
				stringPermissions.add(per);
			}
		}
		SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();
		info.setStringPermissions(stringPermissions);
		return info;//返回给securityManager
	}

}









