package aula02;

import java.util.Scanner;

public class Exercicio23 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        float agua;
        do {
            System.out.println("Qual é a quantidade de água (quilogramas)?");
            agua = scanner.nextFloat();

            if (agua < 0) {
                System.out.println("Por favor, insira uma quantidade não negativa de água.");
            }

        } while (agua < 0);


        System.out.println("Qual é a temperatura inicial?");
        float temp_inicial = scanner.nextFloat();
        
        System.out.println("Qual é a temperatura final?");
        float temp_final = scanner.nextFloat();

        scanner.close();

        float energia = (agua*(temp_final-temp_inicial)*4184);
        
        System.out.println("A energia precisa para aquecer "+agua+" quilogramas de água é de "+ energia);
    }
}
