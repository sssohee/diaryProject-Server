package com.project.diary.service;

import com.project.diary.model.DefaultResponse;

public interface DiaryCategoryServiceInter {
    DefaultResponse categoryAll(final String category);
    DefaultResponse categoryUser(final int user_idx, final String category);
    DefaultResponse categoryOpen(final int user_idx, final int diary_open);
    DefaultResponse categoryBookmark(final int user_idx);
}
