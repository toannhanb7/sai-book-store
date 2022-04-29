package com.saidigital.bookstore.base.common;

import javax.validation.*;
import java.util.Set;

public abstract class SelfValidating<T> {

    private final Validator validator;

    public SelfValidating() {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    protected void validateSelf() {
        Set<ConstraintViolation<T>> validations = validator.validate((T) this);
        if (!validations.isEmpty()) {
            throw new ConstraintViolationException(validations);
        }
    }

    /**
     * Returns a string whose value is this string, with any leading and trailing whitespace removed.
     * @param data
     * @return
     *  - null if data is null
     *  - striped string value
     */
    public String stripData(String data) {
        return data != null ? data.strip() : null;
    }

}
