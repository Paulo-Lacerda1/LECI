package aula02;

import java.util.Scanner;

public class Exercicio21 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Insira os quilometros que deseja converter:");
        double numero_km = scanner.nextDouble();
        scanner.close();
        double numero_milhas = numero_km / 1.609;
        System.out.println("O resultado em milhas: " + numero_milhas);
    }
}
