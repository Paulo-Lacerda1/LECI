public class VeiculoComercial extends Veiculo {
    
    private String formato;
    private int carga;
    private String unidade_carga;

    enum formato{
        furgão,
        caixa_aberta,
        cisterna,
    }

    enum unidade_carga{
        Kilograma, 
        Litro,
        Embalagens
    }
    
    public VeiculoComercial(String marca, String modelo, int precoBase, String formato, int carga, String unidade_carga ) {
        super(marca, modelo, precoBase);
        this.formato = formato;
        this.carga = carga;
        this.unidade_carga = unidade_carga;
    }

    public String getFormato() {
        return formato;
    }

    public void setFormato(String formato) {
        if (formato.equals("furgão") || formato.equals("Litro") || formato.equals("Embalagens")){
            this.formato = formato;
        } 
        else {
            System.out.println("Insira um formato valido");}
    }

    public int getCarga() {
        return carga;
    }

    public void setCarga(int carga) {
        if (carga > 0) {
            this.carga = carga;
        } else {
            System.out.println("O valor da carga tem de ser maior que zero");
        }
    }

    public String getUnidade_carga() {
        return unidade_carga;
    }

    public void setUnidade_carga(String unidade_carga) {
        this.unidade_carga = unidade_carga;
    }

    public String toString() {
        return "VeiculoComercial: formato-" + formato + ", carga-" + carga + ", unidade_carga-" + unidade_carga;
    }

}
