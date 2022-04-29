package com.saidigital.bookstore.paymentservice.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Payment {

    private Long id;

    private PaymentGateway paymentGateway;

    private long createdDate;

    private double amount;

    private long orderId;

    private PaymentCurrency currency;

}
