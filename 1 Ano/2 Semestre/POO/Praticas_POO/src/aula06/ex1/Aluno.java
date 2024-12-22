package aula06.ex1;

import aula05.DateYMD;

import java.time.LocalDate;

public class Aluno extends Pessoa {
    DateYMD iDataInsc;
    private static int count;
    int nMec = 100 + count;

    Aluno(String nome, int cc, DateYMD dataNasc, DateYMD iDataInsc) {
        super(nome, cc, dataNasc);
        this.iDataInsc = iDataInsc;
        count++;
    }

    Aluno(String nome, int cc, DateYMD dataNasc) {
        super(nome, cc, dataNasc);
        this.iDataInsc = new DateYMD(LocalDate.now().getDayOfMonth(), LocalDate.now().getMonthValue(), LocalDate.now().getYear());
        count++;
    }

    public int getNMec() {
        return nMec;
    }

    public DateYMD getiDataInsc() {
        return iDataInsc;
    }

    @Override
    public String toString() {
        return "Aluno: " + nome + "; Nº mec: " + nMec;
    }
}
