import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { BookService } from '../book.service';
import { Router } from '@angular/router';
import { Book } from '../Book';
import { Category } from '../Category';

@Component({
  selector: 'app-add-book',
  templateUrl: './add-book.component.html',
  styleUrls: ['./add-book.component.css']
})
export class AddBookComponent  implements OnInit {
  bookForm: FormGroup;
  categories: Category[] = [];

  constructor(
    private fb: FormBuilder,
    private bookService: BookService,
    private router: Router
  ) {
    this.bookForm = this.fb.group({
      title: ['', Validators.required],
      author: ['', Validators.required],
      price: [0, [Validators.required, Validators.min(0.01)]],
      imageUrl: ['', Validators.required],
      description: ['', Validators.required],
      stock: [0, [Validators.required, Validators.min(0)]],
      category_Id: ['', Validators.required] // only the ID is selected
    });
  }

  ngOnInit(): void {
    this.bookService.getCategories().subscribe({
      next: (data) => this.categories = data,
      error: (err) => console.error('Error loading categories', err)
    });
  }

  onSubmit(): void {
    if (this.bookForm.valid) {
      const formValue = this.bookForm.value;

      // Find the full category object using the selected ID
      const selectedCategory = this.categories.find(
        cat => cat.category_Id === +formValue.category_Id
      );

      if (!selectedCategory) {
        alert('Please select a valid category');
        return;
      }

      // Construct the book object
      const newBook: Book = {
        title: formValue.title,
        author: formValue.author,
        price: formValue.price,
        imageUrl: formValue.imageUrl,
        description: formValue.description,
        stock: formValue.stock,
        category: selectedCategory // Pass full object, not just ID
      };

      this.bookService.addBook(newBook).subscribe({
        next: () => {
          alert('Book added successfully!');
         
        },
        error: (err) => {
          console.error('Error adding book:', err);
          alert('Failed to add book');
        }
      });
    }
  }
goBackHome() {
  this.router.navigate(['/adminbook']);  // Adjust if your home route is different
}
}