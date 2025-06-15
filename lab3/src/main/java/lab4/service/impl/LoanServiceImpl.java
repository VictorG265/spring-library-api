package lab4.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import lab4.dto.BookDTO;
import lab4.dto.ReaderDTO;
import lab4.entity.Book;
import lab4.entity.Reader;
import lab4.repository.BookRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lab4.entity.Loan;
import lab4.repository.LoanRepository;
import lab4.service.LoanService;

@Service
public class LoanServiceImpl implements LoanService {

    @Autowired
    private LoanRepository loanRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Loan read(Long id) {
        return loanRepository.findById(id).orElseThrow(IllegalArgumentException::new);
    }

    @Override
    public List<Loan> read() {
        return loanRepository.findAll();
    }

    @Override
    public List<Loan> readByReaderId(Long readerId) {
        return loanRepository.findByReaderId(readerId);
    }

    @Override
    public List<Loan> readByBookId(Long bookId) {
        return loanRepository.findByBookId(bookId);
    }

    @Override
    public void update(Loan entity) {
        Loan loan = loanRepository.findById(entity.getId()).orElseThrow(IllegalArgumentException::new);
        loan.setBook(entity.getBook());
        loan.setReader(entity.getReader());
        loan.setIssueDate(entity.getIssueDate());
        loan.setReturnDate(entity.getReturnDate());
        loanRepository.save(loan);
    }

    @Override
    public void save(Loan loan) {
        Reader reader = loan.getReader();
        long activeOverdueCount = loanRepository.countActiveOverdueLoans(reader.getId());

        if (activeOverdueCount >= 2) {
            throw new IllegalStateException("Читатель имеет более 2 активных просрочек и не может получить новую книгу.");
        }

        Book book = bookRepository.findById(loan.getBook().getId())
                .orElseThrow(() -> new IllegalArgumentException("Книга не найдена"));

        if (book.getQuantity() > 0) {
            book.setQuantity(book.getQuantity() - 1);
            bookRepository.save(book);
            loanRepository.save(loan);
        } else {
            throw new IllegalStateException("Книга недоступна для выдачи.");
        }
    }

    @Override
    public void returnBook(Long loanId, LocalDate actualReturnDate) {
        Loan loan = loanRepository.findById(loanId)
                .orElseThrow(() -> new IllegalArgumentException("Выдача не найдена"));

        loan.setActualReturnDate(actualReturnDate);
        Book book = loan.getBook();
        book.setQuantity(book.getQuantity() + 1);
        bookRepository.save(book);

        loanRepository.save(loan);
    }

    @Override
    public void delete(Long loanId) {
        Loan loan = loanRepository.findById(loanId).orElseThrow(() -> new IllegalArgumentException("Выдача не найдена"));
        Book book = loan.getBook();
        book.setQuantity(book.getQuantity() + 1);
        bookRepository.save(book);
        loanRepository.delete(loan);
    }

    @Override
    public List<BookDTO> getMostPopularBooks() {
        return loanRepository.findMostPopularBooks().stream()
                .map(obj -> {
                    Book book = (Book) obj[0];
                    long loanCount = (long) obj[1];
                    return new BookDTO(book.getTitle(), book.getAuthor(), loanCount);
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<ReaderDTO> getDebtors() {
        return loanRepository.findAll().stream()
                .filter(loan -> loan.getReturnDate() != null && // Проверяем, есть ли установленный срок возврата
                        (loan.getActualReturnDate() == null && loan.getReturnDate().isBefore(LocalDate.now()) || // Книга не возвращена и срок прошел
                                loan.getActualReturnDate() != null && loan.getActualReturnDate().isAfter(loan.getReturnDate()))) // Книга возвращена, но с просрочкой
                .map(loan -> modelMapper.map(loan.getReader(), ReaderDTO.class))
                .distinct() // Исключаем дубликаты читателей
                .collect(Collectors.toList());
    }
}
