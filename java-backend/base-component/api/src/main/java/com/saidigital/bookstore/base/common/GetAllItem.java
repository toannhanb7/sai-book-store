package com.saidigital.bookstore.base.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * GetAllItem -
 *
 * @author Nhan Nguyen (nhan.nguyentoan@sai-digital.com)
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetAllItem <T>{

    long total;

    List<T> items;
}
