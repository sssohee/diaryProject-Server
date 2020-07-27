package com.project.diary.api;

import com.project.diary.model.DefaultResponse;
import com.project.diary.service.LoginServiceInter;
import com.project.diary.utils.Message;
import com.project.diary.utils.Status;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequestMapping("login")
@RestController
public class LoginController {
    private final LoginServiceInter loginService;

    private static final DefaultResponse INTERNAL_SERVER_ERROR = new DefaultResponse(Status.INTERNAL_SERVER_ERROR,Message.INTERNAL_SERVER_ERROR);

    public LoginController(LoginServiceInter loginService) {
        this.loginService = loginService;
    }

    //로그인
    @PostMapping("")
    public ResponseEntity login (@RequestParam final String user_email, @RequestParam final String user_password) {
        try {
            return new ResponseEntity(loginService.login(user_email,user_password), HttpStatus.OK);
        } catch(Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity(INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //이메일 찾기
    @PostMapping("/findEmail")
    public ResponseEntity findByUserEmail(@RequestParam final String user_name, @RequestParam final String user_phone) {
        try {
            return new ResponseEntity(loginService.findUserEmail(user_name,user_phone), HttpStatus.OK);
        } catch(Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity(INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //비밀번호 찾기
    @PostMapping("/findPassword")
    public ResponseEntity findByUserPass(@RequestParam final String user_email, @RequestParam final String user_phone) {
        try {
            return new ResponseEntity(loginService.findUserPassword(user_email,user_phone), HttpStatus.OK);
        } catch(Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity(INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}