package kr.hs.sunrint.list;

import kr.hs.sunrint.exception.NotExistElementException;

public class Stack<E> {
    ArrayList<E> arrayList = new ArrayList<>();

    public boolean push(E element) {
        return arrayList.add(element);
    }

    public E pop() throws NotExistElementException {
        int size = arrayList.size();

        if(size < 0) throw new NotExistElementException();

        E element = arrayList.get(size - 1);

        arrayList.remove(element);

        return element;
    }

    public boolean isEmpty() {
        return arrayList.isEmpty();
    }
}
