
public class Server {
    private final int identifier;
    private final boolean isAvailable;
    private final boolean hasWaitingCustomer;
    private final double nextAvailableTime;
    Server(int identifier, boolean isAvailable, boolean hasWaitingCustomer, double nextAvailableTime) {
        this.identifier=identifier;
        this.isAvailable=isAvailable;
        this.hasWaitingCustomer=hasWaitingCustomer;
        this.nextAvailableTime=nextAvailableTime;
    }

    public static Server createServer(int identifier, boolean isAvailable, boolean hasWaitingCustomer, double nextAvailableTime){
        return new Server(identifier,isAvailable,hasWaitingCustomer,nextAvailableTime);
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

    @Override
    public String toString() {
        if(isAvailable==true){
            return identifier + " is available";
        }else{
            if(hasWaitingCustomer == false){
                return identifier + " is busy; available at " + String.format("%.3f", nextAvailableTime);
            }else{
                return identifier + " is busy; waiting customer to be served at " + String.format("%.3f", nextAvailableTime);
            }
        }
    }
}
