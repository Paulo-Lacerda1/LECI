package aula08.ex1;

public class AutomovelEletricoLigeiro extends AutomovelLigeiro implements VeiculoEletrico {
    private int autonomiaMaxima;
    private int carga = 100;

    public AutomovelEletricoLigeiro(
        String matricula,
        String marca,
        String modelo,
        int potenciaCv,
        int numeroDoQuadro,
        int capacidadeDaBagageira,
        int autonomiaMaxima,
        int cargaInicial
    ) {
        super(matricula, marca, modelo, potenciaCv, numeroDoQuadro, capacidadeDaBagageira);
        this.autonomiaMaxima = autonomiaMaxima;
        this.carga = cargaInicial;
    }


    public int getAutonomiaMaxima() {
        return autonomiaMaxima;
    }

    public int getCarga() {
        return carga;
    }

    public int autonomia() {
        return autonomiaMaxima * (carga / 100);
    }

    public void carregar(int percentagem) {
        carga += percentagem;
        if (carga > 100) {
            carga = 100;
        }
    }


    @Override
    public String toString() {
        return "AutomovelEletricoLigeiro{" +
            "matricula='" + getMatricula() + '\'' +
            ", marca='" + getMarca() + '\'' +
            ", modelo='" + getModelo() + '\'' +
            ", potencia=" + getPotencia() +
            ", numeroDoQuadro=" + getNumeroQuadro() +
            ", capacidadeDaBagageira=" + getCapacidade() +
            ", autonomiaMaxima=" + autonomiaMaxima +
            ", carga=" + carga +
            '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        AutomovelEletricoLigeiro other = (AutomovelEletricoLigeiro) obj;
        return super.equals(obj) &&
            autonomiaMaxima == other.getAutonomiaMaxima() &&
            carga == other.getCarga();
    }
}
