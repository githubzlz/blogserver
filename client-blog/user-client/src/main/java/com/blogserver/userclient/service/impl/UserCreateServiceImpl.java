package com.blogserver.userclient.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.blog.common.entity.user.UserVO;
import com.blog.common.exception.ConfInputException;
import com.blog.common.result.ResultSet;
import com.blog.common.util.ConfCheckUtil;
import com.blogserver.userclient.mapper.UserCreateMapper;
import com.blogserver.userclient.mapper.UserQueryMapper;
import com.blogserver.userclient.service.UserCreateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * 用户创建的service层实现类
 * @author zhulinzhong
 * @version 1.0 CreateTime:2019/11/4 9:50
 */
@Service
public class UserCreateServiceImpl implements UserCreateService {

    @Autowired
    private UserCreateMapper userCreateMapper;
    @Autowired
    private UserQueryMapper userQueryMapper;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public ResultSet createNew(UserVO userVO) {

        //对用户名密码进行非空检测
        ConfCheckUtil.inputNotNullCheck(userVO, userVO.getUsername(), userVO.getPassword());
        //检测用户名是否已经存在
        QueryWrapper<UserVO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", userVO.getUsername());
        UserVO result = userQueryMapper.selectOne(queryWrapper);
        if(result != null){
            throw new ConfInputException("用户名已经存在");
        }
        //密码加密
        userVO.setPassword(passwordEncoder.encode(userVO.getPassword()));
        //补全数据
        userVO.setLastModifiedTime(new Date());
        userVO.setCreatedTime(new Date());
        userVO.setCreatedUser(userVO.getUsername());
        userVO.setLastModifiedUser(userVO.getUsername());
        //执行插入操作
        int row = userCreateMapper.insert(userVO);
        ConfCheckUtil.oneRowCheck(row);
        //返回操作信息
        return ResultSet.success();
    }
}
