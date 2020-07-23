package com.project.diary.service.Impl;

import com.project.diary.dto.Subject;
import com.project.diary.mapper.SubjectMapper;
import com.project.diary.model.DefaultResponse;
import com.project.diary.service.SubjectServiceInter;
import com.project.diary.utils.Message;
import com.project.diary.utils.Status;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Service
@Component
public class SubjectService implements SubjectServiceInter {

    private SubjectMapper subjectMapper;

    //주제 등록
    @Override
    public DefaultResponse insertSubject(String subject) {
        try {
            if(subjectMapper.getDataSubject(subject)==null){
                subjectMapper.insert(subject);
                return DefaultResponse.res(Status.CREATED, Message.ADD_SUBJECT_SUCCESS);
            }else{
                return DefaultResponse.res(Status.BAD_REQUEST, Message.ADD_SUBJECT_FAIL);
            }
        }catch (Exception e){
            return DefaultResponse.res(Status.DB_ERROR, Message.DB_ERROR);
        }
    }

    //주제 삭제
    @Override
    public DefaultResponse deleteSubject(int subject_idx) {
        try {
            subjectMapper.delete(subject_idx);
            if(subjectMapper.getDataSubjectIdx(subject_idx)==null)
                return DefaultResponse.res(Status.OK, Message.DELETE_SUBJECT_SUCCESS);
            return DefaultResponse.res(Status.BAD_REQUEST, Message.DELETE_SUBJECT_FAIL);
        }catch (Exception e){
            return DefaultResponse.res(Status.DB_ERROR, Message.DB_ERROR);
        }
    }

    //주제 리스트
    @Override
    public DefaultResponse subjectList() {
        try {
            final List<Subject> subjectList = subjectMapper.list();

            if(subjectList != null)
                return DefaultResponse.res(Status.OK,Message.FIND_SUBJECT_SUCCESS,subjectList);
            else
                return DefaultResponse.res(Status.BAD_REQUEST,Message.FIND_SUBJECT_FAIL);
        }catch (Exception e){
            return DefaultResponse.res(Status.INTERNAL_SERVER_ERROR,Message.INTERNAL_SERVER_ERROR);
        }
    }

    //오늘의 주제 조회
    @Override
    public DefaultResponse todaySubject() {
        try {
            final String subject = subjectMapper.todaySubject().getSubject();
            if(subject != null)
                return DefaultResponse.res(Status.OK,Message.FIND_TODAY_SUBJECT_SUCCESS,subject);
            else
                return DefaultResponse.res(Status.BAD_REQUEST,Message.FIND_TODAY_SUBJECT_FAIL);
        }catch (Exception e){
            return DefaultResponse.res(Status.INTERNAL_SERVER_ERROR,Message.INTERNAL_SERVER_ERROR);
        }
    }

    //오늘의 주제 등록
    @Scheduled(cron = "0 0 0 * * *")
    public void insertTodaySubject() {
        try {
            int num;
            Random r = new Random();
            int subject_idx = subjectMapper.findByMaxSubjectIdx();
            while(true){
                num = r.nextInt(subject_idx)+1;
                if(subjectMapper.getDataSubjectIdx(num)!=null)
                    break;
            }
            String subject = subjectMapper.getDataSubjectIdx(num).getSubject();
            subjectMapper.insertTodaySubject(subject);

            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date();
            String today = format.format(date);

            if(subjectMapper.todaySubject().getDate().equals(today))
                System.out.println("todaySubject 추가 성공");
            else
                System.out.println("todaySubject 추가 실패");
        }catch (Exception e){
            System.out.println("todaySubject 추가 오류 발생");
        }
    }
}
