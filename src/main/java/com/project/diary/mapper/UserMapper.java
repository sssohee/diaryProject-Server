package com.project.diary.mapper;

import com.project.diary.dto.User;
import com.project.diary.model.SignUpReq;
import com.project.diary.model.UserModifyReq;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {
    //회원가입
    @Insert("insert into user values(0,#{signUpReq.user_name},#{signUpReq.user_nickname},#{signUpReq.user_email},#{signUpReq.user_phone},#{signUpReq.user_password},0")
    void insert (@Param("signUpReq") final SignUpReq signUpReq);

    //닉네임 중복 체크
    @Select("select count(*) form user where user_nickname=#{user_nickname}")
    int checkNickName (@Param("user_nickname") final String user_nickname);

    //이메일 중복 체크
    @Select("select count(*) form user where user_email=#{user_email}")
    int checkEmail (@Param("user_email") final String user_email);

    //로그인 (반환 값이 없으면 비회원, 0:회원, 1:탈퇴회원, 2:관리자)
    @Select("select user_status from user where user_email=#{user_email} and user_password=#{user_password}")
    int login (@Param("user_email") final String user_email, @Param("user_password") final String user_password);

    //이메일 찾기
    @Select("select user_email from user where user_name=#{user_name} and user_phone=#{user_phone}")
    String findUserEmail (@Param("user_name") final String user_name, @Param("user_phone") final String user_phone);

    //비밀번호 찾기
    @Select("select user_password from user where user_name=#{user_email} and user_phone=#{user_phone}")
    String findUserPassword (@Param("user_email") final String user_email, @Param("user_phone") final String user_phone);

    //이메일로 회원 고유 번호 찾기
    @Select("select user_idx from user where user_email=#{user_email}")
    int getUserIdx (@Param("user_email") final String user_email);

    //회원 개인의 정보 조회
    @Select("select * from user where user_idx=#{user_idx}")
    User userGetData (@Param("user_idx") final int user_idx);

    //회원 전체 조회
    @Select("select * from user order by user_idx")
    List<User> userList ();

    //회원 정보 변경
    @Update("update user set user_name=#{userModifyReq.user_name}, user_nickname=#{userModifyReq.user_nickname}, user_phone=#{userModifyReq.user_phone} where user_idx=#{user_idx}")
    void update(@Param("userModifyReq") final UserModifyReq userModifyReq, @Param("user_idx") final int user_idx);

    //회원 비밀번호 변경
    @Update("update user set user_password=#{user_password} where user_idx=#{user_idx}")
    void updatePass(@Param("user_password") final String user_password, @Param("user_idx") final int user_idx);

    //회원 탈퇴
    @Update("update user set user_status=1 where user_idx=#{user_idx}")
    void delete(@Param("user_idx") final int user_idx);
}
