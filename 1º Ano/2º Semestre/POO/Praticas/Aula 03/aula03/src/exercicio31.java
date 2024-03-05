import java.util.Scanner;

public class exercicio31 {

    public static int ler_positivo(){

        Scanner scanner = new Scanner(System.in);
        int numero;
        System.out.println("Qual número deseja adicionar?");
        numero = scanner.nextInt();
        scanner.close();
        
        do {
            if (numero <= 0){
                System.out.println("O número tem de ser positivo. Insira novamente.");
                }
        } while (numero <= 0);
        return numero;
    }

    public static boolean verificar_primo(int numero){
        if (numero<2){
            return false;
        }
        for (int i = 2; i * i <= numero; i++){
            if (numero % i == 0){
                return false;
            }
        }
        return true;
    }
    
    public static int soma_primos(int numero){

        int soma = 0;

        for (int i = 2; i<=numero;i++){

        if (verificar_primo(i)){
            soma += i;
            }
        }
        return soma;
    }

    public static void main(String[] args) {
        int numero = ler_positivo();
        int soma = soma_primos(numero);
        System.out.println("A soma dos números primos até " + numero + " é de: " + soma);
    }
}
    