package com.blog.oauthserver.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.blog.oauthserver.mapper.*;
import com.blog.common.entity.user.TbPermission;
import com.blog.common.entity.user.TbRolePermission;
import com.blog.common.entity.user.TbUser;
import com.blog.common.entity.user.TbUserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhulinzhong
 * @version 1.0 CreateTime:2019/10/12 16:37
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private TbUserMapper tbUserMapper;
    @Autowired
    private TbUserRoleMapper tbUserRoleMapper;
    @Autowired
    private TbPermissionMapper tbPermissionMapper;
    @Autowired
    private TbRolePermissionMapper tbRolePermissionMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        QueryWrapper<TbUser> query1 = new QueryWrapper();
        query1.eq("username", username);
        TbUser user = tbUserMapper.selectOne(query1);

        QueryWrapper query2 = new QueryWrapper();
        query2.eq("user_id", user.getId());
        List<TbUserRole> tbUserRoles = tbUserRoleMapper.selectList(query2);
        List<Long> roles = new ArrayList<>();
        for(TbUserRole role : tbUserRoles){
            roles.add(role.getRoleId());
        }
        QueryWrapper query3 = new QueryWrapper();
        query1.in("role_id", roles);
        List<TbRolePermission> tbRolePermissions = tbRolePermissionMapper.selectList(query3);

        List<Long> permissions = new ArrayList<>();
        for(TbRolePermission tbRolePermission: tbRolePermissions){
            permissions.add(tbRolePermission.getPermissionId());
        }
        QueryWrapper query4 = new QueryWrapper();
        query4.in("id", permissions);
        List<TbPermission> tbPermissions = tbPermissionMapper.selectList(query4);

        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();

        System.err.println("UserName: "+user.getUsername());
        System.err.println("Password: "+user.getPassword());
        for (TbPermission tbPermission:tbPermissions){
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(tbPermission.getEnname());
            grantedAuthorities.add(grantedAuthority);
            System.err.println("ROLE: "+tbPermission.getName());
        }
        return new User(user.getUsername(), user.getPassword(), grantedAuthorities);
    }
}
