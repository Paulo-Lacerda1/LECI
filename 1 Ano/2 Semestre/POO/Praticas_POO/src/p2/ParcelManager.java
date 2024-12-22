package p2;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;
import java.util.TreeSet;
import java.io.File;

public class ParcelManager {
    TreeSet<Parcel> parcelSet = new TreeSet<>();

    public void addParcel(Parcel p) {
        if (isIdAlreadyUsed(p.getId())) {
            //
        } else {
            parcelSet.add(p);
        }
    }

    public boolean isIdAlreadyUsed(int id) {
        if (getParcel(id) == null) {
            return false;
        } else {
            return true;
        }
    }

    public Parcel searchParcelToRemove(int id) {
        for (Parcel p : parcelSet) {
            if (id == p.getId()) {
                return p;
            }
        }
        return null;
    }

    public void removeParcel(int id) {
        Parcel parcelToRemove = searchParcelToRemove(id);

        if (parcelToRemove != null) {
            parcelSet.remove(parcelToRemove);
        }
    }

    public Parcel getParcel(int id) {
        for (Parcel p : parcelSet) {
            if (id == p.getId()) {
                return p;
            }
        }
        return null;
    }

    public double calculateShippingCost(int id) {
        StandardShippingCostCalculator sscc = new StandardShippingCostCalculator();

        Parcel parcelToCalculateShippingCost = getParcel(id);

        if (parcelToCalculateShippingCost != null) {
            return sscc.calculateShippingCost(parcelToCalculateShippingCost);
        } else {
            return -1;
        }
    }

    public void printAllParcels() {
        for (Parcel p : parcelSet) {
            System.out.println(p);
        }
    }

    public void readFile(String fich) throws FileNotFoundException {
        File parcelsFile = new File("src/" + fich);
        Scanner parcelsScanner = new Scanner(parcelsFile);

        if (parcelsScanner.hasNextLine()) {
            parcelsScanner.nextLine();
        }
        
        while (parcelsScanner.hasNextLine()) {
            String parcelEntry = parcelsScanner.nextLine();
            String[] parcelEntrySplitted = parcelEntry.split("; ");

            int extractedId = Integer.parseInt(parcelEntrySplitted[0]);
            double extractedWeight = Double.parseDouble(parcelEntrySplitted[1]);

            Parcel extractedParcel = new Parcel(extractedId,
                                                extractedWeight,
                                                parcelEntrySplitted[2],
                                                parcelEntrySplitted[3]);
            
            addOrOverrideParcel(extractedParcel);
        }

        parcelsScanner.close();
    }

    private void addOrOverrideParcel(Parcel p) {
        int parcelId = p.getId();
        if (isIdAlreadyUsed(parcelId)) {
            removeParcel(parcelId);
        }
        parcelSet.add(p);
    }

    public void writeFile(String fich) throws FileNotFoundException, UnsupportedEncodingException {
        PrintWriter writer = new PrintWriter(fich, "UTF-8");
        for (Parcel p : parcelSet) {
            writer.println(String.format("%d; %.2f; %s; %s", p.getId(), p.getWeight(), p.getDestination(), p.getSender()));
        }

        writer.close();
    }
}
