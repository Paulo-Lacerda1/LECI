public class Motorizada extends Veiculo {

    private int n_rodas;
    private float vel_maxima;
    
    public Motorizada(String marca, String modelo, int precoBase, int n_rodas, int vel_maxima){
        super(marca, modelo, precoBase);
        this.n_rodas = n_rodas;
        this.vel_maxima = vel_maxima;
    }

    public int getN_rodas() {
        return n_rodas;
    }
    
    public float getvel_maxima(){
        return vel_maxima;
    }

    public void setN_rodas(int n_rodas) {
        if (n_rodas <= 0) {
            System.out.println("O número de rodas tem de ser positivo");
        } 
        else if (n_rodas/2 != 0) {
            System.out.println("Insira um numero par de rodas");
        }
        else {
            this.n_rodas = n_rodas;
        }
    }
    
    public void vel_maxima(int vel_maxima) {
        if (vel_maxima > 0) {
            this.vel_maxima = vel_maxima;
        } else {
            System.out.println("Insira um valor positivo para a velocidade");
        }
    }
}
