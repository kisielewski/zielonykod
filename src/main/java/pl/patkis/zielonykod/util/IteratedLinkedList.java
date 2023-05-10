package pl.patkis.zielonykod.util;

import java.util.List;

public class IteratedLinkedList<T> {

    private Iterator<T> first;

    public IteratedLinkedList(List<T> list) {
        if (list.size() == 0) return;
        Iterator<T> it = new Iterator<>();
        first = it;
        for (T item : list) {
            it.value = item;
            Iterator<T> next = new Iterator<>();
            it.next = next;
            next.prev = it;
            it = next;
        }
        it.prev.next = null;
    }

    public Iterator<T> begin() {
        return first;
    }

    public Iterator<T> remove(Iterator<T> it) {
        if (it.prev == null) {
            first = it.next;
        } else {
            it.prev.next = it.next;
        }
        if (it.next != null) {
            it.next.prev = it.prev;
        }
        return it.next;
    }
    
}
