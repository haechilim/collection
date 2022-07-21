package kr.hs.sunrint.tree;

import kr.hs.sunrint.list.ArrayList;
import kr.hs.sunrint.list.Queue;
import kr.hs.sunrint.list.Stack;

public class BinaryTree<T> {
    private Node<T> rootNode;
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

    public void traversePreorder() {
        buffer = new StringBuffer();
        Stack<Node<T>> stack = new Stack<>();

        stack.push(rootNode);

        while (!stack.isEmpty()) {
            Node<T> node = stack.pop();
            visit(node);

            Node<T> nodeRight = node.getRight();
            Node<T> nodeLeft = node.getLeft();

            if(nodeRight != null) stack.push(nodeRight);
            if(nodeLeft != null) stack.push(nodeLeft);
        }
    }

    public void traverseInorderRecursively() {
        buffer = new StringBuffer();
        inorderRecursively(rootNode);
    }

    public void traverseInorder() {
        buffer = new StringBuffer();
        ArrayList<Node<T>> visitedList = new ArrayList<>();
        Stack<Node<T>> stack = new Stack<>();

        stack.push(rootNode);

        while (!stack.isEmpty()) {
            Node<T> node = stack.pop();
            stack.push(node);

            Node<T> nodeLeft = node.getLeft();

            if(nodeLeft != null && !visitedList.contains(nodeLeft)) stack.push(nodeLeft);
            else {
                Node<T> visitNode = stack.pop();
                Node<T> nodeRight = visitNode.getRight();

                visit(visitNode);
                visitedList.add(visitNode);
                if(nodeRight != null) stack.push(nodeRight);
            }
        }
    }

    public void traversePostorderRecursively() {
        buffer = new StringBuffer();
        postorderRecursively(rootNode);
    }

    public void traversePostorder() {
        buffer = new StringBuffer();
        ArrayList<Node<T>> visitedList = new ArrayList<>();
        Stack<Node<T>> stack = new Stack<>();

        stack.push(rootNode);

        while (!stack.isEmpty()) {
            Node<T> node = stack.pop();
            stack.push(node);

            Node<T> nodeLeft = node.getLeft();
            Node<T> nodeRight = node.getRight();

            if(nodeLeft != null && !visitedList.contains(nodeLeft)) stack.push(nodeLeft);
            else if(nodeRight != null && !visitedList.contains(nodeRight)) stack.push(nodeRight);
            else {
                Node<T> visitNode = stack.pop();
                visitedList.add(visitNode);
                visit(visitNode);
            }
        }
    }

    public void traverseLevel() {
        buffer = new StringBuffer();
        Queue<Node> queue = new Queue<>();
        queue.enqueue(rootNode);

        while (!queue.isEmpty()) {
            Node<T> node = queue.dequeue();
            visit(node);

            Node<T> nodeLeft = node.getLeft();
            Node<T> nodeRight = node.getRight();

            if(nodeLeft != null) queue.enqueue(nodeLeft);
            if(nodeRight != null) queue.enqueue(nodeRight);
        }
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

    private void visit(Node<T> node) {
        buffer.append(node.getData());
    }
}
