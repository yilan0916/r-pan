package com.yilan.pan.server.modules.user.po;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * 校验用户名称PO对象
 *
 * @author yilan0916
 * @date 2024/7/28
 */
@Data
@ApiModel(value = "用户忘记密码-校验用户名参数")
public class CheckUsernamePO implements Serializable {

    private static final long serialVersionUID = -3349702220659001471L;

    @ApiModelProperty(value = "用户名", required = true, position = 1)
    @NotBlank(message = "用户名不能为空")
    @Pattern(regexp = "^[0-9A-Za-z]{6,16}$", message = "请输入6-16位只包含数字和字母的用户名")
    private String username;



}
