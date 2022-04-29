package com.saidigital.bookstore.base.handler;

import com.saidigital.bookstore.base.error.RestError;
import com.saidigital.bookstore.base.exception.UserException;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Convert Exception to RestError.
 * If the Exception is an UserException or ConstraintViolationException, return BadRequest response. Return InternalServerError for other case
 */

@Log4j2
public class ResponseExceptionHandler {

    private static final int CONSTRAINT_ERROR_CODE = 1000;

    private static final String VIOLATIONS_FIELD_NAME = "violations";

    public static RestError handleException(Exception ex) {
        RestError error = new RestError();
        if (ex instanceof UserException) {
            UserException userEx = (UserException) ex;
            error.setCode(userEx.getCode());
            error.setMessage(userEx.getMessage());
            error.setAdditionalInfo(userEx.getExtra());
            error.setStatus(HttpStatus.BAD_REQUEST.value());
            return error;
        } else {
            if (ex instanceof ConstraintViolationException) {
                error.setCode(CONSTRAINT_ERROR_CODE);
                List<String> violations = ((ConstraintViolationException) ex).getConstraintViolations().stream().map(ConstraintViolation::getMessage).collect(Collectors.toList());
                error.setAdditionalInfo(Map.of(VIOLATIONS_FIELD_NAME, violations));
                error.setStatus(HttpStatus.BAD_REQUEST.value());
                return error;
            }
        }
        log.error("Unhandle error, convert it to INTERNAL_SERVER_ERROR", ex);
        error.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
//        error.setMessage(ex.getMessage());
        // We output a generic internal_server_error message key
        error.setMessage("internal_server_error");
        return error;
    }
}
