import java.util.Scanner;

public class exercicio32 {
    
    private static Scanner scanner = new Scanner(System.in);
    
    public static float investimento() {
        float montante;

        do {
            System.out.println("Qual o montante que deseja investir?");
            montante = scanner.nextFloat();

            if (montante < 0) {
                System.out.println("Por favor, insira um montante positivo");
            }
            else if(montante==0){
                System.out.println("Se investir 0 EUR não vai ter lucro nenhum. ");
            }
            else if (montante % 1000 != 0) {
                System.out.println("Por favor, insira um montante que seja múltiplo de 1000 (montante % 1000 = 0)");
            }

        } while (montante <= 0 || montante % 1000 != 0);

        return montante;
    }

    public static float taxa(){
        float taxa;
        do {
            System.out.println("Qual a taxa de juro (%) ?");
            taxa = scanner.nextFloat();
            if(taxa<0 || taxa>5){
                System.out.println("Insira uma taxa entre os 0% e 5%");
            }

        } while(taxa<0 || taxa>5);
        return taxa;
    }
    
    public static void calculo(float investimento, float taxa){
        
        int mes = 0;
        
        if(taxa==0){
            System.out.println("Não vai ter lucros. O montante final é de "+investimento+" EUR");
            return;
        }
        for (mes=0;mes<12;){
            float lucro = investimento*(taxa/100);
            investimento = investimento + lucro;
            mes+=1;
            System.out.println("No mês nº "+mes+" o montante é de "+investimento+" EUR com uma taxa de juro de "+taxa+" %");
        }
    }     
    public static void main(String[] args) {
        float investimento = investimento();
        float taxa = taxa();
        calculo(investimento, taxa);
        scanner.close();
    }
}
