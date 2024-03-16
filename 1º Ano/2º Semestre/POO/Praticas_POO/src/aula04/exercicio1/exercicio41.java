package aula04.exercicio1;
import java.util.Scanner;

public class exercicio41 {
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        String opcao;

        do {
            System.out.println();
            System.out.println("Menu:\n1) Circulo\n2) Triangulo\n3) Retangulo\n4) Sair");
            System.out.print("Opção? ");
            opcao = scanner.nextLine();

            switch (opcao) {
                case "1":
                    System.out.println();
                    System.out.println("Digite o raio do círculo:");
                    double raio = scanner.nextDouble();
                    scanner.nextLine();

                    circulo circulo = new circulo(raio);
                    double areaCirculo = circulo.area();
                    double perimetroCirculo = circulo.perimetro();
                    System.out.println("Area do círculo: " + areaCirculo);
                    System.out.println("Perimetro do círculo: "+perimetroCirculo);
                    
                    break;

                case "2":
                    System.out.println();
                    System.out.println("Digite os lados do triângulo:");
                    System.out.print("Lado 1: ");
                    double lado1 = scanner.nextDouble();
                    System.out.print("Lado 2: ");
                    double lado2 = scanner.nextDouble();
                    System.out.print("Lado 3: ");
                    double lado3 = scanner.nextDouble();
                    scanner.nextLine();

                    triangulo triangulo = new triangulo(lado1, lado2, lado3);
                    double areaTriangulo = triangulo.area();
                    double perimetroTriangulo = triangulo.perimetro();
                    System.out.println("Area do triângulo: " + areaTriangulo);
                    System.out.println("Perimetro do triângulo: " + perimetroTriangulo);
                    
                    break;

                case "3":
                    System.out.println();
                    System.out.println("Qual a altura e a largura do retângulo?");
                    
                    System.out.print("Altura?");
                    double altura = scanner.nextDouble();
                    scanner.nextLine();
                    
                    System.out.print("Largura?");
                    double largura = scanner.nextDouble();
                    scanner.nextLine();

                    retangulo retangulo = new retangulo(largura, altura);
                    double areaRetangulo = retangulo.area();
                    double perimetroRetangulo = retangulo.perimetro();
                    System.out.println("Area do retângulo: "+ areaRetangulo);
                    System.out.println("Perimetro do retângulo: "+ perimetroRetangulo);

                    break;

                case "4":
                    System.out.println("Programa encerrado.");
                    System.exit(0);

                default:
                    System.out.println("Opção inválida. Insira novamente.");
            }

        } while (true);
    }
}
