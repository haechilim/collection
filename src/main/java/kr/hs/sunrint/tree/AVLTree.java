package kr.hs.sunrint.tree;

import kr.hs.sunrint.exception.DuplicatedTreeKeyException;

public class AVLTree<T> extends BinarySearchTree<T> {
    public AVLTree(TreeNode<T> rootTreeNode) {
        super(rootTreeNode);
    }

    @Override
    public boolean insertNode(TreeNode node) throws DuplicatedTreeKeyException {
        if(super.insertNode(node)) {
            calculateBalanceFactor(node);
            balanceTree(node);
            return true;
        }

        return false;
    }

    @Override
    public boolean removeNode(int key) {
        TreeNode node = removeNodeByKey(key);
        calculateBalanceFactor(node);
        balanceTree(node);

        return true;
    }

    private void calculateBalanceFactor(TreeNode node) {
        while (node != null) {
            TreeNode<T> left = node.getLeft();
            TreeNode<T> right = node.getRight();

            int leftHeight = left != null ? left.getHeight() : 0;
            int rightHeight = right != null ? right.getHeight() : 0;

            node.setHeight(Math.max(leftHeight + 1, rightHeight + 1));
            node.setBalanceFactor(leftHeight - rightHeight);

            node = node.getParent();
        }
    }

    public void balanceTree(TreeNode node) {
        if(node == null) return;

        int bf = node.getBalanceFactor();

        if(bf > 1) {
            TreeNode<T> leftNode = node.getLeft();
            if(leftNode.getBalanceFactor() < 0) leftRotate(leftNode);
            rightRotate(node);
            return;
        }
        else if(bf < -1) {
            TreeNode<T> rightNode = node.getRight();
            if(rightNode.getBalanceFactor() > 0) rightRotate(rightNode);
            leftRotate(node);
            return;
        }

        balanceTree(node.getParent());
    }

    private void rightRotate(TreeNode<T> node) {
        TreeNode<T> parent = node.getParent();
        TreeNode<T> leftNode = node.getLeft();
        TreeNode<T> temp = leftNode.getRight();

        if(parent == null) setRootNode(leftNode);
        else {
            if(isLeftChild(node)) parent.setLeft(leftNode);
            else parent.setRight(leftNode);
        }

        leftNode.setParent(parent);
        setRightNode(leftNode, node);
        setLeftNode(node, temp);

        calculateBalanceFactor(node);
    }

    private void leftRotate(TreeNode<T> node) {
        TreeNode<T> parent = node.getParent();
        TreeNode<T> rightNode = node.getRight();
        TreeNode<T> temp = rightNode.getLeft();

        if(parent == null) setRootNode(rightNode);
        else {
            if(isLeftChild(node)) parent.setLeft(rightNode);
            else parent.setRight(rightNode);
        }

        rightNode.setParent(parent);
        setLeftNode(rightNode, node);
        setRightNode(node, temp);

        calculateBalanceFactor(node);
    }

    private TreeNode findNode(TreeNode node) {
        while(node != rootNode) {
            node = node.getParent();

            if(Math.abs(node.getBalanceFactor()) > 1) return node;
        }

        return null;
    }
}
