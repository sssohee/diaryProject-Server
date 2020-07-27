package com.project.diary.model;

import lombok.Data;

@Data
public class DiaryModifyReq {
    private String diary_contents; //글 내용
    private String diary_image; //이미지
    private String diary_open; //공개여부
}
