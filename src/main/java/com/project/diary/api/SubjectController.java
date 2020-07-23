package com.project.diary.api;

import com.project.diary.model.DefaultResponse;
import com.project.diary.service.SubjectServiceInter;
import com.project.diary.utils.Message;
import com.project.diary.utils.Status;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequestMapping("subjects")
@RestController
public class SubjectController {
    private SubjectServiceInter subjectService;

    private static final DefaultResponse NO_CONTENT_RES = new DefaultResponse(Status.BAD_REQUEST, Message.NO_CONTENT);
    private static final DefaultResponse INTERNAL_SERVER_ERROR = new DefaultResponse(Status.INTERNAL_SERVER_ERROR,Message.INTERNAL_SERVER_ERROR);

    //주제 등록
    @PostMapping("")
    public ResponseEntity insertSubject(@RequestParam final String subject) {
        try {
            return new ResponseEntity(subjectService.insertSubject(subject), HttpStatus.OK);
        } catch(Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity(INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //주제 삭제
    @DeleteMapping("/{subject_idx}")
    public ResponseEntity deleteSubject(@PathVariable final int subject_idx){
        try{
            return new ResponseEntity(subjectService.deleteSubject(subject_idx), HttpStatus.OK);
        } catch (Exception e){
            log.error(e.getMessage());
            return new ResponseEntity(INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //주제 리스트
    @GetMapping("")
    public ResponseEntity subjectList(){
        try{
            return new ResponseEntity(subjectService.subjectList(), HttpStatus.OK);
        }catch (Exception e){
            log.error(e.getMessage());
            return new ResponseEntity(NO_CONTENT_RES, HttpStatus.NO_CONTENT);
        }
    }

    //오늘의 주제 조회
    @GetMapping("/todaySubject")
    public ResponseEntity todaySubject(){
        try{
            return new ResponseEntity(subjectService.todaySubject(), HttpStatus.OK);
        }catch (Exception e){
            log.error(e.getMessage());
            return new ResponseEntity(NO_CONTENT_RES, HttpStatus.NO_CONTENT);
        }
    }
}