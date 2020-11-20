import java.util.List;
import java.util.function.Function;

public abstract class Event {
    private final double time;
    private final Customer customer;
    protected final Function<Shop,Pair<Shop,Event>> func;
    public Event(double time, Customer customer, Function<Shop,Pair<Shop,Event>> func){
        this.time = time;
        this.customer = customer;
        this.func = func;
    }

    final Pair<Shop, Event> execute(Shop shop) { // declared final to avoid overriding
        return this.func.apply(shop); // func is the Function property
    }


    public Customer getCustomer() {
        return customer;
    }

    public double getTime() {
        return time;
    }

    public abstract Event execute();
}
