package com.yilan.pan.server.modules.user.context;

import com.yilan.pan.server.modules.user.entity.RPanUser;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * 校验用户密码答案的上下文实体对象
 *
 * @author yilan0916
 * @date 2024/7/28
 */
@Data
public class ChangePasswordContext implements Serializable {

    private static final long serialVersionUID = -2843382070956928058L;

    /**
     * 当前登录的用户Id
     */
    private Long userId;

    /**
     * j旧密码
     */
    private String oldPassword;

    /**
     * 新密码
     */
    private String newPassword;

    /**
     * 当前登录用户的实体信息
     */
    private RPanUser entity;


}
