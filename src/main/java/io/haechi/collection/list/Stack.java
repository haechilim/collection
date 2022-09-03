package io.haechi.collection.list;

import io.haechi.collection.exception.NotExistElementException;
import io.haechi.collection.exception.IndexOutOfBoundException;

public class Stack<E> {
    ArrayList<E> arrayList = new ArrayList<>();

    public boolean push(E element) {
        arrayList.add(element);
        return true;
    }

    public E pop() throws NotExistElementException {
        try {
            int index = arrayList.size() - 1;
            E element = arrayList.get(index);
            arrayList.remove(index);
            return element;
        } catch (IndexOutOfBoundException e) {
            throw new NotExistElementException();
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new NotExistElementException();
        }
    }

    public boolean isEmpty() {
        return arrayList.isEmpty();
    }
}
