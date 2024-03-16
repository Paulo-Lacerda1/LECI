package aula04;
import java.util.Scanner;

class Carro {
    public String marca;
    public String modelo;
    public int ano;
    public int kms;

    public Carro(String marca, String modelo, int ano, int kms) {
        this.marca = marca;
        this.modelo = modelo;
        this.ano = ano;
        this.kms = kms;
    }

    public void conduzir(int distancia) {
        this.kms += distancia;
    }

    public String toString() {
        return this.marca + " " + this.modelo + ", " + this.ano + ", kms: " + this.kms;
    }
}

public class SimpleCarDemo {
    static Scanner sc = new Scanner(System.in);

    static void listarCarros(Carro[] carros) {
        // Exemplo de resultado
        // Carros registados:
        // Renault Megane Sport Tourer, 2015, kms: 35356
        // Toyota Camry, 2010, kms: 32456
        // Mercedes Vito, 2008, kms: 273891
        System.out.println("Carros registados: ");
        for (int i = 0; i < carros.length; i++) {
            System.out.println(carros[i]);
        }
    }

    public static void main(String[] args) {

        Carro[] carros = new Carro[3];
        carros[0] = new Carro("Renault", "Megane Sport Tourer", 2015, 35356);
        carros[1] = new Carro("Toyota", "Camry", 2010, 32456);
        carros[2] = new Carro("Mercedes", "Vito", 2008, 273891);

        listarCarros(carros);

        // Adicionar 10 viagens geradas aleatoriamente
        for (int i = 0; i < 10; i++) {  
            int j = (int) Math.round(Math.random() * 2);
            int kms = (int) Math.round(Math.random() * 1000); 
            System.out.printf("Carro %d viajou %d quilómetros.\n", j, kms);
            carros[j].conduzir(kms);
        }

        listarCarros(carros);

        sc.close();

    }
}
