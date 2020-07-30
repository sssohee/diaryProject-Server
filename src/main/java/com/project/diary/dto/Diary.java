package com.project.diary.dto;

import lombok.Data;

@Data
public class Diary {
    private int diary_idx; //글 고유 번호
    private int user_idx; //회원 고유 번호
    private String diary_subject; //글 주제
    private String diary_contents; //글 내용
    private String diary_image; //글 이미지
    private String diary_date; //글 작성일
    private String diary_open; //공개, 비공개
    private int diary_status; //default:0, 관리자가 글 삭제시:1

}