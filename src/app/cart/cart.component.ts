import { Component, OnInit } from '@angular/core';
import { CartserviceService } from '../cartservice.service';
import { LoginserviceService } from '../loginservice.service';
import { OrderService } from '../order.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {
  cartItems: any[] = [];
  total: number = 0;

  constructor(
    private cartService: CartserviceService,
    private loginService: LoginserviceService,
    private orderService: OrderService, private router: Router
  ) {}
 
 userId: number = 0; // Add this as a class property at the top (optional if already declared)

ngOnInit(): void {
  const user = this.loginService.getUser();
  console.log('Logged in user:', user);

  this.userId = user?.userId || 0;  // ✅ Save to class property
  console.log('User ID:', this.userId);

  if (this.userId) {
    this.cartService.getCartItems(this.userId).subscribe({
      next: (data) => {
        console.log('Cart Items from backend:', data);
        this.cartItems = data;
        this.calculateTotal();
      },
      error: (err) => {
        console.error('Error fetching cart items:', err);
      }
    });
  } else {
    console.warn('No userId found, cannot fetch cart items.');
  }
}

  calculateTotal(): void {
    this.total = this.cartItems.reduce((sum, item) => {
      return sum + (item.book.price * item.quantity);
    }, 0);
  }

removeItem(cartItemId: number): void {
  const item = this.cartItems.find(i => i.cartItemId === cartItemId);
  if (!item) return;

  this.cartService.decrementQuantity(cartItemId).subscribe({
    next: () => {
      // After backend decrements, update frontend cartItems accordingly:
      if (item.quantity > 1) {
        item.quantity -= 1;  // decrement quantity in frontend
      } else {
        // quantity was 1 and now item removed completely from backend, so remove from frontend array
        this.cartItems = this.cartItems.filter(i => i.cartItemId !== cartItemId);
      }
      this.calculateTotal();
    },
    error: (err) => {
      console.error('Error decrementing quantity:', err);
    }
  });
}
placeOrder(): void {
  if (!this.userId) {
    alert("User not logged in.");
    return;
  }

  this.orderService.placeOrder(this.userId).subscribe({
    next: (order) => {
      alert('Order placed successfully! Order ID: ' + order.orderId);
      this.router.navigate(['/orders']);
    },
    error: (err) => {
      console.error('Error placing order:', err);
      alert('Failed to place order. Try again later.');
    }
  });
}


}