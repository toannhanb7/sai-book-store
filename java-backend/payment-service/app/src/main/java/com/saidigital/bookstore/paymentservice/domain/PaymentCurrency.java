package com.saidigital.bookstore.paymentservice.domain;

import lombok.Getter;

public enum PaymentCurrency {

    USD("usd");

    @Getter
    private final String value;

    PaymentCurrency(String value) {
        this.value = value;
    }

    public static PaymentCurrency fromValue(String id) {
        PaymentCurrency[] paymentCurrencies = values();
        for (PaymentCurrency paymentCurrency : paymentCurrencies) {
            if (paymentCurrency.value.equals(id)) {
                return paymentCurrency;
            }
        }
        return null;
    }



}
