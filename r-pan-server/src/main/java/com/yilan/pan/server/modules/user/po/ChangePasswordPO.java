package com.yilan.pan.server.modules.user.po;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * 校验用户密码答案PO对象
 *
 * @author yilan0916
 * @date 2024/7/28
 */
@Data
@ApiModel(value = "用户在线修改密码参数")
public class ChangePasswordPO implements Serializable {

    private static final long serialVersionUID = 44058536536966061L;

    @ApiModelProperty(value = "旧密码", required = true)
    @NotBlank(message = "旧密码不能为空")
    @Length(min = 8, max = 16, message = "请输入8-16位的密码")
    private String oldPassword;

    @ApiModelProperty(value = "新密码", required = true)
    @NotBlank(message = "新密码不能为空")
    @Length(min = 8, max = 16, message = "请输入8-16位的密码")
    private String newPassword;



}
