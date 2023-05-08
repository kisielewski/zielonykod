package pl.patkis.zielonykod.util;

public class Iterator<T> {
    
    protected T value;
    protected Iterator<T> prev;
    protected Iterator<T> next;

    public T value() {
        return value;
    }

    public Iterator<T> next() {
        return next;
    }

}
