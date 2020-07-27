package com.project.diary.api;

import com.project.diary.model.DefaultResponse;
import com.project.diary.model.DiaryAddReq;
import com.project.diary.model.DiaryModifyReq;
import com.project.diary.service.DiaryServiceInter;
import com.project.diary.utils.Message;
import com.project.diary.utils.Status;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequestMapping("diary")
@RestController
public class DiaryController {
    private DiaryServiceInter diaryService;

    public DiaryController(DiaryServiceInter diaryService) {
        this.diaryService = diaryService;
    }

    private static final DefaultResponse NO_CONTENT_RES = new DefaultResponse(Status.BAD_REQUEST, Message.NO_CONTENT);
    private static final DefaultResponse INTERNAL_SERVER_ERROR = new DefaultResponse(Status.INTERNAL_SERVER_ERROR,Message.INTERNAL_SERVER_ERROR);

    //글 작성
    @PostMapping("")
    public ResponseEntity insert (@RequestBody DiaryAddReq diaryAddReq){
        try{
            return new ResponseEntity(diaryService.insert(diaryAddReq), HttpStatus.OK);
        } catch (Exception e){
            log.error(e.getMessage());
            return new ResponseEntity(INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //글 수정
    @PutMapping("{diary_idx}")
    public ResponseEntity update (@RequestBody DiaryModifyReq diaryModifyReq, @PathVariable final int diary_idx){
        try {
            return new ResponseEntity(diaryService.update(diaryModifyReq, diary_idx), HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity(INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //글 삭제
    @PostMapping("{diary_idx}")
    public ResponseEntity delete (@PathVariable final int diary_idx, @RequestParam final int user_idx){
        try {
            return new ResponseEntity(diaryService.delete(diary_idx, user_idx), HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity(INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //일자별 게시글 개수 조회
    @GetMapping("todayCount/{user_idx}")
    public ResponseEntity todayDiaryCount (@PathVariable final int user_idx){
        try {
            return new ResponseEntity(diaryService.diaryDateCount(user_idx), HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity(INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //전체 글 조회
    @GetMapping("")
    public ResponseEntity allList (){
        try {
            return new ResponseEntity(diaryService.allDiaryList(), HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity(INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //회원별 조회
    @GetMapping("userDiaryList/{user_idx}")
    public ResponseEntity userAllList (@PathVariable final int user_idx){
        try {
            return new ResponseEntity(diaryService.userDiaryList(user_idx), HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity(INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //해당 글 조회
    @GetMapping("{diary_idx}")
    public ResponseEntity getDiary (@PathVariable final int diary_idx){
        try {
            return new ResponseEntity(diaryService.getDiary(diary_idx), HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity(INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
