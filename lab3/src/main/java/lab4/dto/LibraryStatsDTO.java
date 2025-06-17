package lab4.dto;

public class LibraryStatsDTO {
    private long totalBooks;
    private long totalLoans;
    private long activeLoans;
    private long totalDebtors;
    private long neverLoanedBooks;

    public LibraryStatsDTO() {
    }

    // Конструктор со всеми полями
    public LibraryStatsDTO(long totalBooks, long totalLoans, long activeLoans,
                           long totalDebtors, long neverLoanedBooks) {
        this.totalBooks = totalBooks;
        this.totalLoans = totalLoans;
        this.activeLoans = activeLoans;
        this.totalDebtors = totalDebtors;
        this.neverLoanedBooks = neverLoanedBooks;
    }

    // Геттеры и сеттеры
    public long getTotalBooks() {
        return totalBooks;
    }

    public void setTotalBooks(long totalBooks) {
        this.totalBooks = totalBooks;
    }

    public long getTotalLoans() {
        return totalLoans;
    }

    public void setTotalLoans(long totalLoans) {
        this.totalLoans = totalLoans;
    }

    public long getActiveLoans() {
        return activeLoans;
    }

    public void setActiveLoans(long activeLoans) {
        this.activeLoans = activeLoans;
    }

    public long getTotalDebtors() {
        return totalDebtors;
    }

    public void setTotalDebtors(long totalDebtors) {
        this.totalDebtors = totalDebtors;
    }

    public long getNeverLoanedBooks() {
        return neverLoanedBooks;
    }

    public void setNeverLoanedBooks(long neverLoanedBooks) {
        this.neverLoanedBooks = neverLoanedBooks;
    }

    // toString() — пригодится для отладки или логов
    @Override
    public String toString() {
        return "LibraryStatsDTO{" +
                "totalBooks=" + totalBooks +
                ", totalLoans=" + totalLoans +
                ", activeLoans=" + activeLoans +
                ", totalDebtors=" + totalDebtors +
                ", neverLoanedBooks=" + neverLoanedBooks +
                '}';
    }
}
