package com.yilan.pan.server.modules.user.context;

import com.yilan.pan.server.modules.user.entity.RPanUser;
import lombok.Data;

import java.io.Serializable;

/**
 * 用户注册业务的上下文实体对象
 *
 * @author yilan0916
 * @date 2024/7/28
 */
@Data
public class UserRegisterContext implements Serializable {
    private static final long serialVersionUID = 6197096580183150272L;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 密保问题
     */
    private String question;

    /**
     * 密保答案
     */
    private String answer;

    /**
     * 用户实体对象
     */
    private RPanUser entity;
}
