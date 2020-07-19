package com.project.diary.utils;

public class Message {
    public static final String LOGIN_SUCCESS = "로그인 성공";
    public static final String LOGIN_FAIL = "일치하는 아이디와 비밀번호가 없음";

    public static final String SIGN_UP_SUCCESS = "회원 가입 성공";
    public static final String SIGN_UP_FAIL = "회원 가입 실패";
    public static final String CHECK_SUCCESS = "사용 가능";
    public static final String EMAIL_WITHDRAWAL = "사용 불가. 탈퇴 EMAIL";
    public static final String EMAIL_DUPLICATION = "사용 불가. EMAIL 중복";
    public static final String NICK_DUPLICATION = "사용 불가. NICK 중복";

    public static final String MODIFY_USER_SUCCESS = "회원 정보 수정 성공";
    public static final String MODIFY_USER_FAIL = "회원 정보 수정 실패";
    public static final String MODIFY_USER_PASSWORD_SUCCESS = "회원 비밀번호 수정 성공";
    public static final String MODIFY_USER_PASSWORD_FAIL = "회원 비밀번호 수정 실패";
    
    public static final String DELETE_USER_SUCCESS = "회원 탈퇴 성공";
    public static final String DELETE_USER_FAIL = "회원 탈퇴 실패";
    
    public static final String NO_CONTENT = "내용 없음";
    public static final String FORBIDDEN = "권한 없음";

    public static final String FIND_ADMIN_SUCCESS = "관리자 정보 조회 성공";
    public static final String FIND_ADMIN_FAIL = "회원을 찾을 수 없습니다";
    public static final String FIND_USER_SUCCESS = "회원 정보 조회 성공";
    public static final String FIND_USER_FAIL = "회원을 찾을 수 없습니다";

    public static final String INTERNAL_SERVER_ERROR = "서버 내부 에러";
    public static final String DB_ERROR = "데이터베이스 에러";
}
