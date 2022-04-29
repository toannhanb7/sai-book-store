import { CartService } from './../../order/services/cart-service';
import { Component, OnInit } from "@angular/core";
import { Book, BookGetAll, BookService } from "generated/book-service";
import { Router } from '@angular/router';

@Component({
  selector: 'product-list',
  templateUrl: './product-list.component.html',
  styleUrls: [
    './product-list.component.scss'
  ]
})

export class ProductListComponent implements OnInit {

  books: Book[] = [];

  constructor(private bookService: BookService,
    private cartService: CartService,
    private router: Router
    ) {
  }

  ngOnInit(): void {
    this.bookService.getAllBooks().subscribe((data: BookGetAll) => {
      this.books = data.items!;
    })
  }

  existInCart(book: Book) {
    return this.cartService.cartContains(book);
  }

  addToCart(book: Book) {
    this.cartService.addToCart(book)
  }

  removeFromCart(book: Book) {
    this.cartService.removeFromCart(book);
  }

  processCheckout() {
    this.router.navigate(['/payment/checkout'])
  }

  get cartItemCount(): number {
    return this.cartService.getAllCartItem().length;
  }
}
