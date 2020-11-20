public class Pair <T,U> {
    public T first;
    public U second;
    public Pair(T t, U u){
        this.first = t;
        this.second = u;
    }
    public static <T,U> Pair of(T t,U u){
        return new Pair(t,u);
    }
}
