package com.yilan.pan.server.modules.user.controller;

import com.yilan.pan.core.response.R;
import com.yilan.pan.core.utils.IdUtil;
import com.yilan.pan.server.common.annotation.LoginIgnore;
import com.yilan.pan.server.common.utils.UserIdUtil;
import com.yilan.pan.server.modules.user.context.*;
import com.yilan.pan.server.modules.user.converter.UserConverter;
import com.yilan.pan.server.modules.user.po.*;
import com.yilan.pan.server.modules.user.service.IUserService;
import com.yilan.pan.server.modules.user.vo.UserInfoVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 该类是用户模块的控制器实体
 *
 * @author yilan0916
 * @date 2024/7/28
 */
@Api(tags = "用户模块")
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final IUserService iUserService;
    private final UserConverter userConverter;

    @ApiOperation(
            value = "用户注册接口",
            notes = "该接口提供类用户注册的功能，实现类幂等性注册的逻辑，可以放心多并发调用",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    @LoginIgnore
    @PostMapping("/register")
    public R register(@Validated @RequestBody UserRegisterPO userRegisterPO) {
        UserRegisterContext userRegisterContext = userConverter.userRegisterPO2userRegisterContext(userRegisterPO);
        Long userId = iUserService.register(userRegisterContext);
        return R.success(IdUtil.encrypt(userId));
    }

    @ApiOperation(
            value = "用户登录接口",
            notes = "该接口提供类用户登录的功能，成功登陆之后，会返回有时效性的accessToken供后续服务使用",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    @LoginIgnore
    @PostMapping("/login")
    public R login(@Validated @RequestBody UserLoginPO userLoginPO) {
        UserLoginContext userLoginContext = userConverter.userLoginPO2userLoginContext(userLoginPO);
        String accessToken = iUserService.login(userLoginContext);
        return R.success(accessToken);
    }

    @ApiOperation(
            value = "用户登出接口",
            notes = "该接口提供了用户登出的功能",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    @PostMapping("/logout")
    public R logout() {
        iUserService.logout(UserIdUtil.get());
        return R.success();
    }

    @ApiOperation(
            value = "用户忘记密码-校验用户名",
            notes = "该接口提供了忘记密码-校验用户名的功能",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    @LoginIgnore
    @PostMapping("/username/check")
    public R checkUsername(@Validated @RequestBody CheckUsernamePO checkUsernamePO) {
        CheckUsernameContext checkUsernameContext = userConverter.checkUsernamePO2checkUsernameContext(checkUsernamePO);
        String question = iUserService.checkUsername(checkUsernameContext);
        return R.success(question);
    }

    @ApiOperation(
            value = "用户忘记密码-校验密保答案",
            notes = "该接口提供了忘记密码-校验密保答案的功能",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    @LoginIgnore
    @PostMapping("/answer/check")
    public R checkAnswer(@Validated @RequestBody CheckAnswerPO checkAnswerPO) {
        CheckAnswerContext checkAnswerContext = userConverter.checkAnswerPO2checkAnswerContext(checkAnswerPO);
        String token = iUserService.checkAnswer(checkAnswerContext);
        return R.success(token);
    }

    @ApiOperation(
            value = "用户忘记密码-重置新密码",
            notes = "该接口提供了忘记密码-重置新密码的功能",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    @PostMapping("/password/reset")
    @LoginIgnore
    public R resetPassword(@Validated @RequestBody ResetPasswordPO resetPasswordPO) {
        ResetPasswordContext resetPasswordContext = userConverter.resetPasswordPO2resetPasswordContext(resetPasswordPO);
        iUserService.resetPassword(resetPasswordContext);
        return R.success();
    }

    @ApiOperation(
            value = "用户在线修改密码",
            notes = "该接口提供了用户在线修改密码的功能",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    @PostMapping("/password/change")
    public R changePassword(@Validated @RequestBody ChangePasswordPO changePasswordPO) {
        ChangePasswordContext changePasswordContext = userConverter.changePasswordPO2changePasswordContext(changePasswordPO);
        changePasswordContext.setUserId(UserIdUtil.get());
        iUserService.changePassword(changePasswordContext);
        return R.success();
    }

    @ApiOperation(
            value = "查询登录用户的基本信息",
            notes = "该接口提供了查询登录用户的基本信息的功能",
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    @GetMapping("/")
    public R info() {
        UserInfoVO userInfoVO = iUserService.info(IdUtil.get());
        return R.success(userInfoVO);
    }


}
