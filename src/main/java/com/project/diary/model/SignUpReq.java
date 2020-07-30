package com.project.diary.model;

import lombok.Data;

@Data
public class SignUpReq {
    private String user_name; //회원 이름
    private String user_nickname; //회원 닉네임
    private String user_email; //회원 이메일
    private String user_phone; //회원 휴대 번호
    private String user_password; //회원 비밀 번호
}
