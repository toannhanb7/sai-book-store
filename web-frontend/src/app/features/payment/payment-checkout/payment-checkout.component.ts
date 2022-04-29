import { PaymentService } from './../../../../../generated/payment-service/api/payment.service';
import { CartService } from './../../order/services/cart-service';
import { Component, OnInit } from "@angular/core";
import { Book } from 'generated/book-service';
import { Payment, PaymentGateway } from 'generated/payment-service';

@Component({
  selector: 'payment-checkout',
  templateUrl: './payment-checkout.component.html',
  styleUrls: ['./payment-checkout.component.scss']
})

export class PaymentCheckoutComponent implements OnInit {

  paymentId: string = '';

  books: Book[] = [];

  displayedColumns = ['name', 'author', 'description', 'price']

  totalAmount = 0;

  constructor(
    private cartService: CartService,
    private paymentService: PaymentService
  ) {

  }

  ngOnInit(): void {
    this.books = this.cartService.getAllCartItem();
    if (this.books.length == 0) {

    } else {
      this.totalAmount = this.calculateTotalAmount(this.books);
      let payment: Payment = {
        gateway: PaymentGateway.Stripe,
        order: {
          amount: this.totalAmount,
          currency: 'usd',
          id: new Date().getTime()
        }
      }
      this.paymentService.createPayment(payment).subscribe((paymentIntent) => {
        this.paymentId = paymentIntent['clientSecret'].toString();
      });
    }
  }

  calculateTotalAmount(items: Book[]) {
    let amount = 0;
    for (let i = 0; i < items.length; i++) {
      amount+= items[i].price!;
    }
    return amount;
  }

}
