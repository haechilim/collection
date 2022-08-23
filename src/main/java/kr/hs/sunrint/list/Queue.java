package kr.hs.sunrint.list;

import kr.hs.sunrint.exception.IndexOutOfBoundException;
import kr.hs.sunrint.exception.NotExistElementException;

public class Queue<E> {
    LinkedList<E> linkedList = new LinkedList<>();

    public boolean enqueue(E element) {
        return linkedList.add(0, element);
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
