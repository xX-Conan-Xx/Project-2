package cs2030.simulator;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ArriveEvent extends Event{


    public ArriveEvent(Customer customer, int queueLength) {
        super(customer.getArrivalTime(), customer, x -> {
            if(!x.find(y->y.isAvailable()).equals(Optional.empty())){
                Server server = x.getServers().stream().filter(z->z.isAvailable()).findFirst().get();
                List<Server> newServerList = new ArrayList<>();
                newServerList.addAll(x.getServers());
                double servingTime = customer.getServiceTime().get();
                int currentLength = server.getQueueLength();
                Server s;
                if(currentLength == 0){
                    s= new Server(server.getIdentifier(),false,false, customer.getArrivalTime()+ servingTime,0);
                }
                else{
                    s= new Server(server.getIdentifier(),false,true, customer.getArrivalTime()+ servingTime,currentLength);
                }
                return Pair.of(x,new ServeEvent(customer,s
                        ,customer.getArrivalTime(),x.getServers().stream().filter(y->y.isAvailable()).findFirst().get().getIdentifier()));
            }
            if(!x.find(z->z.hasVacancy(queueLength)).equals(Optional.empty())){
                int serverID = x.getServers().stream()
                        .filter(z->z.hasVacancy(queueLength))
                        .findFirst()
                        .get().getIdentifier();
                boolean serverIsAvailable = x.getServers().get(serverID-1).isAvailable();
                boolean serverHasWaitingCustomer = x.getServers().get(serverID-1).isHasWaitingCustomer();
                double serverNextAvailableTime = x.getServers().get(serverID-1).getNextAvailableTime();
                int serverCurrentLength = x.getServers().get(serverID-1).getQueueLength();
                Server newServer;
                if(serverHasWaitingCustomer){
                    newServer = new Server(serverID,serverIsAvailable,serverHasWaitingCustomer,serverNextAvailableTime,serverCurrentLength+1);
                }
                else{
                    newServer = new Server(serverID,serverIsAvailable,true,serverNextAvailableTime,serverCurrentLength+1);

                }
                List<Server> newServerList = new ArrayList<>();
                newServerList.addAll(x.getServers());
                x = x.replace(newServer);
                Event e = new WaitEvent(customer,newServer,serverID);
                return Pair.of(x,e);
            }

            return Pair.of(x,new LeaveEvent(customer));



        });
    }
    public ArriveEvent(Customer customer) {
        super(customer.getArrivalTime(), customer, x -> {
            if(!x.find(y->y.isAvailable()).equals(Optional.empty())){
                Server server = x.getServers().stream().filter(z->z.isAvailable()).findFirst().get();
                List<Server> newServerList = new ArrayList<>();
                newServerList.addAll(x.getServers());
                double servingTime = customer.getServiceTime().get();
                int currentLength = server.getQueueLength();
                Server s;
                if(currentLength == 0){
                    s= new Server(server.getIdentifier(),false,false, customer.getArrivalTime()+ servingTime,0);
                }
                else{
                    s= new Server(server.getIdentifier(),false,true, customer.getArrivalTime()+ servingTime,currentLength);
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
                return Pair.of(x,new ServeEvent(customer,s
                        ,customer.getArrivalTime(),x.getServers().stream().filter(y->y.isAvailable()).findFirst().get().getIdentifier()));
            }
            if(!x.find(z->z.hasVacancy(1)).equals(Optional.empty())){
                int serverID = x.getServers().stream()
                        .filter(z->z.hasVacancy(1))
                        .findFirst()
                        .get().getIdentifier();
                boolean serverIsAvailable = x.getServers().get(serverID-1).isAvailable();
                boolean serverHasWaitingCustomer = x.getServers().get(serverID-1).isHasWaitingCustomer();
                double serverNextAvailableTime = x.getServers().get(serverID-1).getNextAvailableTime();
                int serverCurrentLength = x.getServers().get(serverID-1).getQueueLength();
                Server newServer;
                if(serverHasWaitingCustomer==true){
                    newServer = new Server(serverID,serverIsAvailable,serverHasWaitingCustomer,serverNextAvailableTime,serverCurrentLength+1);
                }
                else{
                    newServer = new Server(serverID,serverIsAvailable,serverHasWaitingCustomer,serverNextAvailableTime,serverCurrentLength+1);

                }
                List<Server> newServerList = new ArrayList<>();
                newServerList.addAll(x.getServers());
                x = x.replace(newServer);
                Event e = new WaitEvent(customer,newServer,serverID);
                return Pair.of(x,e);
            }

            return Pair.of(x,new LeaveEvent(customer));
        });
    }
    @Override
    public String toString() {
        return String.format("%.3f", this.getTime()) +" " + this.getCustomer().getCustomerID() + " arrives";
    }
}
