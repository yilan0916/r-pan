package com.yilan.pan.server.modules.user.po;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * 注册用户参数实体对象
 *
 * @author yilan0916
 * @date 2024/7/28
 */
@Data
@ApiModel(value = "用户登录参数")
public class UserLoginPO implements Serializable {

    private static final long serialVersionUID = 5804059644206480712L;

    @ApiModelProperty(value = "用户名", required = true, position = 1)
    @NotBlank(message = "用户名不能为空")
    @Pattern(regexp = "^[0-9A-Za-z]{6,16}$", message = "请输入6-16位只包含数字和字母的用户名")
    private String username;

    @ApiModelProperty(value = "密码", required = true, position = 2)
    @NotBlank(message = "密码不能为空")
    @Length(min = 8, max = 16, message = "请输入8-16位的密码")
    private String password;

}
