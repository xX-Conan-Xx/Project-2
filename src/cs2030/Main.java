import cs2030.simulator.Simulator;
import cs2030.simulator.RNG;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.function.Supplier;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        //System.out.println(new Shop(2));
//        Shop shops = new Shop(List.of(new Server(1, true, false, 0),
//                new Server(2, false,false, 1)));
//        Server s = new Server(1, false, false, 2.0);
//        Pair<Integer,String> pair = Pair.of(1, "one");
//        System.out.println(
//                //shops.find(x->x.isAvailable())
//                //new Shop(2).find(x -> x.isAvailable())
//                //shops.replace(s)
//                //shops.replace(s).find(x -> x.isAvailable())
//                //shops
//                //pair.first()
//                new ArriveEvent(new Customer(1, 1.0)).execute(new Shop(List.of(new Server(1,false,false,1.0)))).second()
//
//        );
        int baseSeed;
        int numOfServers;
        int queueLength;
        int numOfCustomer;
        double arrivalRate;
        double serviceRate;
        baseSeed = 2;//Integer.parseInt(args[0]);
        numOfServers = 5;//Integer.parseInt(args[1]);
        queueLength = 3;
        numOfCustomer = 20;//Integer.parseInt(args[2]);
        arrivalRate = 7.0;//Integer.parseInt(args[3]);
        serviceRate = 1.0;//Integer.parseInt(args[4]);
//        int baseSeed;
//        int numOfServers;
//        int queueLength;
//        int numOfCustomer;
//        double arrivalRate;
//        double serviceRate;
//        baseSeed = 2;//Integer.parseInt(args[0]);
//        numOfServers = 2;//Integer.parseInt(args[1]);
//        numOfCustomer = 10;//Integer.parseInt(args[2]);
//        arrivalRate = 9.0;//Integer.parseInt(args[3]);
//        serviceRate = 1.0;//Integer.parseInt(args[4]);
//        queueLength = 1;
//        int baseSeed;
//        int numOfServers;
//        int queueLength = 1;
//        int numOfCustomer;
//        double arrivalRate;
//        double serviceRate;
//        if(args.length == 5){
//            baseSeed = Integer.parseInt(args[0]);
//            numOfServers = Integer.parseInt(args[1]);
//            numOfCustomer = Integer.parseInt(args[2]);
//            arrivalRate = Double.parseDouble(args[3]);
//            serviceRate = Double.parseDouble(args[4]);
//        }else{
//            baseSeed = Integer.parseInt(args[0]);
//            numOfServers = Integer.parseInt(args[1]);
//            queueLength = Integer.parseInt(args[2]);
//            numOfCustomer = Integer.parseInt(args[3]);
//            arrivalRate = Double.parseDouble(args[4]);
//            serviceRate = Double.parseDouble(args[5]);
//        }

        RNG rng =new RNG(baseSeed, arrivalRate, serviceRate, 0);

        ArrayList<Double> arrivalEvents = new ArrayList<>();
        IntStream.range(1, numOfCustomer + 1).forEach(x->{
            if (x == 1) {
            arrivalEvents.add(0.0);
            } else {
            double rngArrival = rng.genInterArrivalTime();
            int    lastIndex  = arrivalEvents.size() - 1;
            double lastEntry  = arrivalEvents.get(lastIndex);
            arrivalEvents.add(lastEntry + rngArrival);
        }
        });
        Supplier<Double> customerServiceTime = () -> rng.genServiceTime();
        Simulator s = new Simulator(queueLength,arrivalEvents, numOfServers, customerServiceTime);
        s.run();
    }
}
