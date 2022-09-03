package io.haechi.collection.list;

import io.haechi.collection.exception.IndexOutOfBoundException;
import io.haechi.collection.exception.NotExistElementException;

public class LinkedList<E> extends List<E> {
    private Node<E> head;
    private Node<E> tail;
    private int size = 0;

    @Override
    public void add(E element) {
        Node<E> node = new Node(element);

        if(isEmpty()) head = node;
        else {
            tail.next = node;
            node.previous = tail;
        }

        tail = node;
        size++;
    }

    @Override
    public void add(int index, E element) throws IndexOutOfBoundException {
        if(index < 0 || index >= size) throw new IndexOutOfBoundException();
        if(isEmpty()) add(element);
        else {
            Node<E> next = search(index);
            Node<E> previous = next.previous;

            insert(new Node<E>(element), previous, next);

            size++;
        }
    }

    private void insert(Node<E> element, Node<E> previous, Node<E> next) {
        if(previous == null) head = element;
        else {
            previous.next = element;
            element.previous = previous;
        }

        if(next == null) tail = element;
        else {
            element.next = next;
            next.previous = element;
        }
    }

    public E removeLast() {
        return remove(size - 1);
    }

    @Override
    public E remove(int index) throws IndexOutOfBoundException {
        if(index < 0 || index >= size) throw new IndexOutOfBoundException();

        Node<E> node = search(index);
        Node<E> preNode = node.previous == null ? null : node.previous;
        Node<E> nextNode = node.next == null ? null : node.next;

        if(preNode == null) head = nextNode;
        else if(nextNode == null) {
            preNode.next = null;
            tail = preNode;
        }
        else {
            preNode.next = nextNode;
            nextNode.previous = preNode;
        }

        size--;

        return node.data;
    }

    @Override
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

    @Override
    public E set(int index, E element) throws IndexOutOfBoundException {
        if(index < 0 || index >= size) throw new IndexOutOfBoundException();

        Node<E> node = search(index);

        node.data = element;

        return node.data;
    }

    @Override
    public E get(int index) {
        Node<E> node = search(index);

        return node.data;
    }

    @Override
    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size <= 0;
    }

    @Override
    public boolean contains(E element) {
        try {
            getIndexByElement(element);
        } catch (NotExistElementException e) {
            return false;
        }

        return true;
    }

    @Override
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

    private Node<E> search(int index) throws IndexOutOfBoundException {
        if(index < 0 || index >= size) throw new IndexOutOfBoundException();

        boolean reverse = index > size / 2;

        Node<E> current = reverse ? tail : head;

        if(!reverse) {
            for (int i = 0; i < index; i++) {
                current = current.next;
            }
        }
        else {
            for (int i = size - 1; i > index; i--) {
                current = current.previous;
            }
        }

        return current;
    }

    private class Node<E> {
        private E data;
        private Node previous;
        private Node next;

        public Node(E data) {
            this.data = data;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "data=" + data +
                    ", previous=" + previous +
                    ", next=" + next +
                    '}';
        }
    }
}
