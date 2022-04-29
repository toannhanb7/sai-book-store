package com.saidigital.bookstore.base.exception;

public class UserException extends AppException{
    public UserException(String message) {
        super(message);
    }

    public UserException(int code, String message) {
        super(code, message);
    }

    public UserException(int code, String message, Object extra) {
        super(code, message, extra);
    }
}
