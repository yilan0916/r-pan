package com.yilan.pan.server.modules.user;

import cn.hutool.core.lang.Assert;
import com.yilan.pan.core.exception.RPanBusinessException;
import com.yilan.pan.core.utils.JwtUtil;
import com.yilan.pan.server.RPanServerLauncher;
import com.yilan.pan.server.modules.user.constants.UserConstants;
import com.yilan.pan.server.modules.user.context.*;
import com.yilan.pan.server.modules.user.entity.RPanUser;
import com.yilan.pan.server.modules.user.service.IUserService;
import com.yilan.pan.server.modules.user.vo.UserInfoVO;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 用户模块单元测试类
 *
 * @author yilan0916
 * @date 2024/7/28
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = RPanServerLauncher.class)
@Transactional
public class UserTest {

    @Autowired
    private IUserService iUserService;

    @Test
    public void testMp() {
        List<RPanUser> list = iUserService.list(null);
        for (RPanUser rPanUser : list) {
            System.out.println(rPanUser);
        }
    }

    /**
     * 测试成功注册用户信息
     */
    @Test
    public void testRegisterUser() {
        UserRegisterContext context = createUserRegisterContext();
        Long register = iUserService.register(context);
        Assert.isTrue(register > 0L);
    }

    /**
     * 测试重复用户名称
     */
    @Test(expected = RPanBusinessException.class)
    public void testRegisterDuplicateUsername() {
        UserRegisterContext context = createUserRegisterContext();
        Long register = iUserService.register(context);
        System.out.println(register);
        Assert.isTrue(register > 0L);
        Long register2 = iUserService.register(context);
        System.out.println(register2);
    }

    /**
     * 测试登录成功
     */
    @Test
    public void loginSuccess() {
        UserRegisterContext context = createUserRegisterContext();
        Long register = iUserService.register(context);
        System.out.println(register);
        Assert.isTrue(register > 0L);
        RPanUser user = iUserService.getById(register);
        System.out.println(user);

        UserLoginContext userLoginContext = createUserLoginContext();
        String accessToken = iUserService.login(userLoginContext);
        System.out.println(accessToken);
        Assert.isTrue(StringUtils.isNoneBlank(accessToken));
    }

    /**
     * 测试登录失败：用户名不正确
     */
    @Test(expected = RPanBusinessException.class)
    public void wrongUsername() {
        UserRegisterContext context = createUserRegisterContext();
        Long register = iUserService.register(context);
        Assert.isTrue(register > 0L);

        UserLoginContext userLoginContext = createUserLoginContext();
        userLoginContext.setUsername(userLoginContext.getUsername() + "_change");
        iUserService.login(userLoginContext);
    }

    /**
     * 测试登录失败：密码错误
     */
    @Test(expected = RPanBusinessException.class)
    public void wrongPassword() {
        UserRegisterContext context = createUserRegisterContext();
        Long register = iUserService.register(context);
        Assert.isTrue(register > 0L);

        UserLoginContext userLoginContext = createUserLoginContext();
        userLoginContext.setPassword(userLoginContext.getPassword() + "_change");
        iUserService.login(userLoginContext);
    }

    /**
     * 测试用户退出
     */
    @Test
    public void logoutSuccess() {
        UserRegisterContext context = createUserRegisterContext();
        Long register = iUserService.register(context);
        Assert.isTrue(register > 0L);

        UserLoginContext userLoginContext = createUserLoginContext();
        String accessToken = iUserService.login(userLoginContext);
        Assert.isTrue(StringUtils.isNoneBlank(accessToken));

        Long userId = (Long) JwtUtil.analyzeToken(accessToken, UserConstants.LOGIN_USER_ID);
        iUserService.logout(userId);
    }

    /**
     * 校验用户名通过
     */
    @Test
    public void checkUsernameSuccess() {
        UserRegisterContext context = createUserRegisterContext();
        Long register = iUserService.register(context);
        Assert.isTrue(register > 0L);

        CheckUsernameContext checkUsernameContext = new CheckUsernameContext();
        checkUsernameContext.setUsername(USERNAME);
        String question = iUserService.checkUsername(checkUsernameContext);
        Assert.isTrue(StringUtils.isNoneBlank(question));
    }

    /**
     * 校验用户名失败-没有查询到该用户
     */
    @Test(expected = RPanBusinessException.class)
    public void checkUsernameNotExist() {
        UserRegisterContext context = createUserRegisterContext();
        Long register = iUserService.register(context);
        Assert.isTrue(register > 0L);

        CheckUsernameContext checkUsernameContext = new CheckUsernameContext();
        checkUsernameContext.setUsername(USERNAME + "_change");
        iUserService.checkUsername(checkUsernameContext);
    }

    /**
     * 校验用户密保问题答案通过
     */
    @Test
    public void checkAnswerSuccess() {
        UserRegisterContext context = createUserRegisterContext();
        Long register = iUserService.register(context);
        Assert.isTrue(register > 0L);

        CheckAnswerContext checkAnswerContext = new CheckAnswerContext();
        checkAnswerContext.setUsername(USERNAME);
        checkAnswerContext.setQuestion(QUESTION);
        checkAnswerContext.setAnswer(ANSWER);

        String token = iUserService.checkAnswer(checkAnswerContext);
        Assert.isTrue(StringUtils.isNoneBlank(token));
    }

