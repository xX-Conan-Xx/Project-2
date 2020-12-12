package cs2030.simulator;
import java.util.Comparator;

public class EComparator implements Comparator<Event> {
    @Override
    public int compare(Event e1, Event e2) {
        double e1Time = e1.getTime();
        double e2Time = e2.getTime();

        if (e1Time < e2Time) {
            return -1;
        } else if (e1Time > e2Time) {
            return 1;
        } else {
            int customerIDForE1 = e1.getCustomer().getCustomerID();
            int customerIDForE2 = e2.getCustomer().getCustomerID();

            if (customerIDForE1 < customerIDForE2) {
                return customerIDForE1 - customerIDForE2;
            } else {
                return customerIDForE1 - customerIDForE2;
            }
        }
    }
}
