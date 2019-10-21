package com.blog.userserver.server.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.blog.userserver.mapper.UserMapper;
import com.blog.userserver.mapper.UserVoMapper;
import com.blog.userserver.server.UserServer;
import com.zlz.common.constants.UserConstants;
import com.zlz.common.entity.UserVO;
import com.zlz.common.result.ResultSet;
import com.zlz.common.util.PageInfo;
import com.zlz.common.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author zhulinzhong
 * @version 1.0 CreateTime:2019/10/21 9:57
 */
public class UserServiceImpl implements UserServer {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserVoMapper userVoMapper;

    @Override
    public ResultSet getUser(PageInfo<UserVO> pageInfo, String username) {
        return userReturn(pageInfo, username, "is_admin", UserConstants.IS_NOT_ADMIN);
    }

    @Override
    public ResultSet getAdmin(PageInfo<UserVO> pageInfo, String username) {
        return userReturn(pageInfo, username, "is_admin", UserConstants.IS_ADMIN);
    }

    /**
     * 根据输入的信息查寻用户信息的方法
     * @param pageInfo pageInfo
     * @param username 用户名
     * @param column 字段
     * @param value 字段值
     * @return ResultSet
     */
    private ResultSet userReturn(PageInfo pageInfo, String username, String column, Object value){

        //数据验证
        if(pageInfo == null || pageInfo.getPageNum() == null || pageInfo.getPageSize() == null || username == null){
            return ResultSet.inputError();
        }

        //根据用户名和另一个字段查询用户信息
        QueryWrapper<UserVO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        queryWrapper.eq(column, value);
        IPage<UserVO> iPage = userVoMapper.selectPage(PageUtil.getIpage(pageInfo),queryWrapper);

        //返回分页信息
        return pageReturn(iPage, pageInfo);
    }

    /**
     * 返回分页信息的方法
     * @param iPage iPage
     * @param pageInfo pageInfo
     * @return ResultSet
     */
    private ResultSet pageReturn(IPage iPage, PageInfo pageInfo){
        if(iPage.getRecords().isEmpty()){
            return  ResultSet.outError();
        }
        return ResultSet.success(PageUtil.getPageInfo(iPage, pageInfo));
    }

}