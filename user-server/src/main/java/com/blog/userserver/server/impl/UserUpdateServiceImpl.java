package com.blog.userserver.server.impl;

import com.blog.common.entity.user.TbUser;
import com.blog.common.entity.user.UserVO;
import com.blog.common.result.ResultSet;
import com.blog.userserver.mapper.UserMapper;
import com.blog.userserver.mapper.UserVoMapper;
import com.blog.userserver.server.UserUpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * 修改用户信息
 * @author zhulinzhong
 * @version 1.0 CreateTime:2019/10/22 8:35
 */
@Service
public class UserUpdateServiceImpl implements UserUpdateService {

    @Autowired
    private UserVoMapper userVoMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public ResultSet updateUserInfoById(UserVO userVO) {
        if(userVO == null || userVO.getId() == null){
            return ResultSet.inputError();
        }
        //若是修改密码，或者用户名则需要需改TbUser
        if(userVO.getUsername() != null){
            TbUser user = new TbUser();
            user.setId(userVO.getId());
            user.setUsername(userVO.getUsername());
            user.setUpdated(new Date());
            if(userVO.getPassword() != null){
                String password = passwordEncoder.encode(userVO.getPassword());
                user.setPassword(password);
                userVO.setPassword(password);
            }
            int row = userMapper.updateById(user);
            if(row != 1){
                return ResultSet.sysError();
            }
        }

        int row = userVoMapper.updateById(userVO);
        if(row != 1){
            return ResultSet.sysError();
        }
        return ResultSet.success();
    }
}
