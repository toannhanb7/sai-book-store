package com.saidigital.bookstore.base.error;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RestError {

    private int code;

    private int status;

    private String message;

    private Object additionalInfo;

}
