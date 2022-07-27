package kr.hs.sunrint.tree;

import kr.hs.sunrint.list.ArrayList;
import kr.hs.sunrint.list.Queue;
import kr.hs.sunrint.list.Stack;

public class BinaryTree<T> {
    protected Node<T> rootNode;

    public BinaryTree(Node<T> rootNode) {
        this.rootNode = rootNode;
    }

    public boolean insertLeftNode(Node parent, Node node) {
        node.setParent(parent);
        parent.setLeft(node);
        return true;
    }

    public boolean insertRightNode(Node parent, Node node) {
        node.setParent(parent);
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

    public void traversePreorderRecursively(VisitCallback callback) {
        preorderRecursively(rootNode, callback);
    }

    public void traversePreorder(VisitCallback callback) {
        Stack<Node<T>> stack = new Stack<>();

        stack.push(rootNode);

        while (!stack.isEmpty()) {
            Node<T> node = stack.pop();
            visit(node, visit -> callback.action(visit));

            Node<T> nodeRight = node.getRight();
            Node<T> nodeLeft = node.getLeft();

            if(nodeRight != null) stack.push(nodeRight);
            if(nodeLeft != null) stack.push(nodeLeft);
        }
    }

    public void traverseInorderRecursively(VisitCallback callback) {
        inorderRecursively(rootNode, callback);
    }

    public void traverseInorder(VisitCallback callback) {
        ArrayList<Node<T>> visitedNodes = new ArrayList<>();
        Stack<Node<T>> stack = new Stack<>();

        Node<T> node = rootNode;

        while(true) {
            for(Node<T> left = node; left != null; left = left.getLeft()) {
                if(visitedNodes.contains(left)) break;
                stack.push(left);
            }

            node = stack.pop();

            visit(node, visit -> callback.action(visit));
            visitedNodes.add(node);

            Node<T> rightNode = node.getRight();
            if(rightNode != null) node = rightNode;

            if(stack.isEmpty() && rightNode == null) break;
        }
    }

    public void traversePostorderRecursively(VisitCallback callback) {
        postorderRecursively(rootNode, callback);
    }

    public void traversePostorder(VisitCallback callback) {
        ArrayList<Node<T>> visitedNodes = new ArrayList<>();
        Stack<Node<T>> stack = new Stack<>();

        stack.push(rootNode);

        while (!stack.isEmpty()) {
            Node<T> node = stack.pop();
            stack.push(node);

            Node<T> nodeLeft = node.getLeft();
            Node<T> nodeRight = node.getRight();

            if(nodeLeft != null && !visitedNodes.contains(nodeLeft)) stack.push(nodeLeft);
            else if(nodeRight != null && !visitedNodes.contains(nodeRight)) stack.push(nodeRight);
            else {
                Node<T> visitNode = stack.pop();
                visitedNodes.add(visitNode);
                visit(node, visit -> callback.action(visit));
            }
        }
    }

    public void traverseLevel(VisitCallback callback) {
        Queue<Node<T>> queue = new Queue<>();
        queue.enqueue(rootNode);

        while (!queue.isEmpty()) {
            Node<T> node = queue.dequeue();
            visit(node, visit -> callback.action(visit));

            Node<T> nodeLeft = node.getLeft();
            Node<T> nodeRight = node.getRight();

            if(nodeLeft != null) queue.enqueue(nodeLeft);
            if(nodeRight != null) queue.enqueue(nodeRight);
        }
    }

    private void preorderRecursively(Node<T> node, VisitCallback callback) {
        if(node == null) return;

        visit(node, visit -> callback.action(visit));
        preorderRecursively(node.getLeft(), callback);
        preorderRecursively(node.getRight(), callback);
    }

    private void inorderRecursively(Node<T> node, VisitCallback callback) {
        if(node == null) return;

        inorderRecursively(node.getLeft(), callback);
        visit(node, visit -> callback.action(visit));
        inorderRecursively(node.getRight(), callback);
    }

    private void postorderRecursively(Node<T> node, VisitCallback callback) {
        if(node == null) return;

        postorderRecursively(node.getLeft(), callback);
        postorderRecursively(node.getRight(), callback);
        visit(node, visit -> callback.action(visit));
    }

    private void visit(Node<T> node, VisitCallback callback) {
        callback.action(node);
    }

    public interface VisitCallback {
        void action(Node visit);
    }
}
