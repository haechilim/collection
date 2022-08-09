package kr.hs.sunrint.tree;

public class TreeNode<T> {
    private T data;
    private int key;
    private TreeNode<T> left = null;
    private TreeNode<T> right = null;
    private TreeNode<T> parent = null;

    public TreeNode(T data) {
        this.data = data;
    }

    public TreeNode(T data, int key) {
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

    public TreeNode<T> getLeft() {
        return left;
    }

    public void setLeft(TreeNode<T> left) {
        this.left = left;
    }

    public TreeNode<T> getRight() {
        return right;
    }

    public void setRight(TreeNode<T> right) {
        this.right = right;
    }

    public TreeNode<T> getParent() {
        return parent;
    }

    public void setParent(TreeNode<T> parent) {
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
