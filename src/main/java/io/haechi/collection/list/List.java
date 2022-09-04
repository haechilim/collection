package io.haechi.collection.list;

public interface List<E> {
    void add(E element);
    void add(int index, E element);
    E remove(int index);
    boolean remove(E element);
    E set(int index, E element);
    E get(int index);
    void clear();
    int size();
    boolean contains(E element);
    int indexOf(E element);
    boolean isEmpty();
}
