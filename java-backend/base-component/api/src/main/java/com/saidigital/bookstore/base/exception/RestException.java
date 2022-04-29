package com.saidigital.bookstore.base.exception;

import com.saidigital.bookstore.base.error.RestError;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * RestException -
 *
 * @author Nhan Nguyen (nhan.nguyentoan@sai-digital.com)
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RestException extends RuntimeException {

    private RestError restError;

}
