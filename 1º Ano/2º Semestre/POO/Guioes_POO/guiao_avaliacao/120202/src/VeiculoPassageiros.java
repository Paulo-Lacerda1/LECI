 public class VeiculoPassageiros extends Veiculo {

    private int num_passageiros;
    private Combustao combustao;
    private int potenciaKW;

    public VeiculoPassageiros(String marca, String modelo, int precoBase, int num_passageiros, Combustao combustao, int potenciaKW) {
        super(marca, modelo, precoBase);
        this.num_passageiros = num_passageiros;
        this.combustao = combustao;
        this.potenciaKW = potenciaKW;
    }

    public int getNum_passageiros() {
        return num_passageiros;
    }

    public Combustao getcombustao() {
        return combustao;
    }

    public int getPotenciaKW() { //converter se tiver tempo
        return potenciaKW;
    }

    public void setNum_passageiros(int num_passageiros) {
        this.num_passageiros = num_passageiros;
    }

    public void setCombustao(Combustao combustao) {
        this.combustao = combustao;
    }

    public void setPotenciaKW(int potenciaKW) {
        this.potenciaKW = potenciaKW;
    }


    public String toString() {
        return "VeiculoPassageiros: num_passageiros-" + num_passageiros + ", combustao-" + combustao + ", potenciaKW-" + potenciaKW;
    }

    enum Combustao {
        GASOLEO,
        GPL,
        HIBRIDO,
        ELETRICO
    }

}