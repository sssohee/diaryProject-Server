package com.project.diary.service;

import com.project.diary.model.DefaultResponse;
import com.project.diary.model.DiaryAddReq;
import com.project.diary.model.DiaryModifyReq;
import org.springframework.web.multipart.MultipartHttpServletRequest;

public interface DiaryServiceInter {
    DefaultResponse insert(final DiaryAddReq diaryAddReq, final String path);
    DefaultResponse update(final DiaryModifyReq diaryModifyReq, final String path);
    DefaultResponse delete(final int diary_idx, final int user_idx, final String path);

    DefaultResponse diaryDateCount(final int user_idx);

    DefaultResponse allDiaryList();

    DefaultResponse userDiaryList(final int user_idx);
    DefaultResponse getDiary(final int diary_idx);

}