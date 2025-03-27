package com.example.Digital_Library.Digital_Library.Repo;


import com.example.Digital_Library.Digital_Library.Entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

}

