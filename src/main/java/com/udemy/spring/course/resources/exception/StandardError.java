package com.udemy.spring.course.resources.exception;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

public class StandardError implements Serializable {

    private Integer status;
    private String message;
    @JsonFormat(pattern = "MM/dd/yyyy HH:mm")
    private Date time;

    public StandardError(Integer status, String message, Date time) {
        this.status = status;
        this.message = message;
        this.time = time;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
