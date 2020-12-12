package cs2030.simulator;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class DoneEvent extends Event{
    private final Server newServer;
    public DoneEvent(Customer customer, Server server, int serverId) {
//        super(server.getNextAvailableTime(), customer, x->{
//            Server oldServer = x.getServers().get(serverId - 1);
//            if (oldServer.isHasWaitingCustomer()) {
//                Server updatedServer = new Server(oldServer.getIdentifier(),
//                        false,
//                        false,
//                        oldServer.getNextAvailableTime(),
//                        oldServer.getToServe());
//
//                DoneEvent newDE = new DoneEvent(customer,
//                        updatedServer,
//                        serverId);
//
//                return Pair.of(x.replace(updatedServer), newDE);
//            } else {
//                Server updatedServer = new Server(oldServer.getIdentifier());
//
//                DoneEvent newDE = new DoneEvent(customer,
//                        updatedServer,
//                        serverId);
//
//                return Pair.of(x.replace(updatedServer), newDE);
//            }
//        });
        super(server.getNextAvailableTime(), customer, x->{
            List<Server> newServerList = new ArrayList<>();
            newServerList.addAll(x.getServers());
            Server oldServer = x.getServers().get(server.getIdentifier()-1);
            if (oldServer.isHasWaitingCustomer()) {
                List<Server> newnewServerList = newServerList.stream().map(a->{
                    if(a.equals(server)){
                        a = new Server(server.getIdentifier(),false,a.isHasWaitingCustomer(), a.getNextAvailableTime(),a.getQueueLength());
                        return a;
                    }
                    else{
                        return a;
                    }
                }).collect(Collectors.toList());
                new Shop(newnewServerList);
                return Pair.of(new Shop(newnewServerList), null);
            } else {
                List<Server> newnewServerList = newServerList.stream().map(a->{
                    if(a.equals(server)){
                        a = new Server(server.getIdentifier(),true,a.isHasWaitingCustomer(), a.getNextAvailableTime(),a.getQueueLength());
                        return a;
                    }
                    else{
                        return a;
                    }
                }).collect(Collectors.toList());
                new Shop(newnewServerList);
                return Pair.of(new Shop(newnewServerList), null);
            }
        });
        this.newServer = new Server(server.getIdentifier(),true,server.isHasWaitingCustomer(), server.getNextAvailableTime());
    }

    @Override
    public String toString() {
        return String.format("%.3f", super.getTime()) + " " + getCustomer().getCustomerID() + " done serving by server " + newServer.getIdentifier();
    }
}
