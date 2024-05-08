package aula02;

import java.util.Scanner;

public class Exercicio210 {
    public static void main(String[] args) {
        
        Scanner scanner = new Scanner(System.in);

        double primeiroNumero;
        double numero;
        double maximo;
        double minimo;
        int contador = 0;
        double soma = 0;

        System.out.print("Digite um número: ");
        primeiroNumero = scanner.nextDouble();
        minimo = maximo = soma = primeiroNumero;
        contador++;

        while (true) {
            System.out.print("Próximo número (ou insira "+primeiroNumero+"): ");
            numero = scanner.nextDouble();

            if (numero == primeiroNumero) {
                System.out.println("Acabou. Aqui estão os resultados...");
                break;
            }

            soma += numero;
            contador++;
            minimo = Math.min(minimo, numero);
            maximo = Math.max(maximo, numero);
        }

        if (contador == 1) {
            System.out.println("Apenas um número foi inserido.");
        } else {
            double media = soma / contador;

            System.out.println("\nResultados:");
            System.out.println("Valor Máximo: " + maximo);
            System.out.println("Valor Mínimo: " + minimo);
            System.out.println("Média: " + media);
            System.out.println("Total de Números Lidos: " + contador);
        }

        scanner.close();
    }
}