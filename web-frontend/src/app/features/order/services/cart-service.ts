import { Book } from 'generated/book-service';
import { Injectable } from "@angular/core";

@Injectable({
  providedIn: 'root'
})

export class CartService {
  private books: Book[] = [];

  addToCart(book: Book) {
    this.books.push(book);
  }

  removeFromCart(book: Book) {
    this.books = this.books.filter(b => b.id != book.id);
  }

  cartContains(book: Book) {
    for (let i = 0; i < this.books.length; i++) {
      if (this.books[i].id == book.id) {
        return true;
      }
    }
    return false;
  }

  removeAllItemFromCart() {
    this.books = [];
  }

  getAllCartItem() {
    return this.books;
  }
}
