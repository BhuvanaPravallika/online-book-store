import { Component, OnInit } from '@angular/core';
import { Book } from '../Book';
import { BookService } from '../book.service';
import { LoginserviceService } from '../loginservice.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-adminbook',
  templateUrl: './adminbook.component.html',
  styleUrls: ['./adminbook.component.css']
})
export class AdminbookComponent implements OnInit {
  categories: any[] = [];
  filteredBooks: any[] = [];
  selectedCategory: string = 'all';
  searchText: string = '';
  user: any;

  constructor(
    private loginService: LoginserviceService,
    private bookService: BookService,
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
      booksToFilter = category?.books || [];
    }

    if (this.searchText.trim() !== '') {
      const lowerSearch = this.searchText.toLowerCase();
      booksToFilter = booksToFilter.filter(book =>
        book.title.toLowerCase().includes(lowerSearch)
      );
    }

    this.filteredBooks = booksToFilter;
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

  logout(): void {
    this.loginService.logout();
    this.router.navigate(['/home']);
  }

  addBook(): void {
    this.router.navigate(['/add-book']);
  }

 deleteBook(book: any): void {
  const confirmDelete = confirm(`Are you sure you want to delete "${book.title}"?`);
  if (confirmDelete) {
    this.bookService.deleteBook(book.bookId).subscribe({
      next: () => {
        alert('Book deleted successfully');

        // Remove the deleted book from categories array
        this.categories.forEach(cat => {
          if (cat.books) {
            cat.books = cat.books.filter(b => b.bookId !== book.bookId);
          }
        });

        // Remove from filteredBooks as well (for immediate UI update)
        this.filteredBooks = this.filteredBooks.filter(b => b.bookId !== book.bookId);
      },
      error: (err) => {
        console.error('Delete failed', err);
        alert('Failed to delete book');
      }
    });
  }
}


  updateBook(book: any): void {
    this.router.navigate(['/update-book', book.bookId]);
  }
  
}