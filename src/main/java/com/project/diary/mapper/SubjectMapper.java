package com.project.diary.mapper;

import com.project.diary.dto.Subject;
import com.project.diary.dto.TodaySubject;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface SubjectMapper {
    //주제 등록
    @Insert("insert into subject values (0,#{subject})")
    void insert (@Param("subject") final String subject);

    //주제 삭제
    @Delete("delete from subject where subject_idx=#{subject_idx}")
    void delete (@Param("subject_idx") final int subject_idx);

    //주제 조회 - 주제 고유번호로 조회
    @Select("select * from subject where subject_idx=#{subject_idx}")
    Subject getDataSubjectIdx (@Param("subject_idx") final int subject_idx);

    //주제 조회 - 주제로 조회
    @Select("select * from subject where subject=#{subject}")
    Subject getDataSubject (@Param("subject") final String subject);

    //주제 리스트
    @Select("select * from subject order by subject_idx")
    List<Subject> list ();

    //subject_idx의 max값 조회
    @Select("select max(subject_idx) from subject")
    int findByMaxSubjectIdx ();

    //오늘의 주제 등록
    @Insert("insert into today_subject values (now(),#{subject})")
    void insertTodaySubject(@Param("subject") final String subject);

    //오늘의 주제 조회
    @Select("select * from today_subject order by subject_idx desc limit 1")
    TodaySubject todaySubject();
}
