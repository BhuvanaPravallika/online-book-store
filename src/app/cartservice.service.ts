import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CartserviceService {

   private baseUrl = 'http://localhost:8080/cart'; // adjust if your port/backend differs

  constructor(private http: HttpClient) { }

  // ✅ Add to cart using userId, bookId, and quantity
 addToCart(userId: number, bookId: number, quantity: number = 1): Observable<any> {
  return this.http.post(
    `${this.baseUrl}/add/${userId}/${bookId}?quantity=${quantity}`,
    {}  // send empty body
  );
}


  // ✅ Get all items in a user's cart
  getCartItems(userId: number): Observable<any> {
    return this.http.get(`${this.baseUrl}/user/${userId}`);
  }

  // ✅ Remove an item from the cart by cart item ID
  removeFromCart(cartItemId: number): Observable<any> {
    return this.http.delete(`${this.baseUrl}/remove/${cartItemId}`, { responseType: 'text' });
  }

  // ✅ Clear all items from a user's cart
  clearCart(userId: number): Observable<any> {
    return this.http.delete(`${this.baseUrl}/clear/${userId}`, { responseType: 'text' });
  }

  // ✅ Get total price of cart
  getTotalAmount(userId: number): Observable<number> {
    return this.http.get<number>(`${this.baseUrl}/total/${userId}`);
  }

  // ✅ Update quantity of a cart item

decrementQuantity(cartItemId: number): Observable<string> {
  return this.http.put(`${this.baseUrl}/decrementQuantity/${cartItemId}`, null, { responseType: 'text' });
}

 getUserOrders(userId: number) {
    return this.http.get(`${this.baseUrl}/orders/user/${userId}`);
  }


}
