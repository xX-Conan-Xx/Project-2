import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Shop {
    private final List<Server> serverList;
    public Shop(int size){
        //new ArrayList<Server>(size).stream().map(Server::createServer())
        List<Integer> list = Arrays.asList(1,2,3,4,5,6,7,8);
        this.serverList = list.stream().filter(x->x<=size).map(x->Server.createServer(x,true, false, 0)).collect(Collectors.toList());
    }
    public Optional<Server> find(Function<Server,Boolean> f){
        return serverList.stream().findFirst().filter(x->f.apply(x));
    }

    public List<Server> getServers() {
        return serverList;
    }

    public Shop replace(Server newServer){
        Server serverToBeReplaced = serverList.stream().findFirst().filter(x->x.isAvailable()).get();
        List<Server> serverListToBeReplaced = new ArrayList<>();
        serverListToBeReplaced.addAll(serverList);
        List<Server> serverListAfterrepalcing = serverListToBeReplaced.stream().map(x->{
            if(x.getIdentifier()==serverToBeReplaced.getIdentifier()){
                return newServer;
            }else{
                return x;
            }
        }).collect(Collectors.toList());
        return new Shop(serverListAfterrepalcing);
    }

    public Shop(List<Server> serverList){
        this.serverList = serverList;
    }
    public String toString(){
        return serverList.toString();
    }

}
