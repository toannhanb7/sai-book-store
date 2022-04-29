package com.saidigital.bookstore.paymentservice.adapter.out.service.paymentgateway;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ConfigurationProperties(prefix = "payment.provider.stripe")
@Component
public class StripePaymentProperties {

    private String apiKey;

}
