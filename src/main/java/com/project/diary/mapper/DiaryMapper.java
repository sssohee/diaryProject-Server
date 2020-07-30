package com.project.diary.mapper;

import com.project.diary.dto.DateDiaryCount;
import com.project.diary.dto.Diary;
import com.project.diary.model.DiaryAddReq;
import com.project.diary.model.DiaryModifyReq;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DiaryMapper {
    //게시글 등록
    @Insert("insert into diary values (0,#{diaryAddReq.user_idx},#{diaryAddReq.diary_subject},#{diaryAddReq.diary_contents},#{diary_image},now(),#{diaryAddReq.diary_open},0)")
    void insert (@Param("diaryAddReq") final DiaryAddReq diaryAddReq, @Param("diary_image") final String diary_image);

    //게시글 수정
    @Update("update diary set diary_contents=#{diaryModifyReq.diary_contents},diary_image=#{diary_image},diary_open=#{diaryModifyReq.diary_open} where diary_idx=#{diaryModifyReq.diary_idx}")
    void update (@Param("diaryModifyReq") final DiaryModifyReq diaryModifyReq, @Param("diary_image") final String diary_image);

    //게시글 삭제 - 회원
    @Delete("delete from diary where diary_idx=#{diary_idx}")
    void deleteUser (@Param("diary_idx") final int diary_idx);

    //게시글 삭제 - 관리자
    @Update("update diary set diary_status=1 where diary_idx=#{diary_idx}")
    void deleteAdmin (@Param("diary_idx") final int diary_idx);

    //최신 작성글 조회
    @Select("select * from diary where user_idx=#{user_idx} order by diary_idx desc limit 1")
    Diary userNewDiary (@Param("user_idx") final int user_idx);

    //일자별 게시글 개수 확인
    @Select("SELECT DATE_FORMAT(diary_date, '%Y-%m-%d') AS diary_date, count(*) AS diary_date_count FROM diary where user_idx=#{user_idx} GROUP BY DATE_FORMAT(diary_date, '%Y-%m-%d') ORDER BY diary_date")
    List<DateDiaryCount> selectDiaryDateCount (@Param("user_idx") final int user_idx);

    //모든 회원의 게시글 - 최신순
    @Select("select * from diary where diary_open=0 order by diary_date desc")
    List<Diary> allDiaryList ();

    //전체 게시글 카테고리 : 일기 - 최신순
    @Select("select * from diary where diary_subject='일기' and diary_open=0 order by diary_date desc")
    List<Diary> categoryDiaryList ();

    //전체 게시글 카테고리 : 주제 - 최신순
    @Select("select * from diary where diary_subject!='일기' and diary_open=0 order by diary_date desc")
    List<Diary> categorySubjectList ();

    //개인 회원의 모든 게시글 - 최신순
    @Select("select * from diary where user_idx=#{user_idx} order by diary_date desc")
    List<Diary> userDiaryList (@Param("user_idx") final int user_idx);

    //해당 게시글 조회
    @Select("select * from diary where diary_idx=#{diary_idx}")
    Diary getDiary (@Param("diary_idx") final int diary_idx);

    //회원 게시글 카테고리 : 일기 - 최신순
    @Select("select * from diary where user_idx=#{user_idx} and diary_subject='일기' order by diary_date desc")
    List<Diary> userCategoryDiaryList (@Param("user_idx") final int user_idx);

    //회원 게시글 카테고리 : 주제 - 최신순
    @Select("select * from diary where user_idx=#{user_idx} and diary_subject!='일기' order by diary_date desc")
    List<Diary> userCategorySubjectList (@Param("user_idx") final int user_idx);

    //회원 게시글 카테고리 : 공개,비공개 - 최신순
    @Select("select * from diary where user_idx=#{user_idx} and diary_open=#{diary_open} order by diary_date desc")
    List<Diary> userCategoryOpenList (@Param("user_idx") final int user_idx, @Param("diary_open") final int diary_open);

    //공감(북마크) 해놓은 게시글
    @Select("select * from diary d join bookmark b on d.diary_idx = b.diary_idx where b.user_idx=#{user_idx}")
    List<Diary> bookmarkDiary (@Param("user_idx") final int user_idx);
}