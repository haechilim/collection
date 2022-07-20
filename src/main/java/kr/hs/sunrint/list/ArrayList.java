package kr.hs.sunrint.list;

import kr.hs.sunrint.exception.IndexOutOfBoundException;

import java.util.Arrays;

public class ArrayList<E> {
    private Object[] data;
    private int current = 0;
    private int initialSize = 1024;

    public ArrayList() {
        init();
    }

    public ArrayList(int initialSize) {
        this.initialSize = initialSize;

        init();
    }

    private void init() {
        data = new Object[initialSize];
    }

    public boolean add(E element) {
        if(needsExtend()) extend();

        data[current++] = element;

        return true;
    }

    public boolean add(int index, E element) throws IndexOutOfBoundException {
        if(index >= current) throw new IndexOutOfBoundException();

        Object[] temp = new Object[current];

        for(int i = 0; i < temp.length; i++) {
            temp[i] = data[i];
        }

        data[index] = element;

        for(int i = index; i < current; i++) {
            if(data.length <= i + 1) extend();
            data[i + 1] = temp[i];
        }

        current++;

        return true;
    }

    public boolean remove(int index) throws IndexOutOfBoundsException {
        if(index >= current) throw new IndexOutOfBoundsException();

        for(int i = index; i < current; i++ ) {
            data[i] = i + 1 < current ? data[i + 1] : null;
        }

        current--;

        return true;
    }

    public boolean remove(E element) {
        for(int i = 0; i < current; i++) {
            if(data[i] == element) return remove(i);
        }

        return false;
    }

    public E set(int index, E element) throws IndexOutOfBoundsException {
        if(index >= current) throw new IndexOutOfBoundsException();

        data[index] = element;

        return element;
    }

    public E get(int index) throws IndexOutOfBoundsException {
        if(index >= current) throw new IndexOutOfBoundsException();

        return (E)data[index];
    }

    public void clear() {
        data = new String[0];
        current = 0;
    }

    public int size() {
        return current;
    }

    public boolean contains(E element) {
        for(int i = 0; i < current; i++) {
            if(data[i] == element) return true;
        }

        return false;
    }

    public int indexOf(E element) {
        for(int i = 0; i < current; i++) {
            if(data[i] == element) return i;
        }

        return -1;
    }

    public boolean isEmpty() {
        return size() <= 0;
    }

    private boolean needsExtend() {
        return current == data.length;
    }

    private void extend() {
        Object[] temp = data;
        data = new String[current * 2];

        copyData(temp);
    }

    private void copyData(Object[] array) {
        if(data.length < array.length) return;

        for(int i = 0; i < array.length; i++) {
            data[i] = array[i];
        }
    }

    @Override
    public String toString() {
        return "ArrayList{" +
                "data=" + Arrays.toString(data) +
                ", current=" + current +
                '}';
    }
}
