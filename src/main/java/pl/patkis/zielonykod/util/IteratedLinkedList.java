package pl.patkis.zielonykod.util;

public class IteratedLinkedList<T> {

    private Iterator<T> first;
    private Iterator<T> last;

    public IteratedLinkedList() {
        first = new Iterator<>();
        last = new Iterator<>();
        first.next = last;
        last.prev = first;
    }

    public Iterator<T> begin() {
        return first.next;
    }

    public Iterator<T> end() {
        return last;
    }

    public void add(T value) {
        Iterator<T> it = new Iterator<>();
        it.value = value;
        it.prev = last.prev;
        it.next = last;
        it.prev.next = it;
        it.next.prev = it;
    }

    public Iterator<T> remove(Iterator<T> it) {
        it.prev.next = it.next;
        it.next.prev = it.prev;
        return it.next;
    }

    public boolean isEmpty() {
        return first.next == last;
    }
    
}
