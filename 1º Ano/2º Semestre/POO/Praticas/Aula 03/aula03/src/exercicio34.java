import java.util.Random;
import java.util.Scanner;

public class exercicio34 {
    
    private static Scanner scanner = new Scanner(System.in);

    public static int calcular_nota(float notaP,float notaT){
        
        if (notaT<7 || notaP<7 ) {
            int nota_final_arredondada = 66;
            return nota_final_arredondada;
        }
        else {

            float nota_final = (float) ((0.4*notaT)+(0.6*notaP));

            int nota_final_arredondada = Math.round(nota_final);

            return nota_final_arredondada;
        }
    }
    public static void gerador_de_notas(float[][] notas) {
        Random gerador = new Random();
    
        for (int i = 0; i < notas.length; i++) {
            for (int j = 0; j < notas[i].length; j++) {
                notas[i][j] = (float) (Math.round(gerador.nextFloat() * 20.0 * 10.0) / 10.0);
            }
        }
    }
    
    public static void tabela_resultados(float[][] scores) {
        System.out.printf("%-8s%-8s%-8s%n", "NotaT", "NotaP", "Pauta");
    
        for (int i = 0; i < scores.length; i++) {
            float notaT = scores[i][0];
            float notaP = scores[i][1];
    
            int Pauta = calcular_nota(notaT, notaP);
    
            System.out.printf("%-8.1f%-8.1f%-8d%n", notaT, notaP, Pauta);
        }
    }

    public static void main(String[] args) {

        System.out.println("Quantos alunos existem na turma ?");
        int n_alunos = scanner.nextInt();

        System.out.println("Qual foi a nota da componente teórica ?");
        float notaT=scanner.nextFloat();

        System.out.println("Qual foi a nota da componente prática ?");
        float notaP=scanner.nextFloat();
        
        float[][] notas = new float[n_alunos][2];

        gerador_de_notas(notas);

        calcular_nota(notaP, notaT);
        
        tabela_resultados(notas);
    
    
        scanner.close();
    }

}
