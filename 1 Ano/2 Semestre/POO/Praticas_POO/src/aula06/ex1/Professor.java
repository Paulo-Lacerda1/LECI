package aula06.ex1;

import aula05.DateYMD;

public class Professor extends Pessoa {
    String category;
    String dept;

    public Professor(String nome, int cc, DateYMD dataNasc, String category, String dept) {
        super(nome, cc, dataNasc);
        if (category.equals("Auxiliar") || category.equals("Associado") || category.equals("Catedrático")) {
            this.category = category;
            this.dept = dept;
        } else {
            System.out.println("Categoria inválida");
        }
    }

    public String getCategory() {
        return category;
    }

    public String getDept() {
        return dept;
    }

    @Override
    public String toString() {
        return "Professor: " + nome + "; Categoria: " + category + "; Departamento: " + dept;
    }
}
