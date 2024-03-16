package aula03;
import java.util.Scanner;

public class exercicio35 {

    public static Scanner scanner = new Scanner(System.in);

    private static String data() {
        String data;
        do {
            System.out.print("Digite a data no formato mm/yyyy: ");
            data = scanner.nextLine();
            System.out.println("Insira uma data válida no formato (mm/yyyy)");
        } while (!validarData(data));
        return data;
    }

    private static boolean validarData(String data) {
        String[] partes = data.split("/");
        if (partes.length != 2) {
            return false;
        }
        int mes, ano;
        try {
            mes = Integer.parseInt(partes[0]);
            ano = Integer.parseInt(partes[1]);
        } catch (NumberFormatException e) {
            return false;
        }
        return mes >= 1 && mes <= 12 && ano >= 1;
    }

    private static int dia_inico() {
        int diaInicio;
        do {
            System.out.print("Digite o dia da semana em que o mês começa (1 a 7): ");
            diaInicio = scanner.nextInt();
        } while (diaInicio < 1 || diaInicio > 7);
        return diaInicio;
    }

    private static void calendario(String data, int diaInicio) {
        int mes = Integer.parseInt(data.split("/")[0]);
        int ano = Integer.parseInt(data.split("/")[1]);

        System.out.println("\n" + nome_dos_meses(mes) + " " + ano);
        System.out.println("Su Mo Tu We Th Fr Sa");

        int diasNoMes = calcularDiasNoMes(mes, ano);
        int diaAtual = 1;

        for (int i = 1; i < diaInicio; i++) {
            System.out.print("   ");
        }

        for (int i = diaInicio; i <= 7; i++) {
            System.out.printf("%2d ", diaAtual);
            diaAtual++;
        }

        System.out.println();

        for (int i = 1; i <= diasNoMes; i++) {
            if (i % 7 == 1) {
                System.out.printf("%2d ", i);
            } else {
                System.out.printf("%2d", i);
               }
            if (i % 7 == 0) {
                System.out.println();
            } else {
                System.out.print(" ");
            }
        }
        System.out.println();
    }
    
    private static int calcularDiasNoMes(int mes, int ano) {
        int[] diasPorMes = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

        if (mes == 2 && anoBissexto(ano)) {
            return 29;
        } else {
            return diasPorMes[mes];
        }
    }

    private static boolean anoBissexto(int ano) {
        return (ano % 4 == 0 && ano % 100 != 0) || (ano % 400 == 0);
    }

    private static String nome_dos_meses(int mes) {
        String[] nomesMeses = {"", "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
        return nomesMeses[mes];
    }

    public static void main(String[] args){

        String data = data();
        int diaInicio = dia_inico();
        calendario(data, diaInicio);
    }
}