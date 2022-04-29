package com.saidigital.bookstore.base.handler;

import com.saidigital.bookstore.base.error.RestError;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    protected ResponseEntity<RestError> handleConflict(RuntimeException ex, WebRequest request) {
        RestError restError = ResponseExceptionHandler.handleException(ex);
        return ResponseEntity.status(restError.getStatus()).body(restError);
    }

}
