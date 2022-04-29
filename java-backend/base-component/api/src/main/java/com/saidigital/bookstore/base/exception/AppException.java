package com.saidigital.bookstore.base.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class AppException extends RuntimeException {

    private int code;

    private Object extra;

    public AppException(String message) {
        super(message);
    }

    public AppException(int code, String message) {
        this(message);
        this.code = code;
    }

    public AppException(int code, String message, Object extra) {
        this(code, message);
        this.extra = extra;
    }

}
