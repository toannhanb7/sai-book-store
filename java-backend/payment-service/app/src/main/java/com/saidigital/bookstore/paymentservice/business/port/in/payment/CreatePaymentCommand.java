package com.saidigital.bookstore.paymentservice.business.port.in.payment;

import com.saidigital.bookstore.base.common.SelfValidating;
import com.saidigital.bookstore.base.exception.UserException;
import com.saidigital.bookstore.paymentservice.domain.PaymentCurrency;
import com.saidigital.bookstore.paymentservice.domain.PaymentGateway;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
public class CreatePaymentCommand extends SelfValidating<CreatePaymentCommand> {

    private long orderId;

    private double amount;

    @NotNull
    private PaymentCurrency currency;

    @NotNull
    private PaymentGateway gateway;

    public CreatePaymentCommand(Long orderId, Double amount, PaymentCurrency currency, PaymentGateway gateway) {
        if (orderId == null) {
            throw new UserException("orderId.is_empty");
        }
        if (orderId <= 0) {
            throw new UserException("orderId.is_invalid");
        }
        this.orderId = orderId;
        if (amount == null) {
            throw new UserException("amount.is_empty");
        }
        if (amount < 0) {
            throw new UserException("amount.is_invalid");
        }
        this.amount = amount;
        this.currency = currency;
        this.gateway = gateway;

        this.validateSelf();
    }
}
