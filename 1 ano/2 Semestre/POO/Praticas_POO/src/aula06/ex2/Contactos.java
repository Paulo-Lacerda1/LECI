package aula06.ex2;

import aula05.DateYMD;
import aula06.ex1.Pessoa;
import java.util.Scanner;

public class Contactos extends Pessoa {
    
    private int number;
    private String email;
    private static int id = 0;

    private static Scanner scanner = new Scanner(System.in);

    public Contactos(String nome, int cc, DateYMD dataNasc, String input, int i) {
        super(nome, cc, dataNasc);
        id++;

        System.out.print("Introduza um email: ");
        this.email = scanner.nextLine();
        System.out.print("Introduza um número que comece por 9: ");
        this.number = scanner.nextInt();
    }

    public String getEmail() {
        return email;
    }

    public int getNumber() {
        return number;
    }

    public int getID() {
        return id;
    }

    public void setEmail(String input) {
        System.out.print("Introduza um email: ");
        this.email = scanner.nextLine();
        scanner.close();
    }

    public void setNumber(Integer integer) {
        System.out.print("Introduza um número que comece por 9: ");
        this.number = scanner.nextInt();
    }

    public void setName(String nome) {
        super.setNome(nome);
    }

    @Override
    public String toString() {
        return super.toString() + "; Email: " + email + "; Número: " + number + "; ID: " + id;
    }

    public static boolean isValidPhoneNumber(int number) {
        String phoneNumber = String.valueOf(number);
        return phoneNumber.length() == 9 && phoneNumber.startsWith("9");
    }
}
