import java.util.List;

interface BookService {
    List<Book> getAllBooks();
    Book getBookById(String id);
    void addBook(User user, Book book) throws UnauthorizedAccessException;
    void updateBook(User user, Book book) throws UnauthorizedAccessException, ResourceNotFoundException;
    void deleteBook(User user, String bookId) throws UnauthorizedAccessException, ResourceNotFoundException;
}