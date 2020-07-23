package com.project.diary.mapper;

import org.apache.ibatis.annotations.*;

@Mapper
public interface BookmarkMapper {
    //북마크 추가
    @Insert("insert into bookmark values (#{user_idx},#{diary_idx})")
    void bookmarkInsert (@Param("user_idx") final int user_idx, @Param("diary_idx") final int diary_idx);

    //북마크 제거
    @Delete("delete from bookmark where user_idx=#{user_idx} and diary_idx=#{diary_idx}")
    void bookmarkDelete (@Param("user_idx") final int user_idx, @Param("diary_idx") final int diary_idx);

    //북마크 여부 확인
    @Select("select count(*) from bookmark where user_idx=#{user_idx} and diary_idx=#{diary_idx}")
    int checkBookmark (@Param("user_idx") final int user_idx, @Param("diary_idx") final int diary_idx);
}
