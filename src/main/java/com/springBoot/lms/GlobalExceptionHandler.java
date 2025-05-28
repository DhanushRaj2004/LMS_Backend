package com.springBoot.lms;

import com.springBoot.lms.exception.ResourseNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler{

    @ExceptionHandler(exception = ResourseNotFoundException.class)
    public ResponseEntity<?> handlerResourceNotFound(ResourseNotFoundException e){
        Map<String,String> m = new HashMap<>();

        m.put("msg",e.getMessage());

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(m);
    }


}
