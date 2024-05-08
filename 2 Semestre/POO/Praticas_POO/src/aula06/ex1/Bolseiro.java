package aula06.ex1;

import aula05.DateYMD;

public class Bolseiro extends Aluno {
    String orientador;
    int bolsa;

    public Bolseiro(String nome, int cc, DateYMD dataNasc, Professor orientador, int bolsa) {
        super(nome, cc, dataNasc);
        if (    bolsa > 0) {
            this.bolsa = bolsa;
            this.orientador = orientador.getName();
        } else {
            System.out.println("Bolsa inválida");
        }
    }

    public void setBolsa(int bolsa) {
        if (bolsa > 0) {
            this.bolsa = bolsa;
        } else {
            System.out.println("Bolsa inválida");
        }
    }

    public int getBolsa() {
        return bolsa;
    }

    public String getOrientador() {
        return orientador;
    }

    @Override
    public String toString() {
        return "Bolseiro: " + nome + ", NMec: " + nMec + ", Bolsa: " + bolsa + ", Orientador: " + orientador;
    }
}
