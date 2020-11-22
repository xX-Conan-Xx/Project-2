import java.util.ArrayList;
import java.util.List;

public class WaitEvent extends Event{
    private final Server newServer;
    public WaitEvent(Customer customer, List<Server> servers,Server server) {
        super(customer.getArrivalTime(), customer, x->{
            List<Server> editedServers = new ArrayList<>();
            editedServers.addAll(x.getServers());
            editedServers.set(servers.indexOf(server),new Server(server.getIdentifier(),true,true, server.getNextAvailableTime()));
            return Pair.of(new Shop(editedServers),
                    new ServeEvent(customer,x.getServers(),new Server(server.getIdentifier(),true,true, server.getNextAvailableTime())));
        });
        this.newServer = new Server(server.getIdentifier(),true,true, server.getNextAvailableTime());
    }

    @Override
    public String toString() {
        return String.format("%.3f", this.getTime()) + " " + getCustomer().getCustomerID() + " waits to be served by " + newServer.getIdentifier();
    }
}
