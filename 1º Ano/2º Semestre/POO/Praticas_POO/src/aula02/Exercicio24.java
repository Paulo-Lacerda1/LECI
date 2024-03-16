package aula02;

import java.util.Scanner;

public class Exercicio24 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        float juro;
        do {
            System.out.println("Qual é o taxa de juro (%) ?");
            juro = scanner.nextFloat();

            if(juro<0) {
                System.out.println("Insira uma taxa de juro válida");
            }
        } while(juro<0);

        float investimento;

        while(true){
            System.out.println("Qual é o valor que deseja investir?");
            investimento = scanner.nextFloat();
            if (investimento >= 0){
                break;
            } else {
                System.out.println("Insira um valor para o investimento válido");
            }
        }

        juro = juro/100;

        for (int i = 0; i < 3; i++) {
            investimento = investimento + (investimento * juro);
        }

        System.out.println("O valor final é de "+ investimento);
        
        scanner.close();
    }
}
