package lab4.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import lab4.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
	Book findByTitle(String title);

	@Query("SELECT COUNT(b) FROM Book b WHERE b NOT IN (SELECT DISTINCT l.book FROM Loan l)")
	long countBooksNeverLoaned();
}
