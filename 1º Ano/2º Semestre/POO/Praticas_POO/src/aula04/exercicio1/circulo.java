package aula04.exercicio1;
public class circulo {
    private double raio;

    public circulo(double raio) {
        if (raio < 0){ 
            System.out.println("Introduziu um valor inválido"); 
        }
        else {
            this.raio = raio;
        }
    }

    public void setRaio(double raio){
        if (raio < 0){
            System.out.println("Introduziu um valor inválido"); 
        }
        else { 
            this.raio = raio; 
        }
    }

    public boolean equals(circulo circulo){ 
        return circulo.raio == this.raio; 
    }

    public double getRaio(){ 
        return this.raio; 
    }

    public double area() { 
        return Math.PI * Math.pow(raio, 2); 
    }

    public double perimetro() { 
        return 2 * Math.PI * this.raio; 
    }

    public String toString() { 
        return "Raio do círculo: " + raio; 
 
    }

    
}
