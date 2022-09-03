package io.haechi.collection.tree;

import io.haechi.collection.exception.NotSupportedException;

public abstract class Heap<T> extends BinaryTree {
    protected boolean desc;

    public Heap(TreeNode rootTreeNode, boolean desc) {
        super(rootTreeNode);
        this.desc = desc;
    }

    public boolean insertNode(TreeNode<T> node) {
        appendLeafNode(node);
        swapUntilOk(node);
        return true;
    }

    public TreeNode<T> removeRootNode() {
        TreeNode<T> root = rootNode;

        replaceRootNode();
        swapUntilOkRemove();

        return root;
    }

    protected abstract void appendLeafNode(TreeNode<T> node);
    protected abstract void swapUntilOk(TreeNode<T> node);
    protected abstract void replaceRootNode();
    protected abstract void swapUntilOkRemove();
    protected abstract TreeNode<T> getParent(TreeNode<T> node);

    @Override
    public boolean insertLeftNode(TreeNode parent, TreeNode node) throws NotSupportedException {
        throw new NotSupportedException();
    }

    @Override
    public boolean insertRightNode(TreeNode parent, TreeNode node) throws NotSupportedException {
        throw new NotSupportedException();
    }
}
