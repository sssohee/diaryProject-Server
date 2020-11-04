package com.project.diary.service.Impl;

import com.project.diary.mapper.BookmarkMapper;
import com.project.diary.model.DefaultResponse;
import com.project.diary.service.BookmarkServiceInter;
import com.project.diary.utils.Message;
import com.project.diary.utils.Status;
import org.springframework.stereotype.Service;

@Service
public class BookmarkService implements BookmarkServiceInter {

    private BookmarkMapper bookmarkMapper;

    public BookmarkService(BookmarkMapper bookmarkMapper) {
        this.bookmarkMapper = bookmarkMapper;
    }

    //북마크 추가
    @Override
    public DefaultResponse bookmarkInsert(int user_idx, int diary_idx) {
        try {
            if(bookmarkMapper.checkBookmark(user_idx,diary_idx)==0){
                bookmarkMapper.bookmarkInsert(user_idx,diary_idx);
                return DefaultResponse.res(Status.CREATED,Message.ADD_BOOKMARK_SUCCESS);
            }else
                return DefaultResponse.res(Status.BAD_REQUEST,Message.ADD_BOOKMARK_FAIL);
        } catch (Exception e){
            return DefaultResponse.res(Status.DB_ERROR, Message.DB_ERROR);
        }
    }

    //북마크 취소
    @Override
    public DefaultResponse bookmarkDelete(int user_idx, int diary_idx) {
        try {
            bookmarkMapper.bookmarkDelete(user_idx,diary_idx);
            if(bookmarkMapper.checkBookmark(user_idx, diary_idx)==0)
                return DefaultResponse.res(Status.OK, Message.DELETE_BOOKMARK_SUCCESS);
            return DefaultResponse.res(Status.BAD_REQUEST, Message.DELETE_BOOKMARK_FAIL);
        } catch (Exception e){
            return DefaultResponse.res(Status.DB_ERROR, Message.DB_ERROR);
        }
    }

    //북마크 여부 확인
    @Override
    public DefaultResponse bookmarkCheck(int user_idx, int diary_idx) {
        try {
            int bookmark = bookmarkMapper.checkBookmark(user_idx, diary_idx);
            if(bookmark==0)
                return DefaultResponse.res(Status.NO_CONTENT, Message.NO_CONTENT);
            else if (bookmark==1)
                return DefaultResponse.res(Status.OK, Message.FIND_BOOKMARK_SUCCESS);
            else
                return DefaultResponse.res(Status.BAD_REQUEST, Message.FIND_BOOKMARK_FAIL);
        } catch (Exception e){
            return DefaultResponse.res(Status.INTERNAL_SERVER_ERROR, Message.INTERNAL_SERVER_ERROR);
        }
    }
}
