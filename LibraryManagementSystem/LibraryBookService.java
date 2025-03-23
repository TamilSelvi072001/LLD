import java.util.ArrayList;
import java.util.List;

class LibraryBookService implements BookService {
    private final List<Book> bookCatalog;

    public LibraryBookService() {
        this.bookCatalog = new ArrayList<>();
    }

    @Override
    public List<Book> getAllBooks() {
        return new ArrayList<>(bookCatalog);
    }

    @Override
    public Book getBookById(String id) {
        return bookCatalog.stream()
                .filter(book -> book.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public void addBook(User user, Book book) throws UnauthorizedAccessException {
        if (user.getRole() != UserRole.LIBRARIAN) {
            throw new UnauthorizedAccessException("Only librarians can add books");
        }
        bookCatalog.add(book);
    }

    @Override
    public void updateBook(User user, Book updatedBook) throws UnauthorizedAccessException, ResourceNotFoundException {
        if (user.getRole() != UserRole.LIBRARIAN) {
            throw new UnauthorizedAccessException("Only librarians can update books");
        }

        Book existingBook = getBookById(updatedBook.getId());
        if (existingBook == null) {
            throw new ResourceNotFoundException("Book with ID " + updatedBook.getId() + " not found");
        }

        deleteBook(user, updatedBook.getId());
        bookCatalog.add(updatedBook);
    }

    @Override
    public void deleteBook(User user, String bookId) throws UnauthorizedAccessException, ResourceNotFoundException {
        if (user.getRole() != UserRole.LIBRARIAN) {
            throw new UnauthorizedAccessException("Only librarians can delete books");
        }

        boolean removed = bookCatalog.removeIf(book -> book.getId().equals(bookId));
        if (!removed) {
            throw new ResourceNotFoundException("Book with ID " + bookId + " not found");
        }
    }
}

