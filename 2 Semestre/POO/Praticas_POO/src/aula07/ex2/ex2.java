package aula07.ex2;
import java.util.Scanner;

public class ex2 {
    public static void main(String[] args) {
        int input, day, month, year;
        DateYMD date = null;
        Scanner inputs = new Scanner(System.in);

        do {
            System.out.println("Operações de Data:");
            System.out.println("1 - criar nova data");
            System.out.println("2 - mostrar data atual");
            System.out.println("3 - incrementar data");
            System.out.println("4 - decrementar data");
            System.out.println("0 - sair");

            do {
                System.out.println("Escolha uma operação:");
                input = inputs.nextInt();
                if (input < 0 || input > 4) {
                    System.out.println("Operação inválida");
                }
            } while (input < 0 || input > 4);

            switch (input) {
                case 1:
                    do {
                        System.out.println("Introduza o dia: ");
                        day = inputs.nextInt();
                        if (day < 1 || day > 31) {
                            System.out.println("Dia inválido");
                        }
                    } while (day < 1 || day > 31);

                    do {
                        System.out.println("Introduza o mês: ");
                        month = inputs.nextInt();
                        if (month < 1 || month > 12) {
                            System.out.println("Mês inválido");
                        }
                    } while (month < 1 || month > 12);

                    System.out.println("Introduza o ano: ");
                    year = inputs.nextInt();

                    date = new DateYMD(day, month, year);
                    System.out.println("Data criada: " + date);
                    break;

                case 2:
                    if (date == null) {
                        System.out.println("Data não foi criada.");
                        break;
                    }

                    System.out.println("Data atual: " + date.toString());
                    break;

                case 3:
                    if (date == null) {
                        System.out.println("Data não foi criada.");
                        break;
                    }

                    System.out.println("Introduza o número de dias a incrementar: ");
                    int diasIn = inputs.nextInt();
                    date.increment(diasIn);
                    System.out.println("Data incrementada: " + date);
                    break;

                case 4:
                    if (date == null) {
                        System.out.println("Data não foi criada.");
                        break;
                    }

                    System.out.println("Introduza o número de dias a decrementar: ");
                    int diasDe = inputs.nextInt();

                    date.decrement(diasDe);
                    System.out.println("Data decrementada: " + date);
                    break;

                case 0:
                    break;
            }

            System.out.println();
        } while (input != 0);

        inputs.close();
    }
}
