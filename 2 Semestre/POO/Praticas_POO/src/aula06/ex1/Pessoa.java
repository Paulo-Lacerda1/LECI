package aula06.ex1;

import aula05.DateYMD;

public class Pessoa {
    
    String nome;
    int cc;
    DateYMD dataNasc;

    public Pessoa(String nome, int cc, DateYMD dataNasc) {
        if (nome.matches("[ A-Za-z]+") && String.valueOf(cc).length() == 8) {
            this.dataNasc = dataNasc;
            this.cc = cc;
            this.nome = nome;
        } else {
            System.out.println("Dados inválidos");
        }
    }

    public String getName() {
        return nome;
    }

    public int getCc() {
        return cc;
    }

    public DateYMD getDataNasc() {
        return dataNasc;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String toString() {
        return "Nome: " + nome + "; CC: " + cc + "; Data de Nascimento: " + dataNasc;
    }
}
