package com.library.book.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.library.book.model.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book,String>{
	
	Book findByIsbn(String isbn);

}
