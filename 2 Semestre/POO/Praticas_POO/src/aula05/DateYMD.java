package aula05;

import java.util.Scanner;

public class DateYMD {

    public static Scanner scanner = new Scanner(System.in);

    public int dia;
    public int mes;
    public int ano;
    
    public DateYMD(int dia, int mes, int ano){
        if (valid(dia, mes, ano)){
            this.dia = dia; this.mes = mes; this.ano = ano;
        }
        else System.out.println("\nData inválida, tente novamente");
    }

    public static int monthDays(int mes, int ano){
        int dia;
        switch (mes) {
            case 2 -> {
                
                if (leapYear(ano)) dia = 29;

                else dia = 28;
            }
            case 4, 6, 9, 11 -> dia = 30;

                default -> dia = 31;
        }
        return dia;
    }

    public static boolean leapYear(int ano){
        return (ano % 4 == 0 && ano % 100 != 0 || ano % 400 == 0);
    }
        
    public static boolean validMonth(int mes){
        return (1 <= mes && mes <= 12);
    }

    public static boolean valid(int dia, int mes, int ano) {
        if (!validMonth(mes)) { 
            return false;
        }       
        if (dia <= 0) { 
            return false;
        }
        if (mes == 2) {
            if (leapYear(ano)) { 
                return dia <= 29;
            } else { 
                return dia <= 28;
            }
        }
        if (mes == 4 || mes == 6 || mes == 9 || mes == 11) { 
            return dia <= 30;
        }
        return dia <= 31;
    }
    
    public void consultarData(){
        System.out.println("\nData: " + ano + "-" + mes + "-" + dia);
    }

    public void setData(int dia, int mes, int ano){
        if (valid(dia, mes, ano)){
            this.dia = dia; this.mes = mes; this.ano = ano;
        }
        else{ System.out.println("Data inválida, tente novamente");
        }
        }

    public void increment(){

        if (valid(dia+1, mes, ano)){
            ++dia;
            }
        else if(this.mes + 1 > 12){
            this.dia = 1;
            this.mes = 1;
            ++this.ano;
        }
        else {
            this.dia = 1; 
            ++this.mes;
        }
    }

    public void decrement() {
        if (this.dia > 1) {
            --this.dia;
        } else {
            if (this.mes > 1) {
                --this.mes;
                if (this.mes == 4 || this.mes == 6 || this.mes == 9 || this.mes == 11) {
                    this.dia = 30;
                } else if (this.mes == 2) {
                    if (leapYear(this.ano)) {
                        this.dia = 29;
                    } else {
                        this.dia = 28;
                    }
                } else {
                    this.dia = 31;
                }
            } else {
                --this.ano;
                this.mes = 12;
                this.dia = 31;
            }
        }
    }
    
    public static boolean isValidDate(int year, int month, int day) {
        if (year < 1 || month < 1 || month > 12 || day < 1) {
            return false;
        }
    
        int maxDay;
    
        switch (month) {
            case 4:
            case 6:
            case 9:
            case 11:
                maxDay = 30;
                break;
            case 2:
                if ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)) {
                    maxDay = 29;
                } else {
                    maxDay = 28;
                }
                break;
            default:
                maxDay = 31;
                break;
        }
    
        return day <= maxDay;
    }
    

    public String toString() {  
        return ano+"-"+mes+"-"+dia;
    }
    
}  