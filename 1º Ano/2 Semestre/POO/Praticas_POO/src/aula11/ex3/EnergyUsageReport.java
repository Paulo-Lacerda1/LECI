package aula11.ex3;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EnergyUsageReport {
    List<Customer> customers = new ArrayList<>();

    public void load(String path) throws IOException {
        String[] lines = Files.readString(Paths.get(path)).split("\n");
        for (Object key : lines) {
            String[] line = key.toString().split("\\|");

            List<Double> meters = new ArrayList<>();
            for (String number : Arrays.copyOfRange(line, 1, 5)) {
                meters.add(Double.parseDouble(number));
            }

            Customer customer = new Customer(Integer.parseInt(line[0]), meters);
            customers.add(customer);
        }
    }

    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    public void removeCustomer(Integer id) {
        customers.remove(getCustomer(id));
    }

    public Customer getCustomer(Integer id) {
        for (Customer customer : customers) {
            if (customer.getCustomerId() == id)
                return customer;
        }
        return null;
    }

    public int calculateTotalUsage(Integer id) {
        Customer customer = getCustomer(id);
        int total = 0;

        assert customer != null;
        for (Double meter : customer.getMeterReadings()) {
            total += meter;
        }
        return total;
    }

    public void generateReport(String path){
        try {
            try (FileWriter file = new FileWriter(path)) {
                for (Customer customer : customers) {
                    int id = customer.getCustomerId();
                    file.write("Customer " + id + " consumed " + calculateTotalUsage(id) + " watts of energy.\n");
                }
            }
        } catch (IOException e) {
            System.out.println("Writing to file failed");
        }
    }
}
