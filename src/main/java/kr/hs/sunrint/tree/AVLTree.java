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
        TreeNode<T> node = getRootTreeNode();
        int bf = node.getBalanceFactor();

        if(bf > 1) {
            TreeNode<T> leftNode = node.getLeft();
            if(leftNode.getBalanceFactor() < 0) leftRotate(leftNode);
            rightRotate(node);
        }
        else if(bf < -1) {
            TreeNode<T> rightNode = node.getRight();
            if(rightNode.getBalanceFactor() > 0) rightRotate(rightNode);
            leftRotate(node);
        }
    }

    private void rightRotate(TreeNode<T> node) {
        TreeNode<T> parent = node.getParent();
        TreeNode<T> leftNode = node.getLeft();
        TreeNode<T> temp = leftNode.getRight();

        if(parent == null) setRootNode(leftNode);
        else {
            if(isLeftChild(node)) setLeftNode(parent, leftNode);
            else setRightNode(parent, leftNode);
        }

        setRightNode(leftNode, node);
        if(temp != null) setLeftNode(node, temp);

        calculateBalanceFactor(node);
    }

    private void leftRotate(TreeNode<T> node) {
        TreeNode<T> parent = node.getParent();
        TreeNode<T> rightNode = node.getRight();
        TreeNode<T> temp = rightNode.getLeft();

        if(parent == null) setRootNode(rightNode);
        else {
            if(isLeftChild(node)) setLeftNode(parent, rightNode);
            else setRightNode(parent, rightNode);
        }

        setLeftNode(rightNode, node);
        if(temp != null) setRightNode(node, temp);

        calculateBalanceFactor(node);
    }
}
