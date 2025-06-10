import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Book } from './Book';

@Injectable({
  providedIn: 'root'
})
export class BookService {

  private apiUrl = 'http://localhost:8080/books';  // Adjust API URL for books

  constructor(private http: HttpClient) {}

  getBooks(): Observable<any[]> {
    return this.http.get<any[]>(this.apiUrl);
  }
 getCategories(): Observable<any[]> {
  return this.http.get<any[]>('http://localhost:8080/categories');
}


  getBooksByCategory(categoryName: string): Observable<any[]> {
    return this.http.get<any[]>(`${this.apiUrl}/categories/${categoryName}`);
  }

   addBook(book: Book): Observable<Book> {
    return this.http.post<Book>(`${this.apiUrl}/addbook`, book);
  }

  updateBook(bookId: number, book: Book): Observable<Book> {
    return this.http.put<Book>(`${this.apiUrl}/update/${bookId}`, book);
  }

deleteBook(bookId: number): Observable<string> {
  return this.http.delete(`${this.apiUrl}/delete/${bookId}`, { responseType: 'text' });
}

getBookById(bookId: number): Observable<Book> {
  return this.http.get<Book>(`${this.apiUrl}/${bookId}`);
}


}
