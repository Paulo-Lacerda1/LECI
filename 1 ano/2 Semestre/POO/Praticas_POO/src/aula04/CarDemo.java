package aula04;

import java.util.Scanner;

class Carro {
    public String marca;
    public String modelo;
    public int ano;
    public int quilometros;

    public Carro(String marca, String modelo, int ano, int quilometros) {
        this.marca = marca;
        this.modelo = modelo;
        this.ano = ano;
        this.quilometros = quilometros;
    }

    public void conduzir(int distancia) {
        this.quilometros += distancia;
    }
}

public class CarDemo {

    static Scanner scanner = new Scanner(System.in);

    static int registarCarros(Carro[] carros) {
        int contador = 0;
        while (true) {
            System.out.print("Introduza os dados do carro (marca modelo ano quilómetros): ");
            String linha = scanner.nextLine();
            if (linha.isEmpty())
                break;
            String[] lst = linha.split(" ");
            if (lst.length < 4) {
                System.out.println("Dados mal formatados. Por favor, tente novamente.");
                continue;
            }
            if (Integer.parseInt(lst[lst.length - 2]) < 0 && lst[lst.length - 2].length() >= 4) {
                System.out.println("Dados mal formatados. Por favor, tente novamente.");
                continue;
            }
            if (Integer.parseInt(lst[lst.length - 1]) < 0) {
                System.out.println("Dados mal formatados. Por favor, tente novamente.");
                continue;
            }
            carros[contador] = new Carro(lst[0], lst[1], Integer.parseInt(lst[lst.length - 2]), Integer.parseInt(lst[lst.length - 1]));
            contador++;
        }
        return contador;
    }

    static void registarViagens(Carro[] carros, int numCarros) {
        while (true) {
            System.out.print("Registe uma viagem no formato \"carro:distância\": ");
            String linha = scanner.nextLine();
            if (linha.isEmpty())
                break;
            if (!linha.matches("\\d+:\\d+")) {
                System.out.println("Dados mal formatados. Por favor, tente novamente.");
                continue;
            }
            String[] lst = linha.split(":");
            int carro = Integer.parseInt(lst[0]);
            int distancia = Integer.parseInt(lst[1]);
            if (carro < 0 || distancia < 0 || carro > numCarros - 1) {
                System.out.println("Dados mal formatados. Por favor, tente novamente.");
            }
        }
    }

    static void listarCarros(Carro[] carros) {
        System.out.println("\nCarros registados: ");
        for (int i = 0; i < carros.length; i++) {
            if (carros[i] == null)
                break;
            System.out.println(carros[i].marca + " " + carros[i].modelo + ", " + carros[i].ano + ", quilómetros: " + carros[i].quilometros);
        }
    }

    public static void main(String[] args) {
        Carro[] carros = new Carro[10];

        int numCarros = registarCarros(carros);

        if (numCarros > 0) {
            listarCarros(carros);
            registarViagens(carros, numCarros);
            listarCarros(carros);
        }
        scanner.close();
    }
}
