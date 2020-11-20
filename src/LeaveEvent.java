import java.util.List;

public class LeaveEvent extends Event{
    public LeaveEvent(Customer customer, List<Server> servers) {
        super(customer.getArrivalTime(), customer, servers);
    }

    @Override
    public String toString() {
        return String.format("%.3f", super.getTime()) + " " + getCustomer().getCustomerID() + " leaves";
    }

    @Override
    public Event execute() {
        return null;
    }
}
