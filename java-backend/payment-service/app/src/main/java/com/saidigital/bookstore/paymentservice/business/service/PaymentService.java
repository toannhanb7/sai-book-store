package com.saidigital.bookstore.paymentservice.business.service;

import com.saidigital.bookstore.paymentservice.business.port.in.payment.CreatePaymentCommand;
import com.saidigital.bookstore.paymentservice.business.port.in.payment.CreatePaymentUseCase;
import com.saidigital.bookstore.paymentservice.business.port.out.payment.CreatePaymentIntentPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
@Log4j2
public class PaymentService implements CreatePaymentUseCase {

    private final CreatePaymentIntentPort createPaymentIntentPort;

    @Override
    public Map<String, Object> createPayment(CreatePaymentCommand command) {
        return createPaymentIntentPort.createPaymentIntent(command.getAmount(), command.getCurrency(), command.getGateway());
    }
}
