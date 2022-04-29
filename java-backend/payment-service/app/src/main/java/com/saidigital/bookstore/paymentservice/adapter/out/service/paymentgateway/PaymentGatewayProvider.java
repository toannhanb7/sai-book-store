package com.saidigital.bookstore.paymentservice.adapter.out.service.paymentgateway;

import com.saidigital.bookstore.paymentservice.domain.PaymentCurrency;

import java.util.Map;

public interface PaymentGatewayProvider {

    Map<String, Object> createPaymentIntent(double amount, PaymentCurrency currency);

}
