package kr.hs.sunrint.list;

import kr.hs.sunrint.exception.IndexOutOfBoundException;
import kr.hs.sunrint.exception.NotExistElementException;

public class LinkedList {
    private Node head;
    private Node tail;
    private int size = 0;

    public boolean add(String element) {
        Node node = new Node(element);

        if(tail == null) head = node;
        else tail.next = node;

        tail = node;
        size++;

        return true;
    }

    public boolean add(int index, String element) throws IndexOutOfBoundException {
        if(index < 0 || index >= size) throw new IndexOutOfBoundException();

        Node preNode = index - 1 < 0 ? null : search(index - 1);
        Node nextNode = search(index);
        Node node = new Node(element, nextNode);

        if(preNode != null) preNode.next = node;
        else head = node;

        size++;

        return true;
    }

    public boolean addFirst(String element) {
        try {
            add(0, element);
        } catch (IndexOutOfBoundException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    public String removeFirst() {
        return remove(0);
    }

    public String removeLast() {
        return remove(size - 1);
    }

    public String remove(int index) throws IndexOutOfBoundException {
        if(index < 0 || index >= size) throw new IndexOutOfBoundException();

        Node preNode = index - 1 < 0 ? null : search(index - 1);
        Node nextNode = index + 1 >= size ? null : search(index + 1);
        Node node = search(index);

        if(preNode == null) head = nextNode;
        else if(nextNode == null) preNode.next =  tail;
        else preNode.next = nextNode;

        size--;

        return node.data;
    }

    public boolean remove(String element) {
        try {
            int index = getIndexByElement(element);
            remove(index);
        } catch (NotExistElementException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    public String set(int index, String element) throws IndexOutOfBoundException {
        if(index < 0 || index >= size) throw new IndexOutOfBoundException();

        Node node = search(index);

        node.data = element;

        return node.data;
    }

    public String get(int index) {
        Node node = search(index);

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

    public boolean contains(String element) {
        try {
            getIndexByElement(element);
        } catch (NotExistElementException e) {
            return false;
        }

        return true;
    }

    public int indexOf(String element) throws NotExistElementException {
        try {
            return getIndexByElement(element);
        } catch (NotExistElementException e) {
          throw e;
        }
    }

    private int getIndexByElement(String element) throws NotExistElementException {
        Node current = head;

        for(int i = 0; i < size; i++) {
            if(current.data == element) return i;
            current = current.next;
        }

        throw new NotExistElementException();
    }

    private Node search(int index) throws IndexOutOfBoundException {
        if(index < 0 || index >= size) throw new IndexOutOfBoundException();
        if(head == null) return null;

        Node current = head;

        for(int i = 0; i < index; i++) {
            current = current.next;
        }

        return current;
    }

    private class Node {
        private String data;
        private Node next;

        public Node(String data) {
            this.data = data;
        }

        public Node(String data, Node next) {
            this.data = data;
            this.next = next;
        }
    }
}
