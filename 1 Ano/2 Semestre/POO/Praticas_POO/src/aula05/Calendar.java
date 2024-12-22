package aula05;

class Calendario {
    private boolean[][] eventos = new boolean[12][31];
    private int ano;
    private int primeiroDiaAno;

    public Calendario(int ano, int primeiroDiaAno) {
        this.ano = ano;
        this.primeiroDiaAno = primeiroDiaAno - 1;
    }

    public int getAno() {
        return ano;
    }

    public int getPrimeiroDiaAno() {
        return primeiroDiaAno + 1; 
    }

    public int getPrimeiroDiaMes(int mes) {
        int dias = 0;
        for (int i = 1; i < mes; i++) {
            dias += getDiasNoMes(i);
        }
        if (leapYear() && mes > 2) {
            dias++;
        }
        return (dias + primeiroDiaAno) % 7;
    }

    public void adicionarEvento(int dia, int mes) {
        if (dataValida(dia, mes)) {
            eventos[mes - 1][dia - 1] = true;
        } else {
            System.out.println("Data inválida");
        }
    }

    public void removerEvento(int dia, int mes) {
        if (dataValida(dia, mes)) {
            eventos[mes - 1][dia - 1] = false;
        } else {
            System.out.println("Data inválida");
        }
    }

    public void imprimirMes(int mes) {
        String nomeMes = getNomeMes(mes);
        int primeiroDia = getPrimeiroDiaMes(mes);
        int diasNoMes = getDiasNoMes(mes);
    
        System.out.printf("%16s %d\n", nomeMes, ano);
        System.out.println("  Do  Se  Te  Qu  Qu  Se  Sá");
    
        for (int i = 0; i < primeiroDia; i++) {
            System.out.print("  ");
        }
    
        for (int dia = 1; dia <= diasNoMes; dia++) {
            if (eventos[mes - 1][dia - 1]) {
                System.out.print("*" + String.format("%2d", dia) + " ");
            } else {
                System.out.print(" " + String.format("%2d", dia) + " ");
            }
            if ((dia + primeiroDia) % 7 == 0) {
                System.out.println();
            }
        }
        System.out.println();
    }

    public void imprimirCalendario() {
        for (int mes = 1; mes <= 12; mes++) {
            imprimirMes(mes);
            System.out.println();
        }
    }

    private boolean dataValida(int dia, int mes) {
        return dia >= 1 && dia <= getDiasNoMes(mes) && mes >= 1 && mes <= 12;
    }

    private int getDiasNoMes(int mes) {
        switch (mes) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                return 31;
            case 4:
            case 6:
            case 9:
            case 11:
                return 30;
            case 2:
                return leapYear() ? 29 : 28;
            default:
                return 0;
        }
    }

    private boolean leapYear() {
        return (ano % 4 == 0 && ano % 100 != 0) || ano % 400 == 0;
    }

    private String getNomeMes(int mes) {
        switch (mes) {
            case 1:
                return "Janeiro";
            case 2:
                return "Fevereiro";
            case 3:
                return "Março";
            case 4:
                return "Abril";
            case 5:
                return "Maio";
            case 6:
                return "Junho";
            case 7:
                return "Julho";
            case 8:
                return "Agosto";
            case 9:
                return "Setembro";
            case 10:
                return "Outubro";
            case 11:
                return "Novembro";
            case 12:
                return "Dezembro";
            default:
                return "";
        }
    }
}
