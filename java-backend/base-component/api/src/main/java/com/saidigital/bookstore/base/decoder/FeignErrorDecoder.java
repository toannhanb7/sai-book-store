package com.saidigital.bookstore.base.decoder;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.saidigital.bookstore.base.error.RestError;
import com.saidigital.bookstore.base.exception.RestException;
import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.http.HttpStatus;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * SaiFeignErrorDecoder -
 *
 * @author Nhan Nguyen (nhan.nguyentoan@sai-digital.com)
 */
public class FeignErrorDecoder implements ErrorDecoder {

    private final ErrorDecoder errorDecoder = new Default();

    @Override
    public Exception decode(String s, Response response) {
        if (response.status() == HttpStatus.BAD_REQUEST.value()) {
            try {
                ObjectMapper om = new ObjectMapper();
                om.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
                RestError restError = om.readValue(response.body().asReader(StandardCharsets.UTF_8), RestError.class);
                return new RestException(restError);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return errorDecoder.decode(s, response);
    }
}
