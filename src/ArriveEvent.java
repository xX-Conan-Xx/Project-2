import java.util.List;

public class ArriveEvent extends Event{


    public ArriveEvent(Customer customer) {
        super(customer.getArrivalTime(), customer, x -> {
            x.getServers().stream().map(y->{
                if(y.isAvailable() == true){
                    return new ServeEvent(customer);
                }else{
                    
                }
            });

        });
    }
    @Override
    public String toString() {
        return String.format("%.3f", this.getTime()) +" " + this.getCustomer().getCustomerID() + " arrives";
    }

    @Override
    public Event execute() {
        for(Server server:getServers()){
            if(server.isAvailable()==true){
                return new ServeEvent(this.getCustomer(),this.getServers(),server);
            }
        }

        for(Server server:getServers()){
            if(server.isHasWaitingCustomer()==false){
                return new WaitEvent(getCustomer(),getServers(),server);
            }
        }
        return new LeaveEvent(getCustomer(),getServers());
    }
}
