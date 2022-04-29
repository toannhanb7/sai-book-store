package com.saidigital.bookstore.paymentservice.adapter.out.service.paymentgateway;

import com.saidigital.bookstore.paymentservice.domain.PaymentCurrency;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class StripePaymentGatewayProvider implements PaymentGatewayProvider{

    private final StripePaymentProperties properties;

    @Override
    public Map<String, Object> createPaymentIntent(double amount, PaymentCurrency currency) {
        Stripe.apiKey = properties.getApiKey();

        PaymentIntentCreateParams params = PaymentIntentCreateParams.builder()
                .setAmount(convertToAmountInCent(amount))
                .setCurrency(currency.getValue())
                .setAutomaticPaymentMethods(
                        PaymentIntentCreateParams.AutomaticPaymentMethods.builder().setEnabled(Boolean.TRUE).build()
                )
                .build();
        try {
            PaymentIntent paymentIntent = PaymentIntent.create(params);
            return Map.of("clientSecret", paymentIntent.getClientSecret());
        } catch (StripeException e) {
            e.printStackTrace();
        }

        return null;
    }

    private Long convertToAmountInCent(double amount) {
        return Long.valueOf(String.valueOf(amount).replace(".", ""));
    }
}
