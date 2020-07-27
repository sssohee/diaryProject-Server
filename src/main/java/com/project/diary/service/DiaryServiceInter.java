package com.project.diary.service;

import com.project.diary.model.DefaultResponse;
import com.project.diary.model.DiaryAddReq;
import com.project.diary.model.DiaryModifyReq;

public interface DiaryServiceInter {
    DefaultResponse insert(final DiaryAddReq diaryAddReq);
    DefaultResponse update(final DiaryModifyReq diaryModifyReq, final int diary_idx);
    DefaultResponse delete(final int diary_idx, final int user_idx);

    DefaultResponse diaryDateCount(final int user_idx);

    DefaultResponse allDiaryList();

    DefaultResponse userDiaryList(final int user_idx);
    DefaultResponse getDiary(final int diary_idx);
}