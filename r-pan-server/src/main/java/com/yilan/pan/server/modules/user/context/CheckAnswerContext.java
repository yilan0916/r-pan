package com.yilan.pan.server.modules.user.context;

import lombok.Data;

import java.io.Serializable;

/**
 * 校验用户密码答案的上下文实体对象
 *
 * @author yilan0916
 * @date 2024/7/28
 */
@Data
public class CheckAnswerContext implements Serializable {

    private static final long serialVersionUID = -2946988057881956920L;

    /**
     * 用户名称
     */
    private String username;

    /**
     * 密保问题
     */
    private String question;

    /**
     * 密保答案
     */
    private String answer;



}
