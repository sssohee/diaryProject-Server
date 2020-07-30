package com.project.diary.service;

import com.project.diary.model.DefaultResponse;

public interface LoginServiceInter {
    DefaultResponse login(final String user_email, final String user_password);
    DefaultResponse findUserEmail(final String user_name, final String user_phone);
    DefaultResponse findUserPassword(final String user_email, final String user_phone);
}
