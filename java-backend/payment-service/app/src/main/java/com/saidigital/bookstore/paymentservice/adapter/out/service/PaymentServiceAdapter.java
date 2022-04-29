package com.saidigital.bookstore.paymentservice.adapter.out.service;

import com.saidigital.bookstore.base.exception.UserException;
import com.saidigital.bookstore.paymentservice.adapter.out.service.paymentgateway.PaymentGatewayProvider;
import com.saidigital.bookstore.paymentservice.business.port.out.payment.CreatePaymentIntentPort;
import com.saidigital.bookstore.paymentservice.domain.PaymentCurrency;
import com.saidigital.bookstore.paymentservice.domain.PaymentGateway;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Log4j2
public class PaymentServiceAdapter implements CreatePaymentIntentPort {

    private Map<PaymentGateway, PaymentGatewayProvider> paymentProviders;

    @Qualifier("stripePaymentGatewayProvider")
    private final PaymentGatewayProvider stripePaymentGateway;

    @PostConstruct
    private void postConstruct() {
        paymentProviders = Map.of(
                PaymentGateway.Stripe, stripePaymentGateway
        );
    }

    @Override
    public Map<String, Object> createPaymentIntent(double amount, PaymentCurrency currency, PaymentGateway gateway) {
        PaymentGatewayProvider provider = paymentProviders.get(gateway);
        if (provider == null) {
            throw new UserException("payment_gateway_not_support");
        }
        return provider.createPaymentIntent(amount, currency);
    }
}
