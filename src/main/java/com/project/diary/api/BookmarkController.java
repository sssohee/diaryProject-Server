package com.project.diary.api;

import com.project.diary.model.DefaultResponse;
import com.project.diary.service.BookmarkServiceInter;
import com.project.diary.utils.Message;
import com.project.diary.utils.Status;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequestMapping("diary/bookmarks")
@RestController
public class BookmarkController {

    private BookmarkServiceInter bookmarkService;

    private static final DefaultResponse NO_CONTENT_RES = new DefaultResponse(Status.BAD_REQUEST, Message.NO_CONTENT);
    private static final DefaultResponse INTERNAL_SERVER_ERROR = new DefaultResponse(Status.INTERNAL_SERVER_ERROR,Message.INTERNAL_SERVER_ERROR);

    //북마크 저장
    @PostMapping("")
    public ResponseEntity insert(@RequestParam final int user_idx, @RequestParam final int diary_idx){
        try {
            return new ResponseEntity(bookmarkService.bookmarkInsert(user_idx, diary_idx), HttpStatus.OK);
        } catch(Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity(INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //북마크 취소
    @DeleteMapping("")
    public ResponseEntity delete(@RequestParam final int user_idx, @RequestParam final int diary_idx){
        try {
            return new ResponseEntity(bookmarkService.bookmarkDelete(user_idx, diary_idx), HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity(INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //북마크 확인
    @GetMapping("")
    public ResponseEntity check(@RequestParam final int user_idx, @RequestParam final int diary_idx){
        try {
            return new ResponseEntity(bookmarkService.bookmarkCheck(user_idx, diary_idx), HttpStatus.OK);
        } catch (Exception e){
            log.error(e.getMessage());
            return new ResponseEntity(INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
