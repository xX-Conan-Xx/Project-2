package cs2030.simulator;

import java.util.PriorityQueue;

public class Server {
    private final int identifier;
    private final boolean isAvailable;
    private final boolean hasWaitingCustomer;
    private final double nextAvailableTime;
    private final int queueLength;

    Server(int identifier, boolean isAvailable, boolean hasWaitingCustomer, double nextAvailableTime,int currentLength){
        this.identifier = identifier;
        this.isAvailable = isAvailable;
        this.hasWaitingCustomer = hasWaitingCustomer;
        this.nextAvailableTime = nextAvailableTime;
        this.queueLength = currentLength;

    }
    Server(int identifier, boolean isAvailable, boolean hasWaitingCustomer, double nextAvailableTime){
        this.identifier = identifier;
        this.isAvailable = isAvailable;
        this.hasWaitingCustomer = hasWaitingCustomer;
        this.nextAvailableTime = nextAvailableTime;
        if(hasWaitingCustomer){
            this.queueLength = 1;}
        else{
            this.queueLength = 0;
        }


    }

    public static Server createServer(int identifier, boolean isAvailable, boolean hasWaitingCustomer, double nextAvailableTime){
        return new Server(identifier,isAvailable,hasWaitingCustomer,nextAvailableTime);
    }

    public int getQueueLength() {
        return queueLength;
    }


    public int getIdentifier() {
        return identifier;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public boolean isHasWaitingCustomer() {
        return hasWaitingCustomer;
    }

    public double getNextAvailableTime() {
        return nextAvailableTime;
    }

    public boolean hasVacancy(int length){
         return this.queueLength<length;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        } else if (other instanceof Server) {
            Server otherServer = (Server)other;
            return this.getIdentifier() == otherServer.getIdentifier();
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        if(isAvailable){
            return identifier + " is available";
        }else{
            if(!hasWaitingCustomer){
                return identifier + " is busy; available at " + String.format("%.3f", nextAvailableTime);
            }else{
                return identifier + " is busy; waiting customer to be served at " + String.format("%.3f", nextAvailableTime);
            }
        }
    }
}
