package io.haechi.collection.list;

import io.haechi.collection.exception.NotExistElementException;
import io.haechi.collection.exception.IndexOutOfBoundException;

public class Queue<E> {
    LinkedList<E> linkedList = new LinkedList<>();

    public boolean enqueue(E element) {
        linkedList.add(0, element);
        return true;
    }

    public E dequeue() throws NotExistElementException {
        try {
            return linkedList.removeLast();
        } catch (IndexOutOfBoundException e) {
            throw new NotExistElementException();
        }
    }

    public boolean isEmpty() {
        return linkedList.isEmpty();
    }
}
