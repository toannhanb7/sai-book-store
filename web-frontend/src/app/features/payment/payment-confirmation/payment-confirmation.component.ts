import { PaymentService } from './../../../../../generated/payment-service/api/payment.service';
import { Component, OnInit, ViewChild } from "@angular/core";
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { StripeCardElementOptions, StripeElementsOptions } from "@stripe/stripe-js";
import { StripeCardComponent, StripePaymentElementComponent, StripeService } from "ngx-stripe";
import { Payment, PaymentGateway } from 'generated/payment-service';
import { ActivatedRoute, Params } from '@angular/router';

@Component({
  selector: 'payment-confirmation',
  templateUrl: './payment-confirmation.component.html',
  styleUrls: ['./payment-confirmation.component.scss']
})

export class PaymentConfirmationComponent implements OnInit{

  paymentId: string = '';

  gateway: string = '';

  amount: number = 0;

  referenceId: string = '';

  currency: string = '';

  constructor(
    private route: ActivatedRoute,
    private stripeService: StripeService
  ) {
  }

  ngOnInit(): void {
    this.route.queryParams.subscribe(params => {
      this.gateway = params['gateway'];
      this.fetchPaymentStatus(params);
    })
  }

  fetchPaymentStatus(params: Params) {
    if (this.gateway == PaymentGateway.Stripe) {
      let clientSecret = params['payment_intent_client_secret']
      let status = params['redirect_status'];
      if (status == 'succeeded') {
        this.stripeService.retrievePaymentIntent(clientSecret).subscribe((data) => {
          console.log(data);
          this.amount = data.paymentIntent?.amount!;
          this.currency = data.paymentIntent?.currency!;
          this.referenceId = data.paymentIntent?.id!;
        })
      }
    }

  }

  submitPayment() {

  }
}
