package com.yilan.pan.server.modules.user.context;

import com.yilan.pan.server.modules.user.entity.RPanUser;
import lombok.Data;

import java.io.Serializable;

/**
 * 用户登录业务的上下文实体对象
 *
 * @author yilan0916
 * @date 2024/7/28
 */
@Data
public class UserLoginContext implements Serializable {

    private static final long serialVersionUID = -6915098058699626519L;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 用户实体对象
     */
    private RPanUser entity;

    /**
     * 登录成功之后的凭证信息
     */
    private String accessToken;
}
