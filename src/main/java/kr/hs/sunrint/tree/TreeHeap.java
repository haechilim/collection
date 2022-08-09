package kr.hs.sunrint.tree;

public class TreeHeap<T> extends Heap<T> {
    private boolean inserted;
    private boolean find;
    private TreeNode<T> lastTreeNode;

    public TreeHeap(TreeNode rootTreeNode, boolean desc) {
        super(rootTreeNode, desc);
    }

    @Override
    protected void appendLeafNode(TreeNode<T> treeNode) {
        inserted = false;

        traverseLevel(parent -> {
            if(inserted) return;

            if(parent.getLeft() == null) parent.setLeft(treeNode);
            else if(parent.getRight() == null) parent.setRight(treeNode);
            else return;

            treeNode.setParent(parent);

            inserted = true;
        });
    }

    @Override
    protected void swapUntilOk(TreeNode<T> treeNode) {
        TreeNode<T> parent = treeNode.getParent();

        if(parent == null) return;

        while ((desc && parent.getKey() < treeNode.getKey()) || (!desc && parent.getKey() > treeNode.getKey())) {
            swap(parent, treeNode);
            parent = treeNode.getParent();

            if(parent == null) return;
        }
    }

    @Override
    public void replaceRootNode() {
        find = false;

        traverseLevel(visit -> {
            lastTreeNode = visit;
            TreeNode<T> treeNode = null;

            if(find) return;

            if(visit.getLeft() != null && visit.getRight() != null) return;
            else if(visit.getLeft() == null) {
                treeNode = lastTreeNode.getRight();
                lastTreeNode.setRight(null);
            }
            else if(visit.getRight() == null) {
                treeNode = visit.getLeft();
                visit.setLeft(null);
            }

            find = true;

            treeNode.setParent(null);
            treeNode.setLeft(rootTreeNode.getLeft());
            treeNode.setRight(rootTreeNode.getRight());
            rootTreeNode = treeNode;
        });
    }

    @Override
    protected void swapUntilOkRemove(TreeNode<T> treeNode) {
        while (true) {
            TreeNode<T> leftTreeNode = treeNode.getLeft();
            TreeNode<T> rightTreeNode = treeNode.getRight();

            if(leftTreeNode == null && rightTreeNode == null) break;

            if(desc) {
                if(leftTreeNode != null && treeNode.getKey() > leftTreeNode.getKey()) leftTreeNode = null;
                if(rightTreeNode != null && treeNode.getKey() > rightTreeNode.getKey()) rightTreeNode = null;
            }
            else {
                if(leftTreeNode != null && treeNode.getKey() < leftTreeNode.getKey()) leftTreeNode = null;
                if(rightTreeNode != null && treeNode.getKey() < rightTreeNode.getKey()) rightTreeNode = null;
            }

            if(leftTreeNode == null && rightTreeNode != null) swap(treeNode, rightTreeNode);
            else if(rightTreeNode == null && leftTreeNode != null) swap(treeNode, leftTreeNode);
            else if(leftTreeNode != null && rightTreeNode != null) swap(treeNode, (!desc && leftTreeNode.getKey() < rightTreeNode.getKey()) || (desc && leftTreeNode.getKey() > rightTreeNode.getKey()) ? leftTreeNode : rightTreeNode);
            else break;
        }
    }

    @Override
    protected TreeNode<T> getParent(TreeNode<T> treeNode) {
        return treeNode.getParent();
    }

    private void swap(TreeNode<T> parent, TreeNode<T> treeNode) {
        TreeNode<T> leftTreeNode = treeNode.getLeft();
        TreeNode<T> rightTreeNode = treeNode.getRight();
        TreeNode<T> grandParent = parent.getParent();

        if(grandParent == null) rootTreeNode = treeNode;
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
