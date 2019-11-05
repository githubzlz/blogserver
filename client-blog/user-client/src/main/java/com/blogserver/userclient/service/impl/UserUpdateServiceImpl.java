package com.blogserver.userclient.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.blog.common.constants.UserConstants;
import com.blog.common.entity.user.UserVO;
import com.blog.common.exception.AuthorityException;
import com.blog.common.exception.ConfInputException;
import com.blog.common.exception.SysExecuteException;
import com.blog.common.exception.UpdateConfException;
import com.blog.common.result.ResultSet;
import com.blog.common.util.ConfCheckUtil;
import com.blog.common.util.LoginUserUtil;
import com.blogserver.userclient.mapper.UserQueryMapper;
import com.blogserver.userclient.mapper.UserUpdateMapper;
import com.blogserver.userclient.service.UserUpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * 用户修改相关逻辑实现
 * @author zhulinzhong
 * @version 1.0 CreateTime:2019/11/1 18:01
 */
@Service
public class UserUpdateServiceImpl implements UserUpdateService {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private UserUpdateMapper userUpdateMapper;
    @Autowired
    private UserQueryMapper userQueryMapper;

    @Override
    public ResultSet updateUserInfo(UserVO user, HttpServletRequest request) {

        ConfCheckUtil.inputNotNullCheck(user, user.getUsername());

        //检查用户名
        String loginUser = LoginUserUtil.getLoginUser(request);
        if(!user.getUsername().equals(loginUser)){
            throw new ConfInputException("错误的用户名");
        }
        //重要项目设置为空，增加安全性
        user.setIsDeleted(null);
        user.setIsAdmin(null);
        user.setIsBanned(null);
        user.setPassword(null);
        //补全数据
        user.setLastModifiedUser(loginUser);
        user.setLastModifiedTime(new Date());
        //设置条件，进行修改
        QueryWrapper<UserVO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", user.getId());
        int row = userUpdateMapper.update(user,queryWrapper);

        ConfCheckUtil.oneRowCheck(row);
        return ResultSet.success();
    }

    @Override
    public ResultSet updatePassword(UserVO user) {

        ConfCheckUtil.inputNotNullCheck(user,user.getUsername(),user.getPassword(),user.getNickname());
        //查询用户名
        QueryWrapper<UserVO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", user.getUsername());
        //根据昵称判断是否是本人
        queryWrapper.eq("nickname", user.getNickname());
        UserVO result = userQueryMapper.selectOne(queryWrapper);
        if(result == null){
            throw new ConfInputException("用户名输入错误");
        }

        //加密密码,补全数据
        String password = passwordEncoder.encode(user.getPassword());
        user.setPassword(password);
        user.setLastModifiedTime(new Date());
        user.setLastModifiedUser(user.getUsername());
        user.setIsDeleted(null);
        user.setIsAdmin(null);
        user.setIsBanned(null);
        //修改数据
        int row = userUpdateMapper.update(user, null);

        ConfCheckUtil.oneRowCheck(row);
        return ResultSet.success();
    }

    @Override
    public ResultSet setAdmin(UserVO user,HttpServletRequest request) {
        ConfCheckUtil.inputNotNullCheck(user, user.getId(),user.getIsAdmin());
        UserVO userVO = new UserVO();
        String loginUser = LoginUserUtil.getLoginUser(request);

        //获取登陆人信息
        if("未登录".equals(loginUser)){
            throw new ConfInputException("找不到登陆人");
        }
        QueryWrapper<UserVO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", loginUser);
        UserVO login = userQueryMapper.selectOne(queryWrapper);

        //对权限进行判断
        if(login == null || login.getIsAdmin() == null || login.getIsAdmin().equals(0)){
            throw new AuthorityException("权限不足");
        }
        if(login.getIsAdmin() < user.getIsAdmin()){
            throw new AuthorityException("权限不足，不能添加更高级的管理员");
        }

        //设置权限更新信息
        userVO.setId(user.getId());
        userVO.setIsAdmin(user.getIsAdmin());
        int row = userUpdateMapper.update(userVO,null);

        ConfCheckUtil.oneRowCheck(row);
        return ResultSet.success();
    }

    @Override
    public ResultSet banUser(Long id, HttpServletRequest request) {
        ConfCheckUtil.inputNotNullCheck(id);
        String loginUser = LoginUserUtil.getLoginUser(request);

        //查询两个用户的信息
        UserVO user = userUpdateMapper.selectById(id);

        QueryWrapper<UserVO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", loginUser);
        UserVO login = userQueryMapper.selectOne(queryWrapper);

        //比较两者的权限
        if(user == null || login == null){
            throw new SysExecuteException("系统执行异常");
        }
        if(user.getIsAdmin() >= login.getIsAdmin()){
            throw new AuthorityException("权限不足");
        }
        if(user.getIsBanned().equals(UserConstants.IS_BANNED)){
            throw new UpdateConfException("用户已经是封禁状态");
        }

        //设置更新的数据
        UserVO ex = new UserVO();
        ex.setId(id);
        ex.setIsBanned(UserConstants.IS_BANNED);
        int row = userUpdateMapper.update(ex, null);

        ConfCheckUtil.oneRowCheck(row);
        return ResultSet.success();
    }

}
