package com.project.diary.service.Impl;

import com.project.diary.dto.Diary;
import com.project.diary.mapper.DiaryMapper;
import com.project.diary.model.DefaultResponse;
import com.project.diary.service.DiaryCategoryServiceInter;
import com.project.diary.utils.Message;
import com.project.diary.utils.Status;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DiaryCategoryService implements DiaryCategoryServiceInter {

    private DiaryMapper diaryMapper;

    public DiaryCategoryService(DiaryMapper diaryMapper) {
        this.diaryMapper = diaryMapper;
    }

    //전체 회원의 카테고리 - 일기/주제
    @Override
    public DefaultResponse categoryAll(String diary_subject) {
        try {
            List<Diary> diaryList;

            if(diary_subject.equals("일기"))
                diaryList = diaryMapper.categoryDiaryList();
            else
                diaryList = diaryMapper.categorySubjectList();

            if(diaryList.size()>0)
                return DefaultResponse.res(Status.OK, Message.FIND_DIARY_SUCCESS,diaryList);
            else if(diaryList.size()==0)
                return DefaultResponse.res(Status.NO_CONTENT, Message.NO_CONTENT);
            else
                return DefaultResponse.res(Status.BAD_REQUEST, Message.FIND_DIARY_FAIL);
        } catch (Exception e) {
            return DefaultResponse.res(Status.INTERNAL_SERVER_ERROR, Message.INTERNAL_SERVER_ERROR);
        }
    }

    //회원 - 일기/주제
    @Override
    public DefaultResponse categoryUser(int user_idx, String diary_subject) {
        try {
            List<Diary> diaryList;

            if(diary_subject.equals("일기"))
                diaryList = diaryMapper.userCategoryDiaryList(user_idx);
            else
                diaryList = diaryMapper.userCategorySubjectList(user_idx);

            if(diaryList.size()>0)
                return DefaultResponse.res(Status.OK, Message.FIND_DIARY_SUCCESS,diaryList);
            else if(diaryList.size()==0)
                return DefaultResponse.res(Status.NO_CONTENT, Message.NO_CONTENT);
            else
                return DefaultResponse.res(Status.BAD_REQUEST, Message.FIND_DIARY_FAIL);
        } catch (Exception e) {
            return DefaultResponse.res(Status.INTERNAL_SERVER_ERROR, Message.INTERNAL_SERVER_ERROR);
        }
    }

    //회원 - 공개/비공개
    @Override
    public DefaultResponse categoryOpen(int user_idx, int diary_open) {
        try {


            List<Diary> diaryList = diaryMapper.userCategoryOpenList(user_idx, diary_open);

            if(diaryList.size()>0)
                return DefaultResponse.res(Status.OK, Message.FIND_DIARY_SUCCESS,diaryList);
            else if(diaryList.size()==0)
                return DefaultResponse.res(Status.NO_CONTENT, Message.NO_CONTENT);
            else
                return DefaultResponse.res(Status.BAD_REQUEST, Message.FIND_DIARY_FAIL);
        } catch (Exception e) {
            return DefaultResponse.res(Status.INTERNAL_SERVER_ERROR, Message.INTERNAL_SERVER_ERROR);
        }
    }

    //공감한 글
    @Override
    public DefaultResponse categoryBookmark(int user_idx) {
        try {
            List<Diary> diaryList = diaryMapper.bookmarkDiary(user_idx);

            if(diaryList.size()>0)
                return DefaultResponse.res(Status.OK, Message.FIND_DIARY_SUCCESS,diaryList);
            else if(diaryList.size()==0)
                return DefaultResponse.res(Status.NO_CONTENT, Message.NO_CONTENT);
            else
                return DefaultResponse.res(Status.BAD_REQUEST, Message.FIND_DIARY_FAIL);

        } catch (Exception e) {
            return DefaultResponse.res(Status.INTERNAL_SERVER_ERROR, Message.INTERNAL_SERVER_ERROR);
        }
    }
}
