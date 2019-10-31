package com.blog.userserver.controller;

import com.blog.common.constants.UserConstants;
import com.blog.userserver.server.UserQueryService;
import com.blog.common.entity.user.UserVO;
import com.blog.common.result.ResultSet;
import com.blog.common.result.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 查询用户信息操作controller层
 * @author zhulinzhong
 * @version 1.0 CreateTime:2019/10/21 11:41
 */
@RestController
@RequestMapping("/query")
public class UserQueryController {

    @Autowired
    private UserQueryService userServer;

    @RequestMapping("test")
    public String test(){
        return "USER-SERVER";
    }
    /**
     * 普通用户查询
     * @param pageInfo
     * @return
     */
    @PostMapping("/public_user_page")
    public ResultSet getPublicUserPage(@RequestBody PageInfo<UserVO> pageInfo){
        return userServer.getUserPage(pageInfo, UserConstants.IS_NOT_ADMIN);
    }

    /**
     * 所有用户查询
     * @param pageInfo
     * @return
     */
    @PostMapping("/all_user_page")
    public ResultSet getAllUserPage(@RequestBody PageInfo<UserVO> pageInfo){
        return userServer.getUserPage(pageInfo, null);
    }

    /**
     * 管理员查询
     * @param pageInfo
     * @return
     */
    @PostMapping("/admin_page")
    public ResultSet getAdminPage(@RequestBody PageInfo<UserVO> pageInfo){
        return userServer.getUserPage(pageInfo, UserConstants.IS_ADMIN);
    }

    /**
     * 普通用户查询
     * @param userVO
     * @return
     */
    @PostMapping("/public_user_info_id")
    public ResultSet getUserInfo(@RequestBody UserVO userVO){
        return userServer.getUserById(userVO, UserConstants.IS_NOT_ADMIN);
    }

    /**
     * 所有用户查询
     * @param userVO
     * @return
     */
    @PostMapping("/all_user_info_id")
    public ResultSet getAllUserInfo(@RequestBody UserVO userVO){
        return userServer.getUserById(userVO, null);
    }

    /**
     * 管理员查询
     * @param userVO
     * @return
     */
    @PostMapping("/admin_info_id")
    public ResultSet getAdminInfo(@RequestBody UserVO userVO) {
        return userServer.getUserById(userVO, UserConstants.IS_ADMIN);
    }

    /**
     * 普通用户查询
     * @param userVO
     * @return
     */
    @PostMapping("/public_user_info_username")
    public ResultSet getUserList(@RequestBody UserVO userVO){
        return userServer.getUserByUserName(userVO, UserConstants.IS_NOT_ADMIN);
    }

    /**
     * 所有用户查询
     * @param userVO
     * @return
     */
    @PostMapping("/all_user_info_username")
    public ResultSet getAllUserList(@RequestBody UserVO userVO){
        return userServer.getUserByUserName(userVO, null);
    }

    /**
     * 管理员查询
     * @param userVO
     * @return
     */
    @PostMapping("/admin_info_username")
    public ResultSet getAdminList(@RequestBody UserVO userVO){
        return userServer.getUserByUserName(userVO, UserConstants.IS_ADMIN);
    }
}
