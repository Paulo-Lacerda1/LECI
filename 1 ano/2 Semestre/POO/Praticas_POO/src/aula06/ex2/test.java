package aula06.ex2;

import java.util.ArrayList;
import java.util.Scanner;
import aula05.DateYMD;

public class test {
    
    private static ArrayList <Contactos> list = new ArrayList<Contactos>();
    private static Scanner sc = new Scanner(System.in);
        
    public static void main(String[] args) {

        String MENU = "\n 1. Inserir contacto\n 2. Alterar contacto\n 3. Apagar contacto \n 4. Procurar contacto \n 5. Listar contactos\n 0. Sair";
        Boolean stay = true;
        
        int option;
        do {

            System.out.println(MENU);

            try {
                System.out.print("  >> ");
                option = sc.nextInt();
                
            } catch (Exception e) {
                sc.nextLine();
                continue;
            }
            
            try{
                switch (option) {
                    case 0:
                        stay = false;
                        break;
                    case 1: 
                        addContact();
                        break;
                    case 2:
                        alterContact();
                        break;
                    case 3: 
                        removeContact();
                        break;
                    case 4:
                        searchContact();
                        break;
                    case 5:
                        listContacts();
                        break;
                    default:
                        break;
                }
            }catch(Exception e){
                System.out.println("\nErro : Clica ENTER para continuar...");            
                sc.nextLine();
                continue;
            }

            
        } while (stay);
    
    
    }

    private static void theAlteringFunction(Contactos contato){
        System.out.println("\nAlterar\n 1. Nome \n 2. Email/Num\n >> ");
            int key = sc.nextInt();
            switch (key) {
                case 1:
                    System.out.print("Novo Nome: \n >> ");
                    sc.nextLine();
                    String newNome = sc.nextLine();
                    contato.setNome(newNome);
                    break;
                case 2: 
                    System.out.print("Novo Email/Num: ");
                    sc.nextLine();
                    String input = sc.nextLine();
                    try {
                        contato.setNumber(Integer.valueOf(input));
                    } catch (Exception e) {
                        contato.setEmail(input);
                    }
                    break;
                default:
                    break;

            }
    }

    private static void alterContact(){
        ArrayList<Contactos> toAlter = new ArrayList<Contactos>();
        
        System.out.print("\nNome ou Número/Email: ");
        sc.nextLine();
        String input = sc.nextLine();
        int n = 1;

        for (Contactos contato : list) {
            if(contato.getName().contains(input) || Integer.toString(contato.getNumber()).equals(input) || contato.getEmail().equals(input))
                toAlter.add(contato);
        }

        if(toAlter.size() == 0)
            return;
        else if(toAlter.size() == 1){
            theAlteringFunction(toAlter.get(0));
            }
        
        else{
            for (Contactos c : toAlter) {   
                System.out.println(n+". "+c.toString());
                n++;
            }
            System.out.print("Alterar (ID): \n >> ");
            int i = sc.nextInt()-1;
            theAlteringFunction(toAlter.get(i));


        }
    }

    private static void addContact() {
        String name, input;
        Contactos contact;
        sc.nextLine();
        while (true) {
            System.out.print("Nome: ");
            name = sc.nextLine();
    
            System.out.print("Cartão de Cidadão: ");
            int cc = sc.nextInt();
    
            System.out.println("Data de Nascimento");
            int year, month, day;
            do {
                System.out.print("Ano: ");
                year = sc.nextInt();
                System.out.print("Mês: ");
                month = sc.nextInt();
                System.out.print("Dia: ");
                day = sc.nextInt();
                if (!DateYMD.isValidDate(year, month, day)) {
                    System.out.println("Data inválida, tente novamente.");
                }
            } while (!DateYMD.isValidDate(year, month, day));
    
            DateYMD personData = new DateYMD(year, month, day);
    
            System.out.print("Número ou Email: ");
            sc.nextLine();
            input = sc.nextLine();
    
            try {
                contact = new Contactos(name, cc, personData, input, 0);
            } catch (Exception e) {
                continue;
            }
            for (Contactos contato : list) {
                if (contato.getName().equals(name)) {
                    System.out.print("\nContato existente, continuar como novo contato ou cancelar\n (A)dicionar\n Qualquer botão para cancelar \n >> ");
                    if (sc.nextLine().toUpperCase().equals("A")) {
                        list.add(contact);
                        return;
                    }
                }
            }
            list.add(contact);
            break;
        }
    }
    
    private static void listContacts(){
        System.out.println();
        for (Contactos contatos : list) {
            System.out.println(contatos.toString());
            
        }

    }

    private static void removeContact(){
        ArrayList<Contactos> toRemove = new ArrayList<Contactos>();
        
        System.out.print("Nome ou Número/Email: ");
        sc.nextLine();
        String input = sc.nextLine();
        int n = 1;

        for (Contactos contato : list) {

            if(contato.getName().contains(input) || Integer.toString(contato.getNumber()).equals(input) || contato.getEmail().equals(input))
                toRemove.add(contato);
        }

        if(toRemove.size() == 0)
            return;
        else if(toRemove.size() == 1){
            list.remove(toRemove.get(0).getID());
        }
        else{
            for (Contactos c : toRemove) {   
                System.out.println(n+". "+c.toString());
                n++;
            }
            System.out.print("Remover(ID): \n >> ");
            int i = sc.nextInt()-1;
            list.remove(toRemove.get(i).getID());

        }

    }

    private static void searchContact(){
        
        System.out.print("\nNome ou Número/Email: ");
        sc.nextLine();
        String input = sc.nextLine();

        for (Contactos contato : list) {
            if(contato.getName().contains(input) || Integer.toString(contato.getNumber()).equals(input) || contato.getEmail().equals(input))
                System.out.println(contato.toString());
        }

    }
}