import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LibraryManager {
    private static final String FILE_NAME = "books.txt";

    public static List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                books.add(Book.fromString(line));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return books;
    }

    public static void saveBooks(List<Book> books) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Book b : books) {
                bw.write(b.toString());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void addBook(Book book) {
        List<Book> books = getAllBooks();
        books.add(book);
        saveBooks(books);
    }

    public static void deleteBook(String id) {
        List<Book> books = getAllBooks();
        books.removeIf(b -> b.getId().equalsIgnoreCase(id));
        saveBooks(books);
    }

    public static Book searchBook(String id) {
        for (Book b : getAllBooks()) {
            if (b.getId().equalsIgnoreCase(id)) return b;
        }
        return null;
    }

    public static void updateBook(Book updated) {
        List<Book> books = getAllBooks();
        for (Book b : books) {
            if (b.getId().equalsIgnoreCase(updated.getId())) {
                b.setIssued(updated.isIssued());
                break;
            }
        }
        saveBooks(books);
    }
}
