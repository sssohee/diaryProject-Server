package com.project.diary.service;

import com.project.diary.model.DefaultResponse;

public interface SubjectServiceInter {
    DefaultResponse insertSubject(final String subject);
    DefaultResponse deleteSubject(final int subject_idx);
    DefaultResponse subjectList();
    DefaultResponse todaySubject();
    void insertTodaySubject();
}