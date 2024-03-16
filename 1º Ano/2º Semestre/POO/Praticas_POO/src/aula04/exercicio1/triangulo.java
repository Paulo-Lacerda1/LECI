package aula04.exercicio1;
public class triangulo {
    private double lado1, lado2, lado3;

    public triangulo(double lado1, double lado2, double lado3){
        if (!(lado1 > 0 && lado2 > 0 && lado3 > 0)) {
            System.out.println("Os lados do triângulo não podem ser negativos"); return;
        }
        if (lado1 + lado2 < lado3 || lado1 + lado3 < lado2 || lado2 + lado3 < lado1) {
            System.out.println("Os lados do triângulo não formam um triângulo"); return;
        }
        this.lado1 = lado1; this.lado2 = lado2; this.lado3 = lado3;
    }

    public void setLados(double lado1, double lado2, double lado3){
        if (lado1 <= 0 || lado2 <= 0 || lado3 <= 0) {
            System.out.println("Os lados do triângulo têm de ser positivos"); return;
        }
        if (lado1 + lado2 < lado3 || lado1 + lado3 < lado2 || lado2 + lado3 < lado1) {
            System.out.println("Os lados do triângulo não formam um triângulo"); return;
        }
        this.lado1 = lado1; this.lado2 = lado2; this.lado3 = lado3;
    }

    public boolean equals(triangulo triangulo){
        return (triangulo.lado1 == this.lado1 && triangulo.lado2 == this.lado2 && triangulo.lado3 == this.lado3);
    }

    public double[] getLados(){ return new double[] {this.lado1, this.lado2, this.lado3}; }

    public double perimetro(){ return this.lado1 + this.lado2 + this.lado3; }

    public double area(){
        double p = this.perimetro() / 2;
        return Math.sqrt(p * (p - this.lado1) * (p - this.lado2) * (p - this.lado3));
    }

    public String toString(){ return "Lados do triângulo: " + this.lado1 + ", " + this.lado2 + ", " + this.lado3; }
}
