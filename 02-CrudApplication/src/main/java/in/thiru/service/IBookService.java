package in.thiru.service;

import java.util.List;

import in.thiru.entity.Book;

public interface IBookService {
	
	
	public boolean insertBook(Book book);
	public Book getBook(Integer bookId);
	public void deleteBook(Integer bookId);
	public List<Book> getAllBooks();

}
