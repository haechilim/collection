package kr.hs.sunrint.list;

public abstract class List<E> {
    public abstract boolean add(E element);
    public abstract boolean add(int index, E element);
    public abstract E remove(int index);
    public abstract boolean remove(E element);
    public abstract E set(int index, E element);
    public abstract E get(int index);
    public abstract void clear();
    public abstract int size();
    public abstract boolean contains(E element);
    public abstract int indexOf(E element);
    public abstract boolean isEmpty();
}
