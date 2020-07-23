package com.project.diary.service;

import com.project.diary.model.DefaultResponse;

public interface BookmarkServiceInter {
    DefaultResponse bookmarkInsert(final int user_idx, final int diary_idx);
    DefaultResponse bookmarkDelete(final int user_idx, final int diary_idx);
    DefaultResponse bookmarkCheck(final int user_idx, final int diary_idx);
}
