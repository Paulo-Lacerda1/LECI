package aula02;

import java.util.Scanner;

public class Exercicio26 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Qual é o tempo em segundos(s)?");
        int segundos = scanner.nextInt();
        int horas  = segundos / 3600;
        int minutos = (segundos % 3600)/60;
        segundos = segundos % 60;
        System.out.println(horas+" h : "+minutos+" m : "+segundos+" s" );
        scanner.close();

    
}
}
