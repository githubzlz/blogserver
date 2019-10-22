package com.blog.userserver.server.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.blog.common.entity.user.TbUser;
import com.blog.common.entity.user.UserVO;
import com.blog.common.result.ResultSet;
import com.blog.userserver.mapper.UserMapper;
import com.blog.userserver.mapper.UserVoMapper;
import com.blog.userserver.server.UserCreateService;
import com.blog.userserver.server.UserQueryService;
import com.blog.userserver.util.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * 创建用户
 * @author zhulinzhong
 * @version 1.0 CreateTime:2019/10/22 10:09
 */
@Service
public class UserCreateServiceImpl implements UserCreateService {

    @Autowired
    private UserVoMapper userVoMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private UserQueryService userQueryService;

    @Override
    public ResultSet createUser(UserVO userVO, HttpServletRequest request) {
        if(userVO == null || userVO.getUsername() == null || userVO.getPassword() == null){
            return ResultSet.inputError();
        }

        //通过同户名查找数据库
        UserVO result = userQueryService.getUserInfoByName(userVO.getUsername());
        if(result != null){
            return ResultSet.inputError();
        }

        //补全数据
        String password = passwordEncoder.encode(userVO.getPassword());
        userVO.setCreatedTime(new Date());
        userVO.setLastModifiedTime(new Date());
        userVO.setPassword(password);

        //创建TbUser
        int row1 = createTbUser(userVO, password);

        if(row1 != 1){
            return ResultSet.sysError();
        }

        int row2 = userVoMapper.insert(userVO);
        if(row2 != 1){
            return ResultSet.sysError();
        }
        return ResultSet.success();
    }

    /**
     * 创建TbUser
     * @param userVO userVO
     * @param password 加密过的密码
     * @return row
     */

    @DS("mytestdb")
    private int createTbUser(UserVO userVO, String password){
        TbUser user = new TbUser();
        user.setId(userVO.getId());
        user.setUsername(userVO.getUsername());
        user.setPassword(password);
        user.setCreated(new Date());
        user.setUpdated(new Date());
        return userMapper.insert(user);
    }
}
