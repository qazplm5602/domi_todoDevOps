package com.domi.domitodo.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class DomiException extends RuntimeException {
    final String code;
    final String message;
    final HttpStatus status;

    public DomiException(String _code, String _message, HttpStatus _status) {
        super();

        code = _code;
        message = _message;
        status = _status;
    }
}
