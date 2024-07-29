package com.yilan.pan.server.modules.user.context;

import lombok.Data;

import java.io.Serializable;

/**
 * 重置用户密码的上下文信息对象
 *
 * @author yilan0916
 * @date 2024/7/28
 */
@Data
public class ResetPasswordContext implements Serializable {

    private static final long serialVersionUID = -6481229411045942558L;

    /**
     * 用户名称
     */
    private String username;

    /**
     * 用户新密码
     */
    private String password;

    /**
     * 重置密码的token
     */
    private String token;

}
