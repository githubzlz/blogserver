package com.blog.userserver.server.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.blog.common.entity.user.TbUser;
import com.blog.common.entity.user.UserVO;
import com.blog.common.exception.CreateConfException;
import com.blog.common.exception.SysExecuteException;
import com.blog.common.result.ResultSet;
import com.blog.common.util.CommonUtil;
import com.blog.userserver.mapper.UserMapper;
import com.blog.userserver.mapper.UserVoMapper;
import com.blog.userserver.server.UserCreateService;
import com.blog.userserver.server.UserQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

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
    public ResultSet createUser(UserVO userVO) throws CreateConfException, SysExecuteException {

        CommonUtil.inputNotNullCheck(userVO, userVO.getUsername(),userVO.getPassword());
        userVO.setId(null);
        userVO.setIsDeleted(null);
        userVO.setIsBanned(null);
        //通过同户名查找数据库
        UserVO result = userQueryService
                .getUserInfo("username", userVO.getUsername(), null, null);
        if(result != null){
            throw new CreateConfException("重复的用户名");
        }

        //补全数据
        String password = passwordEncoder.encode(userVO.getPassword());
        userVO.setCreatedTime(new Date());
        userVO.setLastModifiedTime(new Date());
        userVO.setPassword(password);

        int row2 = userVoMapper.insert(userVO);
        if(row2 != 1){
            throw new SysExecuteException("创建失败，系统错误");
        }

        //创建TbUser
        int row1 = createTbUser(userVO, password);
        if(row1 != 1){
            userVoMapper.deleteById(userVO.getId());
            throw new SysExecuteException("创建失败，系统错误");
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
