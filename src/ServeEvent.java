import java.util.List;

public class ServeEvent extends Event{
    private final Server newServer;
    public ServeEvent( Customer customer, List<Server> servers, Server server) {
        super(Math.max(server.getNextAvailableTime(),customer.getArrivalTime()), customer, servers);
        this.newServer = new Server(server.getIdentifier(),false,false, this.getTime()+1);
        this.getServers().set(servers.indexOf(server),newServer);
    }

    @Override
    public String toString() {
        return String.format("%.3f", this.getTime()) + " " + getCustomer().getCustomerID() + " served by " + newServer.getIdentifier();
    }

    @Override
    public Event execute() {
        return new DoneEvent(this.getCustomer(),this.getServers(),this.newServer);
    }
}
