package com.project.diary.dto;

import lombok.Data;

@Data
public class DateDiaryCount {
    private String diary_date; //글 작성일
    private int diary_date_count;//일자별 개수
}
