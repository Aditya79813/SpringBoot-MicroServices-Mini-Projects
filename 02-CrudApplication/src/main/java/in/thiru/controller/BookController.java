package in.thiru.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import in.thiru.entity.Book;
import in.thiru.service.IBookService;

@Controller
public class BookController {
	
	
	@Autowired
	private IBookService bookService;
	
	@GetMapping("/")
	public String loadBookPage(Model model)
	{
		model.addAttribute("book", new Book());
		
		return "index";
	}
	
	
	@PostMapping("/addbook")
	public String insertBook(Book book,Model model)
	{
		boolean isInsertBook = bookService.insertBook(book);
		
		if(isInsertBook)
		{
			model.addAttribute("smsg", "Book Inserted");
		}
		else
		{
			model.addAttribute("emsg", "Book Not Inserted");
		}
		
		return "index";
	}
	
	
	@GetMapping("/view-book")
	public String loadViewBookPage(Model model)
	{
		
		List<Book> allBooks = bookService.getAllBooks();
		model.addAttribute("books", allBooks);
		
		return "view-book";
	}
	
	
	@GetMapping("/edit")
	public String editBook(@RequestParam("bookId") Integer bookId, Model model)
	{
		
		Book book = bookService.getBook(bookId);
		
		model.addAttribute("book", book);
		
		return "index";
	}
	
//	@GetMapping("/edit")
//	public String editBook(@RequestParam("bookId") Integer bookId, Model model) {
//	    Book book = bookService.getBook(bookId);
//	    if (book == null) {
//	        // Initialize an empty book object if not found
//	        book = new Book();
//	    }
//	    model.addAttribute("book", book); // Ensure 'book' is always present
//	    return "index";
//	}
	
	
	@GetMapping("/delete")
	public String deleteBook(@RequestParam("bookId") Integer bookId,Model model)
	{
		
		bookService.deleteBook(bookId);
		
		List<Book> allBooks = bookService.getAllBooks();
		
		
		model.addAttribute("books", allBooks);
		
		return "view-book";
	}
	

	
	
	
	

}
