package lab4.service;

import lab4.dto.BookDTO;
import lab4.dto.ReaderDTO;
import lab4.entity.Loan;
import java.time.LocalDate;
import java.util.List;

public interface LoanService extends Service<Loan> {
    List<Loan> readByReaderId(Long readerId);
    List<Loan> readByBookId(Long bookId);
    void returnBook(Long loanId, LocalDate actualReturnDate);
    List<BookDTO> getMostPopularBooks();
    List<ReaderDTO> getDebtors();
}


