package aula04.exercicio1;
public class retangulo {

    private double largura, altura;

    public retangulo(double largura, double altura) {
        if (largura < 0 || altura < 0) {
            System.out.println("Introduziu um valor inválido");
        } else {
            this.altura = altura;
            this.largura = largura;
        }
    }

    public void definirLados(double largura, double altura) {
        if (largura < 0 || altura < 0) {
            System.out.println("Introduziu um valor inválido");
        } else {
            this.altura = altura;
            this.largura = largura;
        }
    }

    public boolean equals(retangulo retangulo) {
        return (this.largura == retangulo.largura && this.altura == retangulo.altura);
    }

    public double[] obterLados() {
        return new double[]{largura, altura};
    }

    public double perimetro() {
        return ((largura * 2) + (altura * 2));
    }

    public double area() {
        return (largura * altura);
    }

    public String toString() {
        return "Base do retângulo: " + largura + "\n" + "Altura do retângulo: " + altura;
    }
}
