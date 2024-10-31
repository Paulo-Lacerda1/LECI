package aula12.ex3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Movie {
    public static void main(String[] args) {
        String loc = "C:\\Users\\paulo\\OneDrive - Universidade de Aveiro\\UA\\2 Semestre\\POO\\Praticas_POO\\src\\aula12\\ex3\\movies.txt"; 
        String gap = "  ";   

        try (Scanner scanner = new Scanner(new File(loc))) {
            while (scanner.hasNextLine()) {
                scanner.nextLine();
                String linha = scanner.nextLine();
                String[] valores = linha.split(gap);

                for (String valor : valores) {
                    System.out.print(valor + " ");
                }
                System.out.println();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
