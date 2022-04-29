import { PaymentCheckoutComponent } from './payment-checkout/payment-checkout.component';
import { PaymentConfirmationComponent } from './payment-confirmation/payment-confirmation.component';
import { BrowserModule } from '@angular/platform-browser';
import { CommonModule } from '@angular/common';
import { NgxStripeModule } from 'ngx-stripe';
import { ProductModule } from './../product/product.module';
import { NgModule } from "@angular/core";
import { StripePaymentComponent } from './stripe/stripe-payment.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { RouterModule, Routes } from '@angular/router';

const routes: Routes = [
  {
    path: 'payment',
    children: [
      {
        path: 'confirmation',
        component: PaymentConfirmationComponent
      },
      {
        path: 'checkout',
        component: PaymentCheckoutComponent
      }
    ]

  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class PaymentRoutingModule { }

