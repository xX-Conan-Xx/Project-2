package cs2030.simulator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Shop {
    private final List<Server> serverList;
    public Shop(int size){
        List<Server> tmpList = new ArrayList<>();

        IntStream.range(1, size + 1)
                .forEach(x -> {
                    Server tmpServer = new Server(x,true,false,0);
                    tmpList.add(tmpServer);
                });
        this.serverList = tmpList;
    }

    public Optional<Server> find(Function<Server,Boolean> f){
        return this.getServers()
                .stream()
                .filter(a->f.apply(a))
                .findFirst();
    }

    public List<Server> getServers() {
        return serverList;
    }

    public Shop replace(Server newServer){
//        Server serverToBeReplaced = serverList.stream().findFirst().filter(x->x.isAvailable()).get();
//        List<Server> serverListToBeReplaced = new ArrayList<>();
//        serverListToBeReplaced.addAll(serverList);
//        List<Server> serverListAfterrepalcing = serverListToBeReplaced.stream().map(x->{
//            if(x.getIdentifier()==serverToBeReplaced.getIdentifier()){
//                return newServer;
//            }else{
//                return x;
//            }
//        }).collect(Collectors.toList());
//        return new Shop(serverListAfterrepalcing);
        List<Server> tmpList = new ArrayList<>();
        tmpList.addAll(this.getServers());
        tmpList.set(newServer.getIdentifier() - 1, newServer);

        return new Shop(tmpList);
    }

    public Shop(List<Server> serverList){
        this.serverList = serverList;
    }
    public String toString(){
        return serverList.toString();
    }

}
