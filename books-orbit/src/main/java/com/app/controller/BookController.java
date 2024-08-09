package com.app.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dtos.BookDTO;
import com.app.dtos.BookDTO2;
import com.app.dtos.CategoryIdDto;
import com.app.dtos.UserIdDto;
import com.app.entites.Books;
import com.app.entites.Category;
import com.app.entites.User;
import com.app.response.ApiResponse;
import com.app.service.BookService;
import com.app.service.CatService;
import com.app.service.UserService;

@CrossOrigin
@RestController
@RequestMapping("/books")
public class BookController {
	

	@Autowired
	private BookService bookService;
	@Autowired
	private CatService catService;
	@Autowired
	private ModelMapper mapper;
	@Autowired
	private UserService userService;
	
	
	
	@PostMapping
	private ResponseEntity<ApiResponse> addBook(@RequestBody BookDTO bookDto){	
		Books book = mapToEntity(bookDto);
		return ResponseEntity.ok(bookService.addBook(book));
	
	}
	
	@GetMapping
	private ResponseEntity<List<BookDTO>> getBooks(){
		List<Books> list2 = bookService.getBooks();
		List<BookDTO> list = list2.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
		
		return ResponseEntity.ok(list);		
	}
	@GetMapping("/{bid}")
	private ResponseEntity<BookDTO2> getBook(@PathVariable Long bid){
		Books book1 = bookService.getBook(bid);
		BookDTO2 book = mapper.map(book1, BookDTO2.class);
		return ResponseEntity.ok(book);		
	}
	
	
	
	@DeleteMapping("/{bid}")
	private ResponseEntity<ApiResponse> delBook(@PathVariable Long bid){
		return ResponseEntity.ok(bookService.delBook(bid));
	}
	
	
	
	private Books mapToEntity(BookDTO bookDto) {

		Books book = new Books();
		book.setBookName(bookDto.getBookName());
		book.setDescription(bookDto.getDescription());
		book.setQuantity(bookDto.getQuantity());
		book.setPrice(bookDto.getPrice());
		book.setImgUrl(bookDto.getImgUrl());
		
		User user = userService.getById(bookDto.getSellerId().getUserId());
		Category cat = catService.getById(bookDto.getCategoryId().getCateId());
		book.setSeller(user);
		book.setCategory(cat);
		return book;
	}
	
	
	private BookDTO convertToDTO(Books book) {
		
		BookDTO dto = new BookDTO();
        dto.setBookId(book.getBookId());
        dto.setBookName(book.getBookName());
        dto.setDescription(book.getDescription());
        dto.setQuantity(book.getQuantity());
        dto.setPrice(book.getPrice());
        dto.setImgUrl(book.getImgUrl());
        dto.setSellerId(new UserIdDto(book.getSeller().getUserId())); 
        dto.setCategoryId(new CategoryIdDto(book.getCategory().getCategoryId()));
        
        return dto;
    }

}
