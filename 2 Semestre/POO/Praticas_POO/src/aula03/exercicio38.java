package aula03;
import java.util.Scanner;

public class exercicio38 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Escreva uma frase:");
        String frase = scanner.nextLine();

        String acronimo = gerarador_acronimo(frase);

        System.out.println("O acrônimo da frase é: " + acronimo);
        
        scanner.close();
    }

    private static String gerarador_acronimo(String frase) {
        StringBuilder acronimo = new StringBuilder();

        String[] palavras = frase.split("\\s+");

        for (String palavra : palavras) {
            if (palavra.length() >= 3) {
                acronimo.append(palavra.charAt(0));
            }
        }

        return acronimo.toString().toUpperCase();
    }
}
