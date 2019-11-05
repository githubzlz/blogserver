package com.blogserver.userclient.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.blog.common.entity.user.UserVO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 查询用户信息的mapper
 * @author zhulinzhong
 * @version 1.0 CreateTime:2019/11/4 11:25
 */
@Mapper
public interface UserQueryMapper extends BaseMapper<UserVO> {
}
