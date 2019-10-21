package com.blog.userserver.server.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.blog.userserver.mapper.UserMapper;
import com.blog.userserver.mapper.UserVoMapper;
import com.blog.userserver.server.UserQueryServer;
import com.zlz.common.constants.UserConstants;
import com.zlz.common.entity.user.UserVO;
import com.zlz.common.result.ResultSet;
import com.zlz.common.util.PageInfo;
import com.zlz.common.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zhulinzhong
 * @version 1.0 CreateTime:2019/10/21 9:57
 */
@Service
public class UserQueryServiceImpl implements UserQueryServer {

    @Autowired
    private UserVoMapper userVoMapper;

    @Override
    public ResultSet getUserList(PageInfo<UserVO> pageInfo) {
        return userPageReturn(pageInfo, "is_admin", UserConstants.IS_NOT_ADMIN);
    }

    @Override
    public ResultSet getAdminList(PageInfo<UserVO> pageInfo) {
        return userPageReturn(pageInfo, "is_admin", UserConstants.IS_ADMIN);
    }

    @Override
    public ResultSet getUserByName(String username) {
        return userReturn("username", username,"is_admin", UserConstants.IS_NOT_ADMIN);
    }

    @Override
    public ResultSet getAdminByName(String username) {
        return userReturn("username", username, "is_admin", UserConstants.IS_ADMIN);
    }

    /**
     * 根据输入的信息查寻用户信息的方法
     * @param pageInfo pageInfo
     * @param column 字段
     * @param value 字段值
     * @return ResultSet
     */
    private ResultSet userPageReturn(PageInfo pageInfo, String column, Object value){

        //数据验证
        if(pageInfo == null || pageInfo.getPageNum() == null || pageInfo.getPageSize() == null){
            return ResultSet.inputError();
        }

        //根据用户名和另一个字段查询用户信息
        QueryWrapper<UserVO> queryWrapper = new QueryWrapper<>();
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

    /**
     * 根据两个字段查询用户信息
     * @param column 字段1
     * @param value 值1
     * @param column2 字段2
     * @param value2 值2
     * @return ResultSet
     */
    private ResultSet userReturn(String column, String value, String column2, Object value2){

        //根据两个字段查询用户信息
        QueryWrapper<UserVO> queryWrapper = new QueryWrapper<>();
        if(column != null && value != null){
            queryWrapper.eq(column, value);
        }
        if(column2 != null && value2 != null){
           queryWrapper .eq(column2, value);
        }
        UserVO userVO = userVoMapper.selectOne(queryWrapper);
        return entityReturn(userVO);
    }

    /**
     * 返回实体信息的方法
     * @param entity 实体
     * @return ResultSet
     */
    private ResultSet entityReturn(Object entity){
        if(entity == null){
            return ResultSet.outError();
        }
        return ResultSet.success(entity);
    }
}