package aula05;
import java.util.Scanner;

public class ex1 {
    public static void main(String[] args) {

        DateYMD data = new DateYMD(0, 0, 0);

        Scanner scanner = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("\nDate operations:\r\n" + "1 - create new date\r\n" + "2 - show current date\r\n" + "3 - increment date\r\n" + "4 - decrement date\r\n" + "0 - exit");
            System.out.print("Opção?");
            opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    System.out.println("\nInsira o dia:");
                    int dia = scanner.nextInt();
                    System.out.println("Insira o mês:");
                    int mes = scanner.nextInt();
                    System.out.println("Insira o ano:");
                    int ano = scanner.nextInt();
                    data.setData(dia, mes, ano);
                    break;
                case 2:
                    data.consultarData();
                    break;
                case 3:
                    data.increment();
                    break;
                case 4:
                    data.decrement();
                    break;
                case 0:
                    System.out.println("Programa encerrado...");
                    break;
                default:
                    System.out.println("\nInsira uma opção válida");
            }
        } while (opcao != 0);

        scanner.close();
    }
}