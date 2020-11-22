import java.util.ArrayList;
import java.util.List;

public class DoneEvent extends Event{
    private final Server newServer;
    public DoneEvent(Customer customer, List<Server> servers ,Server server) {
        super(server.getNextAvailableTime(), customer, x->{
            List<Server> editedServers = new ArrayList<>();
            editedServers.addAll(x.getServers());
            editedServers.set(servers.indexOf(server), new Server(server.getIdentifier(),true,server.isHasWaitingCustomer(), server.getNextAvailableTime()));
            return Pair.of(new Shop(editedServers), null);
        });
        this.newServer = new Server(server.getIdentifier(),true,server.isHasWaitingCustomer(), server.getNextAvailableTime());
    }

    @Override
    public String toString() {
        return String.format("%.3f", super.getTime()) + " " + getCustomer().getCustomerID() + " done serving by " + newServer.getIdentifier();
    }
}
