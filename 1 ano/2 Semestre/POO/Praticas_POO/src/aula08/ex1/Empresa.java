package aula08.ex1;

import java.util.ArrayList;

public class Empresa {
    private String nome;
    private String códigoPostal;
    private String email;
    private ArrayList<Veiculo> veiculos;

    public Empresa(String nome, String códigoPostal, String email) {
        this.nome = nome;
        this.códigoPostal = códigoPostal;
        this.email = email;
        this.veiculos = new ArrayList<>();
    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCódigoPostal() {
        return códigoPostal;
    }

    public void setCódigoPostal(String códigoPostal) {
        this.códigoPostal = códigoPostal;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ArrayList<Veiculo> getVeiculos() {
        return veiculos;
    }

    public void setVeiculos(ArrayList<Veiculo> veiculos) {
        this.veiculos = veiculos;
    }

    public void addVeiculo(Veiculo veiculo) {
        this.veiculos.add(veiculo);
    }

    public void addVeiculos(Veiculo... veiculos) {
        for (Veiculo veiculo : veiculos) {
            this.veiculos.add(veiculo);
        }
    }

    public void validarEmail(String email) {
        if (email.contains("@")) {
            System.out.println("Email válido");
        } else {
            System.out.println("Email inválido");
        }
    }

    @Override
    public String toString() {
        return "Empresa{" +
            "nome='" + nome + '\'' +
            ", códigoPostal='" + códigoPostal + '\'' +
            ", email='" + email + '\'' +
            ", veiculos=" + veiculos +
            '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Empresa other = (Empresa) obj;
        return nome.equals(other.nome) &&
            códigoPostal.equals(other.códigoPostal) &&
            email.equals(other.email) &&
            veiculos.equals(other.veiculos);
    }
}
