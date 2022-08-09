package kr.hs.sunrint.tree;

import kr.hs.sunrint.list.ArrayList;
import kr.hs.sunrint.list.Queue;
import kr.hs.sunrint.list.Stack;

public class BinaryTree<T> {
    protected TreeNode<T> rootTreeNode;

    public BinaryTree(TreeNode<T> rootTreeNode) {
        this.rootTreeNode = rootTreeNode;
    }

    public boolean insertLeftNode(TreeNode parent, TreeNode treeNode) {
        treeNode.setParent(parent);
        parent.setLeft(treeNode);
        return true;
    }

    public boolean insertRightNode(TreeNode parent, TreeNode treeNode) {
        treeNode.setParent(parent);
        parent.setRight(treeNode);
        return true;
    }

    public TreeNode<T> getLeftNode(TreeNode parent) {
        return parent.getLeft();
    }

    public TreeNode<T> getRightNode(TreeNode parent) {
        return parent.getRight();
    }

    public TreeNode<T> getRootTreeNode() {
        return rootTreeNode;
    }

    public void traversePreorderRecursively(VisitCallback callback) {
        preorderRecursively(rootTreeNode, callback);
    }

    public void traversePreorder(VisitCallback callback) {
        Stack<TreeNode<T>> stack = new Stack<>();

        stack.push(rootTreeNode);

        while (!stack.isEmpty()) {
            TreeNode<T> treeNode = stack.pop();
            visit(treeNode, visit -> callback.action(visit));

            TreeNode<T> treeNodeRight = treeNode.getRight();
            TreeNode<T> treeNodeLeft = treeNode.getLeft();

            if(treeNodeRight != null) stack.push(treeNodeRight);
            if(treeNodeLeft != null) stack.push(treeNodeLeft);
        }
    }

    public void traverseInorderRecursively(VisitCallback callback) {
        inorderRecursively(rootTreeNode, callback);
    }

    public void traverseInorder(VisitCallback callback) {
        ArrayList<TreeNode<T>> visitedNodes = new ArrayList<>();
        Stack<TreeNode<T>> stack = new Stack<>();

        TreeNode<T> treeNode = rootTreeNode;

        while(true) {
            for(TreeNode<T> left = treeNode; left != null; left = left.getLeft()) {
                if(visitedNodes.contains(left)) break;
                stack.push(left);
            }

            treeNode = stack.pop();

            visit(treeNode, visit -> callback.action(visit));
            visitedNodes.add(treeNode);

            TreeNode<T> rightTreeNode = treeNode.getRight();
            if(rightTreeNode != null) treeNode = rightTreeNode;

            if(stack.isEmpty() && rightTreeNode == null) break;
        }
    }

    public void traversePostorderRecursively(VisitCallback callback) {
        postorderRecursively(rootTreeNode, callback);
    }

    public void traversePostorder(VisitCallback callback) {
        ArrayList<TreeNode<T>> visitedNodes = new ArrayList<>();
        Stack<TreeNode<T>> stack = new Stack<>();

        stack.push(rootTreeNode);

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
                visit(treeNode, visit -> callback.action(visit));
            }
        }
    }

    public void traverseLevel(VisitCallback callback) {
        Queue<TreeNode<T>> queue = new Queue<>();
        queue.enqueue(rootTreeNode);

        while (!queue.isEmpty()) {
            TreeNode<T> treeNode = queue.dequeue();
            visit(treeNode, visit -> callback.action(visit));

            TreeNode<T> treeNodeLeft = treeNode.getLeft();
            TreeNode<T> treeNodeRight = treeNode.getRight();

            if(treeNodeLeft != null) queue.enqueue(treeNodeLeft);
            if(treeNodeRight != null) queue.enqueue(treeNodeRight);
        }
    }

    private void preorderRecursively(TreeNode<T> treeNode, VisitCallback callback) {
        if(treeNode == null) return;

        visit(treeNode, visit -> callback.action(visit));
        preorderRecursively(treeNode.getLeft(), callback);
        preorderRecursively(treeNode.getRight(), callback);
    }

    private void inorderRecursively(TreeNode<T> treeNode, VisitCallback callback) {
        if(treeNode == null) return;

        inorderRecursively(treeNode.getLeft(), callback);
        visit(treeNode, visit -> callback.action(visit));
        inorderRecursively(treeNode.getRight(), callback);
    }

    private void postorderRecursively(TreeNode<T> treeNode, VisitCallback callback) {
        if(treeNode == null) return;

        postorderRecursively(treeNode.getLeft(), callback);
        postorderRecursively(treeNode.getRight(), callback);
        visit(treeNode, visit -> callback.action(visit));
    }

    private void visit(TreeNode<T> treeNode, VisitCallback callback) {
        callback.action(treeNode);
    }

    public interface VisitCallback {
        void action(TreeNode visit);
    }
}
