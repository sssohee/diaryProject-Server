package com.project.diary.model;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class DiaryAddReq {
    private int user_idx; //작성한 회원의 고유번호
    private String diary_subject; //글 주제
    private String diary_contents; //글 내용
    private MultipartFile diary_image; //이미지
    private String diary_open; //공개여부
}
