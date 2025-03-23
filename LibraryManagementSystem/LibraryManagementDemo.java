import java.time.LocalDate;

class LibraryManagementDemo {
    public static void main(String[] args) {
        BookService bookService = new LibraryBookService();
        UserService userService = new LibraryUserService(bookService);

        User librarian = new User("1", "Tamil Selvi", "L1", "9876543210", UserRole.LIBRARIAN);
        User member = new User("2", "Bhavani", "M1", "8765432109", UserRole.MEMBER);

        Book book1 = new Book("B1", "The Mountain Is You", "Brianna Wiest", "ISBN1", LocalDate.now(), AvailabilityStatus.AVAILABLE);
        Book book2 = new Book("B2", "Think Like a Monk", "Jay Shetty", "ISBN2", LocalDate.now(), AvailabilityStatus.AVAILABLE);
        Book book3 = new Book("B3", "The Power of Your Subconscious Mind", "Joseph Murphy", "ISBN3", LocalDate.now(), AvailabilityStatus.AVAILABLE);

        try {
            System.out.println("Adding books to library...");
            bookService.addBook(librarian, book1);
            bookService.addBook(librarian, book2);
            bookService.addBook(librarian, book3);

            System.out.println("\nList of all books:");
            bookService.getAllBooks().forEach(System.out::println);

            System.out.println("\nUpdating book1...");
            book1.setStatus(AvailabilityStatus.UNAVAILABLE);
            bookService.updateBook(librarian, book1);

            System.out.println("\nMember borrowing book2...");
            userService.borrowBook(member, book2);

            System.out.println("\nBorrowing history for member:");
            userService.getBorrowingHistory(member).forEach(record ->
                    System.out.println(record.getBook().getTitle() + " by " + record.getBook().getAuthor() +
                            " - Due: " + record.getDueDate()));

            System.out.println("\nMember returning book2...");
            userService.returnBook(member, book2);

            System.out.println("\nDeleting book3...");
            bookService.deleteBook(librarian, book3.getId());

            System.out.println("\nFinal list of books:");
            bookService.getAllBooks().forEach(System.out::println);

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}