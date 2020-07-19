package com.project.diary.service;

import com.project.diary.dto.User;
import com.project.diary.model.DefaultResponse;
import com.project.diary.model.SignUpReq;
import com.project.diary.model.UserModifyReq;

import java.util.List;

public interface UserServiceInter {

    DefaultResponse saveUser(final SignUpReq signUpReq);
    DefaultResponse checkNickName(final String user_nickname);
    DefaultResponse checkEmail(final String user_email);
    DefaultResponse getData(final int user_idx);
    DefaultResponse userList();
    DefaultResponse updateUser(final UserModifyReq userModifyReq, final int user_idx);
    DefaultResponse updatePass(final String user_password, final int user_idx);
    DefaultResponse deleteUser(final int user_idx);
}
