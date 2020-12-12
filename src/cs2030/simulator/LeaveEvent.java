package cs2030.simulator;
import java.util.List;

public class LeaveEvent extends Event{
    public LeaveEvent(Customer customer) {
        super(customer.getArrivalTime(), customer, x->Pair.of(x,null));
    }

    @Override
    public String toString() {
        return String.format("%.3f", super.getTime()) + " " + getCustomer().getCustomerID() + " leaves";
    }
}
