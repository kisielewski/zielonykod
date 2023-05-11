package pl.patkis.zielonykod.util;

import java.util.List;

public class IteratedLinkedList<T> {

    private Iterator<T> first;
    private Iterator<T> last;

    public IteratedLinkedList() {
        first = new Iterator<>();
        last = new Iterator<>();
        first.next = last;
        last.prev = first;
    }

    public IteratedLinkedList(List<T> values) {
        Iterator<T> prev = new Iterator<>();
        first = prev;
        for (T value : values) {
            Iterator<T> current = new Iterator<>();
            current.value = value;
            current.prev = prev;
            prev.next = current;
            prev = current;
        }
        last = new Iterator<>();
        last.prev = prev;
        prev.next = last;
    }

    public Iterator<T> begin() {
        return first.next;
    }

    public Iterator<T> end() {
        return last;
    }

    public Iterator<T> remove(Iterator<T> it) {
        it.prev.next = it.next;
        it.next.prev = it.prev;
        return it.next;
    }
    
}
