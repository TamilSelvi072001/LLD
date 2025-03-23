import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

class LibraryUserService implements UserService {
    private final List<BorrowingRecord> borrowingRecords;
    private final BookService bookService;
    private static final int MAX_BOOKS_PER_USER = 5;

    public LibraryUserService(BookService bookService) {
        this.borrowingRecords = new ArrayList<>();
        this.bookService = bookService;
    }

    @Override
    public void borrowBook(User user, Book book) throws BookUnavailableException, BorrowingLimitExceededException {
        if (book.getStatus() == AvailabilityStatus.UNAVAILABLE) {
            throw new BookUnavailableException("Book is currently unavailable");
        }

        List<BorrowingRecord> activeLoans = getUserActiveLoans(user.getId());
        if (activeLoans.size() >= MAX_BOOKS_PER_USER) {
            throw new BorrowingLimitExceededException("User has reached the maximum borrowing limit of " + MAX_BOOKS_PER_USER + " books");
        }

        boolean alreadyBorrowed = activeLoans.stream()
                .anyMatch(record -> record.getBook().getId().equals(book.getId()));
        if (alreadyBorrowed) {
            throw new BookUnavailableException("User has already borrowed this book");
        }

        BorrowingRecord record = new BorrowingRecord(user.getId(), book);
        borrowingRecords.add(record);

        book.setStatus(AvailabilityStatus.UNAVAILABLE);
    }

    @Override
    public void returnBook(User user, Book book) throws ResourceNotFoundException {
        // Find the active borrowing record for this book and user
        BorrowingRecord record = borrowingRecords.stream()
                .filter(r -> r.getUserId().equals(user.getId()) &&
                        r.getBook().getId().equals(book.getId()) &&
                        r.getStatus() == HistoryStatus.BORROWED)
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("No active borrowing record found for this book and user"));

        // Update the record
        record.setStatus(HistoryStatus.RETURNED);
        record.setReturnDate(LocalDate.now());

        // Update book status
        book.setStatus(AvailabilityStatus.AVAILABLE);
    }

    @Override
    public List<BorrowingRecord> getBorrowingHistory(User user) {
        return borrowingRecords.stream()
                .filter(record -> record.getUserId().equals(user.getId()))
                .toList();
    }

    private List<BorrowingRecord> getUserActiveLoans(String userId) {
        return borrowingRecords.stream()
                .filter(record -> record.getUserId().equals(userId) &&
                        record.getStatus() == HistoryStatus.BORROWED)
                .toList();
    }
}
