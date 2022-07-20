package kr.hs.sunrint.tree;

import kr.hs.sunrint.list.Queue;
import kr.hs.sunrint.list.Stack;

public class BinaryTree<T> {
    private Node<T> rootNode;
    private Queue<Node> queue;

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

    public void traversePreorder() {
        preorder(rootNode);
    }

    public void traverseInorder() {
        inorder(rootNode);
    }

    public void traversePostorder() {
        postorder(rootNode);
    }

    public void traverseLevel() {
        queue = new Queue<>();

        queue.enqueue(rootNode);
        level();
    }

    private void preorder(Node<T> node) {
        if(node == null) return;

        visit(node);
        preorder(node.getLeft());
        preorder(node.getRight());
    }

    private void inorder(Node<T> node) {
        if(node == null) return;

        inorder(node.getLeft());
        visit(node);
        inorder(node.getRight());
    }

    private void postorder(Node<T> node) {
        if(node == null) return;

        postorder(node.getLeft());
        postorder(node.getRight());
        visit(node);
    }

     private void level() {
        Node<T> pop = queue.dequeue();

        visit(pop);

        Node<T> leftNode = pop.getLeft();
        Node<T> rightNode = pop.getRight();

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
        System.out.print(node.getData() + "\t");
    }
}
