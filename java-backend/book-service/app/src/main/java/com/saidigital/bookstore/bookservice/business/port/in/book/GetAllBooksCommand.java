package com.saidigital.bookstore.bookservice.business.port.in.book;

import com.saidigital.bookstore.base.common.SelfValidating;
import com.saidigital.bookstore.base.exception.UserException;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
public class GetAllBooksCommand extends SelfValidating<GetAllBooksCommand> {

    @NotNull(message = "search.is_null")
    private final String search;

    private final int page;

    private final int size;

    @NotNull(message = "sortBy.is_null")
    private final String sortBy;

    private final boolean sortDesc;

    private final List<String> BookProperties = List.of("name", "author", "price");

    public GetAllBooksCommand(String search, Integer page, Integer size, String sortBy, Boolean sortDesc) {
        this.search = this.stripData(search == null ? "" : search);
        if (page == null) {
            page = 0;
        }
        if (page < 0) {
            throw new UserException("page.must_greater_than_zero");
        }
        this.page = page;
        if (size  == null) {
            size = 20;
        }
        if (size < 0) {
            throw new UserException("size.must_greater_than_zero");
        }
        this.size = size;
        if (sortBy == null) {
            sortBy = "name";
        }
        if (!BookProperties.contains(sortBy)) {
            throw new UserException("sortBy.invalid_value");
        }
        this.sortBy = sortBy;

        this.sortDesc = sortDesc != null && sortDesc;
    }
}
