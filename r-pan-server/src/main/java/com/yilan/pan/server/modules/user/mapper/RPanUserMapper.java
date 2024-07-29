package com.yilan.pan.server.modules.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yilan.pan.server.modules.user.context.CheckUsernameContext;
import com.yilan.pan.server.modules.user.entity.RPanUser;
import org.apache.ibatis.annotations.Param;

/**
* @author yilan
* @description 针对表【r_pan_user(用户信息表)】的数据库操作Mapper
* @createDate 2024-07-27 00:15:50
* @Entity com.yilan.pan.server.modules.user.entity.RPanUser
*/
public interface RPanUserMapper extends BaseMapper<RPanUser> {

    /**
     * 通过用户名查询用户设置的密保问题
     *
     * @param username
     * @return
     */
    String selectQuestionByUsername(@Param("username") String username);
}




