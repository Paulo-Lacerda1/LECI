package aula05;

import java.util.Scanner;

public class ex2 {
    
    private static final Scanner scanner = new Scanner(System.in);
    private static Calendario calendar = null;

    public static void main(String[] args) {
        while (true) {
            System.out.println("\nCalendar operations:\n1 - Create new calendar\n2 - Print calendar month\n3 - Print calendar\n4 - Add event\n5 - Remove event\n0 - Exit");
            int option = readIntClosed(0, 5, "Option: ");
            switch (option) {
                case 0 -> {
                    System.out.println("Programa encerrado...");
                    System.exit(0);
                }
                case 1 -> {
                    Resultado resultado = diaMes();
                    int pos = resultado.dados[1][0];
                    int ano = resultado.dados[0][0];
                    calendar = new Calendario(ano, pos);
                }
                case 2, 3, 4, 5 -> {
                    if (calendar == null) {
                        System.out.println("\nCrie um novo calendário primeiro.");
                    } else {
                        handleOption(option);
                    }
                }
            }
        }
    }

    private static void handleOption(int option) {
        switch (option) {
            case 2 -> {
                int mes = readIntClosed(1, 12, "Mês para mostrar: ");
                calendar.imprimirMes(mes);
            }
            case 3 -> calendar.imprimirCalendario();
            case 4 -> {
                int dia = readIntClosed(1, 31, "Dia: ");
                int mes = readIntClosed(1, 12, "Mês: ");
                calendar.adicionarEvento(dia, mes);
            }
            case 5 -> {
                int dia = readIntClosed(1, 31, "Dia: ");
                int mes = readIntClosed(1, 12, "Mês: ");
                calendar.removerEvento(dia, mes);
            }
        }
    }

    public static class Resultado {
        public int[][] dados;
        public String str_dia_mes;

        public Resultado(int[][] dados, String str_dia_mes) {
            this.dados = dados;
            this.str_dia_mes = str_dia_mes;
        }
    }

    public static Resultado diaMes() {
        int[][] dados = new int[2][1];
        String str_dia_mes = "";

        do {
            System.out.println("Qual é o ano?:");
            dados[0][0] = readInt("Ano: ");
            if (dados[0][0] <= 0) {
                System.out.println("Insira um ano válido");
            }
        } while (dados[0][0] <= 0);

        do {
            System.out.println("Qual é o dia da semana (entre 1-domingo e 7-sábado) em que começa o ano?");
            dados[1][0] = readIntClosed(1, 7, "Dia da semana (1-7): ");
            if (dados[1][0] < 1 || dados[1][0] > 7) {
                System.out.println("Insira um número válido (1 a 7)");
            } else {
                switch (dados[1][0]) {
                    case 1 -> str_dia_mes = "domingo";
                    case 2 -> str_dia_mes = "segunda-feira";
                    case 3 -> str_dia_mes = "terça-feira";
                    case 4 -> str_dia_mes = "quarta-feira";
                    case 5 -> str_dia_mes = "quinta-feira";
                    case 6 -> str_dia_mes = "sexta-feira";
                    case 7 -> str_dia_mes = "sábado";
                }
            }
        } while (dados[1][0] < 1 || dados[1][0] > 7);

        return new Resultado(dados, str_dia_mes);
    }

    private static int readInt(String prompt) {
        System.out.print(prompt);
        return scanner.nextInt();
    }

    private static int readIntClosed(int min, int max, String prompt) {
        int value;
        do {
            value = readInt(prompt);
            if (value < min || value > max) {
                System.out.println("Insira um número de 0 a 5.");
            }
        } while (value < min || value > max);
        return value;
    }
}