package kr.hs.sunrint.tree;

import kr.hs.sunrint.exception.DuplicatedTreeKeyException;

public class AVLTree<T> extends BinarySearchTree<T> {
    public AVLTree(TreeNode<T> rootTreeNode) {
        super(rootTreeNode);
    }

    @Override
    public boolean insertNode(TreeNode treeNode) throws DuplicatedTreeKeyException {
        if(super.insertNode(treeNode)) {
            calculateBalanceFactor(treeNode);
            //balanceTree();
            return true;
        }

        return false;
    }

    @Override
    public boolean removeNode(int key) {
        calculateBalanceFactor(removeNodeByKey(key));
        balanceTree();

        return true;
    }

    private void calculateBalanceFactor(TreeNode treeNode) {
        while (treeNode != null) {
            TreeNode<T> left = treeNode.getLeft();
            TreeNode<T> right = treeNode.getRight();

            int leftHeight = left != null ? left.getHeight() : 0;
            int rightHeight = right != null ? right.getHeight() : 0;

            treeNode.setHeight(Math.max(leftHeight + 1, rightHeight + 1));
            treeNode.setBalanceFactor(leftHeight - rightHeight);

            treeNode = treeNode.getParent();
        }
    }

    public void balanceTree() {
        TreeNode<T> treeNode = getRootTreeNode();
        int bf = treeNode.getBalanceFactor();

        if(bf > 1) {
            TreeNode<T> leftTreeNode = treeNode.getLeft();
            if(leftTreeNode.getBalanceFactor() < 0) leftRotate(leftTreeNode);
            rightRotate(treeNode);
        }
        else if(bf < -1) {
            TreeNode<T> rightTreeNode = treeNode.getRight();
            if(rightTreeNode.getBalanceFactor() > 0) rightRotate(rightTreeNode);
            leftRotate(treeNode);
        }
    }

    private void rightRotate(TreeNode<T> treeNode) {
        TreeNode<T> parent = treeNode.getParent();
        TreeNode<T> leftTreeNode = treeNode.getLeft();
        TreeNode<T> temp = leftTreeNode.getRight();

        leftTreeNode.setParent(parent);
        leftTreeNode.setRight(treeNode);
        treeNode.setParent(leftTreeNode);
        treeNode.setLeft(temp);

        if(parent == null) rootTreeNode = leftTreeNode;
        else parent.setRight(leftTreeNode);

        calculateBalanceFactor(treeNode);
    }

    private void leftRotate(TreeNode<T> treeNode) {
        TreeNode<T> parent = treeNode.getParent();
        TreeNode<T> rightTreeNode = treeNode.getRight();
        TreeNode<T> temp = rightTreeNode.getLeft();

        rightTreeNode.setParent(parent);
        rightTreeNode.setLeft(treeNode);
        treeNode.setParent(rightTreeNode);
        treeNode.setRight(temp);

        if(parent == null) rootTreeNode = rightTreeNode;
        else parent.setLeft(rightTreeNode);

        calculateBalanceFactor(treeNode);
    }
}
