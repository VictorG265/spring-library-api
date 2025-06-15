package lab4.dto;

public class BookDTO {
    private String title;
    private String author;
    private long loanCount;

    public BookDTO() {}

    public BookDTO(String title, String author, long loanCount) {
        this.title = title;
        this.author = author;
        this.loanCount = loanCount;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public long getLoanCount() {
        return loanCount;
    }

    public void setLoanCount(long loanCount) {
        this.loanCount = loanCount;
    }
}
