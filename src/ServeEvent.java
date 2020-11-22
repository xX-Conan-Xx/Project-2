import java.util.ArrayList;
import java.util.List;

public class ServeEvent extends Event{
    private final Server newServer;
    public ServeEvent( Customer customer, List<Server> servers, Server server) {
        super(Math.max(server.getNextAvailableTime(),customer.getArrivalTime()), customer, x->{
            double thisTime = Math.max(server.getNextAvailableTime(),customer.getArrivalTime());
            List<Server> editedServers = new ArrayList<>();
            editedServers.addAll(x.getServers());
            editedServers.set(servers.indexOf(server),new Server(server.getIdentifier(),false,false, thisTime+1));
            return Pair.of(new Shop(editedServers),
                    new DoneEvent(customer,x.getServers(), new Server(server.getIdentifier(),false,false, thisTime+1)));
        });
        this.newServer = new Server(server.getIdentifier(),false,false, this.getTime()+1);
    }

    @Override
    public String toString() {
        return String.format("%.3f", this.getTime()) + " " + getCustomer().getCustomerID() + " served by " + newServer.getIdentifier();
    }

}
