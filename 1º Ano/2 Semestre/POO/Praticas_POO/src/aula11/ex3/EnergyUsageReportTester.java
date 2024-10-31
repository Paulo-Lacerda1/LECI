package aula11.ex3;

import java.io.IOException;
import java.util.Arrays;

public class EnergyUsageReportTester {

    public static void main(String[] args) throws IOException {
        EnergyUsageReport energyReport = new EnergyUsageReport();

        energyReport.load("clients.txt");

        Customer newCustomer = new Customer(999, Arrays.asList(1500.0, 2000.0, 2500.0, 3000.0));
        energyReport.addCustomer(newCustomer);

        energyReport.removeCustomer(1015);


        double totalEnergyUsage = energyReport.calculateTotalUsage(1003);
        System.out.println("Total energy usage for customer 1003: " + totalEnergyUsage);

        energyReport.generateReport("src/aula11/energy_report.txt");
    }
}
