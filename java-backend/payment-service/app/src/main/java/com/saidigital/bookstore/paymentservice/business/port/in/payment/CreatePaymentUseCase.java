package com.saidigital.bookstore.paymentservice.business.port.in.payment;

import java.util.Map;

public interface CreatePaymentUseCase {

    Map<String, Object> createPayment(CreatePaymentCommand command);

}
