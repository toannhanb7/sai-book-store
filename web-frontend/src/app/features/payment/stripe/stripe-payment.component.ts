import { environment } from './../../../../environments/environment';
import { Book } from 'generated/book-service';
import { CartService } from './../../order/services/cart-service';
import { PaymentService } from './../../../../../generated/payment-service/api/payment.service';
import { Component, Input, OnChanges, OnInit, SimpleChanges, ViewChild } from "@angular/core";
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { StripeCardElementOptions, StripeElementsOptions } from "@stripe/stripe-js";
import { StripeCardComponent, StripePaymentElementComponent, StripeService } from "ngx-stripe";
import { Payment, PaymentGateway } from 'generated/payment-service';

@Component({
  selector: 'stripe-payment',
  templateUrl: './stripe-payment.component.html',
  styleUrls: ['./stripe-payment.component.scss']
})

export class StripePaymentComponent implements OnInit, OnChanges{

  @ViewChild(StripePaymentElementComponent)
  card!: StripePaymentElementComponent;

  @Input()
  paymentId: string = '';

  cardOptions: StripeCardElementOptions = {
    style: {
      base: {
        iconColor: '#666EE8',
        color: '#31325F',
        fontWeight: '300',
        fontFamily: '"Helvetica Neue", Helvetica, sans-serif',
        fontSize: '18px',
        '::placeholder': {
          color: '#CFD7E0'
        }
      }
    }
  };

  elementsOptions: StripeElementsOptions = {
    locale: 'en'
  };

  paymentInfo: FormGroup;

  constructor(
    private formBuilder: FormBuilder,
    private paymentService: PaymentService,
    private stripeService: StripeService,
    private cartService: CartService
  ) {
    this.paymentInfo = this.formBuilder.group({
      name: ['', [Validators.required]]
    });
  }

  ngOnChanges(changes: SimpleChanges): void {
    this.elementsOptions.clientSecret = this.paymentId;
  }
  ngOnInit(): void {
    // let cartItems = this.cartService.getAllCartItem();
    // if (cartItems.length == 0) {

    // } else {
    //   let amount = calculateTotalAmount(cartItems);
    //   let payment: Payment = {
    //     gateway: PaymentGateway.Stripe,
    //     order: {
    //       amount: amount,
    //       currency: 'usd',
    //       id: new Date().getTime()
    //     }
    //   }
    //   this.paymentService.createPayment(payment).subscribe((paymentIntent) => {
    //     this.elementsOptions.clientSecret = paymentIntent['clientSecret'].toString();
    //   })
    // }

  }

  submitPayment() {
    this.stripeService.confirmPayment({
      elements: this.card.elements,
      confirmParams: {
        return_url: `${environment.baseUrl}/payment/confirmation?gateway=${PaymentGateway.Stripe}`,
        payment_method_data: {
          billing_details: {
            name: this.paymentInfo.value.name
          }
        }
      }
    }
    ).subscribe((data) => {
      console.log(data);
    })
  }
}


