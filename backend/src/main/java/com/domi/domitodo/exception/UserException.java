package com.domi.domitodo.exception;

import org.springframework.http.HttpStatus;

public class UserException extends DomiException {
    public enum Type {
        NOT_FOUND_USER(0, "유저를 찾을 수 없습니다.", HttpStatus.NOT_FOUND),;

        final int code;
        final String message;
        final HttpStatus status;

        Type(int _code, String _message, HttpStatus _status) {
            code = _code;
            message = _message;
            status = _status;
        }
    }

    public UserException(Type type) {
        super("USER"+type.code, type.message, type.status);
    }
}