    /**
     * 校验用户密保问题答案失败
     */
    @Test(expected = RPanBusinessException.class)
    public void checkAnswerFail() {
        UserRegisterContext context = createUserRegisterContext();
        Long register = iUserService.register(context);
        Assert.isTrue(register > 0L);

        CheckAnswerContext checkAnswerContext = new CheckAnswerContext();
        checkAnswerContext.setUsername(USERNAME);
        checkAnswerContext.setQuestion(QUESTION);
        checkAnswerContext.setAnswer(ANSWER + "_change");

        iUserService.checkAnswer(checkAnswerContext);
    }

    /**
     * 正常重置用户密码
     */
    @Test
    public void resetPasswordSuccess() {
        UserRegisterContext context = createUserRegisterContext();
        Long register = iUserService.register(context);
        Assert.isTrue(register > 0L);

        CheckAnswerContext checkAnswerContext = new CheckAnswerContext();
        checkAnswerContext.setUsername(USERNAME);
        checkAnswerContext.setQuestion(QUESTION);
        checkAnswerContext.setAnswer(ANSWER);

        String token = iUserService.checkAnswer(checkAnswerContext);
        Assert.isTrue(StringUtils.isNoneBlank(token));

        ResetPasswordContext resetPasswordContext = new ResetPasswordContext();
        resetPasswordContext.setUsername(USERNAME);
        resetPasswordContext.setPassword(PASSWORD + "_change");
        resetPasswordContext.setToken(token);

        iUserService.resetPassword(resetPasswordContext);
    }

    /**
     * 用户重置密码失败，token异常
     */
    @Test(expected = RPanBusinessException.class)
    public void resetPasswordTokenError() {
        UserRegisterContext context = createUserRegisterContext();
        Long register = iUserService.register(context);
        Assert.isTrue(register > 0L);

        CheckAnswerContext checkAnswerContext = new CheckAnswerContext();
        checkAnswerContext.setUsername(USERNAME);
        checkAnswerContext.setQuestion(QUESTION);
        checkAnswerContext.setAnswer(ANSWER);

        String token = iUserService.checkAnswer(checkAnswerContext);
        Assert.isTrue(StringUtils.isNoneBlank(token));

        ResetPasswordContext resetPasswordContext = new ResetPasswordContext();
        resetPasswordContext.setUsername(USERNAME);
        resetPasswordContext.setPassword(PASSWORD + "_change");
        resetPasswordContext.setToken(token + "_change");

        iUserService.resetPassword(resetPasswordContext);
    }

    /**
     * 正常在线修改密码
     */
    @Test
    public void changePasswordSuccess() {
        UserRegisterContext context = createUserRegisterContext();
        Long register = iUserService.register(context);
        Assert.isTrue(register > 0L);

        ChangePasswordContext changePasswordContext = new ChangePasswordContext();
        changePasswordContext.setUserId(register);
        changePasswordContext.setOldPassword(PASSWORD);
        changePasswordContext.setNewPassword(PASSWORD + "_change");

        iUserService.changePassword(changePasswordContext);
    }

    /**
     * 在修修改密码错误-旧密码错误
     */
    @Test(expected = RPanBusinessException.class)
    public void changePasswordFailByWrongOldPassword() {
        UserRegisterContext context = createUserRegisterContext();
        Long register = iUserService.register(context);
        Assert.isTrue(register > 0L);

        ChangePasswordContext changePasswordContext = new ChangePasswordContext();
        changePasswordContext.setUserId(register);
        changePasswordContext.setOldPassword(PASSWORD + "_change");
        changePasswordContext.setNewPassword(PASSWORD + "_change");

        iUserService.changePassword(changePasswordContext);
    }

    @Test
    public void queryUserInfo() {
        UserRegisterContext context = createUserRegisterContext();
        Long register = iUserService.register(context);
        Assert.isTrue(register > 0L);

        UserInfoVO userInfoVO = iUserService.info(register);
        Assert.notNull(userInfoVO);
    }

    /*************************************private*********************************************/

    private final static String USERNAME = "yilan";
    private final static String PASSWORD = "abcd1234";
    private final static String QUESTION = "question";
    private final static String ANSWER = "answer";

    /**
     * 构建注册用户上下文信息
     */
    private UserRegisterContext createUserRegisterContext() {
        UserRegisterContext context = new UserRegisterContext();
        context.setUsername(USERNAME);
        context.setPassword(PASSWORD);
        context.setQuestion(QUESTION);
        context.setAnswer(ANSWER);
        return context;
    }

    /**
     * 构建用户登录上下文信息
     */
    private UserLoginContext createUserLoginContext() {
        UserLoginContext userLoginContext = new UserLoginContext();
        userLoginContext.setUsername(USERNAME);
        userLoginContext.setPassword(PASSWORD);
        return userLoginContext;
    }
}
