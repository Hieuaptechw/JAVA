import Entity.Author;
import Entity.Book;
import Entity.Gender;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {


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
                .toList();

        System.out.println("Books sap xep theo ten:");
        System.out.println("|------------------------------------------------------------------------------------");
        System.out.printf("| %-30s | %-20s | %-14s | %-8s |\n", "Book Name", "Author", "Price", "Quantity");


        sortedBooks.forEach(System.out::println);

        System.out.println("Books co tac gia nu:");
        System.out.println("|------------------------------------------------------------------------------------");
        System.out.printf("| %-30s | %-20s | %-14s | %-8s |\n", "Book Name", "Author", "Price", "Quantity");

        books.stream()
                .filter(b -> b.getAuthor().getGender().equals(Gender.Female))
                .forEach(System.out::println);

        Optional<Book> maxPriceBook = books.stream()
                .max(Comparator.comparing(Book::getPrice));

        System.out.println("\nBook co gia cao nhat:");
        System.out.println("|------------------------------------------------------------------------------------");
        System.out.printf("| %-30s | %-20s | %-14s | %-8s |\n", "Book Name", "Author", "Price", "Quantity");
        maxPriceBook.ifPresent(System.out::println);
        Scanner input = new Scanner(System.in);
        System.out.print("Nhập tên bạn muốn tìm: ");
        String namebook = input.nextLine();
        System.out.println("\nBook ban muon tim la:");
        System.out.println("|------------------------------------------------------------------------------------");
        System.out.printf("| %-30s | %-20s | %-14s | %-8s |\n", "Book Name", "Author", "Price", "Quantity");
        books.stream()
                .filter(b -> b.getName().toLowerCase().contains(namebook.toLowerCase()))
                .forEach(System.out::println);
    }
}
