package com.domi.domitodo.exception;

import org.springframework.http.HttpStatus;

public class TokenException extends DomiException {
    public enum Type {
        EXPIRE_TOKEN(0, "만료된 토큰 입니다.", HttpStatus.BAD_REQUEST),
        WRONG_SIGN(1, "잘못된 서명 입니다.", HttpStatus.FORBIDDEN),;

        final int code;
        final String message;
        final HttpStatus status;

        Type(int _code, String _message, HttpStatus _status) {
            code = _code;
            message = _message;
            status = _status;
        }
    }

    public TokenException(Type type) {
        super("JWT"+type.code, type.message, type.status);
    }
}
