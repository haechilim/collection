package io.haechi.collection.tree;

import io.haechi.collection.list.ArrayList;
import io.haechi.collection.list.Queue;
import io.haechi.collection.list.Stack;

public class BinaryTree<T> {
    protected TreeNode<T> rootNode;

    public BinaryTree(TreeNode<T> rootNode) {
        this.rootNode = rootNode;
    }

    public boolean insertLeftNode(TreeNode parent, TreeNode treeNode) {
        setLeftNode(parent, treeNode);
        return true;
    }

    public boolean insertRightNode(TreeNode parent, TreeNode treeNode) {
        setRightNode(parent, treeNode);
        return true;
    }

    protected void setRootNode(TreeNode<T> node) {
        rootNode = node;
        node.setParent(null);
    }

    protected void setLeftNode(TreeNode<T> parent , TreeNode<T> node) {
        parent.setLeft(node);
        if(node != null) node.setParent(parent);
    }

    protected void setRightNode(TreeNode<T> parent , TreeNode<T> node) {
        parent.setRight(node);
        if(node != null) node.setParent(parent);
    }

    protected boolean isLeftChild(TreeNode<T> treeNode) {
        TreeNode<T> parent = treeNode.getParent();
        if(parent == null) throw new IllegalStateException();
        return parent.getLeft() == treeNode;
    }

    public TreeNode<T> getLeftNode(TreeNode parent) {
        return parent.getLeft();
    }

    public TreeNode<T> getRightNode(TreeNode parent) {
        return parent.getRight();
    }

    public TreeNode<T> getRootNode() {
        return rootNode;
    }

    public void traversePreorder(TraverseCallback callback) {
        Stack<TreeNode<T>> stack = new Stack<>();

        stack.push(rootNode);

        while (!stack.isEmpty()) {
            TreeNode<T> treeNode = stack.pop();
            callback.handle(treeNode);

            TreeNode<T> treeNodeRight = treeNode.getRight();
            TreeNode<T> treeNodeLeft = treeNode.getLeft();

            if(treeNodeRight != null) stack.push(treeNodeRight);
            if(treeNodeLeft != null) stack.push(treeNodeLeft);
        }
    }

    public void traverseInorder(TraverseCallback callback) {
        ArrayList<TreeNode<T>> visitedNodes = new ArrayList<>();
        Stack<TreeNode<T>> stack = new Stack<>();

        TreeNode<T> treeNode = rootNode;

        while(true) {
            for(TreeNode<T> left = treeNode; left != null; left = left.getLeft()) {
                if(visitedNodes.contains(left)) break;
                stack.push(left);
            }

            treeNode = stack.pop();

            callback.handle(treeNode);
            visitedNodes.add(treeNode);

            TreeNode<T> rightTreeNode = treeNode.getRight();
            if(rightTreeNode != null) treeNode = rightTreeNode;

            if(stack.isEmpty() && rightTreeNode == null) break;
        }
    }

    public void traversePostorder(TraverseCallback callback) {
        ArrayList<TreeNode<T>> visitedNodes = new ArrayList<>();
        Stack<TreeNode<T>> stack = new Stack<>();

        stack.push(rootNode);

        while (!stack.isEmpty()) {
            TreeNode<T> treeNode = stack.pop();
            stack.push(treeNode);

            TreeNode<T> treeNodeLeft = treeNode.getLeft();
            TreeNode<T> treeNodeRight = treeNode.getRight();

            if(treeNodeLeft != null && !visitedNodes.contains(treeNodeLeft)) stack.push(treeNodeLeft);
            else if(treeNodeRight != null && !visitedNodes.contains(treeNodeRight)) stack.push(treeNodeRight);
            else {
                TreeNode<T> visitTreeNode = stack.pop();
                visitedNodes.add(visitTreeNode);
                callback.handle(treeNode);
            }
        }
    }

    public void traverseLevel(TraverseCallback callback) {
        Queue<TreeNode<T>> queue = new Queue<>();
        queue.enqueue(rootNode);

        while (!queue.isEmpty()) {
            TreeNode<T> treeNode = queue.dequeue();
            callback.handle(treeNode);

            TreeNode<T> treeNodeLeft = treeNode.getLeft();
            TreeNode<T> treeNodeRight = treeNode.getRight();

            if(treeNodeLeft != null) queue.enqueue(treeNodeLeft);
            if(treeNodeRight != null) queue.enqueue(treeNodeRight);
        }
    }

    public void traversePreorderRecursively(TraverseCallback callback) {
        traversPreorderRecursively(rootNode, callback);
    }

    public void traverseInorderRecursively(TraverseCallback callback) {
        traverseInorderRecursively(rootNode, callback);
    }

    public void traversePostorderRecursively(TraverseCallback callback) {
        traversePostorderRecursively(rootNode, callback);
    }

    private void traversPreorderRecursively(TreeNode<T> treeNode, TraverseCallback callback) {
        if(treeNode == null) return;

        callback.handle(treeNode);
        traversPreorderRecursively(treeNode.getLeft(), callback);
        traversPreorderRecursively(treeNode.getRight(), callback);
    }

    private void traverseInorderRecursively(TreeNode<T> treeNode, TraverseCallback callback) {
        if(treeNode == null) return;

        traverseInorderRecursively(treeNode.getLeft(), callback);
        callback.handle(treeNode);
        traverseInorderRecursively(treeNode.getRight(), callback);
    }

    private void traversePostorderRecursively(TreeNode<T> treeNode, TraverseCallback callback) {
        if(treeNode == null) return;

        traversePostorderRecursively(treeNode.getLeft(), callback);
        traversePostorderRecursively(treeNode.getRight(), callback);
        callback.handle(treeNode);
    }

    public interface TraverseCallback {
        void handle(TreeNode node);
    }
}
