package kr.hs.sunrint.list;

import kr.hs.sunrint.exception.IndexOutOfBoundException;
import kr.hs.sunrint.exception.NotExistElementException;

public class LinkedList<E> {
    private Node<E> head;
    private Node<E> tail;
    private int size = 0;

    public boolean add(E element) {
        Node<E> node = new Node(element);

        if(isEmpty()) head = node;
        else tail.next = node;

        tail = node;
        size++;

        return true;
    }

    public boolean add(int index, E element) throws IndexOutOfBoundException {
        if(index < 0 || index >= size) throw new IndexOutOfBoundException();

        Node<E> node = new Node(element, search(index));

        try {
            Node<E> preNode = search(index - 1);
            preNode.next = node;
        }
        catch (IndexOutOfBoundException e) {
            head = node;
        }

        size++;

        return true;
    }

    public boolean addFirst(E element) {
        try {
            if(size == 0) add(element);
            else add(0, element);
        } catch (IndexOutOfBoundException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    public E removeFirst() {
        return remove(0);
    }

    public E removeLast() {
        return remove(size - 1);
    }

    public E remove(int index) throws IndexOutOfBoundException {
        if(index < 0 || index >= size) throw new IndexOutOfBoundException();

        Node<E> preNode = index - 1 < 0 ? null : search(index - 1);
        Node<E> nextNode = index + 1 >= size ? null : search(index + 1);
        Node<E> node = search(index);

        if(preNode == null) head = nextNode;
        else if(nextNode == null) preNode.next =  tail;
        else preNode.next = nextNode;

        size--;

        return node.data;
    }

    public boolean remove(E element) {
        try {
            int index = getIndexByElement(element);
            remove(index);
        } catch (NotExistElementException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    public E set(int index, E element) throws IndexOutOfBoundException {
        if(index < 0 || index >= size) throw new IndexOutOfBoundException();

        Node<E> node = search(index);

        node.data = element;

        return node.data;
    }

    public E get(int index) {
        Node<E> node = search(index);

        return node.data;
    }

    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size <= 0;
    }

    public boolean contains(E element) {
        try {
            getIndexByElement(element);
        } catch (NotExistElementException e) {
            return false;
        }

        return true;
    }

    public int indexOf(E element) throws NotExistElementException {
        try {
            return getIndexByElement(element);
        } catch (NotExistElementException e) {
          throw e;
        }
    }

    private int getIndexByElement(E element) throws NotExistElementException {
        Node<E> current = head;

        for(int i = 0; i < size; i++) {
            if(current.data == element) return i;
            current = current.next;
        }

        throw new NotExistElementException();
    }

    private Node search(int index) throws IndexOutOfBoundException {
        if(index < 0 || index >= size) throw new IndexOutOfBoundException();

        Node<E> current = head;

        for(int i = 0; i < index; i++) {
            current = current.next;
        }

        return current;
    }

    private class Node<E> {
        private E data;
        private Node next;

        public Node(E data) {
            this.data = data;
        }

        public Node(E data, Node next) {
            this.data = data;
            this.next = next;
        }
    }
}
