import java.util.List;

interface UserService {
    void borrowBook(User user, Book book) throws BookUnavailableException, BorrowingLimitExceededException;
    void returnBook(User user, Book book) throws ResourceNotFoundException;
    List<BorrowingRecord> getBorrowingHistory(User user);
}