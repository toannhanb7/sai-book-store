package com.saidigital.bookstore.paymentservice.business.port.out.payment;

import com.saidigital.bookstore.paymentservice.domain.PaymentCurrency;
import com.saidigital.bookstore.paymentservice.domain.PaymentGateway;

import javax.validation.constraints.NotNull;
import java.util.Map;

public interface CreatePaymentIntentPort {

    Map<String, Object> createPaymentIntent(double amount, PaymentCurrency currency, PaymentGateway gateway);

}
