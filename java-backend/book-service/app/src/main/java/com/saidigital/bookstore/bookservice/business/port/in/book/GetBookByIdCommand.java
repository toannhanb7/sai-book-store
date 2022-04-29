package com.saidigital.bookstore.bookservice.business.port.in.book;

import com.saidigital.bookstore.base.common.SelfValidating;
import com.saidigital.bookstore.base.exception.UserException;
import lombok.Getter;

@Getter
public class GetBookByIdCommand extends SelfValidating<GetBookByIdCommand> {

    private final long bookId;

    public GetBookByIdCommand(Long bookId) {
        if (bookId == null) {
            throw new UserException("bookId.is_null");
        }
        this.bookId = bookId;

        this.validateSelf();
    }
}
