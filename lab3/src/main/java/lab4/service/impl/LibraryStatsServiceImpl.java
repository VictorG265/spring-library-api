package lab4.service.impl;

import lab4.dto.LibraryStatsDTO;
import lab4.repository.BookRepository;
import lab4.repository.LoanRepository;
import lab4.service.LibraryStatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class LibraryStatsServiceImpl implements LibraryStatsService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private LoanRepository loanRepository;

    @Override
    public LibraryStatsDTO getLibraryStats() {
        LibraryStatsDTO dto = new LibraryStatsDTO();
        dto.setTotalBooks(bookRepository.count());
        dto.setTotalLoans(loanRepository.count());
        dto.setActiveLoans(loanRepository.countByActualReturnDateIsNull());
        dto.setNeverLoanedBooks(bookRepository.countBooksNeverLoaned());

        long debtors = loanRepository.findAll().stream()
                .filter(l -> l.getReturnDate() != null &&
                        (l.getActualReturnDate() == null && l.getReturnDate().isBefore(LocalDate.now()) ||
                                l.getActualReturnDate() != null && l.getActualReturnDate().isAfter(l.getReturnDate())))
                .map(l -> l.getReader().getId())
                .distinct()
                .count();

        dto.setTotalDebtors(debtors);
        return dto;
    }
}

