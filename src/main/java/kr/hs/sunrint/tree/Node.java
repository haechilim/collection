package kr.hs.sunrint.tree;

public class Node<T> {
    private T data;
    private int key;
    private Node<T> left = null;
    private Node<T> right = null;
    private Node<T> parent = null;

    public Node(T data) {
        this.data = data;
    }

    public Node(T data, int key) {
        this.data = data;
        this.key = key;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public Node<T> getLeft() {
        return left;
    }

    public void setLeft(Node<T> left) {
        this.left = left;
    }

    public Node<T> getRight() {
        return right;
    }

    public void setRight(Node<T> right) {
        this.right = right;
    }

    public Node<T> getParent() {
        return parent;
    }

    public void setParent(Node<T> parent) {
        this.parent = parent;
    }

    @Override
    public String toString() {
        return "Node{" +
                "data=" + data +
                ", key=" + key +
                ", left=" + left +
                ", right=" + right +
                ", parent=" + parent +
                '}';
    }
}
