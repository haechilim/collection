package kr.hs.sunrint.list;

import kr.hs.sunrint.exception.IndexOutOfBoundException;
import kr.hs.sunrint.exception.NotExistElementException;

public class Stack<E> {
    ArrayList<E> arrayList = new ArrayList<>();

    public boolean push(E element) {
        return arrayList.add(element);
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
