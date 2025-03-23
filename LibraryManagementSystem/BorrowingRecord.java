import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

class BorrowingRecord {
    private final String id;
    private final String userId;
    private final Book book;
    private final LocalDate borrowDate;
    private final LocalDate dueDate;
    private HistoryStatus status;
    private LocalDate returnDate;

    public BorrowingRecord(String userId, Book book) {
        this.id = UUID.randomUUID().toString();
        this.userId = userId;
        this.book = book;
        this.borrowDate = LocalDate.now();
        this.dueDate = borrowDate.plusDays(7);
        this.status = HistoryStatus.BORROWED;
        this.returnDate = null;
    }

    public String getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public Book getBook() {
        return book;
    }

    public LocalDate getBorrowDate() {
        return borrowDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public HistoryStatus getStatus() {
        return status;
    }

    public void setStatus(HistoryStatus status) {
        this.status = status;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BorrowingRecord that = (BorrowingRecord) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "BorrowingRecord{" +
                "id='" + id + '\'' +
                ", userId='" + userId + '\'' +
                ", book=" + book +
                ", borrowDate=" + borrowDate +
                ", dueDate=" + dueDate +
                ", status=" + status +
                ", returnDate=" + returnDate +
                '}';
    }
}