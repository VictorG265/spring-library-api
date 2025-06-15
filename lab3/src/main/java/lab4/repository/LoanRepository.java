package lab4.repository;

import lab4.entity.Reader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import lab4.entity.Loan;
import java.util.List;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Long> {
    List<Loan> findByReaderId(Long readerId);
    List<Loan> findByBookId(Long bookId);

    @Query("SELECT l.book, COUNT(l) AS loanCount FROM Loan l GROUP BY l.book ORDER BY loanCount DESC")
    List<Object[]> findMostPopularBooks();

    @Query("SELECT l.reader FROM Loan l WHERE l.returnDate IS NOT NULL AND (l.actualReturnDate IS NULL AND l.returnDate < CURRENT_DATE OR l.actualReturnDate > l.returnDate)")
    List<Reader> findDebtors();

    @Query("SELECT COUNT(l) FROM Loan l WHERE l.reader.id = :readerId AND l.returnDate IS NOT NULL AND (l.actualReturnDate IS NULL OR l.actualReturnDate > l.returnDate)")
    long countOverdueLoans(@Param("readerId") Long readerId);

    @Query("SELECT COUNT(l) FROM Loan l WHERE l.reader.id = :readerId AND l.returnDate IS NOT NULL AND l.actualReturnDate IS NULL AND l.returnDate < CURRENT_DATE")
    long countActiveOverdueLoans(@Param("readerId") Long readerId);

}

