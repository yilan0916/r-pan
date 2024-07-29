package com.yilan.pan.server.modules.user.converter;

import com.yilan.pan.server.modules.file.entity.RPanUserFile;
import com.yilan.pan.server.modules.user.context.*;
import com.yilan.pan.server.modules.user.entity.RPanUser;
import com.yilan.pan.server.modules.user.po.*;
import com.yilan.pan.server.modules.user.vo.UserInfoVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * 用户模块实体转化工具类
 *
 * @author yilan0916
 * @date 2024/7/28
 */
@Mapper(componentModel = "spring")
public interface UserConverter {

    /**
     * UserRegisterPO转化成UserRegisterContext
     *
     * @param userRegisterPO
     * @return
     */
    UserRegisterContext userRegisterPO2userRegisterContext(UserRegisterPO userRegisterPO);

    /**
     * UserRegisterContext转化成RPanUser
     *
     * @param userRegisterContext
     * @return
     */
    @Mapping(target = "password", ignore = true)
    RPanUser userRegisterContext2rPanUser(UserRegisterContext userRegisterContext);

    /**
     * UserLoginPO转化成UserLoginContext
     *
     * @param userLoginPO
     * @return
     */
    UserLoginContext userLoginPO2userLoginContext(UserLoginPO userLoginPO);

    /**
     * CheckUsernamePO转化成CheckUsernameContext
     *
     * @param checkUsernamePO
     * @return
     */
    CheckUsernameContext checkUsernamePO2checkUsernameContext(CheckUsernamePO checkUsernamePO);

    /**
     * CheckAnswerPO转化为CheckAnswerContext
     *
     * @param checkAnswerPO
     * @return
     */
    CheckAnswerContext checkAnswerPO2checkAnswerContext(CheckAnswerPO checkAnswerPO);

    /**
     * ResetPasswordPO转化为ResetPasswordContext
     *
     * @param resetPasswordPO
     * @return
     */
    ResetPasswordContext resetPasswordPO2resetPasswordContext(ResetPasswordPO resetPasswordPO);

    /**
     * ChangePasswordPO转换为ChangePasswordContext
     *
     * @param changePasswordPO
     * @return
     */
    ChangePasswordContext changePasswordPO2changePasswordContext(ChangePasswordPO changePasswordPO);

    /**
     * 拼装用户基本信息返回实体
     *
     * @param rPanUser
     * @param rPanUserFile
     * @return
     */
    @Mapping(source = "rPanUser.username", target = "username")
    @Mapping(source = "rPanUserFile.fileId", target = "rootFileId")
    @Mapping(source = "rPanUserFile.filename", target = "rootFilename")
    UserInfoVO assembleUserInfoVO(RPanUser rPanUser, RPanUserFile rPanUserFile);
}
