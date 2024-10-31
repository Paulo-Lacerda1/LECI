package utils;

import java.io.FileWriter;
import java.io.IOException;

public class WriteToFile {
    public static void main(String[] args) {
            try {
                FileWriter writer = new FileWriter("example.txt");
                writer.write("Hello, World!");
                writer.close();
                System.out.println("File written successfully!");
            } catch (IOException e) {
                e.printStackTrace();
            }
    }
}

/*public void writeFile(String fich) throws FileNotFoundException, UnsupportedEncodingException {
    PrintWriter writer = new PrintWriter(fich, "UTF-8");
    for (Parcel p : parcelSet) {
        writer.println(String.format("%d; %.2f; %s; %s", p.getId(), p.getWeight(), p.getDestination(), p.getSender()));
    }

    writer.close();
} */

quero que crises um ficheiro  que leia o ticket_bookings.txt e liste os primeiros 10 eventos com bilhetes disponioveis ordenados por data e listar o total de bilhetes vendidos, por tipo de evento