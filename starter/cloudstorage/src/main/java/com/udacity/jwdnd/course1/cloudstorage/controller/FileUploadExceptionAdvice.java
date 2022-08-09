package com.udacity.jwdnd.course1.cloudstorage.controller;

import org.apache.tomcat.util.http.fileupload.impl.SizeLimitExceededException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
@ControllerAdvice
public class FileUploadExceptionAdvice {
    @ExceptionHandler({MaxUploadSizeExceededException.class, SizeLimitExceededException.class})
    public String handleMaxSizeException(){
        return "error";
    }
}
