package com.project.diary.api;

import com.project.diary.model.DefaultResponse;
import com.project.diary.service.DiaryCategoryServiceInter;
import com.project.diary.utils.Message;
import com.project.diary.utils.Status;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequestMapping("diary/category")
@RestController
public class DiaryCategoryController {
    private DiaryCategoryServiceInter diaryCategoryService;

    public DiaryCategoryController(DiaryCategoryServiceInter diaryCategoryService) {
        this.diaryCategoryService = diaryCategoryService;
    }

    private static final DefaultResponse NO_CONTENT_RES = new DefaultResponse(Status.BAD_REQUEST, Message.NO_CONTENT);

    //전체 카테고리 - 일기/주제
    @GetMapping("")
    public ResponseEntity categoryAll(@RequestParam final String diary_subject){
        try {
            return new ResponseEntity(diaryCategoryService.categoryAll(diary_subject), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(NO_CONTENT_RES, HttpStatus.NO_CONTENT);
        }
    }

    //회원 카테고리 - 일기/주제
    @GetMapping("{user_idx}")
    public ResponseEntity categoryUser(@PathVariable final int user_idx, @RequestParam final String diary_subject){
        try {
            return new ResponseEntity(diaryCategoryService.categoryUser(user_idx, diary_subject), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(NO_CONTENT_RES, HttpStatus.NO_CONTENT);
        }
    }

    //회원 글 - 공개/비공개
    @GetMapping("{user_idx}/{user_open}")
    public ResponseEntity categoryOpen(@PathVariable final int user_idx, @PathVariable final int user_open){
        try {
            return new ResponseEntity(diaryCategoryService.categoryOpen(user_idx, user_open), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(NO_CONTENT_RES, HttpStatus.NO_CONTENT);
        }
    }

    //회원이 공감한 글
    @GetMapping("bookmark/{user_idx}")
    public ResponseEntity categoryBookmark(@PathVariable final int user_idx){
        try {
            return new ResponseEntity(diaryCategoryService.categoryBookmark(user_idx), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(NO_CONTENT_RES, HttpStatus.NO_CONTENT);
        }
    }
}
