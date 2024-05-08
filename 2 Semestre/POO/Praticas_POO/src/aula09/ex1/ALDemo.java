package aula09.ex1;

import aula06.ex1.Pessoa;
import aula05.DateYMD; 

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class ALDemo {
    public static void main(String[] args) {
        ArrayList<Integer> c1 = generateIntegerList();
        printIntegerList(c1);

        ArrayList<String> c2 = generateStringList();
        manipulateAndPrintStringList(c2);

        Set<Pessoa> c3 = generatePessoaSet();
        printPessoaSet(c3);
    }

    private static ArrayList<Integer> generateIntegerList() {
        ArrayList<Integer> c1 = new ArrayList<>();
        for (int i = 10; i <= 100; i += 10) {
            c1.add(i);
        }
        return c1;
    }

    private static void printIntegerList(ArrayList<Integer> c1) {
        System.out.println("Size: " + c1.size());
        for (int num : c1) {
            System.out.println("Elemento: " + num);
        }
    }

    private static ArrayList<String> generateStringList() {
        ArrayList<String> c2 = new ArrayList<>();
        c2.add("Vento");
        c2.add("Calor");
        c2.add("Frio");
        c2.add("Chuva");
        return c2;
    }

    private static void manipulateAndPrintStringList(ArrayList<String> c2) {
        System.out.println("Original: " + c2);
        Collections.sort(c2);
        System.out.println("Ordenada: " + c2);

        c2.remove("Frio");
        c2.remove(0);
        System.out.println("Depois de remover elementos: " + c2);
    }

    private static Set<Pessoa> generatePessoaSet() {
        Set<Pessoa> c3 = new HashSet<>();
        c3.add(new Pessoa("Miguel", 12345678, new DateYMD(18, 11, 2005)));
        c3.add(new Pessoa("Joao", 42342289, new DateYMD(18, 3, 2000)));
        c3.add(new Pessoa("Leandro", 42443223, new DateYMD(5, 2, 2005)));
        c3.add(new Pessoa("Joana", 32164499, new DateYMD(6, 1, 2005)));
        c3.add(new Pessoa("Paulo", 98989898, new DateYMD(8, 2, 2005)));
        return c3;
    }

    private static void printPessoaSet(Set<Pessoa> c3) {
        for (Pessoa p : c3) {
            System.out.println(p);
        }
    }
}
