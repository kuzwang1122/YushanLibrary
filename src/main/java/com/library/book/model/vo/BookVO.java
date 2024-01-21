package com.library.book.model.vo;

import com.library.book.model.entity.Book;
import lombok.Data;

@Data
public class BookVO {

	private String isbn;
	private String name;
	private String author;
	private String introduction;
	
	public BookVO(){
		
	}
	
	public BookVO( Book book ) {
		this.isbn = book.getIsbn();
		this.name = book.getName();
		this.author = book.getAuthor();
		this.introduction = book.getIntroduction();
		
	}
}
