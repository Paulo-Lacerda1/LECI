package aula12.ex1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        
        File file = new File("C:\\Users\\paulo\\OneDrive - Universidade de Aveiro\\UA\\2 Semestre\\POO\\Praticas_POO\\src\\aula12\\ex1\\exemplo.txt");
        Scanner scanner = new Scanner(file);
        
        int contadorPalavras = 0;
        Map <String, Integer> contagemPalavras = new HashMap<>();

        while(scanner.hasNextLine()){
            String linha = scanner.nextLine();
            String[] palavras = linha.split("\\s+");
            contadorPalavras += palavras.length;

            for (String palavra : palavras) {
                contagemPalavras.put(palavra, contagemPalavras.getOrDefault(palavra, 0) + 1);
            }
        }

        System.out.println("Número Total de Palavras: " + contadorPalavras);
        System.out.println("Número de Diferentes Palavras: " + contagemPalavras.size());
        
        scanner.close();
    }
}
