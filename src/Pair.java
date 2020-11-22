public class Pair <T,U> {
    private final T first;
    private final U second;
    public Pair(T t, U u){
        this.first = t;
        this.second = u;
    }

    public T first() {
        return first;
    }

    public U second() {
        return second;
    }

    public static <T,U> Pair <T,U> of(T t, U u){
        return new Pair<T,U>(t,u);
    }
}
