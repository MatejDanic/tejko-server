package com.tejko.models.general.payload;

import com.tejko.models.DatabaseEntity;
import com.tejko.models.general.enums.ResponseStatus;

public class ResponseWrapper<T extends RestResponse<? extends DatabaseEntity>> {

    private ResponseStatus status;
    private String message;
    private T data;

    public ResponseWrapper(ResponseStatus status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public ResponseWrapper(ResponseStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public ResponseWrapper(String message) {
        this.message = message;
    }

    public ResponseWrapper(T data) {
        this.data = data;
    }

    public ResponseStatus getStatus() {
        return status;
    }
    public void setStatus(ResponseStatus status) {
        this.status = status;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public T getData() {
        return data;
    }
    public void setData(T data) {
        this.data = data;
    }

    

}