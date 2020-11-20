class Statistics {
    private double waitingTime = 0;
    private int numLeft = 0;
    private int numServed = 0;

    public Statistics(){}

    public void increaseServed() {
        numServed++;
    }

    public void increaseWaitingTime(double time) {
        waitingTime += time;
    }

    public void increaseLeft() {
        numLeft++;
    }

    public String toString() {
        double x = waitingTime / numServed;
        return '[' + String.format("%.3f",x) + ' ' + numServed + ' ' + numLeft + ']';
    }
}