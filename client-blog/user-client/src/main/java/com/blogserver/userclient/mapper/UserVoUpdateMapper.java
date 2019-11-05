package com.blogserver.userclient.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.blog.common.entity.user.UserVO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户修改相关mapper
 * @author zhulinzhong
 * @version 1.0 CreateTime:2019/11/1 18:07
 */
@Mapper
public interface UserVoUpdateMapper extends BaseMapper<UserVO> {
}
