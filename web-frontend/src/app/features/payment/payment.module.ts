import { MatButtonModule } from '@angular/material/button';
import { PaymentCheckoutComponent } from './payment-checkout/payment-checkout.component';
import { PaymentConfirmationComponent } from './payment-confirmation/payment-confirmation.component';
import { MatCardModule } from '@angular/material/card';
import { PaymentRoutingModule } from './payment-routing.module';
import { BrowserModule } from '@angular/platform-browser';
import { CommonModule } from '@angular/common';
import { NgxStripeModule } from 'ngx-stripe';
import { ProductModule } from './../product/product.module';
import { NgModule } from "@angular/core";
import { StripePaymentComponent } from './stripe/stripe-payment.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import {MatTableModule} from "@angular/material/table";

@NgModule({
  declarations:[
    StripePaymentComponent,
    PaymentConfirmationComponent,
    PaymentCheckoutComponent
  ],
  imports: [
    NgxStripeModule,
    FormsModule,
    ReactiveFormsModule,
    CommonModule,
    BrowserModule,
    PaymentRoutingModule,
    MatCardModule,
    MatButtonModule,
    MatTableModule
  ],
  exports: [
    StripePaymentComponent,
    PaymentCheckoutComponent
  ]
})

export class PaymentModule {

}
