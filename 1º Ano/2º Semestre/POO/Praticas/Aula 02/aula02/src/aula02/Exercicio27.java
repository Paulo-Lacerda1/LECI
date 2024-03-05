package aula02;

import java.util.Scanner;

public class Exercicio27 {
    public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    System.out.println("Insira as coordenadas x do ponto A");
    float x1 = scanner.nextFloat();
    System.out.println("Insira as coordenadas y do ponto A");
    float y1 = scanner.nextFloat();
    System.out.println("Insira as coordenadas x do ponto B");
    float x2 = scanner.nextFloat();
    System.out.println("Insira as coordenadas y do ponto B");
    float y2 = scanner.nextFloat();
    scanner.close();

    float soma_x = x2 - x1;
    float soma_y = y2 - y1;

    double hipotenusa = Math.sqrt(Math.pow(soma_x, 2)+ Math.pow(soma_y, 2)); 

    System.out.println("O comprimento entre dois pontos é: "+hipotenusa);
    }
}
