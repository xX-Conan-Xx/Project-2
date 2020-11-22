import java.util.List;
import java.util.Optional;

public class ArriveEvent extends Event{


    public ArriveEvent(Customer customer) {
        super(customer.getArrivalTime(), customer, x -> {
            if(!x.getServers().stream().findFirst().filter(y->y.isAvailable()).equals(Optional.empty())){
                return Pair.of(x,new ServeEvent(customer,x.getServers(),x.getServers().stream().findFirst().filter(y->y.isAvailable()).get()));
            }
            if(!x.getServers().stream().findFirst().filter(y->y.isHasWaitingCustomer()).equals(Optional.empty())){
                return Pair.of(x,new WaitEvent(customer,x.getServers(),x.getServers().stream().findFirst().filter(y->y.isHasWaitingCustomer()).get()));
            }

            return Pair.of(x,new LeaveEvent(customer,x.getServers()));
        });
    }
    @Override
    public String toString() {
        return String.format("%.3f", this.getTime()) +" " + this.getCustomer().getCustomerID() + " arrives";
    }
}
