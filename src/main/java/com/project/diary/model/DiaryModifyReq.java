package com.project.diary.model;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class DiaryModifyReq {
    private int diary_idx; //글 고유번호
    private String diary_contents; //글 내용
    private MultipartFile diary_image; //이미지
    private String diary_open; //공개여부
}
