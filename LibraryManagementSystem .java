import java.util.ArrayList;
import java.util.List;

class Book {
    private int bookId;
    private String title;
    private String author;
    private boolean available;

    public Book(int bookId, String title, String author) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.available = true;
    }

    public int getBookId() {
        return bookId;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isAvailable() {
        return available;
    }

    public void borrow() {
        if (available) {
            available = false;
        } else {
            System.out.println("Sorry, this book is already borrowed.");
        }
    }

    public void returnBook() {
        available = true;
    }

    @Override
    public String toString() {
        return "Book ID: " + bookId + ", Title: " + title + ", Author: " + author + ", Available: " + available;
    }
}

class LibraryMember {
    private int memberId;
    private String name;

    public LibraryMember(int memberId, String name) {
        this.memberId = memberId;
        this.name = name;
    }

    public int getMemberId() {
        return memberId;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Member ID: " + memberId + ", Name: " + name;
    }
}

class Library {
    private List<Book> books;
    private List<LibraryMember> members;

    public Library() {
        books = new ArrayList<>();
        members = new ArrayList<>();
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public void addMember(LibraryMember member) {
        members.add(member);
    }

    public void displayBooks() {
        System.out.println("Library Books:");
        for (Book book : books) {
            System.out.println(book);
        }
    }

    public void displayMembers() {
        System.out.println("Library Members:");
        for (LibraryMember member : members) {
            System.out.println(member);
        }
    }

    public void borrowBook(int memberId, int bookId) {
        LibraryMember member = findMember(memberId);
        Book book = findBook(bookId);

        if (member == null || book == null) {
            System.out.println("Member or book not found.");
            return;
        }

        if (!book.isAvailable()) {
            System.out.println("Sorry, this book is already borrowed.");
        } else {
            book.borrow();
            System.out.println(member.getName() + " has borrowed " + book.getTitle() + ".");
        }
    }

    public void returnBook(int memberId, int bookId) {
        LibraryMember member = findMember(memberId);
        Book book = findBook(bookId);

        if (member == null || book == null) {
            System.out.println("Member or book not found.");
            return;
        }

        book.returnBook();
        System.out.println(member.getName() + " has returned " + book.getTitle() + ".");
    }

    private LibraryMember findMember(int memberId) {
        for (LibraryMember member : members) {
            if (member.getMemberId() == memberId) {
                return member;
            }
        }
        return null;
    }

    private Book findBook(int bookId) {
        for (Book book : books) {
            if (book.getBookId() == bookId) {
                return book;
            }
        }
        return null;
    }
}

public class LibraryManagementSystem {
    public static void main(String[] args) {
        Library library = new Library();

        // Add books to the library
        library.addBook(new Book(1, "The Catcher in the Rye", "J.D. Salinger"));
        library.addBook(new Book(2, "To Kill a Mockingbird", "Harper Lee"));
        library.addBook(new Book(3, "1984", "George Orwell"));

        // Add library members
        library.addMember(new LibraryMember(101, "Alice"));
        library.addMember(new LibraryMember(102, "Bob"));

        // Display available books and library members
        library.displayBooks();
        library.displayMembers();

        // Simulate borrowing and returning books
        library.borrowBook(101, 1); // Alice borrows "The Catcher in the Rye"
        library.borrowBook(101, 1); // Alice tries to borrow the same book again (already borrowed)
        library.returnBook(101, 1); // Alice returns "The Catcher in the Rye"
    }
}
