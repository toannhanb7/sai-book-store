package com.saidigital.bookstore.base.entity;

import java.util.Arrays;
import java.util.List;

public interface ArrayStringTypeWrapper {

    default List<String> toStringList(String value) {
        return value != null && value.length() > 1 ?
            Arrays.asList(value.substring(1, value.length() - 1).split(",")) :
            List.of();
    }

    default String fromStringList(List<String> value) {
        return value != null && value.size() > 0 ?
            "," + String.join(",", value) + "," :
            "";
    }


}
