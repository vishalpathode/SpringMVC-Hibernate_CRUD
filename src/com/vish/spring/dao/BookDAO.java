package com.vish.spring.dao;

import java.util.List;

import com.vish.spring.model.Book;

public interface BookDAO {

	//save the record
	long save(Book book);
	
	//get a single record
	Book get(long id);
	
	//get all records
	List<Book> list();
	
	//update record
	void update(long id,Book book);
	
	//delete record
	void delete(long id);
}
