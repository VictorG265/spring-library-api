package lab4.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import lab4.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
	Book findByTitle(String title);
}
