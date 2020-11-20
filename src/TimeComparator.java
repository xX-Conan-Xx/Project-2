import java.util.Comparator;

public class TimeComparator implements Comparator<Event> {

    public int compare(Event e1, Event e2)  {
        if (e1.getTime() < e2.getTime()) {
            return -1;
        } else if (e1.getTime() > e2.getTime()) {
            return 1;
        } else if (e1.getCustomer().getCustomerID() < e2.getCustomer().getCustomerID()) {
            return -1;
        } else if (e1.getCustomer().getCustomerID() > e2.getCustomer().getCustomerID()) {
            return 1;
        } else {
            System.out.println("Input Error!");
            return 0;
        }

    }
}