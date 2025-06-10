import { Component, OnInit } from '@angular/core';
import { LoginserviceService } from '../loginservice.service';
import { BookService } from '../book.service';
import { Router } from '@angular/router';
import { CartserviceService } from '../cartservice.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
 categories: any[] = [];
  filteredBooks: any[] = [];
  selectedCategory: string = 'all';
  searchText: string = '';
  user: any;

  constructor(
    private loginService: LoginserviceService,
    private bookService: BookService,
      private cartService: CartserviceService,
    private router: Router
  ) {}

 ngOnInit(): void {
    this.user = this.loginService.getUser();

    this.bookService.getCategories().subscribe(data => {
      this.categories = data;
      console.log('Categories from backend:', this.categories);
      this.applyFilters();
    });
  }

  applyFilters(): void {
    console.log('Filtering books for category:', this.selectedCategory, 'and search:', this.searchText);

    let booksToFilter: any[] = [];

    if (this.selectedCategory.toLowerCase() === 'all') {
      this.categories.forEach(cat => {
        if (cat.books && cat.books.length > 0) {
          booksToFilter = booksToFilter.concat(cat.books);
        }
      });
    } else {
      const category = this.categories.find(
        cat => cat.category_Name.toLowerCase() === this.selectedCategory.toLowerCase()
      );
      console.log('Selected category object:', category);
      booksToFilter = category && category.books ? category.books : [];
    }

    if (this.searchText.trim() !== '') {
      const lowerSearch = this.searchText.toLowerCase();
      booksToFilter = booksToFilter.filter(book =>
        book.title.toLowerCase().includes(lowerSearch)
      );
    }

    this.filteredBooks = booksToFilter;
    console.log('Filtered Books:', this.filteredBooks);
  }

  onCategoryChange(category: string): void {
    this.selectedCategory = category;
    this.applyFilters();
  }

  onSearchChange(): void {
    this.applyFilters();
  }

  isLoggedIn(): boolean {
    return this.loginService.isLoggedIn();
  }

  viewProfile(): void {
    this.router.navigate(['/profile']);
  }

  viewCart(): void {
    this.router.navigate(['/cart']);
  }

  logout(): void {
    this.loginService.logout();
    this.router.navigate(['/home']);
  }

addToCart(book: any): void {
  if (this.isLoggedIn()) {
    const userId = this.user?.userId || this.user?.user_Id || this.user?.id;

    if (!userId) {
      alert("Invalid user session");
      return;
    }

    console.log('Adding to cart:', book);  // 👈 See what properties exist
    const bookId = book.bookId; // or book.bookId or whatever your backend expects

    this.cartService.addToCart(userId, bookId, 1).subscribe({
      next: () => {
        alert(`${book.title} added to cart`);
      },
      error: (err) => {
        console.error('Failed to add to cart:', err);
        alert('Failed to add item to cart');
      }
    });
  } else {
    alert('Please log in to add books to the cart');
   
  }
}

}