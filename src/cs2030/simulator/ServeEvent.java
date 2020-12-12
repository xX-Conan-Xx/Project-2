package cs2030.simulator;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ServeEvent extends Event{
    public ServeEvent( Customer customer, Server server,double servingTime, int serverId) {
        super(servingTime, customer,serverId, x->{
            List<Server> newServerList = new ArrayList<>();
            newServerList.addAll(x.getServers());
            Server s;
            int currentLength = server.getQueueLength();
            int newLength = currentLength - 1;
            if(currentLength>0){
                s= new Server(server.getIdentifier(),false,server.isHasWaitingCustomer(), server.getNextAvailableTime(),newLength);
            }
            else{
                s= new Server(server.getIdentifier(),false,server.isHasWaitingCustomer(), server.getNextAvailableTime(),0);
            }
            List<Server> newnewServerList = newServerList.stream().map(a->{
                if(a.equals(server)){
                    if(currentLength>0){
                        a = new Server(server.getIdentifier(),false,a.isHasWaitingCustomer(), Math.max(a.getNextAvailableTime(),server.getNextAvailableTime()),newLength);
                    }
                    else{
                        a = new Server(server.getIdentifier(),false,a.isHasWaitingCustomer(), Math.max(a.getNextAvailableTime(),server.getNextAvailableTime()),0);
                    }
                    return a;
                }
                else{
                    return a;
                }
            }).collect(Collectors.toList());
            return Pair.of(new Shop(newnewServerList),
                    new DoneEvent(customer,
                            s,
                            server.getIdentifier()));
        });
    }

    @Override
    public String toString() {
        return String.format("%.3f", this.getTime()) + " " + getCustomer().getCustomerID() + " served by server " + getServerId();
    }

}
