package com.project.diary.utils;

public class Status {
    public static final int OK = 200; //성공
    public static final int CREATED = 201; //추가 성공
    public static final int NO_CONTENT = 204; //내용 없음
    public static final int BAD_REQUEST = 400; //실패
    public static final int NOT_FOUND = 404; //찾을 수 없음

    public static final int INTERNAL_SERVER_ERROR = 500; //서버 내부 에러
    public static final int DB_ERROR = 600; //데이터베이스 에러
}
