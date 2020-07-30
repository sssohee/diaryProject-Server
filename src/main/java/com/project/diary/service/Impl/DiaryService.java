package com.project.diary.service.Impl;

import com.project.diary.dto.DateDiaryCount;
import com.project.diary.dto.Diary;
import com.project.diary.mapper.DiaryMapper;
import com.project.diary.model.DefaultResponse;
import com.project.diary.model.DiaryAddReq;
import com.project.diary.model.DiaryModifyReq;
import com.project.diary.service.DiaryServiceInter;
import com.project.diary.utils.Message;
import com.project.diary.utils.Status;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@Service
public class DiaryService implements DiaryServiceInter {
    private DiaryMapper diaryMapper;

    public DiaryService(DiaryMapper diaryMapper) {
        this.diaryMapper = diaryMapper;
    }

    public class SpringFileWrite {
        private FileOutputStream fos;

        public void writeFileRename(MultipartFile file, String path, String fileName) {
            try {
                byte[] fileData = file.getBytes();
                fos = new FileOutputStream(path+"\\"+fileName);
                fos.write(fileData);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //글 작성
    @Override
    public DefaultResponse insert(DiaryAddReq diaryAddReq, String path) {
        try{
            if(diaryAddReq.getUser_idx()<0||diaryAddReq.getDiary_subject().isEmpty()||
                    diaryAddReq.getDiary_contents().isEmpty()||diaryAddReq.getDiary_open().isEmpty()) {
                return DefaultResponse.res(Status.NO_CONTENT, Message.NO_CONTENT);
            }

            String diary_image = diaryAddReq.getDiary_image().getOriginalFilename();

            if(!diary_image.equals("")){
                //실제 폴더에 이미지 저장
                SpringFileWrite sfw = new SpringFileWrite();
                sfw.writeFileRename(diaryAddReq.getDiary_image(), path, diary_image);
            }else
                diary_image = "0";

            diaryMapper.insert(diaryAddReq, diary_image);

            if(diaryMapper.userNewDiary(diaryAddReq.getUser_idx()).getDiary_contents().equals(diaryAddReq.getDiary_contents()))
                return DefaultResponse.res(Status.CREATED, Message.ADD_DIARY_SUCCESS);
            else
                return DefaultResponse.res(Status.BAD_REQUEST,Message.ADD_DIARY_FAIL);

        }catch (Exception e){
            return DefaultResponse.res(Status.DB_ERROR, Message.DB_ERROR);
        }
    }

    //글 수정
    @Override
    public DefaultResponse update(DiaryModifyReq diaryModifyReq, String path) {
        try{
            if(diaryModifyReq.getDiary_contents().isEmpty()||diaryModifyReq.getDiary_open().isEmpty()) {
                return DefaultResponse.res(Status.NO_CONTENT, Message.NO_CONTENT);
            }

            String diary_image = diaryModifyReq.getDiary_image().getOriginalFilename();

            if(!diary_image.equals("")){
                //이미지가 존재할경우 삭제
                File file = new File(path+"\\"+diary_image);
                if(file.exists())
                    file.delete();

                //실제 폴더에 이미지 저장
                SpringFileWrite sfw = new SpringFileWrite();
                sfw.writeFileRename(diaryModifyReq.getDiary_image(), path, diary_image);
            }else
                diary_image = "0";

            diaryMapper.update(diaryModifyReq, diary_image);

            final Diary diary = diaryMapper.getDiary(diaryModifyReq.getDiary_idx());

            if(diary.getDiary_contents().equals(diaryModifyReq.getDiary_contents()) && diary.getDiary_image().equals(diary_image)
                    && diary.getDiary_open().equals(diaryModifyReq.getDiary_open()))
                return DefaultResponse.res(Status.CREATED, Message.MODIFY_DIARY_SUCCESS);
            else
                return DefaultResponse.res(Status.BAD_REQUEST,Message.MODIFY_DIARY_FAIL);

        }catch (Exception e){
            return DefaultResponse.res(Status.DB_ERROR, Message.DB_ERROR);
        }
    }

    //글 삭제
    @Override
    public DefaultResponse delete(int diary_idx, int user_idx, String path) {
        try{
            //이미지 파일명
            String diary_image = diaryMapper.getDiary(diary_idx).getDiary_image();

            //이미지가 존재할경우 삭제
            File file = new File(path+"\\"+diary_image);
            if(file.exists())
                file.delete();

            if(user_idx==1){
                diaryMapper.deleteAdmin(diary_idx);
                if(diaryMapper.getDiary(diary_idx).getDiary_status()==1)
                    return DefaultResponse.res(Status.OK, Message.DELETE_DIARY_SUCCESS);
                else
                    return DefaultResponse.res(Status.BAD_REQUEST, Message.DELETE_DIARY_FAIL);
            }else{
                diaryMapper.deleteUser(diary_idx);
                if (diaryMapper.getDiary(diary_idx)==null)
                    return DefaultResponse.res(Status.OK, Message.DELETE_DIARY_SUCCESS);
                else
                    return DefaultResponse.res(Status.BAD_REQUEST, Message.DELETE_DIARY_FAIL);
            }
        }catch (Exception e){
            return DefaultResponse.res(Status.DB_ERROR, Message.DB_ERROR);
        }
    }

    //일자별 게시글 개수 조회
    @Override
    public DefaultResponse diaryDateCount(int user_idx) {
        try{
            List<DateDiaryCount> diaryDateList = diaryMapper.selectDiaryDateCount(user_idx);

            if(diaryDateList.size()>0)
                return DefaultResponse.res(Status.OK, Message.FIND_DIARY_DATE_COUNT_SUCCESS,diaryDateList);
            else if(diaryDateList.size()==0)
                return DefaultResponse.res(Status.NO_CONTENT, Message.NO_CONTENT);
            else
                return DefaultResponse.res(Status.BAD_REQUEST, Message.FIND_DIARY_DATE_COUNT_FAIL);
        }catch (Exception e){
            return DefaultResponse.res(Status.INTERNAL_SERVER_ERROR, Message.INTERNAL_SERVER_ERROR);
        }
    }

    //전체 글 조회
    @Override
    public DefaultResponse allDiaryList() {
        try{
            List<Diary> diaryList = diaryMapper.allDiaryList();

            if(diaryList.size()>0)
                return DefaultResponse.res(Status.OK, Message.FIND_DIARY_SUCCESS,diaryList);
            else if(diaryList.size()==0)
                return DefaultResponse.res(Status.NO_CONTENT, Message.NO_CONTENT);
            else
                return DefaultResponse.res(Status.BAD_REQUEST, Message.FIND_DIARY_FAIL);
        }catch (Exception e){
            return DefaultResponse.res(Status.INTERNAL_SERVER_ERROR, Message.INTERNAL_SERVER_ERROR);
        }
    }

    //회원이 작성한 글 조회
    @Override
    public DefaultResponse userDiaryList(int user_idx) {
        try{
            List<Diary> diaryList = diaryMapper.userDiaryList(user_idx);

            if(diaryList.size()>0)
                return DefaultResponse.res(Status.OK, Message.FIND_DIARY_SUCCESS,diaryList);
            else if(diaryList.size()==0)
                return DefaultResponse.res(Status.NO_CONTENT, Message.NO_CONTENT);
            else
                return DefaultResponse.res(Status.BAD_REQUEST, Message.FIND_DIARY_FAIL);
        }catch (Exception e){
            return DefaultResponse.res(Status.INTERNAL_SERVER_ERROR, Message.INTERNAL_SERVER_ERROR);
        }
    }

    //해당 글 조회
    @Override
    public DefaultResponse getDiary(int diary_idx) {
        try{
            Diary diary = diaryMapper.getDiary(diary_idx);

            if(diary!=null)
                return DefaultResponse.res(Status.OK, Message.FIND_DIARY_SUCCESS,diary);
            else
                return DefaultResponse.res(Status.BAD_REQUEST, Message.FIND_DIARY_FAIL);
        }catch (Exception e){
            return DefaultResponse.res(Status.INTERNAL_SERVER_ERROR, Message.INTERNAL_SERVER_ERROR);
        }
    }
}
