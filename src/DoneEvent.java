import java.util.List;

public class DoneEvent extends Event{
    private final Server newServer;
    public DoneEvent(Customer customer, List<Server> servers ,Server server) {
        super(server.getNextAvailableTime(), customer, servers);
        this.newServer = new Server(server.getIdentifier(),true,server.isHasWaitingCustomer(), server.getNextAvailableTime());
        this.getServers().set(servers.indexOf(server), newServer);
    }

    @Override
    public String toString() {
        return String.format("%.3f", super.getTime()) + " " + getCustomer().getCustomerID() + " done serving by " + newServer.getIdentifier();
    }

    @Override
    public Event execute() {
        return null;
    }
}
