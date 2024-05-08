package aula06.ex1;

import aula05.DateYMD;

public class Test {
    public static void main(String[] args) {
        Aluno al = new Aluno("Andreia Melo", 98556784, new DateYMD(18, 7, 1990), new DateYMD(1, 9, 2018));
        Professor p1 = new Professor("Jorge Almeida", 34627225, new DateYMD(13, 3, 1967),
                "Associado", "Informática");
        Bolseiro bls = new Bolseiro("Igor Santos", 89765434, new DateYMD(11, 5, 1985), p1,
                900);
        bls.setBolsa(1050);

        System.out.println("Aluno: " + al.getName());
        System.out.println(al);

        System.out.println("Bolseiro: " + bls.getName() + ", NMec: " + bls.getNMec() + ", Bolsa: " + bls.getBolsa() + ", Orientador: " + bls.getOrientador());
        System.out.println(bls);
    }
}
