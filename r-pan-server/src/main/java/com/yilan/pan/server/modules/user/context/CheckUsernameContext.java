package com.yilan.pan.server.modules.user.context;

import lombok.Data;

import java.io.Serializable;

/**
 * 校验用户名称的上下文实体对象
 *
 * @author yilan0916
 * @date 2024/7/28
 */
@Data
public class CheckUsernameContext implements Serializable {

    private static final long serialVersionUID = 4184561030852979996L;

    /**
     * 用户名称
     */
    private String username;


}
