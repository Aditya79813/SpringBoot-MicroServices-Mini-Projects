package in.thiru.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import in.thiru.entity.Book;

public interface BookRepo extends JpaRepository<Book, Integer>{

}
