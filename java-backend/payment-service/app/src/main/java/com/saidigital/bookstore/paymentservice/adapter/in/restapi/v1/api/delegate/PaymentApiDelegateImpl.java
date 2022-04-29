package com.saidigital.bookstore.paymentservice.adapter.in.restapi.v1.api.delegate;

import com.saidigital.bookstore.paymentservice.adapter.in.restapi.v1.api.PaymentApiDelegate;
import com.saidigital.bookstore.paymentservice.adapter.in.restapi.v1.model.Payment;
import com.saidigital.bookstore.paymentservice.adapter.in.restapi.v1.model.PaymentGateway;
import com.saidigital.bookstore.paymentservice.business.port.in.payment.CreatePaymentCommand;
import com.saidigital.bookstore.paymentservice.business.port.in.payment.CreatePaymentUseCase;
import com.saidigital.bookstore.paymentservice.domain.PaymentCurrency;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RequiredArgsConstructor
@Log4j2
public class PaymentApiDelegateImpl implements PaymentApiDelegate {

    private final CreatePaymentUseCase createPaymentUseCase;

    @Override
    public ResponseEntity<Map<String, Object>> createPayment(Payment payment) {
        return ResponseEntity.ok(createPaymentUseCase.createPayment(new CreatePaymentCommand(
                payment.getOrder().getId(), payment.getOrder().getAmount(), toDomainPaymentCurrency(payment.getOrder().getCurrency()),toDomainPaymentGateway(payment.getGateway())
        )));
    }

    private PaymentCurrency toDomainPaymentCurrency(String currency) {
        return PaymentCurrency.fromValue(currency);
    }

    private com.saidigital.bookstore.paymentservice.domain.PaymentGateway toDomainPaymentGateway(PaymentGateway gateway) {
        return com.saidigital.bookstore.paymentservice.domain.PaymentGateway.valueOf(gateway.getValue());
    }
}
