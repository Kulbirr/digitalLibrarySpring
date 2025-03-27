package com.example.Digital_Library.Digital_Library.Controller;


import com.example.Digital_Library.Digital_Library.CustomException.BookNotFoundException;
import com.example.Digital_Library.Digital_Library.Entity.Book;
import com.example.Digital_Library.Digital_Library.Service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/books")
public class BookController {
//    didn't face any challenges when building this project it was easy and if given more time I would improve it with
//    security build librarian class student class assign roles.
    @Autowired
    private BookService bookService;

    // Add book
    @PostMapping("/add")
    public ResponseEntity<Book> addBook(@RequestBody Book book) {
        return ResponseEntity.ok(bookService.addBook(book));
    }

    // Get all books
    @GetMapping("/all")
    public ResponseEntity<List<Book>> getAllBooks() {
        return ResponseEntity.ok(bookService.getAllBooks());
    }

    // Get book by ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getBookById(@PathVariable Long id) throws BookNotFoundException {
        try {
            Optional<Book> book = Optional.ofNullable(bookService.getBookById(id));
            return book.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
        } catch (BookNotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    // Update book
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateBook(@PathVariable Long id, @RequestBody Book updatedBook) {
        try {
            Book book = bookService.updateBook(id, updatedBook);
            return ResponseEntity.ok(book);
        } catch (BookNotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    // Delete book
    @DeleteMapping("/delete/{id}")
    public String deleteBook(@PathVariable Long id) throws BookNotFoundException {
        try{
            bookService.deleteBook(id);
            return "Book Deleted Successfully.";
        }catch (BookNotFoundException e){
            return e.getMessage();
        }

    }
}

