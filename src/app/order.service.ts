import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Order } from './Order';

@Injectable({
  providedIn: 'root'
})
export class OrderService {

 private baseUrl = 'http://localhost:8080/orders';

  constructor(private http: HttpClient) {}

  placeOrder(userId: number): Observable<Order> {
    return this.http.post<Order>(`${this.baseUrl}/place/${userId}`, {});
  }

 getUserOrders(userId: number): Observable<Order[]> {
  const headers = new HttpHeaders({
    'Cache-Control': 'no-cache',
    'Pragma': 'no-cache',
    'Expires': '0'
  });
  return this.http.get<Order[]>(`${this.baseUrl}/user/${userId}`, { headers });
}
 deleteOrder(orderId: number): Observable<void> {
  return this.http.delete<void>(`${this.baseUrl}/${orderId}`, { responseType: 'text' as 'json' });
}

}