package com.blogserver.userclient.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.blog.common.entity.user.UserVO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 创建用户的mapper层
 * @author zhulinzhong
 * @version 1.0 CreateTime:2019/11/4 9:53
 */
@Mapper
public interface UserCreateMapper extends BaseMapper<UserVO>{
}
