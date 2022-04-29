package com.saidigital.bookstore.paymentservice.domain;

import lombok.Getter;

public enum PaymentGateway {

    Stripe(1);

    @Getter
    private final int value;

    PaymentGateway(int value) {
        this.value = value;
    }

    public static PaymentGateway fromValue(int id) {
        PaymentGateway[] paymentGateways = values();
        for (PaymentGateway paymentGateway : paymentGateways) {
            if (paymentGateway.value == id) {
                return paymentGateway;
            }
        }
        return null;
    }

}
