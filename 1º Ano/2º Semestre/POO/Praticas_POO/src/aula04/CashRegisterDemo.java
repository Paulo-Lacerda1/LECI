package aula04;
class Product {
    private String name;
    private double price;
    private int quantity;

    public Product(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public double getTotalValue() {
        return price * quantity;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int  getQuantity() {
        return quantity;
    }
}


class CashRegister {

    private final Product[] products = new Product[5];
    
    public void addProduct(Product prod) {
        int i = 0;
        while (i < products.length) {
            if (products[i] == null) {
                products[i] = prod;
                break;
            }
            i++;
        }
    }
    public double calcTotal(){
        double sum = 0;
        for (int i = 0; i < products.length; i++) {
            sum += products[i].getQuantity() * products[i].getPrice();
        }
        return sum;
    }

    public String toString(){
        String text = String.format("%-17s%5s%12s%9s\n", "Product", "Price", "Quantity", "Total");
        for (int i = 0; i < products.length; i++) {
            text += (String.format("%-17s%5.2f%12d%9.2f\n", products[i].getName(), products[i].getPrice(), products[i].getQuantity(), products[i].getTotalValue()));
        }
        text += "Total value: " + calcTotal();
        return text;
    }


}
public class CashRegisterDemo {

    public static void main(String[] args) {

        CashRegister cr = new CashRegister();
        cr.addProduct(new Product("Book", 9.99, 3));
        cr.addProduct(new Product("Pen", 1.99, 10));
        cr.addProduct(new Product("Headphones", 29.99, 2));
        cr.addProduct(new Product("Notebook", 19.99, 5));
        cr.addProduct(new Product("Phone case", 5.99, 1));
        
        System.out.println(cr);

    }
}