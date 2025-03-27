package com.example.Digital_Library.Digital_Library.Service;


import com.example.Digital_Library.Digital_Library.CustomException.BookNotFoundException;
import com.example.Digital_Library.Digital_Library.DAO.BookDAO;
import com.example.Digital_Library.Digital_Library.Entity.Book;
import com.example.Digital_Library.Digital_Library.Repo.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookDAO bookDAO; // Now using BookDAO instead of BookRepository

    // Add new book
    public Book addBook(Book book) {
        return bookDAO.save(book);
    }

    // Get all books
    public List<Book> getAllBooks() {
        return bookDAO.findAll();
    }

    // Get book by ID
    public Book getBookById(Long id) throws BookNotFoundException {
        return bookDAO.findById(id)
                .orElseThrow(() -> new BookNotFoundException("Book with ID " + id + " not found"));
    }

    // Update book
    public Book updateBook(Long id, Book updatedBook) throws BookNotFoundException {
        if (!bookDAO.existsById(id)) {
            throw new BookNotFoundException("Book with ID " + id + " not found");
        }
        return bookDAO.updateBook(id, updatedBook);
    }

    // Delete book
    public void deleteBook(Long id) throws BookNotFoundException {
        if (!bookDAO.existsById(id)) {
            throw new BookNotFoundException("Book with ID " + id + " not found");
        }
        bookDAO.deleteById(id);
    }
}
