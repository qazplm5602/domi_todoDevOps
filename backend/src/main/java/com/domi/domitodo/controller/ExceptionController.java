package com.domi.domitodo.controller;

import com.domi.domitodo.VO.ExceptionVO;
import com.domi.domitodo.exception.DomiException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionController {
    @ExceptionHandler(DomiException.class)
    ResponseEntity<ExceptionVO> responseError(DomiException err) {
        ExceptionVO result = new ExceptionVO(err.getCode(), err.getMessage());
        return ResponseEntity.status(err.getStatus()).body(result);
    }
}
