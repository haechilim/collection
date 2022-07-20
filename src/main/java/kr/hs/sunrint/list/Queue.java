package kr.hs.sunrint.list;

public class Queue<E> {
    LinkedList<E> linkedList = new LinkedList<>();

    public boolean enqueue(E element) {
        return linkedList.addFirst(element);
    }

    public E dequeue() {
        return linkedList.removeLast();
    }

    public boolean isEmpty() {
        return linkedList.isEmpty();
    }
}
