package kr.hs.sunrint.tree;

import kr.hs.sunrint.list.Queue;

public class BinaryTree<T> {
    private Node<T> rootNode;
    private Queue<Node> queue;
    private StringBuffer buffer;

    public BinaryTree(Node<T> rootNode) {
        this.rootNode = rootNode;
    }

    public boolean insertLeftNode(Node parent, Node node) {
        parent.setLeft(node);
        return true;
    }

    public boolean insertRightNode(Node parent, Node node) {
        parent.setRight(node);
        return true;
    }

    public Node<T> getLeftNode(Node parent) {
        return parent.getLeft();
    }

    public Node<T> getRightNode(Node parent) {
        return parent.getRight();
    }

    public Node<T> getRootNode() {
        return rootNode;
    }

    public void traversePreorderRecursively() {
        buffer = new StringBuffer();
        preorderRecursively(rootNode);
    }

    public void traverseInorderRecursively() {
        buffer = new StringBuffer();
        inorderRecursively(rootNode);
    }

    public void traversePostorderRecursively() {
        buffer = new StringBuffer();
        postorderRecursively(rootNode);
    }

    public void traverseLevel() {
        buffer = new StringBuffer();

        queue = new Queue<>();
        queue.enqueue(rootNode);

        level();
    }

    public String getTraversalNodes() {
        return buffer.toString();
    }

    private void preorderRecursively(Node<T> node) {
        if(node == null) return;

        visit(node);
        preorderRecursively(node.getLeft());
        preorderRecursively(node.getRight());
    }

    private void inorderRecursively(Node<T> node) {
        if(node == null) return;

        inorderRecursively(node.getLeft());
        visit(node);
        inorderRecursively(node.getRight());
    }

    private void postorderRecursively(Node<T> node) {
        if(node == null) return;

        postorderRecursively(node.getLeft());
        postorderRecursively(node.getRight());
        visit(node);
    }

     private void level() {
        Node<T> node = queue.dequeue();

        visit(node);

        Node<T> leftNode = node.getLeft();
        Node<T> rightNode = node.getRight();

        if(leftNode != null) {
            queue.enqueue(leftNode);

            if(rightNode != null) {
                queue.enqueue(rightNode);
                level();
            }

            level();
        }
    }

    private void visit(Node<T> node) {
        buffer.append(node.getData() + "\t");
        //System.out.print(node.getData() + "\t");
    }
}
