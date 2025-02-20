package com.domi.domitodo.exception;

import org.springframework.http.HttpStatus;

public class TodoException extends DomiException {
    public enum Type {
        NOT_FOUND(0, "내용을 찾을 수 없습니다.", HttpStatus.NOT_FOUND),
        NEED_PERMISSION(1, "권한이 필요합니다.", HttpStatus.FORBIDDEN),;

        final int code;
        final String message;
        final HttpStatus status;

        Type(int _code, String _message, HttpStatus _status) {
            code = _code;
            message = _message;
            status = _status;
        }
    }

    public TodoException(Type type) {
        super("TODO"+type.code, type.message, type.status);
    }

}