package com.example.Digital_Library.Digital_Library.DAO;


import com.example.Digital_Library.Digital_Library.CustomException.BookNotFoundException;
import com.example.Digital_Library.Digital_Library.Entity.Book;
import com.example.Digital_Library.Digital_Library.Repo.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class BookDAO {

    @Autowired
    private BookRepository bookRepository;

    // Save new book
    public Book save(Book book) {
        return bookRepository.save(book);
    }

    // Get all books
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    // Get book by ID
    public Optional<Book> findById(Long id) {
        return bookRepository.findById(id);
    }

    // Check if book exists
    public boolean existsById(Long id) {
        return bookRepository.existsById(id);
    }

    // Update existing book
    public Book updateBook(Long id, Book updatedBook) {
        try {
            return bookRepository.findById(id).map(existingBook -> {
                //updating fields that are provided
                if (updatedBook.getTitle() != null && !updatedBook.getTitle().isEmpty()) {
                    existingBook.setTitle(updatedBook.getTitle());
                }
                if (updatedBook.getAuthor() != null && !updatedBook.getAuthor().isEmpty()) {
                    existingBook.setAuthor(updatedBook.getAuthor());
                }
                if (updatedBook.getGenre() != null && !updatedBook.getGenre().isEmpty()) {
                    existingBook.setGenre(updatedBook.getGenre());
                }
                if (updatedBook.getAvailability() != null) {
                    existingBook.setAvailability(updatedBook.getAvailability());
                }

                return bookRepository.save(existingBook);
            }).orElseThrow(() -> new BookNotFoundException("Book with ID " + id + " not found"));
        } catch (BookNotFoundException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    // Delete book by ID
    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }
}