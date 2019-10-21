package com.blog.userserver.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zlz.common.entity.user.TbUser;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author zhulinzhong
 * @version 1.0 CreateTime:2019/10/21 9:50
 */

@DS("mytestdb")
@Mapper
public interface UserMapper extends BaseMapper<TbUser> {
}
