package com.project.diary.model;

import com.project.diary.utils.Message;
import com.project.diary.utils.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Builder
@AllArgsConstructor
public class DefaultResponse<T> {
    private int status;
    private String message;
    private T data;

    public DefaultResponse(final int status, final String message){
        this.status = status;
        this.message = message;
        this.data = null;
    }

    public static<T> DefaultResponse<T> res(final int status, final String message){
        return res(status, message, null);
    }

    public static<T> DefaultResponse<T> res(final int status, final String message, final T t){
        return DefaultResponse.<T>builder()
                .data(t)
                .status(status)
                .message(message)
                .build();
    }

    public static final DefaultResponse FAIL_DEFAULT_RES = new DefaultResponse(Status.INTERNAL_SERVER_ERROR, Message.INTERNAL_SERVER_ERROR);
}
