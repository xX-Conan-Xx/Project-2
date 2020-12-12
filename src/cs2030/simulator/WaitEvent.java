package cs2030.simulator;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class WaitEvent extends Event{
    public WaitEvent(Customer customer, Server server, int serverId) {
        super(customer.getArrivalTime(), customer, serverId, x->{
            List<Server> newServerList = new ArrayList<>();
            newServerList.addAll(x.getServers());
            double servingTime = customer.getServiceTime().get();
            int currentLength = server.getQueueLength();
            int newLength = currentLength ;
            Server s;
            if(newLength == 0){
                s= new Server(server.getIdentifier(),false,false, server.getNextAvailableTime()+ servingTime,0);
            }
            else{
                s= new Server(server.getIdentifier(),false,true, server.getNextAvailableTime()+ servingTime,newLength);
            }
            List<Server> newnewServerList = newServerList.stream().map(a->{
                if(a.equals(server)){
                    a = s;
                    return a;
                }
                else{
                    return a;
                }
            }).collect(Collectors.toList());
            return Pair.of(new Shop(newnewServerList),
                    new ServeEvent(customer,s,server.getNextAvailableTime()
                            ,server.getIdentifier()));

        });
    }

    @Override
    public String toString() {
        return String.format("%.3f", this.getTime()) + " " + getCustomer().getCustomerID() + " waits to be served by server " + getServerId();
    }
}
