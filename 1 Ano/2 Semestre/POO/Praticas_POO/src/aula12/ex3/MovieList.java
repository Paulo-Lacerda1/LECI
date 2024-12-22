package aula12.ex3;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class MovieList {
    static class Movie {
        String name;
        double score;
        String rating;
        String genre;
        int runningTime;

        public Movie(String name, double score, String rating, String genre, int runningTime) {
            this.name = name;
            this.score = score;
            this.rating = rating;
            this.genre = genre;
            this.runningTime = runningTime;
        }

        @Override
        public String toString() {
            return String.format("%s\t%.1f\t%s\t%s\t%d", name, score, rating, genre, runningTime);
        }

        public String getGenre() {
            return genre;
        }

        public double getScore() {
            return score;
        }

        public int getRunningTime() {
            return runningTime;
        }
    }

    public static void main(String[] args) {
        List<Movie> movies = new ArrayList<>();
        try {
            File file = new File("C:\\Users\\paulo\\OneDrive - Universidade de Aveiro\\UA\\2 Semestre\\POO\\Praticas_POO\\src\\aula12\\ex3\\movies.txt");
            Scanner scanner = new Scanner(file);

            scanner.nextLine(); // Ignora a primeira linha

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] fields = line.split("\t");
                String name = fields[0];
                double score = Double.parseDouble(fields[1]);
                String rating = fields[2];
                String genre = fields[3];
                int runningTime = Integer.parseInt(fields[4]);
                movies.add(new Movie(name, score, rating, genre, runningTime));
            }

            scanner.close(); // Fechar o scanner após a leitura

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // Ordenar por nome
        Collections.sort(movies, Comparator.comparing(movie -> movie.name.toLowerCase()));

        System.out.println("Filmes ordenados por ordem alfabética do nome:");
        for (Movie movie : movies) {
            System.out.println(movie);
        }

        System.out.println();

        // Ordenar por score (decrescente)
        Collections.sort(movies, Comparator.comparingDouble(Movie::getScore).reversed());

        System.out.println("Filmes ordenados por ordem decrescente de score:");
        for (Movie movie : movies) {
            System.out.println(movie);
        }

        System.out.println();

        // Ordenar por running time (crescente)
        Collections.sort(movies, Comparator.comparingInt(Movie::getRunningTime));

        System.out.println("Filmes ordenados por ordem crescente de 'running time':");
        for (Movie movie : movies) {
            System.out.println(movie);
        }

        // Contar o número de filmes por género
        Map<String, Integer> genreCount = new HashMap<>();
        for (Movie movie : movies) {
            genreCount.put(movie.getGenre(), genreCount.getOrDefault(movie.getGenre(), 0) + 1);
        }

        System.out.println();
        System.out.println("Géneros distintos existentes e o número de filmes por género:");
        for (Map.Entry<String, Integer> entry : genreCount.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue() + " filme(s)");
        }
        
        //input do género
        
        Scanner scanner = new Scanner(System.in);
        System.out.print("\nDigite o género desejado: ");
        String genero_escolhido = scanner.nextLine();

        //score superior a 60
        try {
            FileWriter writer = new FileWriter("C:\\Users\\paulo\\OneDrive - Universidade de Aveiro\\UA\\2 Semestre\\POO\\Praticas_POO\\src\\aula12\\ex3\\myselection.txt");
            for (Movie movie : movies) {
                if (movie.getGenre().equalsIgnoreCase(genero_escolhido) && movie.getScore() > 60) {
                    writer.write(movie.toString() + "\n");
                }
            }
            
            writer.close();
            
            System.out.println("\nOs filmes com 60+ de score e têm o género: " + genero_escolhido + " foram escritos no arquivo 'myselection.txt'.");
        
        } catch (IOException e) {
            e.printStackTrace();
        }

        scanner.close();
    }
}
