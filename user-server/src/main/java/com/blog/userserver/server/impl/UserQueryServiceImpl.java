package com.blog.userserver.server.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.blog.common.entity.user.UserVO;
import com.blog.common.exception.ConfInputException;
import com.blog.common.exception.ConfOutputException;
import com.blog.common.result.PageInfo;
import com.blog.common.result.ResultSet;
import com.blog.common.util.CommonUtil;
import com.blog.common.util.PageUtil;
import com.blog.userserver.mapper.UserVoMapper;
import com.blog.userserver.server.UserQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 查询用户信息
 * @author zhulinzhong
 * @version 1.0 CreateTime:2019/10/21 9:57
 */
@Service
public class UserQueryServiceImpl implements UserQueryService {

    @Autowired
    private UserVoMapper userVoMapper;

    @Override
    public ResultSet getUserPage(PageInfo<UserVO> pageInfo,Integer isAdmin)
            throws ConfInputException, ConfOutputException {

        CommonUtil.inputNotNullCheck(pageInfo,pageInfo.getPageNum(),pageInfo.getPageSize());

        if(isAdmin == null){
            pageInfo = getUserPage(pageInfo, null, null);
        }
        pageInfo = getUserPage(pageInfo,"is_admin",isAdmin);

        CommonUtil.outputNotNullCheck(pageInfo);

        return ResultSet.success(pageInfo);
    }

    @Override
    public ResultSet getUserById(UserVO userVO,Integer isAdmin)
            throws ConfInputException, ConfOutputException {

        CommonUtil.inputNotNullCheck(userVO, userVO.getId());

        if(isAdmin != null){
            userVO = getUserInfo("id", userVO.getId(), "is_admin", isAdmin);
        }
        userVO = getUserInfo("id", userVO.getId(), null, null);

        CommonUtil.outputNotNullCheck(userVO);

        return ResultSet.success(userVO);
    }

    @Override
    public ResultSet getUserByUserName(UserVO userVO,Integer isAdmin)
            throws ConfInputException, ConfOutputException {

        CommonUtil.inputNotNullCheck(userVO, userVO.getUsername());

        if(isAdmin != null){
            userVO = getUserInfo("username", userVO.getUsername(), "is_admin", isAdmin);
        }
        userVO = getUserInfo("username", userVO.getUsername(), null, null);

        CommonUtil.outputNotNullCheck(userVO);

        return ResultSet.success(userVO);
    }

    @Override
    public UserVO getUserInfo(String column,Object value, String column2, Object value2) {
        //根据两个字段查询用户信息
        QueryWrapper<UserVO> queryWrapper = getQueryWrapper(column, value, column2, value2);
        UserVO userVO = userVoMapper.selectOne(queryWrapper);
        return userVO;
    }

    /**
     * 分页查询数据
     * @param pageInfo
     * @param column
     * @param value
     * @return
     */
    private PageInfo<UserVO> getUserPage(PageInfo pageInfo, String column, Object value){

        //根据用户名和另一个字段查询用户信息
        QueryWrapper<UserVO> queryWrapper = new QueryWrapper<>();
        if(column != null){
            queryWrapper.eq(column, value);
        }
        IPage<UserVO> iPage = userVoMapper.selectPage(PageUtil.getIpage(pageInfo),queryWrapper);

        //返回分页信息
        return PageUtil.getPageInfo(iPage, pageInfo);
    }

    /**
     * 创建queryWrapper条件
     * @param column
     * @param value
     * @param column2
     * @param value2
     * @return
     */
    private QueryWrapper<UserVO> getQueryWrapper(String column, Object value, String column2, Object value2) {
        QueryWrapper<UserVO> queryWrapper = new QueryWrapper<>();
        if(column != null && value != null){
            queryWrapper.eq(column, value);
        }
        if(column2 != null && value2 != null){
            queryWrapper .eq(column2, value2);
        }
        return  queryWrapper;
    }

}