package lab4.controller;

import jakarta.annotation.PostConstruct;
import lab4.dto.BookDTO;
import lab4.dto.ReaderDTO;
import lab4.entity.Loan;
import lab4.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "api/loan", produces = MediaType.APPLICATION_JSON_VALUE)
public class LoanController extends AbstractController<Loan> {

    @Autowired
    private LoanService service;

    private HttpHeaders headers;

    @PostConstruct
    private void init() {
        headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
    }

    @Override
    public LoanService getService() {
        return service;
    }

    @GetMapping("/reader/{readerId}")
    public ResponseEntity<List<Loan>> getLoansByReader(@PathVariable Long readerId) {
        List<Loan> loans = service.readByReaderId(readerId);
        return loans.isEmpty() ? new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                new ResponseEntity<>(loans, headers, HttpStatus.OK);
    }

    @GetMapping("/book/{bookId}")
    public ResponseEntity<List<Loan>> getLoansByBook(@PathVariable Long bookId) {
        List<Loan> loans = service.readByBookId(bookId);
        return loans.isEmpty() ? new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                new ResponseEntity<>(loans, headers, HttpStatus.OK);
    }

    @GetMapping("/top-books")
    public ResponseEntity<List<BookDTO>> getPopularBooks() {
        return ResponseEntity.ok(service.getMostPopularBooks());
    }

    @GetMapping("/debtors")
    public ResponseEntity<List<ReaderDTO>> getDebtors() {
        return ResponseEntity.ok(service.getDebtors());
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> returnBook(@PathVariable Long id, @RequestBody Loan loan) {
        service.returnBook(id, loan.getActualReturnDate());
        return ResponseEntity.ok("Книга успешно возвращена");
    }

}
