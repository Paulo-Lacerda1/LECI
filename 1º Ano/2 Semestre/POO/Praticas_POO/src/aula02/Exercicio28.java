package aula02;
import java.util.Scanner;

public class Exercicio28 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        float cateto_a;

        while (true) {
            System.out.println("Insira o tamanho do cateto A:");
            cateto_a = scanner.nextFloat();
            
            if (cateto_a > 0) {
                break;
            } else {
                System.out.println("Insira um valor positivo");
                System.out.println();
            }
        }
        
        float cateto_b;

        while (true) {
            System.out.println("Insira o tamanho do cateto B:");
            cateto_b = scanner.nextFloat();
            
            if (cateto_b > 0) {
                break;
            } else {
                System.out.println("Insira um valor positivo");
                System.out.println();
            }
        }

        double hipotenusa = Math.sqrt((cateto_a * cateto_a)+(cateto_b * cateto_b));

        double angulo_graus = Math.toDegrees(Math.atan2(cateto_a, hipotenusa));

        System.out.println("O valor da hipotenusa é de: "+ hipotenusa+ " e o valor do angulo é de "+angulo_graus+ "°");  
        scanner.close();
    }
}
