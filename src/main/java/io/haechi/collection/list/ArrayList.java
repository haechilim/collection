package io.haechi.collection.list;

import io.haechi.collection.exception.IndexOutOfBoundException;

import java.util.Arrays;

public class ArrayList<E> implements List<E> {
    private Object[] data;
    private int current = 0;
    private int initialSize = 10;
    private int increment = 2;

    public ArrayList() {
        init();
    }

    public ArrayList(int initialSize) {
        this.initialSize = initialSize;

        init();
    }

    public ArrayList(int initialSize, int increment) {
        this.initialSize = initialSize;
        this.increment = increment;

        init();
    }

    private void init() {
        data = new Object[initialSize];
    }

    @Override
    public void add(E element) {
        if(needsExtend()) extend();

        data[current++] = element;
    }

    @Override
    public void add(int index, E element) throws IndexOutOfBoundException {
        if(index > current) throw new IndexOutOfBoundException();

        if(current == index) add(element);
        else {
            if (needsExtend()) extend();
            System.arraycopy(data, index,
                    data, index + 1, current - index);
            data[index] = element;
            current++;
        }
    }

    @Override
    public E remove(int index) throws IndexOutOfBoundException {
        if(index >= current) throw new IndexOutOfBoundsException();

        E result = (E) data[index];

        System.arraycopy(data, index + 1, data, index, current - (index + 1));

        current--;

        return result;
    }

    @Override
    public boolean remove(E element) {
        for(int i = 0; i < current; i++) {
            if(data[i] == element) {
                remove(i);
                return true;
            }
        }

        return false;
    }

    @Override
    public E set(int index, E element) throws IndexOutOfBoundsException {
        if(index >= current) throw new IndexOutOfBoundsException();

        data[index] = element;

        return element;
    }

    @Override
    public E get(int index) throws IndexOutOfBoundsException {
        if(index >= current) throw new IndexOutOfBoundsException();

        return (E)data[index];
    }

    @Override
    public void clear() {
        data = new String[0];
        current = 0;
    }

    @Override
    public int size() {
        return current;
    }

    @Override
    public boolean contains(E element) {
        for(int i = 0; i < current; i++) {
            if(data[i] == element) return true;
        }

        return false;
    }

    @Override
    public int indexOf(E element) {
        for(int i = 0; i < current; i++) {
            if(data[i] == element) return i;
        }

        return -1;
    }

    @Override
    public boolean isEmpty() {
        return size() <= 0;
    }

    private boolean needsExtend() {
        return current == data.length;
    }

    private void extend() {
        Object[] newData = new Object[current * increment];
        System.arraycopy(data, 0, newData, 0, current);
        data = newData;
    }

    @Override
    public String toString() {
        return "ArrayList{" +
                "data=" + Arrays.toString(data) +
                ", current=" + current +
                '}';
    }
}
