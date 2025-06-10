import { Component, OnInit } from '@angular/core';
import { Order } from '../Order';
import { OrderService } from '../order.service';
import { LoginserviceService } from '../loginservice.service';

@Component({
  selector: 'app-order',
  templateUrl: './order.component.html',
  styleUrls: ['./order.component.css']
})
export class OrderComponent implements OnInit {
   orders: Order[] = [];
  userId!: number;  // will be set dynamically

  constructor(
    private orderService: OrderService,
    private loginService: LoginserviceService
  ) {}

  ngOnInit(): void {
    const userObj = this.loginService.getUser();
    if (userObj && userObj.userId) {
      this.userId = userObj.userId;
      this.loadOrders();
    } else {
      alert("User not logged in. Please login first.");
    }
  }

  loadOrders(): void {
    this.orderService.getUserOrders(this.userId).subscribe(
      data => {
        this.orders = data;
      },
      error => {
        console.error('Failed to load orders:', error);
        alert('Could not fetch orders.');
      }
    );
  }

  deleteOrder(orderId: number): void {
    this.orderService.deleteOrder(orderId).subscribe({
      next: () => {
        alert(`Order ${orderId} deleted successfully`);
        this.orders = this.orders.filter(order => order.orderId !== orderId);
      },
      error: (err) => {
        console.error('Delete failed:', err);
        alert(`Failed to delete order ${orderId}: ${err.message || err.statusText || 'Unknown error'}`);
      }
    });
  }
}