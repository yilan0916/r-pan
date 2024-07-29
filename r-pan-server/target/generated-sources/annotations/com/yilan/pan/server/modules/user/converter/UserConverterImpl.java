package com.yilan.pan.server.modules.user.converter;

import com.yilan.pan.server.modules.file.entity.RPanUserFile;
import com.yilan.pan.server.modules.user.context.ChangePasswordContext;
import com.yilan.pan.server.modules.user.context.CheckAnswerContext;
import com.yilan.pan.server.modules.user.context.CheckUsernameContext;
import com.yilan.pan.server.modules.user.context.ResetPasswordContext;
import com.yilan.pan.server.modules.user.context.UserLoginContext;
import com.yilan.pan.server.modules.user.context.UserRegisterContext;
import com.yilan.pan.server.modules.user.entity.RPanUser;
import com.yilan.pan.server.modules.user.po.ChangePasswordPO;
import com.yilan.pan.server.modules.user.po.CheckAnswerPO;
import com.yilan.pan.server.modules.user.po.CheckUsernamePO;
import com.yilan.pan.server.modules.user.po.ResetPasswordPO;
import com.yilan.pan.server.modules.user.po.UserLoginPO;
import com.yilan.pan.server.modules.user.po.UserRegisterPO;
import com.yilan.pan.server.modules.user.vo.UserInfoVO;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-07-29T23:48:48+0800",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 1.8.0_372 (Azul Systems, Inc.)"
)
@Component
public class UserConverterImpl implements UserConverter {

    @Override
    public UserRegisterContext userRegisterPO2userRegisterContext(UserRegisterPO userRegisterPO) {
        if ( userRegisterPO == null ) {
            return null;
        }

        UserRegisterContext userRegisterContext = new UserRegisterContext();

        userRegisterContext.setUsername( userRegisterPO.getUsername() );
        userRegisterContext.setPassword( userRegisterPO.getPassword() );
        userRegisterContext.setQuestion( userRegisterPO.getQuestion() );
        userRegisterContext.setAnswer( userRegisterPO.getAnswer() );

        return userRegisterContext;
    }

    @Override
    public RPanUser userRegisterContext2rPanUser(UserRegisterContext userRegisterContext) {
        if ( userRegisterContext == null ) {
            return null;
        }

        RPanUser rPanUser = new RPanUser();

        rPanUser.setUsername( userRegisterContext.getUsername() );
        rPanUser.setQuestion( userRegisterContext.getQuestion() );
        rPanUser.setAnswer( userRegisterContext.getAnswer() );

        return rPanUser;
    }

    @Override
    public UserLoginContext userLoginPO2userLoginContext(UserLoginPO userLoginPO) {
        if ( userLoginPO == null ) {
            return null;
        }

        UserLoginContext userLoginContext = new UserLoginContext();

        userLoginContext.setUsername( userLoginPO.getUsername() );
        userLoginContext.setPassword( userLoginPO.getPassword() );

        return userLoginContext;
    }

    @Override
    public CheckUsernameContext checkUsernamePO2checkUsernameContext(CheckUsernamePO checkUsernamePO) {
        if ( checkUsernamePO == null ) {
            return null;
        }

        CheckUsernameContext checkUsernameContext = new CheckUsernameContext();

        checkUsernameContext.setUsername( checkUsernamePO.getUsername() );

        return checkUsernameContext;
    }

    @Override
    public CheckAnswerContext checkAnswerPO2checkAnswerContext(CheckAnswerPO checkAnswerPO) {
        if ( checkAnswerPO == null ) {
            return null;
        }

        CheckAnswerContext checkAnswerContext = new CheckAnswerContext();

        checkAnswerContext.setQuestion( checkAnswerPO.getQuestion() );
        checkAnswerContext.setAnswer( checkAnswerPO.getAnswer() );

        return checkAnswerContext;
    }

    @Override
    public ResetPasswordContext resetPasswordPO2resetPasswordContext(ResetPasswordPO resetPasswordPO) {
        if ( resetPasswordPO == null ) {
            return null;
        }

        ResetPasswordContext resetPasswordContext = new ResetPasswordContext();

        resetPasswordContext.setUsername( resetPasswordPO.getUsername() );
        resetPasswordContext.setPassword( resetPasswordPO.getPassword() );
        resetPasswordContext.setToken( resetPasswordPO.getToken() );

        return resetPasswordContext;
    }

    @Override
    public ChangePasswordContext changePasswordPO2changePasswordContext(ChangePasswordPO changePasswordPO) {
        if ( changePasswordPO == null ) {
            return null;
        }

        ChangePasswordContext changePasswordContext = new ChangePasswordContext();

        changePasswordContext.setOldPassword( changePasswordPO.getOldPassword() );
        changePasswordContext.setNewPassword( changePasswordPO.getNewPassword() );

        return changePasswordContext;
    }

    @Override
    public UserInfoVO assembleUserInfoVO(RPanUser rPanUser, RPanUserFile rPanUserFile) {
        if ( rPanUser == null && rPanUserFile == null ) {
            return null;
        }

        UserInfoVO userInfoVO = new UserInfoVO();

        if ( rPanUser != null ) {
            userInfoVO.setUsername( rPanUser.getUsername() );
        }
        if ( rPanUserFile != null ) {
            userInfoVO.setRootFileId( rPanUserFile.getFileId() );
            userInfoVO.setRootFilename( rPanUserFile.getFilename() );
        }

        return userInfoVO;
    }
}
