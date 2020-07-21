package com.project.diary.service.Impl;

import com.project.diary.dto.User;
import com.project.diary.mapper.UserMapper;
import com.project.diary.model.DefaultResponse;
import com.project.diary.model.SignUpReq;
import com.project.diary.model.UserModifyReq;
import com.project.diary.service.UserServiceInter;
import com.project.diary.utils.Message;
import com.project.diary.utils.Status;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements UserServiceInter {
    private UserMapper userMapper;

    //회원 가입
    @Override
    public DefaultResponse saveUser(SignUpReq signUpReq) {
        try {
            // 빈칸 검사
            if(signUpReq.getUser_name().isEmpty() || signUpReq.getUser_nickname().isEmpty() || signUpReq.getUser_email().isEmpty()||
                    signUpReq.getUser_password().isEmpty()||signUpReq.getUser_phone().isEmpty()) {
                return DefaultResponse.res(Status.BAD_REQUEST, Message.SIGN_UP_FAIL);
            }
            userMapper.insert(signUpReq);
            return DefaultResponse.res(Status.CREATED, Message.SIGN_UP_SUCCESS);
        }catch (Exception e){
            return DefaultResponse.res(Status.DB_ERROR, Message.DB_ERROR);
        }
    }

    //nickname 중복 검사
    @Override
    public DefaultResponse checkNickName(String user_nickname) {
        try {
            if(userMapper.checkNickName(user_nickname)!=0)
                return DefaultResponse.res(Status.BAD_REQUEST,Message.NICK_DUPLICATION);
            else if(userMapper.checkNickName(user_nickname)==0)
                return DefaultResponse.res(Status.OK,Message.CHECK_SUCCESS);
            else
                return DefaultResponse.res(Status.NO_CONTENT,Message.NO_CONTENT);
        }catch (Exception e){
            return DefaultResponse.res(Status.INTERNAL_SERVER_ERROR, Message.INTERNAL_SERVER_ERROR);
        }
    }

    //email 중복 검사
    @Override
    public DefaultResponse checkEmail(String user_email) {
        try {
            if(userMapper.checkEmail(user_email)==0)
                return DefaultResponse.res(Status.OK,Message.CHECK_SUCCESS);
            else if(userMapper.checkEmail(user_email)!=0){
                int idx = userMapper.getUserIdx(user_email);
                int status = userMapper.userGetData(idx).getUser_status();
                if(status==2)
                    return DefaultResponse.res(Status.BAD_REQUEST,Message.EMAIL_WITHDRAWAL);
                else
                    return DefaultResponse.res(Status.BAD_REQUEST,Message.EMAIL_DUPLICATION);
            }
            else
                return DefaultResponse.res(Status.NO_CONTENT,Message.NO_CONTENT);
        }catch (Exception e){
            return DefaultResponse.res(Status.INTERNAL_SERVER_ERROR, Message.INTERNAL_SERVER_ERROR);
        }
    }

    //회원의 정보 조회
    @Override
    public DefaultResponse getData(int user_idx) {
        try {
            final User user = userMapper.userGetData(user_idx);
            if(user != null)
                return DefaultResponse.res(Status.OK,Message.FIND_USER_SUCCESS,user);
            else
                return DefaultResponse.res(Status.NOT_FOUND,Message.FIND_USER_FAIL);
        }catch (Exception e){
            return DefaultResponse.res(Status.INTERNAL_SERVER_ERROR,Message.INTERNAL_SERVER_ERROR);
        }
    }

    //회원 list
    @Override
    public DefaultResponse userList() {
        try {
            final List<User> userList = userMapper.userList();
            if(userList != null)
                return DefaultResponse.res(Status.OK,Message.FIND_USER_SUCCESS,userList);
            else
                return DefaultResponse.res(Status.NOT_FOUND,Message.FIND_USER_FAIL);
        }catch (Exception e){
            return DefaultResponse.res(Status.INTERNAL_SERVER_ERROR,Message.INTERNAL_SERVER_ERROR);
        }
    }

    //회원 정보 수정
    @Override
    public DefaultResponse updateUser(UserModifyReq userModifyReq, int user_idx) {
        try {
            // 빈칸 검사
            if(userModifyReq.getUser_name().isEmpty() || userModifyReq.getUser_nickname().isEmpty() ||userModifyReq.getUser_phone().isEmpty()) {
                return DefaultResponse.res(Status.BAD_REQUEST, Message.MODIFY_USER_FAIL);
            }
            userMapper.update(userModifyReq,user_idx);
            return DefaultResponse.res(Status.OK, Message.MODIFY_USER_SUCCESS);
        }catch (Exception e){
            return DefaultResponse.res(Status.DB_ERROR, Message.DB_ERROR);
        }
    }

    //비밀 번호 수정
    @Override
    public DefaultResponse updatePass(String user_password, int user_idx) {
        try {
            // 빈칸 검사
            if(user_password.isEmpty()) {
                return DefaultResponse.res(Status.BAD_REQUEST, Message.MODIFY_USER_PASSWORD_FAIL);
            }
            userMapper.updatePass(user_password,user_idx);
            return DefaultResponse.res(Status.OK, Message.MODIFY_USER_PASSWORD_SUCCESS);
        }catch (Exception e){
            return DefaultResponse.res(Status.DB_ERROR, Message.DB_ERROR);
        }
    }

    //회원 탈퇴
    @Override
    public DefaultResponse deleteUser(int user_idx) {
        try {
            userMapper.delete(user_idx);
            if(userMapper.userGetData(user_idx).getUser_status()!=1){
                return DefaultResponse.res(Status.BAD_REQUEST, Message.DELETE_USER_FAIL);
            }
            return DefaultResponse.res(Status.OK, Message.DELETE_USER_SUCCESS);
        }catch (Exception e){
            return DefaultResponse.res(Status.INTERNAL_SERVER_ERROR,Message.INTERNAL_SERVER_ERROR);
        }
    }
}
