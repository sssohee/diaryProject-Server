package com.project.diary.api;

import com.project.diary.model.DefaultResponse;
import com.project.diary.model.SignUpReq;
import com.project.diary.model.UserModifyReq;
import com.project.diary.service.UserServiceInter;
import com.project.diary.utils.Message;
import com.project.diary.utils.Status;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequestMapping("users")
@RestController
public class UserController {
    private final UserServiceInter userService;

    private static final DefaultResponse NO_CONTENT_RES = new DefaultResponse(Status.BAD_REQUEST, Message.NO_CONTENT);
    private static final DefaultResponse INTERNAL_SERVER_ERROR = new DefaultResponse(Status.INTERNAL_SERVER_ERROR,Message.INTERNAL_SERVER_ERROR);

    public UserController(UserServiceInter userService) {
        this.userService = userService;
    }

    //회원 가입
    @PostMapping("")
    public ResponseEntity signUp(@RequestBody SignUpReq signUpReq) {
        try {
            return new ResponseEntity(userService.saveUser(signUpReq), HttpStatus.OK);
        } catch(Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity(INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //nickname 중복검사
    @GetMapping("/check")
    public ResponseEntity checkNickname(@RequestParam final String user_nickname){
        try {
            return new ResponseEntity(userService.checkNickName(user_nickname), HttpStatus.OK);
        } catch(Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity(INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //email 중복검사
    @GetMapping("/check")
    public ResponseEntity checkEmail(@RequestParam final String user_email){
        try {
            return new ResponseEntity(userService.checkEmail(user_email), HttpStatus.OK);
        } catch(Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity(INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //회원 list
    @GetMapping("")
    public ResponseEntity userList(){
        try {
            return new ResponseEntity(userService.userList(), HttpStatus.OK);
        } catch(Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity(INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    //회원 개인 정보 조회
    @GetMapping("/{user_idx}")
    public ResponseEntity userGetData(@PathVariable final int user_idx){
        try {
            return new ResponseEntity(userService.getData(user_idx), HttpStatus.OK);
        } catch(Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity(INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //회원 정보 변경
    @PutMapping("/{user_idx}")
    public ResponseEntity updateUser(@RequestBody UserModifyReq userModifyReq, @PathVariable final int user_idx){
        try {
            return new ResponseEntity(userService.updateUser(userModifyReq,user_idx), HttpStatus.OK);
        } catch(Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity(NO_CONTENT_RES, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //회원 비밀번호 변경
    @PutMapping("")
    public ResponseEntity updateUserPassword(@RequestParam final String user_password, @RequestParam final int user_idx){
        try {
            return new ResponseEntity(userService.updatePass(user_password,user_idx), HttpStatus.OK);
        } catch(Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity(INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //회원 탈퇴
    @PutMapping("")
    public ResponseEntity deleteUser(@RequestParam final int user_idx){
        try {
            return new ResponseEntity(userService.deleteUser(user_idx), HttpStatus.OK);
        } catch(Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity(INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
