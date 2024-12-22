import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainStand {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        List<Veiculo> veiculos = new ArrayList<>();
        int opcao = 0;
        do {
            System.out.println("\nMenu:");
            System.out.println("1. Adicionar um veiculo");
            System.out.println("2. Listar veiculos");
            System.out.println("3. Vender um veiculo");
            System.out.println("4. Calcular o lucro total");
            System.out.println("5. Sair");
            System.out.print("Opçao: ");
            opcao = sc.nextInt();
            sc.nextLine();
            switch (opcao) {
                case 1:
                    System.out.println("\n1. Adicionar um veiculo do tipo");
                    System.out.println("1. Passageiros");
                    System.out.println("2. Comercial");
                    System.out.println("3. Motorizada");
                    System.out.print("Tipo de produto: ");
                    int opcaoVeiculo = sc.nextInt();
                    sc.nextLine();
                    switch (opcaoVeiculo) {
                        case 1:
                            System.out.println("Qual é a marca do veiculo?");
                            String marca = sc.nextLine();
                            System.out.println("Qual o modelo do veiculo?");
                            String modelo = sc.nextLine();
                            System.out.println("Qual é o preco base do veiculo?");
                            int precoBase = sc.nextInt();
                            Veiculo veiculo = new Veiculo(marca,modelo, precoBase);
                            veiculos.add(veiculo);
                            break;

                            //nao consegui fazer com que os dados fossem verificados na outra class (sem tempo)
                        case 2:
                        //repetir o mesmo processo de cima com as novas variaveis 
                            VeiculoComercial veiculoComercial = new VeiculoComercial(null, null, opcao, null, opcaoVeiculo, null);
                            veiculos.add(veiculoComercial);
                            break;
                        case 3:
                        //repetir o mesmo processo de cima com as novas variaveis 
                            Motorizada motorizada = new Motorizada(null, null, opcaoVeiculo, opcao, opcaoVeiculo);
                            veiculos.add(motorizada);
                            break;
                    }
                    break;
                case 2:
                    for (int i = 0; i < veiculos.size(); i++) {
                        System.out.println((i + 1) + ". " + veiculos.get(i));
                    }
                    break;
                case 3:
                    // Vender um Veiculo
                    break;
                case 4:
                    // Apresentar o Lucro Total
                    break;
                case 5:
                    System.out.println("5. Sair");
                    sc.close();
                    break;
            }
        } while (opcao != 5);
    }
}