package p2;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

public class ShippingTester {
    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
        ParcelManager pm = new ParcelManager();

        // ----------------------------------------------------------
        Parcel p1 = new Parcel(10, "Aveiro, Portugal", "Madrid, Spain");
        Parcel p2 = new Parcel(15, "Dublin, Ireland", "Berlin, Germany");
        pm.addParcel(p1);
        pm.addParcel(p2);
        // ----------------------------------------------------------

        pm.printAllParcels();

        // ----------------------------------------------------------
        System.out.println(pm.getParcel(1));
        System.out.println(pm.calculateShippingCost(1));
        System.out.println(pm.getParcel(2));
        System.out.println(pm.calculateShippingCost(2));
        System.out.println(pm.getParcel(30));              // não existe!
        System.out.println(pm.calculateShippingCost(30));  // não existe!
        // ----------------------------------------------------------
        System.out.println("======================================");
        pm.readFile("encomendas.txt");
        pm.printAllParcels();

        // ----------------------------------------------------------
        System.out.println(pm.getParcel(1));
        System.out.println(pm.calculateShippingCost(1));
        System.out.println(pm.getParcel(2));
        System.out.println(pm.calculateShippingCost(2));
        System.out.println(pm.getParcel(30));
        System.out.println(pm.calculateShippingCost(30));
        // ----------------------------------------------------------

        pm.writeFile("resultado.txt");

    }
}
