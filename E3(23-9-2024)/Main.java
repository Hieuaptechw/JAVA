import Entity.Author;
import Entity.Book;
import Entity.Gender;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);


        System.out.print("Nhập tên bạn muốn tìm: ");
        String namebook = input.nextLine();


        List<Author> authors = new ArrayList<>();
        List<Book> books = new ArrayList<>();


        authors.add(new Author("Bean", "bean@hotmail.com", Gender.Male));
        authors.add(new Author("Hieu", "Hieu@hotmail.com", Gender.Male));
        authors.add(new Author("Mark", "mark@hotmail.com", Gender.Female));


        books.add(new Book("Làm Giàu Không Khó", authors.get(0), 20000, 1000));
        books.add(new Book("Java", authors.get(1), 1000, 2000));
        books.add(new Book("Python", authors.get(2), 1000, 2000));


        List<Book> sortedBooks = books.stream()
                .sorted(Comparator.comparing(Book::getName).reversed())
                .collect(Collectors.toList());

        System.out.println("Books sap xep theo ten:");
        System.out.println("| Book Name                      | Author               | Price          | Quantity |");
        System.out.println("|--------------------------------|----------------------|----------------|----------|");


        sortedBooks.forEach(System.out::println);


        System.out.println("\nBooks co tac gia nu:");
        System.out.println("| Book Name                      | Author               | Price          | Quantity |");
        System.out.println("|--------------------------------|----------------------|----------------|----------|");

        books.stream()
                .filter(b -> b.getAuthor().getGender().equals(Gender.Female))
                .forEach(System.out::println);


        Optional<Book> maxPriceBook = books.stream()
                .max(Comparator.comparing(Book::getPrice));

        System.out.println("\nBook co gia cao nhat:");
        System.out.println("| Book Name                      | Author               | Price          | Quantity |");
        System.out.println("|--------------------------------|----------------------|----------------|----------|");

        maxPriceBook.ifPresent(System.out::println);


        System.out.println("\nBook ban muon tim la:");
        System.out.println("| Book Name                      | Author               | Price          | Quantity |");
        System.out.println("|--------------------------------|----------------------|----------------|----------|");

        books.stream()
                .filter(b -> searchNameBook(b.getName(),namebook))
                .forEach(System.out::println);

    }
    public static boolean searchNameBook(String bookname, String namebook) {
        if (namebook == null ) {
            return true;
        }

        String[] bookWords = bookname.toLowerCase().split("\\s+");
        String[] searchWords = namebook.toLowerCase().split("\\s+");

        for (int i = 0; i < searchWords.length; i++) {
            boolean check = false;
            for (int j = 0; j < bookWords.length; j++) {
                if (bookWords[j].equalsIgnoreCase(searchWords[i])) {
                    check = true;
                    break;
                }
            }

            if (!check) {
                return false;
            }
        }

        return true;
    }


}