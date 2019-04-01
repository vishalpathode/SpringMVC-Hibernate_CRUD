package com.vish.spring.controller;

import java.util.List;

import javax.validation.Valid;

/*import javax.validation.Valid;*/

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.vish.spring.model.Book;
import com.vish.spring.service.BookService;

@CrossOrigin("*") // to enable cross-origin requests.
@RestController
public class BookController {

	@Autowired
	private BookService bookService;
	
	//get all books
	@GetMapping("/api/book")
	public ResponseEntity<List<Book>> list(){
		List<Book> list=null;
		try {
			list = bookService.list();
		}catch(Exception e){
			System.out.println(e.getMessage());
			return ResponseEntity.ok().body(null);
		}
		return ResponseEntity.ok().body(list);
	}
	
	//save the book
	@PostMapping("/api/saveBook")
	public ResponseEntity<?> save(@Valid @RequestBody Book book,BindingResult result){
		
		if(result.hasFieldErrors()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result.getFieldError().getDefaultMessage());
		}else {
			long id = bookService.save(book);	
			return ResponseEntity.ok().body("Book is created with ID: "+id);
		}
		
	}	
	
	// get single record
	@GetMapping("/api/get/{id}")
	public ResponseEntity<?> get(@PathVariable("id") String _id) {
		Long id =0L;
		try {
			id = Long.parseLong(_id);
			Book book = bookService.get(id);

			return ResponseEntity.ok().body(book);
			
		}catch (NumberFormatException e) {
			System.out.println("Id should be Number only...");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Id should be Number only.");
		}catch (Exception e) {
			System.out.println(e.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Id '"+_id+"' not found.");
		}	
		
		
	
	}
	
	// update record
	@PutMapping("/api/update/{id}")
	public ResponseEntity<?> update(@PathVariable("id") String _id,@Valid @RequestBody Book book,BindingResult result ) {
		Long id =0L;
		try {
			if(result.hasFieldErrors()) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result.getFieldError().getDefaultMessage());
			}
			
			id = Long.parseLong(_id);
			bookService.update(id, book);
			return ResponseEntity.ok().body("Book has been updated.");
			
		}catch (NumberFormatException e) {
			System.out.println("Id should be Number only...");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Id should be Number only.");
		}catch (Exception e) {
			System.out.println("Id not found...");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Id '"+_id+"' not found.");
		}
	}
	
	//delete record
	@DeleteMapping("/api/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") String _id){
		try {
			Long id = Long.parseLong(_id);
			bookService.delete(id);
			return ResponseEntity.ok().body("Record deleted...");
			
		}catch (NumberFormatException e) {
			System.out.println("Id should be Number only...");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Id should be Number only.");
		}catch (Exception e) {
			System.out.println(e.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Id '"+_id+"' not found.");
		}	
	}
	
	
}
