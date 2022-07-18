package kr.hs.sunrint.list;

import java.util.Arrays;

public class ArrayList {
    private String[] arrayList = new String[1];
    private int size = 0;

    public boolean add(String element) {
        if(isExtend()) extend();

        arrayList[size++] = element;

        return true;
    }

    public boolean add(int index, String element) throws IndexOutOfBoundsException {
        if(index >= size) throw new IndexOutOfBoundsException();

        String[] temp = new String[size];

        for(int i = 0; i < temp.length; i++) {
            temp[i] = arrayList[i];
        }

        arrayList[index] = element;

        for(int i = index; i < size; i++) {
            if(arrayList.length <= i + 1) extend();
            arrayList[i + 1] = temp[i];
        }

        size++;

        return true;
    }

    public boolean remove(int index) throws IndexOutOfBoundsException {
        if(index >= size) throw new IndexOutOfBoundsException();

        for(int i = index; i < size; i++ ) {
            arrayList[i] = i + 1 < size ? arrayList[i + 1] : null;
        }

        size--;

        return true;
    }

    public boolean remove(String element) {
        for(int i = 0; i < size; i++) {
            if(arrayList[i] == element) return remove(i);
        }

        return false;
    }

    public String set(int index, String element) throws IndexOutOfBoundsException {
        if(index >= size) throw new IndexOutOfBoundsException();

        arrayList[index] = element;

        return element;
    }

    public String get(int index) throws IndexOutOfBoundsException {
        if(index >= size) throw new IndexOutOfBoundsException();

        return arrayList[index];
    }

    public void clear() {
        arrayList = new String[0];
        size = 0;
    }

    public int size() {
        return size;
    }

    public boolean contains(String element) {
        for(int i = 0; i < size; i++) {
            if(arrayList[i] == element) return true;
        }

        return false;
    }

    public int indexOf(String element) {
        for(int i = 0; i < size; i++) {
            if(arrayList[i] == element) return i;
        }

        return -1;
    }

    private boolean isExtend() {
        return size == arrayList.length;
    }

    private void extend() {
        String[] temp = arrayList;
        arrayList = new String[size * 2];

        copyData(temp);
    }

    private void copyData(String[] array) {
        if(arrayList.length < array.length) return;

        for(int i = 0; i < array.length; i++) {
            arrayList[i] = array[i];
        }
    }

    public class IndexOutOfBoundsException extends RuntimeException {
        public IndexOutOfBoundsException() {

        }
    }

    @Override
    public String toString() {
        return "ArrayList{" +
                "arrayList=" + Arrays.toString(arrayList) +
                ", size=" + size +
                '}';
    }
}
