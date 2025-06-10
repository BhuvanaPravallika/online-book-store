import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { BookService } from '../book.service';
import { Book } from '../Book';
import { Category } from '../Category';

@Component({
  selector: 'app-update-book',
  templateUrl: './update-book.component.html',
  styleUrls: ['./update-book.component.css']
})
export class UpdateBookComponent implements OnInit {
  bookForm!: FormGroup;
  categories: any[] = [];
  bookId!: number;

  constructor(
    private fb: FormBuilder,
    private route: ActivatedRoute,
    private bookService: BookService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.bookId = +this.route.snapshot.paramMap.get('bookId')!;

    // Initialize form with empty/default values first
    this.bookForm = this.fb.group({
      title: ['', Validators.required],
      author: ['', Validators.required],
      price: [0, [Validators.required, Validators.min(0)]],
      imageUrl: ['', Validators.required],
      description: ['', Validators.required],
      stock: [0, [Validators.required, Validators.min(0)]],
      category: ['', Validators.required]
    });

    // Load categories
    this.bookService.getCategories().subscribe(categories => {
      this.categories = categories;

      // Load book data after categories loaded
      this.bookService.getBookById(this.bookId).subscribe(book => {
        // Patch form values with the book data
        this.bookForm.patchValue({
          title: book.title,
          author: book.author,
          price: book.price,
          imageUrl: book.imageUrl,
          description: book.description,
          stock: book.stock,
          category: book.category?.category_Name || ''
        });
      });
    });
  }

  onSubmit(): void {
    if (this.bookForm.invalid) return;

    const updatedBook = {
      bookId: this.bookId,
      title: this.bookForm.value.title,
      author: this.bookForm.value.author,
      price: this.bookForm.value.price,
      imageUrl: this.bookForm.value.imageUrl,
      description: this.bookForm.value.description,
      stock: this.bookForm.value.stock,
      category: this.categories.find(
        c => c.category_Name === this.bookForm.value.category
      )
    };

    this.bookService.updateBook(this.bookId, updatedBook).subscribe({
      next: () => {
        alert('Book updated successfully!');
        this.router.navigate(['/adminbooks']);
      },
      error: err => {
        console.error('Update failed', err);
        alert('Failed to update book');
      }
    });
  }
  goBack(): void {
  this.router.navigate(['/adminbook']);
}
}