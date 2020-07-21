package com.project.diary.service.Impl;

import com.project.diary.mapper.UserMapper;
import com.project.diary.model.DefaultResponse;
import com.project.diary.service.LoginServiceInter;
import com.project.diary.utils.Message;
import com.project.diary.utils.Status;
import org.springframework.stereotype.Service;

@Service
public class LoginService implements LoginServiceInter {

    private UserMapper userMapper;

    //login
    @Override
    public DefaultResponse login(String user_email, String user_password) {
        try {
            int status = userMapper.login(user_email,user_password);
            int user_idx = userMapper.getUserIdx(user_email);

            if(status == 2)
                return DefaultResponse.res(Status.OK, Message.LOGIN_SUCCESS_ADMIN,user_idx);
            else if(status == 1)
                return DefaultResponse.res(Status.BAD_REQUEST,Message.LOGIN_FAIL_WITHDRAWAL_MEMBER);
            else if(status == 0)
                return DefaultResponse.res(Status.OK,Message.LOGIN_SUCCESS_USER,user_idx);
            else
                return DefaultResponse.res(Status.BAD_REQUEST,Message.LOGIN_FAIL);
        }catch (Exception e){
            return DefaultResponse.res(Status.INTERNAL_SERVER_ERROR, Message.INTERNAL_SERVER_ERROR);
        }
    }

    //이메일 찾기
    @Override
    public DefaultResponse findUserEmail(String user_name, String user_phone) {
        try {
            if(userMapper.findUserEmail(user_name,user_phone)!=null)
                return DefaultResponse.res(Status.OK, Message.FIND_USER_EMAIL_SUCCESS);
            else
                return DefaultResponse.res(Status.BAD_REQUEST,Message.FIND_USER_EMAIL_FAIL);
        }catch (Exception e){
            return DefaultResponse.res(Status.INTERNAL_SERVER_ERROR, Message.INTERNAL_SERVER_ERROR);
        }
    }

    //비밀번호 찾기
    @Override
    public DefaultResponse findUserPassword(String user_email, String user_phone) {
        try {
            if(userMapper.findUserPassword(user_email,user_phone)!=null)
                return DefaultResponse.res(Status.OK, Message.FIND_USER_PASS_SUCCESS);
            else
                return DefaultResponse.res(Status.BAD_REQUEST,Message.FIND_USER_PASS_FAIL);
        }catch (Exception e){
            return DefaultResponse.res(Status.INTERNAL_SERVER_ERROR, Message.INTERNAL_SERVER_ERROR);
        }
    }
}
