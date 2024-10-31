package aula11.ex3;

import java.util.List;

public class Customer {
    private int customerId;
    private List<Double> meterReadings;

    public Customer(int customerId, List<Double> meterReadings) {
        this.meterReadings = meterReadings;
        this.customerId = customerId;
        
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }
    
    public void setMeterReadings(List<Double> meterReadings) {
        this.meterReadings = meterReadings;
    
    }
    public List<Double> getMeterReadings() {
        return meterReadings;
    }
}
