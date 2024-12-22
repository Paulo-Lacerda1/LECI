package aula10.ex2;

import aula10.ex1.Book;

import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;
import java.util.TreeMap;

public class Main {
    public static void main(String[] args) {

        LinkedList<Book> books = new LinkedList<>();

        books.add(new Book("The Catcher in the Rye", "J.D. Salinger", 1951));
        books.add(new Book("To Kill a Mockingbird", "Harper Lee", 1960));

        System.out.println("Books in the collection:");

        for (Book book : books) {
            System.out.println(book);
        }

        TreeMap<String, LinkedList<Book>> genres = new TreeMap<>();

        genres.put("Drama", new LinkedList<>());
        genres.put("Romance", new LinkedList<>());
        genres.put("War", new LinkedList<>());
        genres.put("Crime", new LinkedList<>());
        genres.put("Self-Help", new LinkedList<>());

        System.out.println(formatTreeMap(genres));
        System.out.println("---------------------------------------------------");

        genres.get("Drama").add(new Book("A Little Life", "Hanya Yanagihara", 2016));
        genres.get("Romance").add(new Book("Normal People", "Sally Rooney", 2018));
        genres.get("War").add(new Book("Novel Without a Name", "Duong Thu Huong", 1995));
        genres.get("Crime").add(new Book("Crime and Punishment", "Fyodor Dostoyevsky", 1866));
        genres.get("Self-Help").add(new Book("12 Rules for Life", "Jordan Peterson", 2018));

        System.out.println(formatTreeMap(genres));
        System.out.println("---------------------------------------------------");

        genres.get("Drama").add(new Book("The Great Gatsby", "F. Scott Fitzgerald", 1925));
        genres.get("Romance").add(new Book("Pride and Prejudice", "Jane Austen", 1813));

        System.out.println(formatTreeMap(genres));
        System.out.println("---------------------------------------------------");

        genres.get("Romance").remove(1);

        System.out.println(formatTreeMap(genres));

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Género? ");
            String chosenGenre = scanner.next();
            if (genres.containsKey(chosenGenre)) {
                Random r = new Random();
                LinkedList<Book> selectedGenre = genres.get(chosenGenre);
                System.out.println(selectedGenre.get(r.nextInt(selectedGenre.size())));
                break;
            }
        }
        
        scanner.close();
    
}

    public static String formatTreeMap(TreeMap<String, LinkedList<Book>> map) {
        StringBuilder result = new StringBuilder();
        for (String key : map.keySet()) {
            result.append("\n").append(key).append(":\n");
            result.append(formatLinkedList(map.get(key)));
            result.append("\n");
        }
        return result.toString();
    }

    public static String formatLinkedList(LinkedList<Book> list) {
        StringBuilder result = new StringBuilder();
        for (Book book : list) {
            result.append(book).append("\n");
        }
        return result.toString();
    }
}
