import java.util.List;

public class Main {
    public static void main(String[] args) {
        //System.out.println(new Shop(2));
        Shop shops = new Shop(List.of(new Server(1, true, false, 0),
                new Server(2, false,false, 1)));
        Server s = new Server(1, false, false, 2.0);
        Pair<Integer,String> pair = Pair.of(1, "one");
        System.out.println(
                //shops.find(x->x.isAvailable())
                //new Shop(2).find(x -> x.isAvailable())
                //shops.replace(s)
                //shops.replace(s).find(x -> x.isAvailable())
                //shops
                //pair.first()
                new ArriveEvent(new Customer(1, 1.0)).execute(new Shop(List.of(new Server(1,false,false,1.0)))).second()

        );
    }
}
