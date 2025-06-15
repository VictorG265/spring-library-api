package lab4.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDate;

@Entity
@Table(name = "loans")
public class Loan extends AbstractEntity {

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "book_id", nullable = false)
    @NotNull(message = "Книга не может быть пустой")
    private Book book;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "reader_id", nullable = false)
    @NotNull(message = "Читатель не может быть пустым")
    private Reader reader;

    @PastOrPresent(message = "Дата выдачи не может быть в будущем")
    @Column
    @NotNull(message = "Дата выдачи обязательна!")
    private LocalDate issueDate;

    @FutureOrPresent(message = "Дата возврата не может быть в прошлом")
    @Column
    @NotNull(message = "Дата возврата обязательна!")
    private LocalDate returnDate;

    @PastOrPresent(message = "Фактическая дата возврата не может быть в будущем")
    @Column
    private LocalDate actualReturnDate;


    // Геттеры и сеттеры
    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Reader getReader() {
        return reader;
    }

    public void setReader(Reader reader) {
        this.reader = reader;
    }

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(LocalDate issueDate) {
        this.issueDate = issueDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public LocalDate getActualReturnDate() {
        return actualReturnDate;
    }

    public void setActualReturnDate(LocalDate actualReturnDate) {
        this.actualReturnDate = actualReturnDate;
    }
}
