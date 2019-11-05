package com.blogserver.userclient.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.blog.common.entity.user.UserVO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 管理员用户修改mapper接口
 * @author zhulinzhong
 * @version 1.0 CreateTime:2019/11/1 18:02
 */
@Mapper
public interface UserUpdateMapper extends BaseMapper<UserVO> {
}
