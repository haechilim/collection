package io.haechi.collection.tree;

public class TreeHeap<T> extends Heap<T> {
    private boolean inserted;
    private boolean find;
    private TreeNode<T> lastNode;

    public TreeHeap(TreeNode rootTreeNode, boolean desc) {
        super(rootTreeNode, desc);
    }

    @Override
    protected void appendLeafNode(TreeNode<T> node) {
        inserted = false;

        traverseLevel(parent -> {
            if(inserted) return;

            if(parent.getLeft() == null) parent.setLeft(node);
            else if(parent.getRight() == null) parent.setRight(node);
            else return;

            node.setParent(parent);

            inserted = true;
        });
    }

    @Override
    protected void swapUntilOk(TreeNode<T> node) {
        TreeNode<T> parent = node.getParent();

        if(parent == null) return;

        while ((desc && parent.getKey() < node.getKey()) || (!desc && parent.getKey() > node.getKey())) {
            swap(parent, node);
            if(parent.getLeft() == null && parent.getRight() == null) lastNode = parent;

            parent = node.getParent();
            if(parent == null) return;
        }
    }

    @Override
    public void replaceRootNode() {
        rootNode = lastNode;
    }

    @Override
    protected void swapUntilOkRemove() {
        /*while (true) {
            TreeNode<T> leftTreeNode = node.getLeft();
            TreeNode<T> rightTreeNode = node.getRight();

            if(leftTreeNode == null && rightTreeNode == null) break;

            if(desc) {
                if(leftTreeNode != null && node.getKey() > leftTreeNode.getKey()) leftTreeNode = null;
                if(rightTreeNode != null && node.getKey() > rightTreeNode.getKey()) rightTreeNode = null;
            }
            else {
                if(leftTreeNode != null && node.getKey() < leftTreeNode.getKey()) leftTreeNode = null;
                if(rightTreeNode != null && node.getKey() < rightTreeNode.getKey()) rightTreeNode = null;
            }

            if(leftTreeNode == null && rightTreeNode != null) swap(node, rightTreeNode);
            else if(rightTreeNode == null && leftTreeNode != null) swap(node, leftTreeNode);
            else if(leftTreeNode != null && rightTreeNode != null) swap(node, (!desc && leftTreeNode.getKey() < rightTreeNode.getKey()) || (desc && leftTreeNode.getKey() > rightTreeNode.getKey()) ? leftTreeNode : rightTreeNode);
            else break;
        }*/
    }

    @Override
    protected TreeNode<T> getParent(TreeNode<T> treeNode) {
        return treeNode.getParent();
    }

    private void swap(TreeNode<T> parent, TreeNode<T> treeNode) {
        TreeNode<T> leftTreeNode = treeNode.getLeft();
        TreeNode<T> rightTreeNode = treeNode.getRight();
        TreeNode<T> grandParent = parent.getParent();

        if(grandParent == null) rootNode = treeNode;
        else if(grandParent.getRight() == parent) grandParent.setRight(treeNode);
        else if(grandParent.getLeft() == parent) grandParent.setLeft(treeNode);

        treeNode.setParent(grandParent);

        if(parent.getLeft() == treeNode) {
            TreeNode<T> right = parent.getRight();

            treeNode.setRight(right);
            treeNode.setLeft(parent);

            if(right != null) right.setParent(treeNode);
        }
        else if(parent.getRight() == treeNode) {
            TreeNode<T> left = parent.getLeft();

            treeNode.setRight(parent);
            treeNode.setLeft(left);

            if(left != null) left.setParent(treeNode);
        }

        parent.setLeft(leftTreeNode);
        parent.setRight(rightTreeNode);
        parent.setParent(treeNode);
    }
}
