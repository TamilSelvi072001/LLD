import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

class Book {
    private final String id;
    private String title;
    private String author;
    private String isbn;
    private LocalDate publicationDate;
    private AvailabilityStatus status;

    public Book(String title, String author, String isbn, LocalDate publicationDate) {
        this.id = UUID.randomUUID().toString();
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.publicationDate = publicationDate;
        this.status = AvailabilityStatus.AVAILABLE;
    }

    public Book(String id, String title, String author, String isbn, LocalDate publicationDate, AvailabilityStatus status) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.publicationDate = publicationDate;
        this.status = status;
    }

    public String getId() {
        return id;
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

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public LocalDate getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(LocalDate publicationDate) {
        this.publicationDate = publicationDate;
    }

    public AvailabilityStatus getStatus() {
        return status;
    }

    public void setStatus(AvailabilityStatus status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(id, book.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Book{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", isbn='" + isbn + '\'' +
                ", publicationDate=" + publicationDate +
                ", status=" + status +
                '}';
    }
}