package aula02;

import java.util.Scanner;

public class Exercicio25 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        double v1, d1;
        do {
            System.out.print("Qual é a velocidade? (v1): ");
            v1 = scanner.nextDouble();
        } while (v1 <= 0);

        do {
            System.out.print("Qual a distancia (d1): ");
            d1 = scanner.nextDouble();
        } while (d1 <= 0);

        double v2, d2;
        do {
            System.out.print("Qual a velocidade (v2): ");
            v2 = scanner.nextDouble();
        } while (v2 <= 0);

        do {
            System.out.print("Qual a distancia (d2): ");
            d2 = scanner.nextDouble();
        } while (d2 <= 0);

    
        scanner.close();

        double tempo_total = (d1/v1)+(d2/v2);
        double distancia_total = d1 + d2;
        double velocidade_media = (distancia_total/ tempo_total );

        System.out.println("A velocidade média final é "+ velocidade_media);
    }
}

