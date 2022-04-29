import { PaymentService } from './../../generated/payment-service/api/payment.service';
import { BookService } from 'generated/book-service';
import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'book-store';

  constructor(
    private bookService: BookService,
    private paymentService: PaymentService
  ) {
    this.bookService.configuration.basePath =
    this.paymentService.configuration.basePath =
    '/api/v1';
  }
}
