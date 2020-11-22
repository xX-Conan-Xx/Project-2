import java.util.List;
import java.util.function.Function;

public abstract class Event {
    private final double time;
    private final Customer customer;
    private final Function<Shop,Pair<Shop,Event>> func;
    public Event(double time, Customer customer, Function<Shop,Pair<Shop,Event>> func){
        this.time = time;
        this.customer = customer;
        this.func = func;
    }


    public Customer getCustomer() {
        return customer;
    }

    public double getTime() {
        return time;
    }

    final Pair<Shop, Event> execute(Shop shop) { // declared final to avoid overriding
        return this.func.apply(shop); // func is the Function property
    }
}
