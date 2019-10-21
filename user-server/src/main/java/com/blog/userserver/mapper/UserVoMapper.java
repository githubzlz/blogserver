package com.blog.userserver.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.blog.common.entity.user.UserVO;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author zhulinzhong
 * @version 1.0 CreateTime:2019/10/21 9:55
 */

@DS("user")
@Mapper
public interface UserVoMapper extends BaseMapper<UserVO> {
}
