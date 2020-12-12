package cs2030.simulator;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.IntStream;
import java.util.function.Supplier;


public class Simulator {
    private final ArrayList<Customer> customerList;
    private final PriorityQueue<Event> eventPQ;
    private final PriorityQueue<Event> printPQ;
    private final Shop shop;
    private final Supplier<Double> customerServiceTime;
    private final int queueLength;

    public Simulator(ArrayList<Double> customerArrivals,
                     int serverCount, Supplier<Double> customerServiceTime) {

        this.customerList = createCustomerList(customerArrivals, customerServiceTime);
        this.eventPQ      = new PriorityQueue<>(new EComparator());
        this.printPQ      = new PriorityQueue<>(new EComparator());
        this.shop         = new Shop(serverCount);
        this.customerServiceTime = customerServiceTime;
        this.queueLength = 1;
    }

    public Simulator(int queueLength, ArrayList<Double> customerArrivals,
                     int serverCount, Supplier<Double> customerServiceTime) {

        this.customerList = createCustomerList(customerArrivals, customerServiceTime);
        this.eventPQ      = new PriorityQueue<>(new EComparator());
        this.printPQ      = new PriorityQueue<>(new EComparator());
        this.shop         = new Shop(serverCount);
        this.customerServiceTime = customerServiceTime;
        this.queueLength = queueLength;
    }

    public ArrayList<Customer> createCustomerList(ArrayList<Double> customerArrivals, Supplier<Double> customerServiceTime) {
        ArrayList<Customer> tmpList = new ArrayList<>();

        IntStream.range(1, customerArrivals.size() + 1)
                .forEach(x -> {
                    Customer tmpCustomer = new Customer(x, customerArrivals.get(x - 1), customerServiceTime);
                    tmpList.add(tmpCustomer);
                });

        return tmpList;
    }


    public void populateEventPQ1() {
        for (Customer customer : customerList) {
            ArriveEvent arriveEvent = new ArriveEvent(customer,queueLength);
            eventPQ.offer(arriveEvent);
        }
    }
    public void populateEventPQ2() {
        for (Customer customer : customerList) {
            ArriveEvent arriveEvent = new ArriveEvent(customer);
            eventPQ.offer(arriveEvent);
        }
    }


    public void run() {
        int totalCustomer    = 0;
        int customersLost    = 0;
        int customersServed  = 0;
        double totalWaitTime = 0.0;


        Shop updatedShop     = this.shop;

        if(queueLength == 1){
            populateEventPQ2();
        }else{
            populateEventPQ1();
        }

        while (eventPQ.peek() != null) {

            Event pollEvent = this.eventPQ.poll();
            printPQ.offer(pollEvent);

            if (pollEvent instanceof ArriveEvent) {

                totalCustomer++;

                Pair<Shop,Event> result = pollEvent.execute(updatedShop);

                updatedShop = result.first();
                eventPQ.offer(result.second());

            } else if (pollEvent instanceof WaitEvent) {

                Pair<Shop,Event> result = pollEvent.execute(updatedShop);

                updatedShop = result.first();
                eventPQ.offer(result.second());

            } else if (pollEvent instanceof ServeEvent) {

                customersServed++;

                totalWaitTime += pollEvent.getTime() -
                        pollEvent.getCustomer().getArrivalTime();

                Pair<Shop,Event> result = pollEvent.execute(updatedShop);

                updatedShop = result.first();
                eventPQ.offer(result.second());

            } else if (pollEvent instanceof DoneEvent) {

                Pair<Shop,Event> result = pollEvent.execute(updatedShop);

                updatedShop = result.first();

            } else if (pollEvent instanceof LeaveEvent) {
                customersLost++;
            }
        }
        printEvents(printPQ);
        printStats(totalWaitTime, customersServed, customersLost);
    }

    public void printEvents(PriorityQueue<Event> printPQ) {
        for (Event e : printPQ) {
            System.out.println(e);
        }
    }

    public void printStats(double totalTime, int served, int lost) {

        double averageTime = 0;

        if (served == 0) {
            averageTime = 0;
        } else {
            averageTime = totalTime / served;
        }
        System.out.println(String.format("[%.3f %d %d]",
                averageTime,
                served,
                lost));
    }
}