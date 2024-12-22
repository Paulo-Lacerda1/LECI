package aula03;
import java.util.Random;
import java.util.Scanner;

public class exercicio33 {
    
    private static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        
        Random gerador = new Random();
        int numero_aleatorio = gerador.nextInt(100)+1;
        int aposta;
        int tentativas = 1;
        boolean chave;
        do {
            do {
                System.out.println("Palpite?");
                aposta = scanner.nextInt();

                if (aposta<numero_aleatorio){  
                    System.out.println("Baixo");
                    tentativas++;
                }

                else if (aposta>numero_aleatorio){
                    System.out.println("Alto");
                    tentativas++;
                }

            } while(aposta != numero_aleatorio);
            
            System.out.println("Acertou! Consegui em "+tentativas+" tentativas");
            System.out.println("Deseja jogar novamente? (S/N)");
            String resposta = scanner.next();
            
            if (resposta.equals("S") || resposta.equals("Sim")) {
                
                chave=true;
            }

            else{

                chave=false;
            } 

        } while (chave);

        System.out.println("Obrigado por jogar!");
        
        scanner.close();
    }
}
