public class Veiculo {
    private static int ID_Base = 100; 
    private int ID;
    private String marca;
    private String modelo;
    private int precoBase;

    public Veiculo(String marca, String modelo, int precoBase) {
        
        this.ID = ID_Base; 
        this.marca = marca;
        this.modelo = modelo;
        this.precoBase = precoBase;
        ID_Base++; 
    }

    public int getCodigo() {
        return ID;
    }

    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    public int getPrecoBase() {
        return precoBase;
    }

    public void setID(int iD) {
        ID = iD;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public void setPrecoBase(int precoBase) {
        this.precoBase = precoBase;
    }

    public String toString() {
        return "Veiculo: ID-" + ID + ", marca=" + marca + ", modelo-" + modelo + ", precoBase-" + precoBase ;
    }

}

