package cs2030.simulator;

import java.util.function.Supplier;

public class Customer {
    private final int customerID;
    private final double arrivalTime;
    private final Supplier<Double> serviceTime;
    Customer(int customerID, double arrivalTime, Supplier<Double> serviceTime){
        this.customerID=customerID;
        this.arrivalTime=arrivalTime;
        this.serviceTime = serviceTime;
    }

    public Customer(int id, double arrivalTime) {
        this.customerID  = id;
        this.arrivalTime = arrivalTime;
        Supplier<Double> defaultT = () -> 1.0;
        this.serviceTime = defaultT;
    }

    public int getCustomerID() {
        return customerID;
    }

    public double getArrivalTime() {
        return arrivalTime;
    }

    public Supplier<Double> getServiceTime() {
        return this.serviceTime;
    }

    @Override
    public String toString() {
        return  customerID + " arrives at " + arrivalTime;
    }
}
