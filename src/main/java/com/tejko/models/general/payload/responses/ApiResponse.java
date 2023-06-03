package com.tejko.models.general.payload.responses;

import com.tejko.models.general.enums.ResponseStatus;

public class ApiResponse<T> {

    private ResponseStatus status = ResponseStatus.SUCCESS;
    private String message;
    private T object;

    public ApiResponse(ResponseStatus status, String message, T object) {
        this.status = status;
        this.message = message;
        this.object = object;
    }

    public ApiResponse(ResponseStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public ApiResponse(String message) {
        this.message = message;
    }

    public ApiResponse(T object) {
        this.object = object;
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
    public T getobject() {
        return object;
    }
    public void setobject(T object) {
        this.object = object;
    }

    

}