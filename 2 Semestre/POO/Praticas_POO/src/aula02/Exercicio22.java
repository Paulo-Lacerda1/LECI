package aula02;

import java.util.Scanner;

public class Exercicio22 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Insira a temperatura em Graus Celsius:");
        float temp_celsius = scanner.nextFloat();
        float temp_fahrenheit = (1.8f*temp_celsius+32);
        System.out.println("A temperatura em Fahrenheit é:"+temp_fahrenheit);
        scanner.close();
    }
}
