import { Category } from "./Category";

export interface Book {
  bookId?: number;         // Optional when adding a new book
  title: string;
  author: string;
  price: number;
  imageUrl: string;
  description: string;
  stock: number;
  category?: Category;     // Optional – depends on how you're handling categories
}
