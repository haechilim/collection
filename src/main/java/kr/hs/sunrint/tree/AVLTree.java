package kr.hs.sunrint.tree;

public class AVLTree<T> extends BinarySearchTree<T> {
    public AVLTree(TreeNode<T> rootTreeNode) {
        super(rootTreeNode);
    }

    public void balanceTree() {
        traverseLevel(visit -> balanceTree(visit));
    }

    public int getBalanceFactor(TreeNode<T> treeNode) {
        return getHeight(treeNode.getLeft()) - getHeight(treeNode.getRight());
    }

    public int getHeight(TreeNode<T> treeNode) {
        if(treeNode == null) return 0;
        if(treeNode.getLeft() == null && treeNode.getRight() == null) return 1;

        int leftHeight = getHeight(treeNode.getLeft());
        int rightHeight = getHeight(treeNode.getRight());
        int height = Math.max(leftHeight, rightHeight);

        return ++height;
    }

    public void balanceTree(TreeNode<T> treeNode) {
        int bf = getBalanceFactor(treeNode);

        if( bf >= -1 && bf <= 1) return;

        if(bf > 1) {
            TreeNode<T> leftTreeNode = treeNode.getLeft();

            if(getBalanceFactor(leftTreeNode) < 0) leftRotate(leftTreeNode);

            rightRotate(treeNode);
        }
        else if(bf < -1) {
            TreeNode<T> rightTreeNode = treeNode.getRight();

            if(getBalanceFactor(rightTreeNode) > 0) rightRotate(rightTreeNode);

            leftRotate(treeNode);
        }
    }

    private void rightRotate(TreeNode<T> treeNode) {
        TreeNode<T> parent = treeNode.getParent();
        TreeNode<T> leftTreeNode = treeNode.getLeft();
        TreeNode<T> temp = leftTreeNode.getRight();

        leftTreeNode.setParent(parent);
        leftTreeNode.setRight(treeNode);
        treeNode.setLeft(temp);

        if(parent == null) rootTreeNode = leftTreeNode;
        else parent.setRight(leftTreeNode);
    }

    private void leftRotate(TreeNode<T> treeNode) {
        TreeNode<T> parent = treeNode.getParent();
        TreeNode<T> rightTreeNode = treeNode.getRight();
        TreeNode<T> temp = rightTreeNode.getLeft();

        rightTreeNode.setParent(parent);
        rightTreeNode.setLeft(treeNode);
        treeNode.setRight(temp);

        if(parent == null) rootTreeNode = rightTreeNode;
        else parent.setLeft(rightTreeNode);
    }
}
