package aula02;
import java.util.Scanner;

public class Exercicio29 {
    public static void main(String[] args) {
        
    Scanner scanner = new Scanner(System.in);

    int numero;

    while (true) {
        System.out.println("Insira um número positivo:");
        numero = scanner.nextInt();

        if (numero > 0) {
            break;
        } else {
            System.out.println("Insira um número válido positivo");
        }
    }
    
    scanner.close();

    for (int i = numero; i >= 0; i--) {
        System.out.print(i);
        if (i % 10 == 0) {                       //se for multiplo de 10
            System.out.println(); 
        } 
        else {
            System.out.print(", ");
        }
        }
    }
}
