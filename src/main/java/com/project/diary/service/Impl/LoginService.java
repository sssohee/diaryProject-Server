package com.project.diary.service.Impl;

import com.project.diary.mapper.UserMapper;
import com.project.diary.model.DefaultResponse;
import com.project.diary.service.LoginServiceInter;
import com.project.diary.utils.Message;
import com.project.diary.utils.Status;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class LoginService implements LoginServiceInter {

    private UserMapper userMapper;

    //login
    @Override
    public DefaultResponse login(String user_email, String user_password) {
        try {
            int status = userMapper.login(user_email,user_password);
            int user_idx = userMapper.getUserIdx(user_email);

            HashMap<String,Object> map = new HashMap<>();

            if(status == 2) {
                map.put("user_status", "관리자");
                map.put("user_idx", user_idx);
                return DefaultResponse.res(Status.OK, Message.LOGIN_SUCCESS, map);
            }else if(status == 1){
                String user_status = "탈퇴회원";
                return DefaultResponse.res(Status.BAD_REQUEST,Message.LOGIN_FAIL_WITHDRAWAL_MEMBER,user_status);
            }else if(status == 0) {
                map.put("user_status", "회원");
                map.put("user_idx", user_idx);
                return DefaultResponse.res(Status.OK, Message.LOGIN_SUCCESS, map);
            }else
                return DefaultResponse.res(Status.BAD_REQUEST,Message.LOGIN_FAIL);
        }catch (Exception e){
            return DefaultResponse.res(Status.INTERNAL_SERVER_ERROR, Message.INTERNAL_SERVER_ERROR);
        }
    }

    //이메일 찾기
    @Override
    public DefaultResponse findUserEmail(String user_name, String user_phone) {
        try {
            final String user_email = userMapper.findUserEmail(user_name, user_phone);
            if(user_email!=null){
                if(userMapper.getUserStatus(user_email)==1)
                    return DefaultResponse.res(Status.BAD_REQUEST,Message.LOGIN_FAIL_WITHDRAWAL_MEMBER);
                else
                    return DefaultResponse.res(Status.OK, Message.FIND_USER_EMAIL_SUCCESS,user_email);
            }
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
            if(userMapper.findUserPassword(user_email,user_phone)!=null) {
                if(userMapper.getUserStatus(user_email)==1)
                    return DefaultResponse.res(Status.BAD_REQUEST,Message.LOGIN_FAIL_WITHDRAWAL_MEMBER);
                else
                    return DefaultResponse.res(Status.OK, Message.FIND_USER_PASS_SUCCESS);
            }else
                return DefaultResponse.res(Status.BAD_REQUEST,Message.FIND_USER_PASS_FAIL);
        }catch (Exception e){
            return DefaultResponse.res(Status.INTERNAL_SERVER_ERROR, Message.INTERNAL_SERVER_ERROR);
        }
    }
}
