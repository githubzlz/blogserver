package com.blogserver.userclient.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.blog.common.entity.user.UserVO;
import com.blog.common.result.ResultSet;
import com.blog.common.util.ConfCheckUtil;
import com.blogserver.userclient.mapper.UserQueryMapper;
import com.blogserver.userclient.service.UserQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zhulinzhong
 * @version 1.0 CreateTime:2019/11/4 11:27
 */
@Service
public class UserQueryServiceImpl implements UserQueryService {

    @Autowired
    private UserQueryMapper userQueryMapper;

    @Override
    public ResultSet selectByUserName(String username) {
        ConfCheckUtil.inputNotNullCheck(username);

        //根据用户名查询
        QueryWrapper<UserVO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        UserVO userVO = userQueryMapper.selectOne(queryWrapper);

        ConfCheckUtil.outputNotNullCheck(userVO);

        return ResultSet.success(userVO);
    }

    @Override
    public ResultSet selectById(Long id) {
        ConfCheckUtil.outputNotNullCheck(id);

        //根据id查询
        UserVO userVO = userQueryMapper.selectById(id);

        ConfCheckUtil.outputNotNullCheck(userVO);
        return ResultSet.success(userVO);
    }
}
