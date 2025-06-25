package in.thiru.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.thiru.entity.Book;
import in.thiru.repo.BookRepo;

@Service
public class BookServiceImpl implements IBookService {

	@Autowired
	private BookRepo bookRepo;

	@Override
	public boolean insertBook(Book book) {
		Book save = bookRepo.save(book);
		return save.getBookId() != null;
	}

	@Override
	public Book getBook(Integer bookId) {
		Optional<Book> findById = bookRepo.findById(bookId);
		return findById.get();
	}

	

	@Override
	public List<Book> getAllBooks() {
		List<Book> findAll = bookRepo.findAll();
		return findAll;
	}

	@Override
	public void deleteBook(Integer bookId) {
		bookRepo.deleteById(bookId);
		
	}

}
